(function (angular) {
    'use strict';

    angular.module('pdti').controller('PlanoDiretorController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $filter, $element, $mdMedia) {

        $importService("planoDiretorService");

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

        //STATES
        /**
         * Variável estática que representa
         * o estado de listagem de registros.
         */
        $scope.LIST_STATE = "plano-diretor.listar";
        /**
         * Variável estática que representa
         * o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "plano-diretor.detalhe";
        /**
         * Variável estática que representa
         * o estado para a criação de registros.
         */
        $scope.INSERT_STATE = "plano-diretor.criar";
        /**
         * Variável estática que representa
         * o estado para a edição de registros.
         */
        $scope.UPDATE_STATE = "plano-diretor.editar";
        /**
         * Variável estática que representa
         * o estado para a edição de registros.
         */
        $scope.SECTIONS_STATE = "plano-diretor.secoes";

        /**
         *
         */
        $scope.model = {
            content: [],
            form: {},
            itensExcluir: [],
            filters: {
                status: null
            }
        };

        /**
         *
         * @type {{}}
         */
        $scope.model = {
            dirtyForm: false,
            windowsHeight: {
                'height': '400px'
            },
            secaoCopia: null,
            filters: {
                statusDocumento: ''
            }
        }

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
        $scope.currentPage = {
            content: []
        };

        /**
         *
         */
        $scope.secaoAtual = null;

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

            $scope.currentState = $state.current.name;

            $scope.pageRequest = pageRequest;

            $scope.planoDiretorId = $state.params.id;

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
                case $scope.SECTIONS_STATE:
                    {
                        $scope.changeToSections($state.params.id);
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

            $scope.carregarLista($scope.pageRequest);
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

            $scope.currentEntity = new Object();

            $scope.entidade = {};
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

            planoDiretorService.findPlanoDiretorById(id, {
                callback: function (result) {
                    $scope.entidade = result;
                    $scope.currentState = $scope.UPDATE_STATE;
                    $state.go($scope.UPDATE_STATE);
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                }
            });
        };

        /**
         * Realiza os procedimentos iniciais (prepara o estado)
         * para a tela de detalhe e após isso, muda o estado para detail.
         * @see DETAIL_STATE
         * @see $stateChangeSuccess
         *
         * Para mudar para este estado, deve-se primeiro obter via id
         * o registro atualizado pelo serviço de consulta e só então mudar o estado da tela.
         * Caso o indentificador esteja inválido, retorna para o estado de listagem.
         */
        $scope.changeToDetail = function (id) {
            $log.info("changeToDetail", id);

            if (id == null || id == "" || id == 0) {
                $scope.currentState = $scope.LIST_STATE;
                $state.go($scope.LIST_STATE);
                return;
            }

            planoDiretorService.findPlanoDiretorById(id, {
                callback: function (result) {
                    // $scope.currentEntity = result;
                    $scope.entidade = result;
                    // $scope.currentState = $scope.UPDATE_STATE;
                    $scope.currentState = $scope.DETAIL_STATE;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                }
            });
        };

        /**
         *
         * @param id
         */
        $scope.changeToSections = function (id) {
            $log.info("changeToSections", id);

            if (id == null || id == "" || id == 0) {
                $scope.currentState = $scope.LIST_STATE;
                $state.go($scope.LIST_STATE);
                return;
            }

            planoDiretorService.listSecoesByPlanoDiretorId(id, {
                callback: function (result) {
                    var orderBy = $filter('orderBy');
                    if (result.length > 0) {
                        $scope.secoes = $scope.arrangeSectionsPositions(result, true);
                        $scope.secaoAtual = $scope.secoes[0];
                        $scope.model.secaoCopia = angular.copy($scope.secaoAtual);

                        if ($scope.isTabular($scope.secaoAtual)) {
                            $scope.listItensSecao($scope.secaoAtual);
                        }

                    } else {
                        $scope.secoes = [];
                    }
                    $scope.currentState = $scope.SECTIONS_STATE;

                    $state.go($scope.SECTIONS_STATE);
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $log.error(message);
                }
            });
        };

        /**
         *
         * @param filter
         * @param pageRequest
         */
        $scope.carregarLista = function (pageRequest) {
            planoDiretorService.listPlanosDiretores(null, null, pageRequest, {
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
        $scope.savePlanoDiretor = function () {
            planoDiretorService.insertNovoPlanoDiretor($scope.entidade, {
                callback: function (result) {
                    $scope.entidade = result;

                    var toast = $mdToast.simple()
                        .content('Informações do Plano Diretor foram salvas com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                    $state.go($scope.LIST_STATE);

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
                    $log.error(message);
                }
            })
        }

        /**
         *
         * @param secao
         * @param ev
         */
        $scope.selectSecao = function (secao, ev) {
            if (secao.id == $scope.secaoAtual.id) return false;
            if ($scope.model.dirtyForm) {
                var confirm = $mdDialog.confirm()
                    .parent(angular.element(document.body))
                    .title('Deseja salvar as alterações da seção?')
                    .content('As alterações serão descartadas caso não sejam salvas')
                    .ariaLabel('Salvar alterações de Seção')
                    .ok('Salvar')
                    .cancel('Descartar')
                    .targetEvent(ev);
                $mdDialog.show(confirm).then(function () {
                    $scope.salvarSecaoAtual(secao);
                }, function () {

                    $scope.secaoAtual.titulo = $scope.model.secaoCopia.titulo;
                    $scope.secaoAtual.conteudo = $scope.model.secaoCopia.conteudo;
                    $scope.secaoAtual.posConteudo = $scope.model.secaoCopia.posConteudo;


                    $scope.secaoAtual = secao;
                    $scope.model.secaoCopia = angular.copy(secao);
                    $scope.model.dirtyForm = false;
                    //$scope.recarregarSecaoAtual(secao);

                });
            } else {
                $scope.secaoAtual = secao;
                $scope.model.secaoCopia = angular.copy(secao);
            }

            $scope.itensDocumento = [];

            if (secao.tipoSecao != 'TEXTO') {
                $scope.listItensSecao(secao);
            }
        }

        /**
         *
         * @param secaoASelecionar
         */
        $scope.recarregarSecaoAtual = function (secaoASelecionar) {
            planoDiretorService.findSecaoById($scope.secaoAtual.id, {
                callback: function (result) {
                    $scope.secaoAtual = secaoASelecionar != null ? secaoASelecionar : result;
                    $scope.model.dirtyForm = false;

                    for (var i = 0; $scope.secoes.length > i; i++) {
                        if ($scope.secoes[i].id == result.id) {
                            $scope.secoes[i] = result;
                        }
                    }
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
        $scope.listItensSecao = function (secao) {
            // Função de callback para o método assíncrono do DWR
            var callbackFn = function (result) {
                $scope.itensDocumento = result;
                $scope.$apply();
            }

            var errorHandlerFn = function (message, error) {
                var toast = $mdToast.simple()
                    .content(message)
                    .action('Fechar')
                    .highlightAction(false)
                    .position('bottom right');
                $mdToast.show(toast).then(function () {
                });
                $log.error(error);
            }

            if (secao.tipoSecao == 'ANALISE_SWOT') {
                planoDiretorService.listItensAnaliseSwotBySecaoId(secao.id, {
                    callback: callbackFn,
                    errorHandler: errorHandlerFn
                });
            } else if (secao.tipoSecao == 'ESTRATEGIA') {
                planoDiretorService.listItensEstrategiaBySecaoId(secao.id, {
                    callback: callbackFn,
                    errorHandler: errorHandlerFn
                });
            } else if (secao.tipoSecao == 'DOCUMENTO_REFERENCIA') {
                planoDiretorService.listItensDocumentoReferenciaBySecaoId(secao.id, {
                    callback: callbackFn,
                    errorHandler: errorHandlerFn
                });
            } else if (secao.tipoSecao == 'TERMO_ABREVIACAO') {
                planoDiretorService.listItensTermoAbreviacaoBySecaoId(secao.id, {
                    callback: callbackFn,
                    errorHandler: errorHandlerFn
                });
            } else if (secao.tipoSecao == 'PRINCIPIO_DIRETROZ') {
                planoDiretorService.listItensPrincipioDiretrizBySecaoId(secao.id, {
                    callback: callbackFn,
                    errorHandler: errorHandlerFn
                });
            }
        };

        /**
         *
         * @param ev
         */
        $scope.abrirPopupNovaSecao = function (ev) {
            $mdDialog.show({
                controller: 'AddSectionPopupController',
                templateUrl: './modules/pdti/ui/plano-diretor/popup/popup-adicionar-secao.html',
                targetEvent: ev,
                locals: {
                    planoDiretorId: $scope.planoDiretorId
                }
            })
                .then(function (result) {
                    $scope.secoes.push(result);
                    if ($scope.secaoAtual == undefined) $scope.secaoAtual = result;
                    //$scope.secoes = $scope.arrangeSectionsPositions();
                }, function () {
                });

        }

        /**
         *
         * @param secaoASelecionar
         */
        $scope.salvarSecaoAtual = function (secaoASelecionar) {
            planoDiretorService.updateSecao($scope.secaoAtual, {
                callback: function (result) {
                    $scope.secaoAtual = secaoASelecionar != null ? secaoASelecionar : result;
                    $scope.model.secaoCopia = angular.copy(result);
                    $scope.model.dirtyForm = false;

                    var toast = $mdToast.simple()
                        .content('Seção salva com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom right');
                    $mdToast.show(toast).then(function () {
                    });
                    $scope.$apply();
                },
                errorHandler: function (message, error) {
                    var toast = $mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom right');
                    $mdToast.show(toast).then(function () {
                    });
                }
            });
        }


        /**
         *
         * @param ev
         */
        //         $scope.abrirPopupNovaEntidade = function (ev) {
        //             $mdDialog.show({
        //                 controller: PlanoDiretorDialogController,
        //                 templateUrl: './modules/pdti/ui/plano-diretor/popup/popup-plano-diretor.html',
        //                 targetEvent: ev,
        //                 locals: {
        //                     entidadeExterna: null
        //                 }
        //             })
        //                 .then(function (result) {
        // 
        //                     $scope.currentPage.content.push(result);
        // 
        //                     var toast = $mdToast.simple()
        //                         .content('Registro salvo com sucesso!')
        //                         .action('Fechar')
        //                         .highlightAction(false)
        //                         .position('bottom left right');
        //                     $mdToast.show(toast).then(function () {
        //                     });
        // 
        //                 }, function () {
        //                 });
        //         }

        /**
         *
         * @param ev
         * @param entidade
         */
        //         $scope.abrirPopupAlterarEntidade = function (ev, entidade) {
        //             $mdDialog.show({
        //                 controller: PlanoDiretorDialogController,
        //                 templateUrl: './modules/pdti/ui/plano-diretor/popup/popup-plano-diretor.html',
        //                 targetEvent: ev,
        //                 hasBackdrop: true,
        //                 bindToController: true,
        //                 locals: {
        //                     entidadeExterna: angular.copy(entidade)
        //                 }
        //             })
        //                 .then(function (result) {
        // 
        //                     var toast = $mdToast.simple()
        //                         .content('Registro atualizado com sucesso!')
        //                         .action('Fechar')
        //                         .highlightAction(false)
        //                         .position('bottom left right');
        //                     $mdToast.show(toast).then(function () {
        //                     });
        // 
        //                     var i = $scope.findByIdInArray($scope.currentPage.content, result);
        //                     $scope.currentPage.content[i] = result;
        // 
        //                 }, function () {
        //                     //tratar o "cancelar" da popup
        //                 });
        //         }

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
         * @param lista
         */
        $scope.excluirSecao = function (ev) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Seção')
                .content('Tem certeza que deseja excluir o registro? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Seção')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            $mdDialog.show(confirm).then(function () {
                planoDiretorService.removeSecao($scope.secaoAtual, {
                    callback: function () {
                        var toast = $mdToast.simple()
                            .content('Seção excluída com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom right');
                        $mdToast.show(toast).then(function () {
                        });

                        $scope.model.dirtyForm = false;

                        for (var x = 0; x < $scope.secoes.length; x++) {
                            var i = $scope.findByIdInArray($scope.secoes, $scope.secaoAtual);
                            if (i > -1) {
                                $scope.secaoAtual = $scope.secoes[i - 1];
                                $scope.model.secaoCopia = angular.copy($scope.secaoAtual);
                                $scope.secoes.splice(i, 1);
                                break;
                            }
                        }
                        if ($scope.secoes.length > 0) {
                            $scope.secoes = $scope.arrangeSectionsPositions($scope.secoes, true);
                            $scope.atualizarOrdemSecoes($scope.secoes);
                        }
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        $log.error("Erro ao excluir registro(s)", message);
                    }
                })
            }, function () {
            });
        };

        /**
         *
         * @param secoes
         */
        $scope.atualizarOrdemSecoes = function (secoes) {
            planoDiretorService.updateSecoes(secoes, {
                callback: function (result) {
                    $scope.secoes = $scope.arrangeSectionsPositions(result, false);
                    $scope.$apply();
                }, errorHandler: function (message, error) {
                    var toast = $mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom right');
                    $mdToast.show(toast).then(function () {
                    });
                }

            })
        };

        /**
         *
         * @param ev
         * @param lista
         */
        $scope.excluirPlanoDiretor = function (ev, lista) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Plano Diretor de TI')
                .content('Tem certeza que deseja excluir o(s) registros(s)? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Plano Diretor de TI')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            var listaCopia = angular.copy(lista);
            $mdDialog.show(confirm).then(function () {
                planoDiretorService.removePlanoDiretor(lista, {
                    callback: function (result) {
                        var toast = $mdToast.simple()
                            .content('Registro(s) excluído(s) com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom right');
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
                        var toast = $mdToast.simple()
                            .content(message)
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom right');
                        $mdToast.show(toast).then(function () {
                        });
                    }
                })
            }, function () {
                });
        };

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
        };

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
         * @param item
         */
        $scope.itemClicked = function (item) {
            $scope.abrirPopupAlterarEntidade(null, item);
        };

        /**
         *
         * @param secoes
         * @returns {Array}
         */
        $scope.arrangeSectionsPositions = function (secoes, changePositions) {

            if (secoes == null || secoes.length == 0) return secoes;

            var fathersSections = [];
            var childrenSections = [];

            for (var i = 0; i < secoes.length; i++) {
                if (secoes[i].secaoSuperior == null) {
                    fathersSections.push(angular.copy(secoes[i]));
                }
            }

            fathersSections = $filter('orderBy')(fathersSections, 'posicao');

            if (changePositions == true) {
                for (var i = 1; i <= fathersSections.length; i++) {
                    fathersSections[i - 1].posicao = i;
                }
            }

            for (i = 0; i < secoes.length; i++) {
                if (secoes[i].secaoSuperior != null) {
                    childrenSections.push(angular.copy(secoes[i]));
                }
            }

            childrenSections = $filter('orderBy')(childrenSections, 'posicao');

            var arrangedSections = [];
            var arrayAux = null;

            for (i = 0; i < fathersSections.length; i++) {
                arrayAux = [];
                arrayAux.push(fathersSections[i]);
                for (var x = 0; childrenSections.length > x; x++) {
                    if (childrenSections[x].secaoSuperior.id == fathersSections[i].id) {
                        arrayAux.push(childrenSections[x]);
                    }
                }
                if (changePositions == true) {
                    var position = 1;
                    for (var y = 0; y < arrayAux.length; y++) {
                        if (arrayAux[y].secaoSuperior != null) {
                            arrayAux[y].posicao = position;
                            position++;
                        }
                    }
                }
                arrangedSections = arrangedSections.concat(arrayAux);
            }

            return arrangedSections;
        };

        /**
         *
         * @param secao
         * @returns {boolean}
         */
        $scope.isFatherSection = function (secao) {
            if ($scope.secoes == undefined) return false;
            for (var i = 0; $scope.secoes.length > i; i++) {
                if ($scope.secoes[i].secaoSuperior != null && $scope.secoes[i].secaoSuperior.id == secao.id) {
                    return true;
                }
            }
            return false
        };

        /**
         *
         * @param secao
         * @returns {*}
         */
        $scope.subirSecao = function (secao) {
            var subirSecao = function (secao) {

                if (secao.posicao == 1) return false;

                var x = $scope.findByIdInArray($scope.secoes, secao);

                if (x > -1) {
                    var i = x - 1;
                    if ($scope.secoes[i].secaoSuperior != null && $scope.secoes[x].secaoSuperior == null) {
                        for (i; i > 0; i--) {
                            if ($scope.secoes[i].secaoSuperior == null) break;
                        }
                    }
                    $scope.secoes[i].posicao++;
                    $scope.secoes[x].posicao--;
                    $scope.secaoAtual = $scope.secoes[x];
                }

                $scope.atualizarOrdemSecoes($scope.arrangeSectionsPositions($scope.secoes, false));
            }

            if ($scope.model.dirtyForm == true) {
                var confirm = $mdDialog.confirm()
                    .parent(angular.element(document.body))
                    .title('Deseja salvar as alterações da seção?')
                    .content('As alterações serão descartadas caso não sejam salvas')
                    .ariaLabel('Salvar alterações de Seção')
                    .ok('Salvar')
                    .cancel('Descartar')
                    .targetEvent(null);
                return $mdDialog.show(confirm).then(function () {
                    subirSecao(secao);
                }, function () {
                    $scope.secaoAtual.titulo = $scope.model.secaoCopia.titulo;
                    $scope.secaoAtual.conteudo = $scope.model.secaoCopia.conteudo;
                    $scope.secaoAtual.posConteudo = $scope.model.secaoCopia.posConteudo;

                    $scope.model.dirtyForm = false;

                    subirSecao(secao);
                });
            } else {
                subirSecao(secao);
            }
        }

        /**
         *
         * @param secao
         * @returns {boolean}
         */
        $scope.isAbleToDescend = function (secao) {

            if (secao == null || secao == undefined) return false;

            var x = $scope.findByIdInArray($scope.secoes, secao);
            if ($scope.secoes[x + 1] == null || ($scope.secoes[x].secaoSuperior != null && $scope.secoes[x + 1].secaoSuperior == null)) {
                return false;
            } else if (($scope.secoes[x].secaoSuperior == null && $scope.secoes[x + 1].secaoSuperior != null)) {
                var i = x + 1;
                for (i; i > 0; i++) {
                    if ($scope.secoes[i] == undefined) {
                        return false;
                    }
                    if ($scope.secoes[i].secaoSuperior == null) {
                        return true;
                    }
                }
            }

            return true;
        }

        /**
         *
         * @param secao
         * @returns {*}
         */
        $scope.descerSecao = function (secao) {
            var subirSecao = function (secao) {

                if (!$scope.isAbleToDescend(secao)) return false;

                var x = $scope.findByIdInArray($scope.secoes, secao);
                if (x > -1) {
                    var i = x + 1;
                    if ($scope.secoes[i].secaoSuperior != null && $scope.secoes[x].secaoSuperior == null) {
                        for (i; i > 0; i++) {
                            if ($scope.secoes[i].secaoSuperior == null) break;
                        }
                    }
                    $scope.secoes[i].posicao--;
                    $scope.secoes[x].posicao++;
                    $scope.secaoAtual = $scope.secoes[x];
                }

                $scope.atualizarOrdemSecoes($scope.arrangeSectionsPositions($scope.secoes, false));
            }

            if ($scope.model.dirtyForm == true) {
                var confirm = $mdDialog.confirm()
                    .parent(angular.element(document.body))
                    .title('Deseja salvar as alterações da seção?')
                    .content('As alterações serão descartadas caso não sejam salvas')
                    .ariaLabel('Salvar alterações de Seção')
                    .ok('Salvar')
                    .cancel('Descartar')
                    .targetEvent(null);
                return $mdDialog.show(confirm).then(function () {
                    subirSecao(secao);
                }, function () {
                    $scope.secaoAtual.titulo = $scope.model.secaoCopia.titulo;
                    $scope.secaoAtual.conteudo = $scope.model.secaoCopia.conteudo;
                    $scope.secaoAtual.posConteudo = $scope.model.secaoCopia.posConteudo;

                    $scope.model.dirtyForm = false;

                    subirSecao(secao);
                });
            } else {
                subirSecao(secao);
            }
        }

        /**
         *
         * @param secao
         */
        $scope.indentSecaoToRight = function (secao) {

            var indentRight = function (secao) {
                var x = $scope.findByIdInArray($scope.secoes, secao);
                if (x > -1) {
                    $scope.secoes[x].posicao = $scope.secoes[x - 1].secaoSuperior != null ? $scope.secoes[x - 1].posicao + 1 : 1;
                    $scope.secoes[x].secaoSuperior = $scope.secoes[x - 1].secaoSuperior != null ? $scope.secoes[x - 1].secaoSuperior : $scope.secoes[x - 1];
                    $scope.secaoAtual = $scope.secoes[x];
                    for (x = x + 1; x < $scope.secoes.length; x++) {
                        if ($scope.secoes[x].secaoSuperior == null) $scope.secoes[x].posicao--;
                    }
                }
                $scope.atualizarOrdemSecoes($scope.secoes);
            }

            if ($scope.model.dirtyForm == true) {
                var confirm = $mdDialog.confirm()
                    .parent(angular.element(document.body))
                    .title('Deseja salvar as alterações da seção?')
                    .content('As alterações serão descartadas caso não sejam salvas')
                    .ariaLabel('Salvar alterações de Seção')
                    .ok('Salvar')
                    .cancel('Descartar')
                    .targetEvent(null);
                return $mdDialog.show(confirm).then(function () {
                    indentRight(secao);
                }, function () {
                    $scope.secaoAtual.titulo = $scope.model.secaoCopia.titulo;
                    $scope.secaoAtual.conteudo = $scope.model.secaoCopia.conteudo;
                    $scope.secaoAtual.posConteudo = $scope.model.secaoCopia.posConteudo;

                    $scope.model.dirtyForm = false;

                    indentRight(secao);
                });
            } else {
                indentRight(secao);
            }
        };

        /**
         *
         * @param secao
         */
        $scope.indentSecaoToLeft = function (secao) {

            var indentLeft = function (secao) {
                var x = $scope.findByIdInArray($scope.secoes, secao);
                if (x > -1) {
                    $scope.secoes[x].posicao = $scope.secoes[x].secaoSuperior.posicao + 1;
                    var secaoSuperiorId = $scope.secoes[x].secaoSuperior.id;
                    $scope.secoes[x].secaoSuperior = null;
                    $scope.secaoAtual = $scope.secoes[x];
                    for (x = x + 1; x < $scope.secoes.length; x++) {
                        if ($scope.secoes[x].secaoSuperior != null && $scope.secoes[x].secaoSuperior.id == secaoSuperiorId) {
                            $scope.secoes[x].posicao--;
                        } else if ($scope.secoes[x].secaoSuperior == null) {
                            $scope.secoes[x].posicao++;
                        }
                    }
                }
                $scope.atualizarOrdemSecoes($scope.secoes);
            }
            if ($scope.model.dirtyForm == true) {
                var confirm = $mdDialog.confirm()
                    .parent(angular.element(document.body))
                    .title('Deseja salvar as alterações da seção?')
                    .content('As alterações serão descartadas caso não sejam salvas')
                    .ariaLabel('Salvar alterações de Seção')
                    .ok('Salvar')
                    .cancel('Descartar')
                    .targetEvent(null);
                return $mdDialog.show(confirm).then(function () {
                    indentLeft(secao);
                }, function () {
                    $scope.secaoAtual.titulo = $scope.model.secaoCopia.titulo;
                    $scope.secaoAtual.conteudo = $scope.model.secaoCopia.conteudo;
                    $scope.secaoAtual.posConteudo = $scope.model.secaoCopia.posConteudo;

                    $scope.model.dirtyForm = false;

                    indentLeft(secao);
                });
            } else {
                indentLeft(secao);
            }
        };

        /**
         *
         * @param secao
         */
        $scope.attachRegistersToSection = function (secao, ev) {
            $mdDialog.show({
                controller: 'AssociarItensPopupController',
                templateUrl: './modules/pdti/ui/plano-diretor/popup/popup-associar-itens-documento.html',
                targetEvent: ev,
                locals: {
                    secao: secao,
                    itensExistentes: $scope.itensDocumento
                }
            }).then(function (result) {

                $scope.itensDocumento = $scope.itensDocumento.concat(result);

            }, function () {
            });
        };

        /**
         *
         */
        $scope.removerItemDaSecao = function () {

        }

        /**
         *
         * @param secao
         * @returns {boolean}
         */
        $scope.isTabular = function (secao) {
            if (secao != null && secao.tipoSecao != 'ESTRATEGIA' && secao.tipoSecao != 'DOCUMENTO_REFERENCIA' && secao.tipoSecao != 'PRINCIPIO_DIRETRIZ' && secao.tipoSecao != 'ANALISE_SWOT' && secao.tipoSecao != 'TERMO_ABREVIACAO') {
                return false;
            }
            return true;
        }

        $scope.cancelar = function () {
            $mdDialog.cancel();
        };

        $scope.alterarPlanoDiretor = function (entidade) {
            planoDiretorService.updatePlanoDiretor(entidade, {
                callback: function (result) {
                    var toast = $mdToast.simple()
                        .content('Registro atualizado com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                    $scope.entidade = result;

                    $state.go($scope.LIST_STATE);
                    // $state.go($scope.DETAIL_STATE);
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
        $scope.abrirPopupAssociarColaborador = function (ev) {
            $mdDialog.show({
                controller: ColaboradorDialogController,
                templateUrl: './modules/pdti/ui/plano-diretor/popup/popup-associar-colaborador.html',
                targetEvent: ev,
                locals: {
                    planoDiretor: $scope.entidade,
                    listaColaboradorAOcultar: $scope.colaboradoresAssociados
                }
            })
                .then(function (result) {

                $scope.colaboradoresAssociados = $scope.colaboradoresAssociados.concat(result);

            }, function () { });
        };


        /*-------------------------------------------------------------------
         * 		 				 	  HANDLERS
         *-------------------------------------------------------------------*/
        /**
         *
         */
        $scope.triggerChangeHandler = function () {
            $scope.model.dirtyForm = true;
        }

        /**
         *
         */
        $scope.calculateWindowSize = function () {
            var sectionsWindow = angular.element(document.querySelector('#sectionsWindow'));
            if (sectionsWindow != null) {
                var windowHeight = window.innerHeight;
                var height = windowHeight - sectionsWindow.offset().top + 'px';
                sectionsWindow.css('height', height);
            }
        }

        /**
         *
         */
        angular.element(window).on('resize', $scope.calculateWindowSize);

        /**
         *
         */
        $element.on('$destroy', function () {
            angular.element(window).off('resize', $scope.calculateWindowSize);
        })

    });

    /**
     * Controller da popup de Plano Diretor de TI
     */
    angular.module('pdti').controller('PlanoDiretorDialogController', function ($scope, $mdDialog, $importService, $mdToast, $log, entidadeExterna) {

        $importService("planoDiretorService");

        /**
         *
         */
        $scope.entidade = entidadeExterna != null ? entidadeExterna : new PlanoDiretor();

        /**
         *
         */
        $scope.cancelar = function () {
            $mdDialog.cancel();
        };

        /**
         *
         */
        $scope.itemClicked = function (item) {
            $state.go('plano-diretor.editar', { id: item.id });
        }

            $scope.alterarPlanoDiretor = function (entidade) {
                 planoDiretorService.updatePlanoDiretor(entidade, {
                       callback: function (result) {

                    var toast = $mdToast.simple()
                        .content('Registro atualizado com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom right');
                    $mdToast.show(toast).then(function () {
                    });

                    $scope.$apply();

                    $mdDialog.hide(result);
                }, errorHandler: function (message, exception) {
                    $log.error(message);
                }
            });
        }
    });

    /**
     * Controller da popup de adição de seções
     */
    angular.module('pdti').controller('AddSectionPopupController', function ($scope, $mdDialog, $importService, $mdToast, planoDiretorId) {
        $importService('planoDiretorService');

        /**
         *
         * @type {{entidade: Secao}}
         */
        $scope.model = {
            entidade: new Secao()
        }

        $scope.model.entidade.planoDiretor = new PlanoDiretor();
        $scope.model.entidade.planoDiretor.id = planoDiretorId;

        /**
         *
         */
        $scope.salvar = function () {
            planoDiretorService.insertSecao($scope.model.entidade, {
                callback: function (result) {
                    var toast = $mdToast.simple()
                        .content('Seção cadastrada com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom right');
                    $mdToast.show(toast).then(function () {
                    });

                    $mdDialog.hide(result);
                },
                errorHandler: function (message, error) {
                    var toast = $mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom right');
                    $mdToast.show(toast).then(function () {
                    });
                }
            })
        }

        $scope.cancelar = function () {
            $mdDialog.cancel();
        };

        /**
         *
         */
        $scope.itemClicked = function (item) {
            console.log("clicou no item");
        }

    });

    /**
     * Controller da popup de associação entre seções e itens de documento.
     */
    angular.module('pdti').controller('AssociarItensPopupController', function ($scope, $mdDialog, $importService, $mdToast, secao, itensExistentes) {

            /**
             *
             */
            $importService('principioDiretrizService');
            $importService('documentoReferenciaService');
            $importService('itemSwotService');
            $importService('estrategiaService');
            $importService('termoAbreviacaoService');
            $importService('planoDiretorService');

            /**
             *
             * @type {{entidade: Secao}}
             */
            $scope.model = {
                content: [],
                secao: secao,
                pageRequest: new PageRequest(),
                tipoSecao: secao.tipoSecao,
                selectedItens: [],
                itensExistentes: itensExistentes
            }

            /**
             *
             */
            $scope.salvar = function () {
                if (secao.tipoSecao == 'ANALISE_SWOT') {
                    var listaItensAnaliseSwot = []
                    for (var i = 0; i < $scope.model.selectedItens.length; i++) {
                        var itemAnaliseSwot = new ItemAnaliseSwot();
                        itemAnaliseSwot.secao = $scope.model.secao;
                        itemAnaliseSwot.itemSwot = $scope.model.selectedItens[i];
                        itemAnaliseSwot.codigo = $scope.model.selectedItens[i].codigo;
                        listaItensAnaliseSwot.push(itemAnaliseSwot);
                    }

                    planoDiretorService.insertItemAnaliseSwot(listaItensAnaliseSwot, {
                        callback: function (result) {
                            $mdDialog.hide(result);
                        },
                        errorHandler: function (message, error) {
                            var toast = $mdToast.simple()
                                .content(message)
                                .action('Fechar')
                                .highlightAction(false)
                                .position('bottom right');
                            $mdToast.show(toast).then(function () {
                            });
                        }
                    })
                } else if (secao.tipoSecao == 'ESTRATEGIA') {
                    var listaItensEstrategia = []
                    for (var i = 0; i < $scope.model.selectedItens.length; i++) {
                        var itemEstrategia = new ItemEstrategia();
                        itemEstrategia.secao = $scope.model.secao;
                        itemEstrategia.estrategia = $scope.model.selectedItens[i];
                        itemEstrategia.codigo = $scope.model.selectedItens[i].codigo;
                        listaItensEstrategia.push(itemEstrategia);
                    }

                    planoDiretorService.insertItemEstrategia(listaItensEstrategia, {
                        callback: function (result) {
                            $mdDialog.hide(result);
                        },
                        errorHandler: function (message, error) {
                            var toast = $mdToast.simple()
                                .content(message)
                                .action('Fechar')
                                .highlightAction(false)
                                .position('bottom right');
                            $mdToast.show(toast).then(function () {
                            });
                        }
                    })
                } else if (secao.tipoSecao == 'PRINCIPIO_DIRETRIZ') {
                    var listaItensPrincipioDiretriz = []
                    for (var i = 0; i < $scope.model.selectedItens.length; i++) {
                        var itemPrincipioDiretriz = new ItemPrincipioDiretriz();
                        itemPrincipioDiretriz.secao = $scope.model.secao;
                        itemPrincipioDiretriz.itemSwot = $scope.model.selectedItens[i];
                        itemPrincipioDiretriz.principioDiretriz = $scope.model.selectedItens[i].codigo;
                        listaItensPrincipioDiretriz.push(itemPrincipioDiretriz);
                    }

                    planoDiretorService.insertItemPrincipioDiretriz(listaItensPrincipioDiretriz, {
                        callback: function (result) {
                            $mdDialog.hide(result);
                        },
                        errorHandler: function (message, error) {
                            var toast = $mdToast.simple()
                                .content(message)
                                .action('Fechar')
                                .highlightAction(false)
                                .position('bottom right');
                            $mdToast.show(toast).then(function () {
                            });
                        }
                    })
                } else if (secao.tipoSecao == 'DOCUMENTO_REFERENCIA') {
                    var listaItensDocumentoReferencia = []
                    for (var i = 0; i < $scope.model.selectedItens.length; i++) {
                        var itemDocumentoReferencia = new ItemDocumentoReferencia();
                        itemDocumentoReferencia.secao = $scope.model.secao;
                        itemDocumentoReferencia.documentoReferencia = $scope.model.selectedItens[i];
                        itemDocumentoReferencia.codigo = $scope.model.selectedItens[i].codigo;
                        listaItensDocumentoReferencia.push(itemDocumentoReferencia);
                    }

                    planoDiretorService.insertItemDocumentoReferencia(listaItensDocumentoReferencia, {
                        callback: function (result) {
                            $mdDialog.hide(result);
                        },
                        errorHandler: function (message, error) {
                            var toast = $mdToast.simple()
                                .content(message)
                                .action('Fechar')
                                .highlightAction(false)
                                .position('bottom right');
                            $mdToast.show(toast).then(function () {
                            });
                        }
                    })
                } else if (secao.tipoSecao == 'TERMO_ABREVIACAO') {
                    var listaItensTermoAbreviacao = []
                    for (var i = 0; i < $scope.model.selectedItens.length; i++) {
                        var itemTermoAbreviacao = new ItemTermoAbreviacao();
                        itemTermoAbreviacao.secao = $scope.model.secao;
                        itemTermoAbreviacao.termoAbreviacao = $scope.model.selectedItens[i];
                        listaItensTermoAbreviacao.push(itemTermoAbreviacao);
                    }

                    planoDiretorService.insertItemTermoAbreviacao(listaItensTermoAbreviacao, {
                        callback: function (result) {
                            $mdDialog.hide(result);
                        },
                        errorHandler: function (message, error) {
                            var toast = $mdToast.simple()
                                .content(message)
                                .action('Fechar')
                                .highlightAction(false)
                                .position('bottom right');
                            $mdToast.show(toast).then(function () {
                            });
                        }
                    })
                }
            };

            var filtrarRegistrosJaCadastrados = function (registros, existentes, campoComparacao) {

                var posicoesEliminar = [];

                for (var i = 0; i < existentes.length; i++) {
                    for (var y = 0; y < registros.length; y++) {
                        if (existentes[i][campoComparacao].id == registros[y].id) {
                            posicoesEliminar.push(y);
                        }
                    }
                }

                if (posicoesEliminar.length > 0) {
                    for (y = posicoesEliminar.length - 1; y > -1; y--) {
                        registros.splice(posicoesEliminar[y], 1);
                    }
                }

                return registros;
            };

            /**
             *
             * @param selectedItens
             */
            $scope.selectionUpdate = function (selectedItens) {
                $scope.model.selectedItens = selectedItens;
            }

            if (secao.tipoSecao == 'PRINCIPIO_DIRETRIZ') {

                principioDiretrizService.listPrincipiosDiretrizesByFilters(null, $scope.pageRequest, {
                    callback: function (result) {
                        $scope.model.content = filtrarRegistrosJaCadastrados(result.content, $scope.model.itensExistentes, 'principioDiretriz');
                    },
                    errorHandler: function (message, error) {
                        var toast = $mdToast.simple()
                            .content(message)
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom right');
                        $mdToast.show(toast).then(function () {
                        });
                    }
                });

            } else if (secao.tipoSecao == 'DOCUMENTO_REFERENCIA') {
                documentoReferenciaService.listDocumentosReferenciaByFilters(null, false, $scope.pageRequest, {
                    callback: function (result) {
                        $scope.model.content = filtrarRegistrosJaCadastrados(result.content, $scope.model.itensExistentes, 'documentoReferencia');
                    },
                    errorHandler: function (message, error) {
                        var toast = $mdToast.simple()
                            .content(message)
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom right');
                        $mdToast.show(toast).then(function () {
                        });
                    }
                })
            } else if (secao.tipoSecao == 'ANALISE_SWOT') {
                itemSwotService.listItemSwotByFilters(null, $scope.pageRequest, {
                    callback: function (result) {
                        $scope.model.content = filtrarRegistrosJaCadastrados(result.content, $scope.model.itensExistentes, 'itemSwot');
                    },
                    errorHandler: function (message, error) {
                        var toast = $mdToast.simple()
                            .content(message)
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom right');
                        $mdToast.show(toast).then(function () {
                        });
                    }
                })
            } else if (secao.tipoSecao == 'ESTRATEGIA') {
                estrategiaService.listEstrategiasByFilters(null, $scope.pageRequest, {
                    callback: function (result) {
                        $scope.model.content = filtrarRegistrosJaCadastrados(result.content, $scope.model.itensExistentes, 'estrategia');
                    },
                    errorHandler: function (message, error) {
                        var toast = $mdToast.simple()
                            .content(message)
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom right');
                        $mdToast.show(toast).then(function () {
                        });
                    }
                })
            } else if (secao.tipoSecao == 'TERMO_ABREVIACAO') {
                termoAbreviacaoService.listTermoAbreviacaoByFilters(null, $scope.pageRequest, {
                    callback: function (result) {
                        $scope.model.content = filtrarRegistrosJaCadastrados(result.content, $scope.model.itensExistentes, 'termoAbreviacao');
                    },
                    errorHandler: function (message, error) {
                        var toast = $mdToast.simple()
                            .content(message)
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom right');
                        $mdToast.show(toast).then(function () {
                        });
                    }
                })
            }

            /**
             *
             */
            $scope.cancelar = function () {
                $mdDialog.cancel();
            }
        }
    );

    function ColaboradorDialogController($scope, $mdDialog) {

        /**
         *
         */
        $scope.cancelar = function () {
            $mdDialog.cancel();
        };

        /**
         *
         */
        $scope.itemClicked = function (item) {
            console.log("clicou no item");
        }

    }


} (window.angular));
