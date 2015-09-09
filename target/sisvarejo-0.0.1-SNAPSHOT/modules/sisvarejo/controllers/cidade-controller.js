(function (angular) {
    'use strict';


    angular.module('sisvarejo').controller('CidadeController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

        $importService("localizacaoService");

        /**
         * Injeta os métodos, atributos e seus cidades herdados de AbstractCRUDController.
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

        /**
         *
         * @param filter
         * @param pageRequest
         */
        $scope.carregarLista = function (filter, pageRequest) {
            localizacaoService.listCidades({
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
                controller: 'CidadeDialogController',
                templateUrl: './modules/sisvarejo/ui/localizacao/cidade/popup/popup-cidade.html',
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
                controller: 'CidadeDialogController',
                templateUrl: './modules/sisvarejo/ui/localizacao/cidade/popup/popup-cidade.html',
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
        $scope.excluirCidades = function (ev, lista) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Cidade')
                .content('Tem certeza que deseja excluir o(s) registros(s)? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Cidade')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            var listaCopia = angular.copy(lista);
            $mdDialog.show(confirm).then(function () {
                localizacaoService.removeCidades(lista, {
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
     * Controller da popup de Cidade
     */
    angular.module('sisvarejo').controller('CidadeDialogController', function ($scope, $mdDialog, $importService, $mdToast, $log, entidadeExterna) {

        $importService("localizacaoService");

        /**
         *
         */
        $scope.model = {
            nameFilter: '',
            estadoFilter: null,
            currentState: 'buscar-cidade',
            estado: {},
            pais: {},
            content: [],
            query: {
                order: 'nome'
            }
        }

        /**
         *
         */
        if (entidadeExterna != null) {
            $scope.entidade = entidadeExterna;
            $scope.modoAlteracao = true;
        } else {
            $scope.entidade = {};
            $scope.modoAlteracao = false;
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
        localizacaoService.listEstados({
            callback: function (result) {
                $scope.estados = result;
                $scope.$apply();
            },
            errorHandler: function (message, exception) {
                $mdToast.show($mdToast.simple()
                    .content(message)
                    .action('Fechar')
                    .highlightAction(false)
                    .position('top')).then(function () {
                });
            }
        })

        /**
         *
         */
        $scope.listPaises = function(){
            localizacaoService.listPaises({
                callback: function (result) {
                    $scope.paises = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $mdToast.show($mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('top')).then(function () {
                    });
                }
            })
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

        /**
         *
         */
        $scope.salvar = function () {
            if ($scope.validaForm()) {

                if (!$scope.modoAlteracao) {
                    localizacaoService.insertCidade($scope.entidade, {
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
                    localizacaoService.updateCidade($scope.entidade, {
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

        $scope.salvarEBuscar = function () {
            localizacaoService.insertCidade($scope.entidade, {
                callback: function (result) {
                    $scope.model.content.push(result);
                    $scope.model.currentState = 'buscar-cidade';
                    $mdToast.showSimple("Cidade salva com sucesso!");
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

        $scope.salvarEstado = function () {

            if ($scope.model.estado.nome.length == 0 ) {
                $mdToast.simpleSimple("Preencha os nomes.");
                return false;
            }

            if ($scope.model.estado.pais == null ) {
                $mdToast.simpleSimple("Escolha um país.");
                return false;
            }

            localizacaoService.insertEstado($scope.model.estado, {
                callback: function (result) {
                    $scope.escolherEstado(result);
                    $mdToast.showSimple("Estado salvo com sucesso!");
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

        $scope.salvarPais = function () {

            if ($scope.model.pais.nome.length == 0 ) {
                $mdToast.simpleSimple("Preencha os nomes.");
                return false;
            }

            localizacaoService.insertPais($scope.model.pais, {
                callback: function (result) {
                    $scope.escolherPais(result);
                    $mdToast.showSimple("País salvo com sucesso!");
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

        /**
         *
         */
        $scope.listCidadesByFilter = function () {
            localizacaoService.listCidadesByFilters($scope.model.nameFilter, $scope.model.estadoFilter, {
                callback: function (result) {
                    console.log(result);
                    $scope.model.content = result;
                    $scope.$apply();
                },
                errorHandler: function (message, errorHandler) {
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

        /**
         *
         */
        $scope.cadastrarCidade = function () {
            $scope.model.currentState = 'cadastrar-cidade';
            $scope.model.entidade = new Cidade();
        }

        /**
         *
         */
        $scope.cadastrarPais = function () {
            $scope.model.currentState = 'cadastrar-pais';
            $scope.model.pais = new Pais();
        }

        /**
         *
         */
        $scope.cadastrarEstado = function () {
            $scope.model.currentState = 'cadastrar-estado';
            $scope.model.estado = new Estado();
            $scope.listPaises();
        }

        /**
         *
         * @param estado
         */
        $scope.escolherEstado = function(estado) {
            $scope.model.entidade.estado = estado;
            if ($scope.estados.indexOf(estado) == -1) {
                $scope.estados.push(estado);
            }
            $scope.model.currentState = 'cadastrar-cidade';
        }

        /**
         *
         * @param pais
         */
        $scope.escolherPais = function(pais) {
            $scope.model.estado.pais = pais;
            if ($scope.paises.indexOf(pais) == -1) {
                $scope.paises.push(pais);
            }
            $scope.model.currentState = 'cadastrar-estado';
        }

        /**
         *
         * @param cidade
         */
        $scope.escolherCidade = function (cidade) {
            $mdDialog.hide(cidade);
        }
    });


}(window.angular));