(function (angular) {
    'use strict';

    /**
     *
     */
    angular.module('pdti')
        .controller('NecessidadeTIController', function ($scope, $rootScope, $mdToast, $log, $injector, $importService, $mdDialog, $state, $mdBottomSheet) {

        $importService("necessidadeService");
        $importService("criterioPriorizacaoService");
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
        $scope.LIST_STATE = "necessidade-ti.listar";
        /**
         * Variável estática que representa
         * o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "necessidade-ti.detalhe";
        /**
         * Variável estática que representa
         * o estado para a criação de registros.
         */
        $scope.INSERT_STATE = "necessidade-ti.criar";
        /**
         * Variável estática que representa
         * o estado para a edição de registros.
         */
        $scope.UPDATE_STATE = "necessidade-ti.editar";

        /**
         *
         */
        $scope.model = {
            content: [],
            itemChecked: false,
            planoDiretorId: $state.params.planoDiretorId
        };

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
         * 
         */
        $scope.changeTags = function(tags) {
            // $scope.tags = tags;
            // $scope.carregarLista( $scope.tagsString , $scope.pageRequest);
        }

        /**
         * 
         */
        $scope.$watch('data.tagsString', function(newValue){
            $scope.carregarLista( newValue , $scope.pdtiId, $scope.pageRequest);
        });

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

            $scope.carregarLista(null, $scope.pdtiId, $scope.pageRequest);
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

            $scope.carregarListaCriterioPriorizacao();
            $scope.carregarListaDocumentos();


            $scope.entidade = {};
            $scope.entidade.planoDiretor = new PlanoDiretor();
            $scope.entidade.planoDiretor.id = $scope.model.planoDiretorId;

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
            
            necessidadeService.findNecessidadeById(id, {
                callback: function (result) {
                    $scope.entidade = result;
                    $scope.carregarListaOrganizacoesAssociadas(result.id);
                    $scope.carregarListaDocumentos();
                    $scope.carregarListaCriterioPriorizacao();
                    $scope.populaListaNecessidadesRelacionadas(result);
                    $scope.currentState = $scope.UPDATE_STATE;
                    $state.go($scope.UPDATE_STATE);
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                }
            });
        };

        /**
         * 
         */
        $scope.removerNecessidadeOrganizacaoMilitar = function (row) {
            necessidadeService.removeNecessidadeOrganizacaoMilitar(row, {
                callback: function () {
                    var i = $scope.organizacoesAssociadas.indexOf(row);
                    $scope.organizacoesAssociadas.splice(i, 1);
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                }
            })
        }
        
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

            necessidadeService.findNecessidadeById(id, {
                callback: function (result) {
                    $scope.entidade = result;
                    $scope.populaListaNecessidadesRelacionadas(result);
                    $scope.carregarListaOrganizacoesAssociadas(result.id);
                    $scope.currentState = $scope.DETAIL_STATE;
                    $scope.carregarListaCriterioPriorizacao();
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                }
            });
        };

        /**
         *
         */
        $scope.carregarLista = function (filter, pdtiId, pageRequest) {
            necessidadeService.listNecessidadesByFilter(filter, pdtiId, pageRequest, {
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
        $scope.populaListaNecessidadesRelacionadas = function (entidade) {
            $scope.necessidadesRelacionadas = [];

            for (var i = 0; i < entidade.itensEstrategia.length; i++) {
                entidade.itensEstrategia[i].tipo = 'ESTRATEGIA';
                $scope.necessidadesRelacionadas.push(entidade.itensEstrategia[i]);
            }

            for (i = 0; i < entidade.itensPrincipioDiretriz.length; i++) {
                entidade.itensPrincipioDiretriz[i].tipo = 'PRINCIPIO_DIRETRIZ';
                $scope.necessidadesRelacionadas.push(entidade.itensPrincipioDiretriz[i]);
            }

            for (i = 0; i < entidade.itensAnaliseSwot.length; i++) {
                entidade.itensAnaliseSwot[i].tipo = 'ITEM_SWOT';
                $scope.necessidadesRelacionadas.push(entidade.itensAnaliseSwot[i]);
            }
        }

        /**
         *
         */
        $scope.carregarListaCriterioPriorizacao = function () {

            var pageRequest = new PageRequest();
            pageRequest.size = 100;

            criterioPriorizacaoService.listCriterioPriorizacaoByFilters(null, pageRequest, {
                callback: function (result) {
                    $scope.listaCriteriosPriorizacao = result.content;
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
        $scope.saveNecessidadeInfo = function () {
            necessidadeService.insertNecessidade($scope.entidade, {
                callback: function (result) {
                    $scope.entidade = result;

                    var toast = $mdToast.simple()
                        .content('Informações da Necessidade de TI foram salvas com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

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
         */
        $scope.carregarListaDocumentos = function () {
            planoDiretorService.listItensDocumentoReferenciaByPlanoDiretorId($scope.model.planoDiretorId, {
                callback: function (result) {
                    $scope.listaDocumentos = result;
                    $scope.$apply();
                }, errorHandler: function (message, exception) {
                    $log.error(message);
                }
            });
        };
        
        /**
         *
         */
        $scope.abrirPopupAssociarOrganizacaoMilitar = function (ev) {
            $mdDialog.show({
                controller: NecessidadeOrganizacaoMilitarDialogController,
                templateUrl: './modules/pdti/ui/necessidade-ti/popup/popup-associar-organizacao-militar.html',
                targetEvent: ev,
                locals: {
                    necessidade: $scope.entidade,
                    listaOrganizacoesAOcultar: $scope.organizacoesAssociadas
                }
            })
                .then(function (result) {

                $scope.organizacoesAssociadas = $scope.organizacoesAssociadas.concat(result);

            }, function () { });
        };

        /**
         * 
         */
        $scope.abrirPopupAssociarNecessidadesRelacionadas = function (ev) {
            $mdDialog.show({
                controller: NecessidadesRelacionadasDialogController,
                templateUrl: './modules/pdti/ui/necessidade-ti/popup/popup-associar-necessidades-relacionadas.html',
                targetEvent: ev,
                locals: {
                    necessidade: $scope.entidade,
                    listaNecessidadesRelacionadas: $scope.necessidadesRelacionadas
                }
            }).then(function (result) {

                $scope.entidade = result;
                $scope.populaListaNecessidadesRelacionadas(result);

            }, function () { });
        };

        /**
         * 
         */
        $scope.carregarListaOrganizacoesAssociadas = function (id) {
            necessidadeService.listNecessidadeOrganizacoesMilitaresByNecessidadeId(id, {
                callback: function (result) {
                    $scope.organizacoesAssociadas = result;
                    $scope.$apply();
                }, errorHandler: function (message, error) {
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
         * @param entidade
         */
        $scope.alterarNecessidade = function (entidade) {
            necessidadeService.updateNecessidade(entidade, {
                callback: function (result) {
                    var toast = $mdToast.simple()
                        .content('Registro atualizado com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                    $scope.entidade = result;

                    $scope.populaListaNecessidadesRelacionadas(result);

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
        $scope.excluirNecessidade = function (ev, lista) {
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Necessidade de TI')
                .content('Tem certeza que deseja excluir o(s) registros(s)? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Necessidade de TI')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);
            var listaCopia = angular.copy(lista);
            $mdDialog.show(confirm).then(function () {
                necessidadeService.removeNecessidade(lista, {
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
                });
            }, function () {
                });
        };

        $scope.zerarNecessidades = function () {
            $scope.entidade.itensAnaliseSwot = [];
            $scope.entidade.itensEstrategia = [];
            $scope.entidade.itensPrincipioDiretriz = [];

            $scope.alterarNecessidade($scope.entidade);
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
        };

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
            $state.go('necessidade-ti.editar', { id: item.id });
        };

    });

    /**
     * Controller da popup de associação entre Necessidade de TI e Organização Militar
     */
    function NecessidadeOrganizacaoMilitarDialogController($scope, $mdDialog, $mdToast, $importService, $log, necessidade, listaOrganizacoesAOcultar) {

        $importService("necessidadeService");
        $importService("organizacaoMilitarService");

        $scope.listaOrganizacoesMilitares = [];
        $scope.listaOrganizacoesAOcultar = listaOrganizacoesAOcultar;

        organizacaoMilitarService.listOrganizacoesMilitaresByFilters(null, new PageRequest(), {
            callback: function (result) {
                var values = [];
                
                for (var i = 0; i < result.content.length; i++) {
                    if ($scope.findByIdInArray($scope.listaOrganizacoesAOcultar, result.content[i]) < 0) values.push(result.content[i]);
                }
                $scope.listaOrganizacoesMilitares = values;
                $scope.$apply();
            },
            errorHandler: function (message, error) {

            }
        });

        /**
         * 
         */
        $scope.associarOrganizacoes = function () {
            necessidadeService.insertNecessidadeOrganizacoesMilitares(necessidade, $scope.selectedItens, {
                callback: function (result) {
                    var toast = $mdToast.simple()
                        .content("Organizações Militares associadas com sucesso!")
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                    $mdDialog.hide(result);

                }, errorHandler: function (message, error) {
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
        };
        
        /**
         * 
         */
        $scope.findByIdInArray = function (array, entity) {
            for (var i = 0; i < array.length; i++) {
                if (array[i].organizacaoMilitar.id == entity.id) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 
         */
        $scope.selectionUpdate = function (selectedItens) {
            $scope.selectedItens = selectedItens;
        }

        /**
         * 
         */
        $scope.cancelar = function () {
            $mdDialog.cancel();
        };
    }
    
    /**
     * Controller da popup de Necessidade de TI
     */
    function NecessidadesRelacionadasDialogController($scope, $mdDialog, $mdToast, $log, $importService, necessidade, listaNecessidadesRelacionadas) {

        $importService('planoDiretorService');

        $scope.listaNecessidades = [];
        $scope.selectedItens = [];
        $scope.itensAOcultar = listaNecessidadesRelacionadas;

        $scope.necessidade = angular.copy(necessidade);

        planoDiretorService.listItensAnaliseSwotByPlanoDiretorId($scope.necessidade.planoDiretor.id, {
            callback: function (result) {
                var values = [];

                for (var i = 0; i < result.length; i++) {
                    result[i].tipo = 'ITEM_SWOT';
                    if ($scope.findByIdInArray($scope.itensAOcultar, result[i]) < 0) values.push(result[i]);
                }
                $scope.listaNecessidades = $scope.listaNecessidades.concat(values);
                $scope.$apply();
            }, errorHandler: function (message, error) {
                $log.error(message);
            }
        });

        planoDiretorService.listItensPrincipioDiretrizByPlanoDiretorId($scope.necessidade.planoDiretor.id, {
            callback: function (result) {
                var values = [];

                for (var i = 0; i < result.length; i++) {
                    result[i].tipo = 'PRINCIPIO_DIRETRIZ';
                    if ($scope.findByIdInArray($scope.itensAOcultar, result[i]) < 0) values.push(result[i]);
                }

                $scope.listaNecessidades = $scope.listaNecessidades.concat(values);

                $scope.$apply();
            }, errorHandler: function (message, error) {
                $log.error(message);
            }
        });

        planoDiretorService.listItensEstrategiaByPlanoDiretorId($scope.necessidade.planoDiretor.id, {
            callback: function (result) {
                var values = [];

                for (var i = 0; i < result.length; i++) {
                    result[i].tipo = 'ESTRATEGIA';
                    if ($scope.findByIdInArray($scope.itensAOcultar, result[i]) < 0) values.push(result[i]);
                }
                $scope.listaNecessidades = $scope.listaNecessidades.concat(values);
                $scope.$apply();
            }, errorHandler: function (message, error) {
                $log.error(message);
            }
        });
        
        /**
         * 
         */
        $scope.selectionUpdate = function (selectedItens) {
            $scope.selectedItens = selectedItens;
        }

        /**
         * 
         */
        $scope.findByIdInArray = function (array, entity) {
            for (var i = 0; i < array.length; i++) {
                if (array[i].id == entity.id && array[i].tipo == entity.tipo) {
                    return i;
                }
            }
            return -1;
        }
        
        /**
         * 
         */
        $scope.associarNecessidades = function () {

            for (var i = 0; i < $scope.selectedItens.length; i++) {
                switch ($scope.selectedItens[i].tipo) {
                    case 'ESTRATEGIA':
                        delete $scope.selectedItens[i].tipo;
                        $scope.necessidade.itensEstrategia.push($scope.selectedItens[i]);
                        break;
                    case 'PRINCIPIO_DIRETRIZ':
                        delete $scope.selectedItens[i].tipo;
                        $scope.necessidade.itensPrincipioDiretriz.push($scope.selectedItens[i]);
                        break;
                    case 'ITEM_SWOT':
                        delete $scope.selectedItens[i].tipo;
                        $scope.necessidade.itensAnaliseSwot.push($scope.selectedItens[i]);
                        break;
                }
            }

            necessidadeService.updateNecessidade($scope.necessidade, {
                callback: function (result) {
                    var toast = $mdToast.simple()
                        .content("Necessidades associadas com sucesso!")
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                    $mdDialog.hide(result);

                }, errorHandler: function (message, error) {
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
        };

        /**
         * 
         */
        $scope.cancelar = function () {
            $mdDialog.cancel();
        };
    }
} (window.angular));