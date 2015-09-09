(function (angular) {
    'use strict';

    angular.module('pdti').controller('EstrategiaController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

        $importService("estrategiaService");

        /**
         * Injeta os métodos, atributos e seus estados herdados de AbstractCRUDController.
         * @see AbstractCRUDController
         */
        $injector.invoke(AbstractCRUDController, this, { $scope: $scope });

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
            tagsString: "",
            query: {
                order: 'descricao',
                limit: 5,
                page: 1
            },
            selected: [],
            itensExcluir: []
        };

        /**
         * 
         */
        $scope.ordenacao = {
            predicado: "codigo",
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

            $scope.pageRequest = pageRequest;

            $scope.carregarLista(null, $scope.pageRequest);
        }

        /**
         * 
         */
        $scope.carregarLista = function (filter, pageRequest) {
            estrategiaService.listEstrategiasByFilters(filter, pageRequest, {
                callback: function (result) {
                    $scope.currentPage = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $log.error(message);
                }
            })
        }
        
        /**
         * 
         */
        $scope.changeTags = function(tags) {
//            $scope.carregarLista( $scope.tagsString , $scope.pageRequest);
        }
        
        /**
         * 
         */
        $scope.$watch('model.tagsString', function(newValue){
            $scope.carregarLista( newValue , $scope.pageRequest);
        });

        /**
         * 
         */
        $scope.abrirPopupNovaEntidade = function (ev) {
            $mdDialog.show({
                controller: EstrategiaDialogController,
                templateUrl: './modules/pdti/ui/estrategia/popup/popup-estrategia.html',
                targetEvent: ev,
                locals: {
                    entidadeExterna: null
                }
            })
                .then(function (result) {

                $scope.currentPage.content.push(result);

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
         */
        $scope.abrirPopupAlterarEntidade = function (ev, entidade) {
            $mdDialog.show({
                controller: EstrategiaDialogController,
                templateUrl: './modules/pdti/ui/estrategia/popup/popup-estrategia.html',
                targetEvent: ev,
                hasBackdrop: true,
                bindToController: true,
                locals: {
                    entidadeExterna: angular.copy(entidade)
                }
            })
                .then(function (result) {

                var toast = $mdToast.simple()
                    .content('Registro atualizado com sucesso!')
                    .action('Fechar')
                    .highlightAction(false)
                    .position('bottom left right');
                $mdToast.show(toast).then(function () {
                });

                var i = $scope.findByIdInArray($scope.currentPage.content, result);
                $scope.currentPage.content[i] = result;

            }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param item
         * @param incluso
         */
        $scope.incluiItemExcluir = function (item, incluso) {
            if (incluso) {
                var i = $scope.findByIdInArray($scope.data.itensExcluir, item);
                $scope.data.itensExcluir.splice(i, 1);
                if ($scope.data.itensExcluir.length == 0) $rootScope.$broadcast('showEitsBottomSheetEvent');
            } else if (!incluso && $scope.data.itensExcluir.length == 0) {
                $rootScope.$broadcast('showEitsBottomSheetEvent');
                $scope.data.itensExcluir.push(item);
            } else if (!incluso) {
                $scope.data.itensExcluir.push(item);
            }
        }

        /**
         *
         */
        $scope.limparSelecao = function () {
            for (var x = 0; x < $scope.data.itensExcluir.length; x++) {
                $scope.data.checkBox[$scope.data.itensExcluir[x].id] = false;
            }
            $scope.data.itensExcluir.splice(0, $scope.data.itensExcluir.length);
            $rootScope.$broadcast('showEitsBottomSheetEvent');
        }

        /**
         *
         * @param ev
         * @param id
         */
        $scope.excluirEstrategia = function (ev, lista) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Estratégia')
                .content('Tem certeza que deseja excluir o(s) registros(s)? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Estratégia')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            var listaCopia = angular.copy(lista);
            $mdDialog.show(confirm).then(function () {
                estrategiaService.removeEstrategia(lista, {
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
                            var i = $scope.findByIdInArray($scope.currentPage.content, listaCopia[x]);
                            $scope.currentPage.content.splice(i, 1);
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

        /**
         *
         */
        $scope.$watchCollection('model.selected', function(newValue, oldValue){
            if (newValue == oldValue) return false;

            $scope.selectionUpdate(newValue);
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
     * Controller da popup de Estratégia
     */
    function EstrategiaDialogController($scope, $mdDialog, $importService, $mdToast, entidadeExterna) {

        $scope.listaDocumentos = [];

        $importService("documentoReferenciaService");

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
            if (!$scope.estrategiaForm.$valid) {
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
        }

        /**
         *
         */
        $scope.salvar = function () {
            if ($scope.validaForm()) {

                if (!$scope.modoAlteracao) {
                    estrategiaService.insertEstrategia($scope.entidade, {
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
                    estrategiaService.updateEstrategia($scope.entidade, {
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
        documentoReferenciaService.listDocumentosReferenciaByFilters(null, false, {
            callback: function (result) {
                $scope.listaDocumentos = result.content;
                $scope.$apply();
            }, errorHandler: function (message, exception) {
                $log.error(message);
            }
        });

        $scope.querySearch = function (query) {
            var results = query ? $scope.listaDocumentos.filter($scope.createFilterFor(query)) : $scope.listaDocumentos,
                deferred;
            var simulateQuery = false;
            if (simulateQuery) {
                deferred = $q.defer();
                $timeout(function () {
                    deferred.resolve(results);
                }, Math.random() * 1000, false);
                return deferred.promise;
            } else {
                return results;
            }
        };

        $scope.createFilterFor = function (query) {
            var lowercaseQuery = angular.lowercase(query);
            return function filterFn(documento) {
                return (angular.lowercase(documento.titulo).indexOf(lowercaseQuery) === 0);
            };
        }

        //$('html').bind('keypress', function(e)
        //{
        //    if(e.keyCode == 13)
        //    {
        //        return false;
        //    }
        //});
    }

} (window.angular));
