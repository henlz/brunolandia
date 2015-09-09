(function (angular) {
    'use strict';


    angular.module('sisvarejo').controller('PaisController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

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
         * 		 				 	ATTRIBUTES
         *-------------------------------------------------------------------*/

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

        /**
         *
         */
        $scope.ordenacao = {
            predicado: "-sigla",
            ordem: false
        }

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
            localizacaoService.listPaises({
                callback: function (result) {
                    $scope.model.content = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $log.error(message);
                }
            })
        };

        $scope.abrirPopupNovaEntidade = function (ev) {
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
                controller: 'PaisDialogController',
                templateUrl: './modules/sisvarejo/ui/localizacao/pais/popup/popup-pais.html',
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
         * @param entidade
         */
        $scope.salvarPrincipioDiretriz = function (entidade) {
            localizacaoService.insertPrincipioDiretriz(entidade, {
                callback: function (result) {
                    $scope.model.content.push(result);
                    var toast = $mdToast.simple()
                        .content('Registro salvo com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
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
        $scope.alterarPrincipioDiretriz = function (entidade) {
            localizacaoService.updatePrincipioDiretriz(entidade, {
                callback: function (result) {
                    var toast = $mdToast.simple()
                        .content('Registro atualizado com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                    var i = $scope.findByIdInArray($scope.model.content, result);
                    $scope.model.content.splice(i, 1);
                    $scope.model.content.push(result);

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
        $scope.excluirPaises = function (ev, lista) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de País')
                .content('Tem certeza que deseja excluir o(s) registros(s)? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de País')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            var listaCopia = angular.copy(lista);
            $mdDialog.show(confirm).then(function () {
                localizacaoService.removePais(lista, {
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

        $scope.$watchCollection('model.selected', function(newValue){
            if (newValue != null &&newValue != undefined) $scope.selectionUpdate(newValue);
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
    angular.module('sisvarejo').controller('PaisDialogController', function ($scope, $mdDialog, $importService, $mdToast, entidadeExterna) {

        $importService("localizacaoService");
        
        /**
         * 
         */
        $scope.model = {
    		nameFilter: '',
    		paisesList: [],
    		query: {
    			order: 'nome'
    		}
        }

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
            if (!$scope.paisForm.$valid) {
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
                    localizacaoService.insertPais($scope.entidade, {
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
                    localizacaoService.updatePais($scope.entidade, {
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
        $scope.lisPaisesByFilter = function(filter) {
        	localizacaoService.lisPaisesByFilter(filter, {
        		callback: function(result){
        			$scope.model.paisesList = result;    			
        			$scope.$apply();
        		},
        		errorHandler: function(message, errorHandler) {
        			$mdToast.show($mdToast.simple()
                            .content(message)
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right'))
                            .then(function () {
                            });
        		} 		
        	});
        };
    });


}(window.angular));