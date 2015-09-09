(function (angular) {
    'use strict';


    angular.module('sisvarejo').controller('TamanhoController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

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
         */
        $scope.model = {
            content: [],
            itensExcluir: [],
            query: {
                order: 'nome'
            },
            selected: []
        };


        /*-------------------------------------------------------------------
         * 		 				 	  BEHAVIORS
         *-------------------------------------------------------------------*/

        /**
         *
         */
        $scope.initialize = function () {
            var pageRequest = {};
            pageRequest.size = 10;

            $scope.pageRequest = pageRequest;

            $scope.carregarLista(null, pageRequest);
        }

        $scope.carregarLista = function (filter, pageRequest) {
            caracteristicaService.listTamanhos({
                callback: function (result) {
                    $scope.model.content = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $log.error(message);
                }
            })
        }

        $scope.abrirPopupNovaEntidade = function (ev) {
            $mdDialog.show({
                controller: TamanhoDialogController,
                templateUrl: './modules/sisvarejo/ui/caracteristica/tamanho/popup/popup-tamanho.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    entidadeExterna: null
                }
            })
                .then(function (result) {

                    $scope.model.content.push(result);

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
                controller: TamanhoDialogController,
                templateUrl: './modules/sisvarejo/ui/caracteristica/tamanho/popup/popup-tamanho.html',
                targetEvent: ev,
                hasBackdrop: true,
                bindToController: true,
                locals: {
                    entidadeExterna: angular.copy(entidade)
                }
            })
                .then(function (result) {
                    var i = $scope.findByIdInArray($scope.model.content, result);
                    $scope.model.content[i] = result;
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
         * @param id
         */
        $scope.excluirTamanhos = function (ev, lista) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Tamanho')
                .content('Tem certeza que deseja excluir o(s) registros(s)? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Tamanho')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            var listaCopia = angular.copy(lista);
            $mdDialog.show(confirm).then(function () {
                caracteristicaService.removeTamanhos(lista, {
                    callback: function (result) {
                        var toast = $mdToast.simple()
                            .content('Registro(s) excluído(s) com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });

                        $scope.limparSelecao();

                        for (var x = 0; x < listaCopia.length; x++) {
                            var i = $scope.findByIdInArray($scope.model.content, listaCopia[x]);
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

            if ($scope.model.itensExcluir.length == 0 && selectedItens.length > 0) {
                $rootScope.$broadcast('showEitsBottomSheetEvent');
            } else if ($scope.model.itensExcluir.length > 0 && selectedItens.length == 0) {
                $rootScope.$broadcast('showEitsBottomSheetEvent');
            }

            $scope.model.itensExcluir = angular.copy(selectedItens);
        }

        $scope.$watchCollection('model.selected', function (newValue) {
            if (newValue != null && newValue != undefined) $scope.selectionUpdate(newValue);
        })

        /**
         *
         */
        $scope.limparSelecao = function () {
            $scope.model.itensExcluir = [];
            $scope.model.selected.splice(0, $scope.model.selected.length);
            $rootScope.$broadcast('showEitsBottomSheetEvent');
        }

        /**
         *
         * @param item
         */
        $scope.itemClicked = function (ev, item) {
            $scope.abrirPopupAlterarEntidade(ev, item);
        }
    });

    /**
     * Controller da popup de Princípio e Diretriz
     */
    function TamanhoDialogController($scope, $mdDialog, $importService, $mdToast, entidadeExterna) {

        $importService("caracteristicaService");

        if (entidadeExterna != null) {
            $scope.entidade = entidadeExterna;
            $scope.modoAlteracao = true;
        } else {
            $scope.entidade = {};
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
            if (!$scope.tamanhoForm.$valid) {
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
                    caracteristicaService.insertTamanho($scope.entidade, {
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
                    caracteristicaService.updateTamanho($scope.entidade, {
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