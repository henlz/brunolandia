(function (angular) {
    'use strict';


    angular.module('sisvarejo').controller('ProdutoController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

        $importService("estoqueService");
        $importService("caracteristicaService");

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
        $scope.LIST_STATE = "produto.listar";
        /**
         * Variável estática que representa
         * o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "produto.detalhe";
        /**
         * Variável estática que representa
         * o estado para a criação de registros.
         */
        $scope.INSERT_STATE = "produto.cadastrar";
        /**
         * Variável estática que representa
         * o estado para a edição de registros.
         */
        $scope.UPDATE_STATE = "produto.alterar";

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
            fornecedores: [],
            cores: [],
            tamanhos: [],
            entidade: {},
            query: {
                order: 'codigo'
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

            $scope.carregarListaCores();
            $scope.carregarListaTamanhos();
            $scope.carregarListaFornecedores();

            $scope.model.entidade = new Produto();
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

            estoqueService.findProdutoById(id, {
                callback: function (result) {
                    $scope.model.entidade = result;
                    $scope.carregarListaCores();
                    $scope.carregarListaTamanhos();
                    $scope.carregarListaFornecedores();
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
            estoqueService.listProdutos({
                callback: function (result) {
                    $scope.model.content = result;
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
         */
        $scope.carregarListaCores = function () {
            caracteristicaService.listCores({
                callback: function (result) {
                    $scope.model.cores = result;
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
         */
        $scope.carregarListaTamanhos = function () {
            caracteristicaService.listTamanhos({
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
        }

        /**
         *
         */
        $scope.carregarListaFornecedores = function () {
            estoqueService.listFornecedoresByStatus(true, {
                callback: function (result) {
                    $scope.model.fornecedores = result;
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

        $scope.abrirPopupFornecedor = function (ev) {
            $mdDialog.show({
                controller: 'FornecedorDialogController',
                templateUrl: './modules/sisvarejo/ui/estoque/fornecedor/popup/popup-busca-fornecedor.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    entidadeExterna: null
                }
            })
                .then(function (result) {

                    $scope.model.entidade.fornecedor = result;

                }, function () {
                    //tratar o "cancelar" da popup
                });
        };

        /**
         *
         * @param ev
         */
        $scope.abrirPopupNovaEntidade = function (ev) {
            $mdDialog.show({
                controller: ProdutoDialogController,
                templateUrl: './modules/sisvarejo/ui/estoque/produto/popup/popup-produto.html',
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

        $scope.abrirPopupAlterarEntidade = function (ev, entidade) {
            $mdDialog.show({
                controller: ProdutoDialogController,
                templateUrl: './modules/sisvarejo/ui/estoque/produto/popup/popup-produto.html',
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
        $scope.salvarProduto = function (entidade) {
            estoqueService.insertProduto(entidade, {
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

        $scope.abrirPopupBuscarCor = function (ev) {
            $mdDialog.show({
                controller: CorDialogController,
                templateUrl: './modules/sisvarejo/ui/caracteristica/cor/popup/popup-buscar-cor.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    entidadeExterna: null
                }
            })
                .then(function (result) {

                    $scope.model.entidade.cor = result;

                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param entidade
         */
        $scope.alterarPrincipioDiretriz = function (entidade) {
            estoqueService.updatePrincipioDiretriz(entidade, {
                callback: function (result) {
                    var toast = $mdToast.simple()
                        .content('Registro atualizado com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                    var i = $scope.findByIdInArray($scope.currentPage, result);
                    $scope.currentPage.splice(i, 1);
                    $scope.currentPage.push(result);

                    $scope.$apply();
                },
                errorHandler: function (message, error) {
                    $log.error(message);
                }
            });
        };

        /**
         *
         * @param ev
         * @param id
         */
        $scope.excluirProduto = function (ev, produto) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Produto')
                .content('Tem certeza que deseja excluir o registros? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Produto')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            $mdDialog.show(confirm).then(function () {
                estoqueService.removeProduto(produto, {
                    callback: function () {
                        var toast = $mdToast.simple()
                            .content('Registro excluído com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });

                        var i = $scope.findByIdInArray($scope.model.content, produto);
                        if (i > -1) {
                            $scope.model.content.splice(i, 1);
                        }
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        $log.error("Erro ao excluir registro(s)", message);
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
        }

    });

    /**
     * Controller da popup de Princípio e Diretriz
     */
    function ProdutoDialogController($scope, $mdDialog, $importService, $mdToast, entidadeExterna) {

        $importService("estoqueService");

        if (entidadeExterna != null) {
            $scope.model.entidade = entidadeExterna;
            $scope.modoAlteracao = true;
        } else {
            $scope.model.entidade = {};
            $scope.modoAlteracao = false;
        }

        $scope.cancelar = function () {
            $mdDialog.cancel();
        };

        /**
         *
         * @returns {boolean}
         */
        $scope.validaForm = function () {
            if (!$scope.produtoForm.$valid) {
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

        /**
         *
         */
        $scope.salvar = function () {
            if ($scope.validaForm()) {

                if (!$scope.modoAlteracao) {
                    estoqueService.insertProduto($scope.model.entidade, {
                        callback: function (result) {
                            $mdDialog.hide(result);
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
                } else {
                    estoqueService.updateProduto($scope.model.entidade, {
                        callback: function (result) {
                            $mdDialog.hide(result);
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
                }
            }
        };


        /**
         *
         */
        $('html').bind('keypress', function (e) {
            if (e.keyCode == 13) {
                return false;
            }
        });
    }


}(window.angular));