(function (angular) {
    'use strict';


    angular.module('sisvarejo').controller('CondicaoController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

        $importService("financeiroService");

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
        $scope.LIST_STATE = "condicao.listar";
        /**
         * Variável estática que representa
         * o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "condicao.detalhe";
        /**
         * Variável estática que representa
         * o estado para a criação de registros.
         */
        $scope.INSERT_STATE = "condicao.cadastrar";
        /**
         * Variável estática que representa
         * o estado para a edição de registros.
         */
        $scope.UPDATE_STATE = "condicao.alterar";


        /*-------------------------------------------------------------------
         * 		 				 	ATTRIBUTES
         *-------------------------------------------------------------------*/

        /**
         *
         */
        $scope.data = {
            itensExcluir: []
        };

        /**
         *
         * @type {{entidade: {}, content: Array, query: {order: string}}}
         */
        $scope.model = {
            entidade: {},
            content: [],
            query: {
                order: 'descricao'
            }
        }

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
                case $scope.LIST_STATE: {
                    $scope.changeToList();
                }
                    break;
                case $scope.DETAIL_STATE: {
                    $scope.changeToDetail($state.params.id);
                }
                    break;
                case $scope.INSERT_STATE: {
                    $scope.changeToInsert();
                }
                    break;
                case $scope.UPDATE_STATE: {
                    $scope.changeToUpdate($state.params.id);
                }
                    break;
                default: {
                    $state.go($scope.LIST_STATE);
                }
            }
        };

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

            $scope.model.entidade = new Condicao();
            $scope.model.entidade.parcelas = [];
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

            financeiroService.findCondicaoById(id, {
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

        $scope.carregarLista = function (filter, pageRequest) {
            financeiroService.listCondicoesByFilters(null, {
                callback: function (result) {
                    $scope.model.content = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $log.error(message);
                }
            })
        }

        /**
         *
         * @param ev
         */
        $scope.abrirPopupNovaParcela = function (ev) {
            var parcela = new Parcela();
            parcela.numeroDaParcela = $scope.getLastParcela($scope.model.entidade);
            $mdDialog.show({
                controller: CondicaoDialogController,
                templateUrl: './modules/sisvarejo/ui/financeiro/condicao/popup/popup-condicao.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    entidadeExterna: parcela,
                    isEditing: false
                }
            })
                .then(function (result) {
                    $scope.model.entidade.parcelas.push(result);
                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param ev
         * @param entidade
         */
        $scope.abrirPopupAlterarParcela = function (ev, entidade) {
            $mdDialog.show({
                controller: CondicaoDialogController,
                templateUrl: './modules/sisvarejo/ui/financeiro/condicao/popup/popup-condicao.html',
                targetEvent: ev,
                hasBackdrop: true,
                bindToController: true,
                locals: {
                    entidadeExterna: angular.copy(entidade),
                    isEditing: true
                }
            })
                .then(function (result) {
                    var i = $scope.findByIdInArray($scope.model.entidade.parcelas, result);
                    $scope.model.entidade.parcelas[i] = result;
                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param entidade
         * @returns {number}
         */
        $scope.getLastParcela = function (entidade) {
            var numeroDaParcela = 0;
            if (entidade.parcelas != null && entidade.parcelas.length > 0) {
                for (var i = 0; entidade.parcelas.length > i; i++) {
                    numeroDaParcela = entidade.parcelas[i].numeroDaParcela > numeroDaParcela ? entidade.parcelas[i].numeroDaParcela : numeroDaParcela;
                }
            }
            numeroDaParcela++
            return numeroDaParcela;
        }

        /**
         *
         * @param ev
         * @param parcela
         */
        $scope.excluirParcela = function (ev, parcela) {
            var i = $scope.findByIdInArray($scope.model.entidade.parcelas, parcela);
            $scope.model.entidade.parcelas.splice(i, 1);
        }

        /**
         *
         * @param entidade
         * @returns {boolean}
         */
        $scope.validarParcelas = function (entidade) {
            var count = 0;
            for (var i = 0; entidade.parcelas.length > i; i++) {
                count += entidade.parcelas[i].percentual;
            }

            if (count < 100) {
                return false;
            }

            return true;
        }

        /**
         *
         * @param entidade
         */
        $scope.salvarCondicao = function (entidade) {
            if ($scope.validarParcelas(entidade) == true) {
                financeiroService.insertCondicao(entidade, {
                    callback: function (result) {
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
            } else {
                var toast = $mdToast.simple()
                    .content('O percentual das parcelas devem totalizar em 100%!')
                    .action('Fechar')
                    .highlightAction(false)
                    .position('bottom left right');
                $mdToast.show(toast).then(function () {
                });
            }
        };

        /**
         *
         * @param entidade
         */
        $scope.alterarCondicao = function (entidade) {
            if ($scope.validarParcelas(entidade)) {
                financeiroService.updateCondicao(entidade, {
                    callback: function (result) {
                        var toast = $mdToast.simple()
                            .content('Registro atualizado com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });
                        $state.go($scope.LIST_STATE);
                        $scope.$apply();
                    },
                    errorHandler: function (message, error) {
                        $log.error(message);
                    }
                });
            } else {
                var toast = $mdToast.simple()
                    .content('O percentual das parcelas devem totalizar em 100%!')
                    .action('Fechar')
                    .highlightAction(false)
                    .position('bottom left right');
                $mdToast.show(toast).then(function () {
                });
            }
        };

        /**
         *
         * @param ev
         * @param id
         */
        $scope.excluirCondicao = function (ev, condicao) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Condicao')
                .content('Tem certeza que deseja excluir o(s) registros(s)? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Condicao')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            $mdDialog.show(confirm).then(function () {
                financeiroService.removeCondicoes([condicao], {
                    callback: function (result) {
                        var toast = $mdToast.simple()
                            .content('Registro(s) excluído(s) com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });

                        var i = $scope.findByIdInArray($scope.model.content, condicao);
                        $scope.model.content.splice(i, 1);
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

        /**
         *
         * @param item
         */
        $scope.itemClicked = function (item) {
            $scope.abrirPopupAlterarEntidade(null, item);
        }
    });

    /**
     * Controller da popup de Princípio e Diretriz
     */
    function CondicaoDialogController($scope, $mdDialog, $importService, $mdToast, entidadeExterna, isEditing) {

        $importService("financeiroService");

        /**
         *
         */
        $scope.entidade = entidadeExterna;
        $scope.modoAlteracao = isEditing;

        /**
         *
         */
        $scope.cancelar = function () {
            $mdDialog.cancel();
        };

        /**
         *
         */
        $scope.carregarListaFormasPagamento = function () {
            financeiroService.listFormasPagamentoByFilters(null, {
                callback: function (result) {
                    $scope.formasPagamento = result;
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
            })
        }

        /**
         *
         * @returns {boolean}
         */
        $scope.validaForm = function () {
            if (!$scope.parcelaForm.$valid) {
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

                $mdDialog.hide($scope.entidade);

            }
        };
    }


}(window.angular));