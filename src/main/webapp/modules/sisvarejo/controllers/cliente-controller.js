(function (angular) {
    'use strict';


    angular.module('sisvarejo').controller('ClienteController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

        $importService("lojaService");
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
        $scope.LIST_STATE = "cliente.listar";
        /**
         * Variável estática que representa
         * o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "cliente.detalhe";
        /**
         * Variável estática que representa
         * o estado para a criação de registros.
         */
        $scope.INSERT_STATE = "cliente.cadastrar";
        /**
         * Variável estática que representa
         * o estado para a edição de registros.
         */
        $scope.UPDATE_STATE = "cliente.alterar";

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
            entidade: {},
            query: {
                order: 'nome'
            },
            filtros: {
                nome: null,
                apelido: null,
                cpf: null,
                rg: null
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

            //$scope.carregarListaPaises();

            $scope.model.entidade = {};
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

            lojaService.findClienteById(id, {
                callback: function (result) {
                    $scope.model.entidade = result;
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
            lojaService.listClientesByFilters($scope.model.filtros.nome, $scope.model.filtros.apelido,
                $scope.model.filtros.cpf, $scope.model.filtros.rg, {
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
         * @param ev
         */
        $scope.abrirPopupBuscaCondicao = function (ev) {
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
        }

        $scope.buscaCondicaoByCodigo = function () {
            financeiroService.findCondicaoByCodigo($scope.model.codigoCondicao, {
                callback: function (result) {
                    if (result != null)
                        $scope.model.entidade.condicaoPagamento = result;

                    $scope.$apply();
                },
                errorHandler: function () {
                    $mdToast.showSimple("Erro ao buscar a condição de pagamento");
                }
            })
        };

        /**
         *
         * @param entidade
         */
        $scope.salvarCliente = function (entidade) {
            lojaService.insertCliente(entidade, {
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
        $scope.atualizarCliente = function (entidade) {
            lojaService.updateCliente(entidade, {
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
        $scope.excluirCliente = function (ev, cliente) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Cliente')
                .content('Tem certeza que deseja excluir o cliente "' + cliente.nome + '"? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Cliente')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            $mdDialog.show(confirm).then(function () {
                lojaService.removeCliente(cliente, {
                    callback: function () {
                        var toast = $mdToast.simple()
                            .content('Registro excluído com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });

                        var i = $scope.findByIdInArray($scope.model.content, cliente);
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

                    //$scope.model.cidades.push(result);
                    $scope.model.entidade.cidade = result;
                    //$scope.model.estado = result.estado;
                    //$scope.model.pais = result.estado.pais;
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

    });

    /**
     * Controller da popup de Cidade
     */
    angular.module('sisvarejo').controller('ClienteDialogController', function ($scope, $mdDialog, $importService, $mdToast, $window, $interval) {

        $importService("lojaService");

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
        $scope.listClientesByFilters = function () {
            lojaService.listClientesByFilters($scope.model.filtros.razaoSocial, $scope.model.filtros.nomeFantasia,
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
        $scope.escolherCliente = function (fornecedor) {
            $mdDialog.hide(fornecedor);
        }

    });
    /**
     * Controller da popup de Cidade
     */
    angular.module('sisvarejo').controller('BuscaCondicaoDialogController', function ($scope, $mdDialog, $importService, $mdToast, $window, $interval) {

        $importService("financeiroService");

        /**
         *
         */
        $scope.model = {
            content: [],
            query: {
                order: 'id'
            },
            filtros: {
                descricao: null,
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
        $scope.listCondicoes = function () {
            financeiroService.listCondicoesByFilters($scope.model.filtros.descricao, {
                callback: function (result) {
                    $scope.model.content = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $mdToast.showSimple(message)
                }
            });
        };

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
        $scope.escolherCondicao = function (condicao) {
            $mdDialog.hide(condicao);
        }

    });

}(window.angular));