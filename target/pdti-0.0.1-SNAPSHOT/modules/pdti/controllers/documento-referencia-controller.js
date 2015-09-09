(function (angular) {
    'use strict';


    angular.module('pdti')
        .controller('DocumentoReferenciaController', function ($scope, $rootScope, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

        $importService("documentoReferenciaService");

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
                order: 'sigla',
                limit: 5,
                page: 1
            },
            selected: [],
            itensExcluir: []
        };

        /**
         * Variável que armazena o estado da paginação para renderizar o pager
         * e também para fazer as requisições das novas páginas, contendo o estado 
         * do Sort incluído.
         * 
         * @PageRequest
         */
        $scope.ordenacao = {
            predicado: "codigo",
            ordem: false
        }
            
        /**
         * 
         */
        $scope.currentPage;
        
        /**
         * 
         */
        $scope.tagsString = "";

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
            documentoReferenciaService.listDocumentosReferenciaByFilters(filter, true, pageRequest, {
                callback: function (result) {
                    $scope.currentPage = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $log.error(exception);
                    $log.error(message);
                }
            })
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
        $scope.abrirPopupNovaEntidade = function (ev) {
            $mdDialog.show({
                controller: DocumentoReferenciaDialogController,
                templateUrl: './modules/pdti/ui/documento-referencia/popup/popup-documento-referencia.html',
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
                controller: DocumentoReferenciaDialogController,
                templateUrl: './modules/pdti/ui/documento-referencia/popup/popup-documento-referencia.html',
                targetEvent: ev,
                hasBackdrop: true,
                bindToController: true,
                locals: {
                    entidadeExterna: angular.copy(entidade)
                }
            })
                .then(function (result) {
                var i = $scope.findByIdInArray($scope.currentPage.content, result);
                $scope.currentPage.content[i] = result;
                var toast = $mdToast.simple()
                    .content('Registro atualizado com sucesso!')
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
        $scope.salvarDocumentoReferencia = function (entidade) {
            documentoReferenciaService.insertDocumentoReferencia(entidade, {
                callback: function (result) {
                    $scope.currentPage.content.push(result);
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
                    $log.error(error);
                    $log.error(message);
                }
            });
        };

        /**
         *
         * @param entidade
         */
        $scope.alterarDocumentoReferencia = function (entidade) {

            entidade.anexo = typeof entidade.anexo === 'string' ? null : entidade.anexo;

            documentoReferenciaService.updateDocumentoReferencia(entidade, {
                callback: function (result) {
                    var toast = $mdToast.simple()
                        .content('Registro atualizado com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });


                    var i = $scope.findByIdInArray($scope.currentPage.content, result);
                    $scope.currentPage.content[i] = result;

                    $scope.$apply();
                },
                errorHandler: function (message, error) {
                    $log.error(message);

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
         * @param ev
         * @param id
         */
        $scope.excluirDocumentoReferencia = function (ev, lista) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Documento de Referência')
                .content('Tem certeza que deseja excluir o(s) registros(s)? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Documento de Referência')
                .ok('Sim!')
                .cancel('Cancelar')
                .targetEvent(ev);

            $mdDialog.show(confirm).then(function () {

                var listaCopia = angular.copy(lista);

                for (var i = 0; i < listaCopia.length; i++) {
                    listaCopia[i].anexo = null;
                }

                documentoReferenciaService.removeDocumentoReferencia(listaCopia, {
                    callback: function (result) {
                        var toast = $mdToast.simple()
                            .content('Registro(s) excluído(s) com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });

                        for (var j = 0; j < lista.length; j++) {
                            var i = $scope.findByIdInArray($scope.currentPage.content, lista[j]);
                            $scope.currentPage.content.splice(i, 1);
                        }
                        $scope.limparSelecao();
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        if (exception.javaClassName == "org.springframework.transaction.TransactionSystemException") {
                            var toast = $mdToast.simple()
                                .content('Erro ao excluir, um ou mais registros estão sendo usados pelo sistema!')
                                .action('Fechar')
                                .highlightAction(true)
                                .position('bottom left right');
                            $mdToast.show(toast).then(function () {
                            });
                        }
                        $log.error("Erro ao excluir registro(s).", message);
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
     * Controller da popup de Documento de Rerefência
     */
    function DocumentoReferenciaDialogController($scope, $mdDialog, $log, $importService, $mdToast, entidadeExterna) {

        $scope.downloadIconFlag = false;

        if (entidadeExterna != null) {
            $scope.entidade = entidadeExterna;
            if (typeof entidadeExterna.anexo == 'string') {
                $scope.downloadIconFlag = true;
            }
            $scope.modoAlteracao = true;
        } else {
            $scope.entidade = {
                codigo: "",
                sigla: "",
                titulo: "",
                dataInicio: null,
                dataFim: null,
                anexo: null
            };
            $scope.modoAlteracao = false;
        }

        $scope.cancelar = function () {
            $mdDialog.cancel();
        };

        $scope.validaForm = function () {

            var mensagem = "Preencha todos os campos obrigatórios!";

            if ($scope.entidade.dataInicio > $scope.entidade.dataFim) {
                mensagem = "A data de início não pode ser maior que a data de fim";

                var toast = $mdToast.simple()
                    .content(mensagem)
                    .action('Fechar')
                    .highlightAction(false)
                    .position('top');

                $mdToast.show(toast);

                return false;
            }
            if (!$scope.documentoForm.$valid) {
                var toast = $mdToast.simple()
                    .content(mensagem)
                    .action('Fechar')
                    .highlightAction(false)
                    .position('top');

                $mdToast.show(toast);

                return false;
            } else {
                return true;
            }
        }

        $scope.vinculaArquivo = function (file) {
            $scope.entidade.anexo = file;
        }

        $scope.salvar = function () {
            if ($scope.validaForm()) {

                if (!$scope.modoAlteracao) {
                    documentoReferenciaService.insertDocumentoReferencia($scope.entidade, {
                        callback: function (result) {
                            $mdDialog.hide(result);
                            $scope.$apply();
                        },
                        errorHandler: function (message, error) {
                            var toast = $mdToast.simple()
                                .content(message)
                                .action('Fechar')
                                .highlightAction(false)
                                .position('top');

                            $mdToast.show(toast);
                        }
                    });
                } else {

                    $scope.entidade.anexo = typeof $scope.entidade.anexo === 'string' ? null : $scope.entidade.anexo;
                    documentoReferenciaService.updateDocumentoReferencia($scope.entidade, {
                        callback: function (result) {
                            $mdDialog.hide(result);
                            $scope.$apply();
                        },
                        errorHandler: function (message, error) {
                            var toast = $mdToast.simple()
                                .content(message)
                                .action('Fechar')
                                .highlightAction(false)
                                .position('top');

                            $mdToast.show(toast);
                        }
                    });
                }
            }
        };
    }

} (window.angular));