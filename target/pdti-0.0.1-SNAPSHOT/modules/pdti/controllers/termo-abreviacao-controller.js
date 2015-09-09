(function (angular) {
    'use strict';

    /**
     *
     */
    angular.module('pdti')
        .controller('TermoAbreviacaoController', function ($scope, $rootScope, $mdToast, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

        $importService("termoAbreviacaoService");

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
        $scope.changeTags = function(tags) {
//            $scope.tags = tags;
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
        $scope.carregarLista = function (filter, pageRequest) {
            termoAbreviacaoService.listTermoAbreviacaoByFilters( filter, pageRequest, {
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
        $scope.abrirPopupNovaEntidade = function (ev) {
            $mdDialog.show({
                controller: TermoAbreviacaoDialogController,
                templateUrl: './modules/pdti/ui/termo-abreviacao/popup/popup-termo-abreviacao.html',
                targetEvent: ev,
                hasBackdrop: true,
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
                controller: TermoAbreviacaoDialogController,
                templateUrl: './modules/pdti/ui/termo-abreviacao/popup/popup-termo-abreviacao.html',
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
                    $mdToast.show(toast).then(function () {});

                    var i = $scope.findByIdInArray($scope.currentPage.content, result);
                    $scope.currentPage.content[i] = result;
                }, function () {});
        }



        /**
         *
         * @param entidade
         */
        $scope.alterarTermoAbreviacao = function (entidade) {
            termoAbreviacaoService.updateTermoAbreviacao(entidade, {
                callback: function (result) {
                    var toast = $mdToast.simple()
                        .content('Registro atualizado com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                    var i = $scope.findByIdInArray($scope.currentPage.content, result);
                    $scope.currentPage.content.splice(i, 1);
                    $scope.currentPage.content.push(result);

                    $scope.$apply();
                },
                errorHandler: function (message, error) {
                    $log.error(message);
                }
            });
        };

        /**
         *
         */
        $scope.excluirTermoAbreviacao = function (ev, lista) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Termo e Abreviação')
                .content('Tem certeza que deseja excluir o(s) registros(s)? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Termo e Abreviação')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);
            var listaCopia = angular.copy(lista);
            $mdDialog.show(confirm).then(function () {
                termoAbreviacaoService.removeTermoAbreviacao(lista, {
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
     * Controller da popup de Termo e Abreviação
     */
    function TermoAbreviacaoDialogController($scope, $mdDialog, $mdToast, $log, entidadeExterna) {

        if (entidadeExterna != null) {
            $scope.entidade = entidadeExterna;
            $scope.modoAlteracao = true;
        } else {
            $scope.entidade = {
                abreviacao: "",
                termo: ""
            };
            $scope.modoAlteracao = false;
        }

        $scope.cancelar = function () {
            $mdDialog.cancel();
        };

        $scope.validaForm = function () {
            if (!$scope.termoAbreviacaoForm.$valid) {
                $mdToast.show($mdToast.simple()
                    .content('Preencha todos os campos obrigatórios!')
                    .action('Fechar')
                    .highlightAction(false)
                    .position('top')).then(function () {
                });
                return false;
            }
            return true;
        }

        $scope.salvar = function () {
            if ($scope.validaForm()) {
                termoAbreviacaoService.insertTermoAbreviacao($scope.entidade, {
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
        };

        $('html').bind('keypress', function (e) {
            if (e.keyCode == 13) {
                return false;
            }
        });
    }

} (window.angular));