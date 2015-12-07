(function (angular) {
    'use strict';


    angular.module('sisvarejo').controller('FornecedorController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

        $importService("estoqueService");
        $importService("caracteristicaService");
        $importService("financeiroService");
        $importService("localizacaoService");

        /**
         * Injeta os métodos, atributos e seus estados herdados de AbstractCRUDController.
         * @see AbstractCRUDController
         */
        $injector.invoke(AbstractCRUDController, this, {$scope: $scope});

        /*-------------------------------------------------------------------
         * 		 				 	EVENT HANDLER
         *-------------------------------------------------------------------*/

        /*-------------------------------------------------------------------
         * 		 				 	  STATES
         *-------------------------------------------------------------------*/

        //STATES
        /**
         * Variável estática que representa
         * o estado de listagem de registros.
         */
        $scope.LIST_STATE = "fornecedor.listar";
        /**
         * Variável estática que representa
         * o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "fornecedor.detalhe";
        /**
         * Variável estática que representa
         * o estado para a criação de registros.
         */
        $scope.INSERT_STATE = "fornecedor.cadastrar";
        /**
         * Variável estática que representa
         * o estado para a edição de registros.
         */
        $scope.UPDATE_STATE = "fornecedor.alterar";

        /*-------------------------------------------------------------------
         * 		 				 	ATTRIBUTES
         *-------------------------------------------------------------------*/

        /**
         *
         */
        $scope.data = {
            content: [],
            itensExcluir: []
        };

        /**
         *
         * @type {{content: Array}}
         */
        $scope.model = {
            content: [],
            cidades: [],
            estados: [],
            estado: {},
            paises: [],
            pais: {},
            tamanhos: [],
            entidade: {},
            filtros: {
                razaoSocial: null,
                nomeFantasia: null,
                telefone: null,
                cnpj: null
            },
            query: {
                order: 'razaoSocial'
            }
        };

        /**
         *
         */
        $scope.ordenacao = {
            predicado: "-sigla",
            ordem: false
        }

        /**
         *
         */
        $scope.currentPage;


        /*-------------------------------------------------------------------
         * 		 				 	  BEHAVIORS
         *-------------------------------------------------------------------*/

        /**
         *
         */
        $scope.initialize = function () {

            var pageRequest = new PageRequest();
            pageRequest.size = 10;

            $scope.currentState = $state.current.name;

            $scope.pageRequest = pageRequest;

            switch ($scope.currentState) {
                case $scope.LIST_STATE:
                {
                    $scope.changeToList();
                }
                    break;
                case $scope.DETAIL_STATE:
                {
                    $scope.changeToDetail($state.params.id);
                }
                    break;
                case $scope.INSERT_STATE:
                {
                    $scope.changeToInsert();
                }
                    break;
                case $scope.UPDATE_STATE:
                {
                    $scope.changeToUpdate($state.params.id);
                }
                    break;
                default:
                {
                    $state.go($scope.LIST_STATE);
                }
            }
        }

        /**
         * Realiza os procedimentos iniciais (prepara o estado)
         * para a tela de consulta e após isso, muda o estado para list.
         * @see LIST_STATE
         * @see $stateChangeSuccess
         *
         * Para mudar para este estado, deve-se primeiro carregar os dados da consulta.
         */
        $scope.changeToList = function () {
            $log.info("changeToList");

            var pageRequest = new PageRequest();
            pageRequest.size = 10;
            $scope.pageRequest = pageRequest;

            $scope.carregarLista();
        };

        /**
         * Realiza os procedimentos iniciais (prepara o estado)
         * para a tela de inserção e após isso, muda o estado para insert.
         * @see INSERT_STATE
         * @see $stateChangeSuccess
         *
         * Para mudar para este estado, deve-se primeiro instanciar um novo currentEntity,
         * para limpar os campos e configurar valores defaults.
         */
        $scope.changeToInsert = function () {
            $log.info("changeToInsert");

            $scope.carregarListaPaises();

            $scope.model.entidade = new Fornecedor();
            $scope.currentState = $scope.INSERT_STATE;

        };

        /**
         * Realiza os procedimentos iniciais (prepara o estado)
         * para a tela de edição e após isso, muda o estado para update.
         * @see UPDATE_STATE
         * @see $stateChangeSuccess
         *
         * Para mudar para este estado, deve-se primeiro obter via id
         * o registro pelo serviço de consulta e só então mudar o estado da tela.
         */
        $scope.changeToUpdate = function (id) {

            $log.info("changeToUpdate", id);

            if (id == null || id == "" || id == 0) {
                $scope.currentState = $scope.LIST_STATE;
                $state.go($scope.LIST_STATE);
                return;
            }

            estoqueService.findFornecedorById(id, {
                callback: function (result) {
                    $scope.model.entidade = result;
                    $scope.carregarListaPaises();

                    if (result.cidade != null) {
                        $scope.model.estado = result.cidade.estado;
                        $scope.model.pais = result.cidade.estado.pais;
                        $scope.paisChanged();
                        $scope.estadoChanged();
                    }

                    $scope.currentState = $scope.UPDATE_STATE;
                    $state.go($scope.UPDATE_STATE);
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    var toast = $mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
                }
            });
        };

        /**
         *
         */
        $scope.carregarLista = function () {
            estoqueService.listFornecedoresByFilters($scope.model.filtros.razaoSocial, $scope.model.filtros.nomeFantasia,
                $scope.model.filtros.telefone, $scope.model.filtros.cnpj, {
                    callback: function (result) {
                        $scope.model.content = result;
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple(message)
                    }
                });
        }

        /**
         *
         */
        $scope.carregarListaPaises = function () {
            localizacaoService.listPaises({
                callback: function (result) {
                    $scope.model.paises = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    var toast = $mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
                }
            })
        }

        /**
         *
         * @param pais
         */
        $scope.paisChanged = function () {
            localizacaoService.listEstadosByPais($scope.model.pais, {
                callback: function (result) {
                    $scope.model.estados = result;
                    $scope.$apply();
                },
                errorHandler: function (message, error) {
                    var toast = $mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
                }
            })
        }

        /**
         *
         * @param estado
         */
        $scope.estadoChanged = function () {
            localizacaoService.listCidadesByEstado($scope.model.estado, {
                callback: function (result) {
                    $scope.model.cidades = result;
                    $scope.$apply();
                },
                errorHandler: function (message, error) {
                    var toast = $mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
                }
            })
        }

        /**
         *
         */
        $scope.carregarListaCidadesByEstado = function (estado) {
            localizacaoService.listTamanhos({
                callback: function (result) {
                    $scope.model.tamanhos = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    var toast = $mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
                }
            })
        };

        /**
         *
         */
        $scope.carregarListaFornecedores = function () {
            caracteristicaService.listCidades({
                callback: function (result) {
                    $scope.model.cidades = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    var toast = $mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
                }
            })
        };

        /**
         *
         * @param ev
         */
        $scope.abrirPopupNovaEntidade = function (ev) {
            $mdDialog.show({
                controller: ProdutoDialogController,
                templateUrl: './modules/sisvarejo/ui/estoque/fornecedor/popup/popup-fornecedor.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    entidadeExterna: null
                }
            })
                .then(function (result) {

                    $scope.currentPage.push(result);

                    var toast = $mdToast.simple()
                        .content('Registro salvo com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param fornecedor
         * @param status
         */
        $scope.mudarStatusFornecedor = function (fornecedor, status) {
            fornecedor.ativo = status;
            $scope.atualizarFornecedor(fornecedor);
        }

        /**
         *
         * @param ev
         * @param entidade
         */
        $scope.abrirPopupAlterarEntidade = function (ev, entidade) {
            $mdDialog.show({
                controller: FornecedorDialogController,
                templateUrl: './modules/sisvarejo/ui/estoque/fornecedor/popup/popup-fornecedor.html',
                targetEvent: ev,
                hasBackdrop: true,
                bindToController: true,
                locals: {
                    entidadeExterna: angular.copy(entidade)
                }
            })
                .then(function (result) {
                    var i = $scope.findByIdInArray($scope.currentPage, result);
                    $scope.currentPage[i] = result;
                    var toast = $mdToast.simple()
                        .content('Registro salvo com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param entidade
         */
        $scope.salvarFornecedor = function (entidade) {
            estoqueService.insertFornecedor(entidade, {
                callback: function (result) {
                    $scope.model.content.push(result);
                    var toast = $mdToast.simple()
                        .content('Registro salvo com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
                    $state.go($scope.LIST_STATE);
                    $scope.$apply();
                },
                errorHandler: function (message, error) {
                    $mdToast.show($mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right'))
                        .then(function () {
                        });
                    $log.error(message);
                }
            });
        };

        /**
         *
         * @param entidade
         */
        $scope.atualizarFornecedor = function (entidade) {
            estoqueService.updateFornecedor(entidade, {
                callback: function (result) {
                    var i = $scope.findByIdInArray($scope.model.content, result);
                    $scope.model.content[i] = result;
                    var toast = $mdToast.simple()
                        .content('Registro salvo com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
                    $state.go($scope.LIST_STATE);
                    $scope.$apply();
                },
                errorHandler: function (message, error) {
                    $mdToast.show($mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right'))
                        .then(function () {
                        });
                    $log.error(message);
                }
            });
        };

        /**
         *
         * @param ev
         * @param id
         */
        $scope.excluirFornecedor = function (ev, fornecedor) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Fornecedor')
                .content('Tem certeza que deseja excluir o fornecedor "'+fornecedor.razaoSocial+'"? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Fornecedor')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            $mdDialog.show(confirm).then(function () {
                estoqueService.removeFornecedor(fornecedor, {
                    callback: function () {
                        var toast = $mdToast.simple()
                            .content('Registro excluído com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });

                        var i = $scope.findByIdInArray($scope.model.content, fornecedor);
                        if (i > -1) {
                            $scope.model.content.splice(i, 1);
                        }
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        var toast = $mdToast.simple()
                            .content(message)
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });
                    }
                })
            }, function () {
            });
        }

        /**
         *
         * @param selectedItens
         */
        $scope.selectionUpdate = function (selectedItens) {

            if ($scope.data.itensExcluir.length == 0 && selectedItens.length > 0) {
                $rootScope.$broadcast('showEitsBottomSheetEvent');
            } else if ($scope.data.itensExcluir.length > 0 && selectedItens.length == 0) {
                $rootScope.$broadcast('showEitsBottomSheetEvent');
            }

            $scope.data.itensExcluir = angular.copy(selectedItens);
        }

        /**
         *
         */
        $scope.limparSelecao = function () {
            $scope.data.itensExcluir = [];
            table.clearSelection();
            $rootScope.$broadcast('showEitsBottomSheetEvent');
        };

        /**
         *
         * @param ev
         */
        $scope.abrirPopupNovoPais = function (ev) {
            $mdDialog.show({
                controller: 'PaisDialogController',
                templateUrl: './modules/sisvarejo/ui/localizacao/pais/popup/popup-pais.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    entidadeExterna: null
                }
            })
                .then(function (result) {

                    $scope.model.paises.push(result);
                    $scope.model.pais = result;
                    $scope.paisChanged();

                    var toast = $mdToast.simple()
                        .content('Registro salvo com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        $scope.buscaCondicaoByCodigo = function() {
            financeiroService.findCondicaoByCodigo($scope.model.codigoCondicao, {
                callback: function(result) {
                    if (result != null)
                        $scope.model.entidade.condicaoPagamento = result;

                    $scope.$apply();
                },
                errorHandler: function() {
                    $mdToast.showSimple("Erro ao buscar a condição de pagamento");
                }
            })
        };

        $scope.buscaCidadeByCodigo = function() {
            localizacaoService.findCidadeByCodigo($scope.model.codigoCidade, {
                callback: function(result) {
                    if (result != null)
                        $scope.model.entidade.cidade = result;

                    $scope.$apply();
                },
                errorHandler: function() {
                    $mdToast.showSimple("Erro ao buscar a condição de pagamento");
                }
            })
        };

        /**
         *
         * @param ev
         */
        $scope.abrirPopupNovoEstado = function (ev) {
            $mdDialog.show({
                controller: 'EstadoDialogController',
                templateUrl: './modules/sisvarejo/ui/localizacao/estado/popup/popup-estado.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    entidadeExterna: null
                }
            })
                .then(function (result) {

                    $scope.model.estados.push(result);
                    $scope.model.estado = result;
                    $scope.model.pais = result.pais;
                    $scope.estadoChanged();

                    var toast = $mdToast.simple()
                        .content('Registro salvo com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param ev
         */
        $scope.abrirPopupCidade = function (ev) {
            $mdDialog.show({
                controller: 'CidadeDialogController',
                templateUrl: './modules/sisvarejo/ui/localizacao/cidade/popup/popup-busca-cidade.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    entidadeExterna: null
                }
            })
                .then(function (result) {

                    $scope.model.cidades.push(result);
                    $scope.model.entidade.cidade = result;
                    $scope.model.estado = result.estado;
                    $scope.model.pais = result.estado.pais;
                    //$scope.estadoChanged();

                    //var toast = $mdToast.simple()
                    //    .content('Registro salvo com sucesso!')
                    //    .action('Fechar')
                    //    .highlightAction(false)
                    //    .position('bottom left right');
                    //$mdToast.show(toast).then(function () {
                    //});

                }, function () {
                    //tratar o "cancelar" da popup
                });
        };

        /**
         *
         * @param ev
         */
        $scope.abrirPopupCondicao = function (ev) {
            $mdDialog.show({
                    controller: 'BuscaCondicaoDialogController',
                    templateUrl: './modules/sisvarejo/ui/loja/cliente/popup/popup-busca-condicao.html',
                    targetEvent: ev,
                    hasBackdrop: true,
                    locals: {
                        entidadeExterna: null
                    }
                })
                .then(function (result) {

                    $scope.model.entidade.condicaoPagamento = result;

                }, function () {
                    //tratar o "cancelar" da popup
                });
        };

    });

    /**
     * Controller da popup de Cidade
     */
    angular.module('sisvarejo').controller('FornecedorDialogController', function ($scope, $mdDialog, $importService, $mdToast, $window, $interval) {

        $importService("estoqueService");

        /**
         *
         */
        $scope.model = {
            content: [],
            query: {
                order: 'id'
            },
            filtros: {
                razaoSocial: null,
                nomeFantasia: null,
                telefone: null,
                cnpj: null,
                representante: null
            }
        }

        /**
         *
         */
        $scope.cancelar = function () {
            $mdDialog.cancel();
        };

        /**
         *
         */
        $scope.listFornecedoresByFilters = function () {
            estoqueService.listFornecedoresByFilters($scope.model.filtros.razaoSocial, $scope.model.filtros.nomeFantasia,
                $scope.model.filtros.telefone, $scope.model.filtros.cnpj, $scope.model.filtros.representante, {
                    callback: function (result) {
                        $scope.model.content = result;
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple(message)
                    }
                });
        }

        /**
         *
         * @returns {boolean}
         */
        $scope.validaForm = function () {
            if (!$scope.cidadeForm.$valid) {
                $mdToast.show($mdToast.simple()
                    .content('Preencha todos os campos obrigatórios!')
                    .action('Fechar')
                    .highlightAction(false)
                    .position('top')).then(function () {
                });
                return false;
            } else {
                return true;
            }
        };

        $scope.cadastrarCidade = function () {
            $scope.model.currentState = 'cadastrar';

            $scope.model.entidade = new Cidade();
        }

        /**
         *
         * @param cidade
         */
        $scope.escolherFornecedor = function (fornecedor) {
            $mdDialog.hide(fornecedor);
        }

        /**
         *
         */
        $scope.showPopup = function showPopup() {
            // center the popup window
            var left = screen.width / 2 - 200
                , top = screen.height / 2 - 250
                , popup = $window.open('/#/fornecedor/cadastrar', '', "top=" + top + ",left=" + left + ",width=400,height=500")
                , interval = 1000;

            // create an ever increasing interval to check a certain global value getting assigned in the popup
            var i = $interval(function () {
                interval += 500;
                try {

                    // value is the user_id returned from paypal
                    if (popup.value) {
                        $interval.cancel(i);
                        popup.close();
                    }
                } catch (e) {
                    console.error(e);
                }
            }, interval);

        }
    });

}(window.angular));