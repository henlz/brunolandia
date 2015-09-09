	
	(function (angular) {
	    'use strict';
	
	    /**
	     * Controller do caso de uso Plano de Ações
	     * @param $scope
	     * @param $state
	     */
	    angular.module('pdti')
	        .controller('PlanoAcaoController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {
	
	        /**
	         * Serviços importados do DWR
	         */
	        
	        $importService("necessidadeService");
	        $importService("planoAcoesService");
	        
	        /**
	         * Injeta os métodos, atributos e seus estados herdados de AbstractCRUDController.
	         * @see AbstractCRUDController
	         */
	        $injector.invoke(AbstractCRUDController, this, { $scope: $scope });
	        
	        /*-------------------------------------------------------------------
	         * 		 				 	ATTRIBUTES
	         *-------------------------------------------------------------------*/
	
	        //STATES
	        /**
	         * Variável estática que representa
	         * o estado de listagem de registros de metas da necessidade.
	         */
	         
	         $scope.LIST_METAS_STATE = "plano-acoes.listar-metas";
	         
	         
	         /**
	          * Representa o estado para a criação de metas
	          */
	         $scope.INSERT_META_STATE = "plano-acoes.criar-meta";
	         
	         /**
	          * Representa o estado para a edição de metas
	          */
	         $scope.EDIT_META_STATE = "plano-acoes.editar-meta";
	
	         //----FORM MODEL
	
	        /**
	         * Contém os estados dos filtros na tela
	         * Contém estado da paginação inicial contendo sorting
	         */
	        $scope.model = {
	            form: null,
	            meta: new Meta(),				
	            filter:"",
				atividadefilter:"",
	            page: {//PageImpl
		    	content: [],
	    	    	pageable: {//PageRequest
	    	    		size: 5,
	    	    		page: 0,
	    	        	sort: {//Sort
	    	        		orders:[
	    	        		   { direction: 'ASC', property: 'codigo' }
	    	        		]
	    	        	},
	    	    	}
	            },
				pageAtividades: {//PageImpl
		    	content: [],
	    	    	pageable: {//PageRequest
	    	    		size: 5,
	    	    		page: 0,
	    	        	sort: {//Sort
	    	        		orders:[
	    	        		   { direction: 'ASC', property: 'codigo' }
	    	        		]
	    	        	},
	    	    	}
	            },	            
				tags: [],
				itensExcluir:[]                        
	        };
	
	        /**
	         *
	         */
	        //$scope.currentPage; -> scope.model.page.pageable.page
	        
	        /**
	         * 
	         */
	        //$scope.tagsString = "" - > scope.model.tagsString;
	        /*-------------------------------------------------------------------
	        * 		 				  POST CONSTRUCT
	        *-------------------------------------------------------------------*/
			$scope.initialize = function () {				
				$scope.necessidadeId = $state.params.id;
				$scope.metaId = $state.params.metaId;
	            $scope.currentState = $state.current.name;				
	            switch($scope.currentState) {
	                case $scope.INSERT_META_STATE: {
	                    $log.info("insert new meta");
						$scope.changeToInsert();
						break;
	                }
	                
					case $scope.EDIT_META_STATE: {						
	                    $log.info("edit meta");
						$scope.changeToEditMeta($scope.metaId);
						break;   
	                }
	                
	                case $scope.LIST_METAS_STATE: {						
	                    $scope.changeToListMeta(false);
						break;						       
	                }
	                
	                default: {
	                    
	                }
	            }            
        	};
			
	        
	       /*-------------------------------------------------------------------
	        * 		 				 	  BEHAVIOURS
	        *-------------------------------------------------------------------*/
	        
            /**
	         * 
	         */
	         $scope.$watch('model.filter', function(newValue){
			 if(newValue) {
			 		$scope.carregarListaMeta( newValue , $scope.pageRequest);	
			 	}
	         });
			
			$scope.changeTags = function(tags) {
              //$scope.model.tags = tags;
              //$scope.carregarListaMeta( $scope.model.filter , $scope.model.page.pageable);			  
        	};
            
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
	        };			
	
	        /**
	         *
	         */
	        $scope.limparSelecao = function () {
	            $scope.model.itensExcluir = [];
	            table.clearSelection();
	            $rootScope.$broadcast('showEitsBottomSheetEvent');
	        };
	
	        /**
	         *
	         * @param item
	         */
	        $scope.itemClicked = function (item) {            
	            $state.go($scope.EDIT_META_STATE, {metaId:item.id});
	            $scope.$apply();
	        };
			
			
			$scope.abrirPopUpNovaAtividade = function(ev) {
				var atividade = new Atividade();								
				atividade.meta = $scope.model.meta;
				$mdDialog.show({
	                controller: AtividadeDialogController,
	                templateUrl: './modules/pdti/ui/plano-acao/popup/popup-atividade.html',
	                targetEvent: ev,
	                hasBackdrop: true,
		                locals: {
							atividade : atividade		                    
		                }
	            	})
	                .then(function (result) {
	
		                $scope.model.pageAtividades.content.push(result);
		
		                var toast = $mdToast.simple()
		                    .content('Registro salvo com sucesso!')
		                    .action('Fechar')
		                    .highlightAction(false)
		                    .position('bottom left right');
		                $mdToast.show(toast).then(function () {
		                });
					},
					function () {});
			};
			
			$scope.abrirPopupAlterarAtividade = function(ev,atividade) {
				$mdDialog.show({
					controller: AtividadeDialogController,
					templateUrl: './modules/pdti/ui/plano-acao/popup/popup-atividade.html',
					targetEvent: ev,
					hasBackdrop: true,
					locals: {
							atividade : angular.copy(atividade)									                    
		                }
					
				})
				.then(function(result) {
					var toast = $mdToast.simple()
                    .content('Registro atualizado com sucesso!')
                    .action('Fechar')
                    .highlightAction(false)
                    .position('bottom left right');
					$mdToast.show(toast).then(function () {});
					var i = $scope.findByIdInArray($scope.model.pageAtividades.content, result);
                    $scope.model.pageAtividades.content[i] = result;
				},
				function(){});
			};			
	         
	         /*-------------------------------------------------------------------
	          * 		 				 	  HANDLERS
	          *-------------------------------------------------------------------*/
	          //--------
	          // State changing
	          //--------
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
	                console.debug("changeToInsert");
	                $scope.model.meta = new Meta();
	                $state.go($scope.INSERT_META_STATE);					    
	            };
	            
	            /**
	            * Realiza os procedimentos iniciais (prepara o estado)
	            * para a tela de edição e após isso, muda o estado para update.
	            *
	            * Para mudar para este estado, deve-se primeiro obter via id
	            * o registro pelo serviço de consulta e só então mudar o estado da tela.
	            * 
	            * @see $scope.EDIT_STATE
	            */
	            $scope.changeToEditMeta = function( id ) {
	                console.debug("changeToEdit", id);
					$scope.itensExcluir = [];
	                planoAcoesService.findMetaById( id, {
	                    callback : function(result) {
	                    	$scope.model.meta = result;
							console.debug("result", result);
	                        $scope.carregarListaAtividades($scope.model.atividadefilter, id, $scope.model.pageAtividades.pageable);
	                    },
	                    errorHandler : function(message, exception) {
	                    	$mdToast.showSimple( message );
	                    	$state.go( $scope.LIST_META_STATE );
	                        $scope.$apply();
	                    }
	                });
	            };
	            
	            
	            /**
	            * Realiza os procedimentos iniciais (prepara o estado)
	            * para a tela de consulta e após isso, muda o estado para list.
	            * Para mudar para este estado, deve-se primeiro carregar os dados da consulta.
	            * 
	            * @see $scope.LIST_STATE
	            * 
	            * @param paginate boolean para saber se precisa paginar
	            */
	            $scope.changeToListMeta = function(paginate) {
	                console.debug("changeToList");
	                $scope.itensExcluir = [];
	                if ( paginate ) $scope.model.page.pageable.page++;
	                
	                $scope.carregarListaMeta($scope.model.filter, $scope.model.page.pageable);
	            };
	            
	          /**
	          * Realiza os procedimentos iniciais (prepara o estado)
	          * para a tela de exclusão.
	          * Antes de excluir, o usuário notificado para confirmação e só então o registro é excluido.
	          */
	          $scope.changeToRemoveMeta = function( event, lista ) {
	              console.debug("changeToRemoveMeta", lista);
	              var confirm = $mdDialog.confirm()
	                .title('Exclusão de Metas')
	                .content('Tem certeza que deseja excluir o(s) registros(s)? Todas as atividades associadas a meta serão excluídas. Esta operação não poderá ser desfeita.')
	                .ariaLabel('Exclusão de Metas')
	                .ok('Sim')
	                .cancel('Cancelar')
	                .targetEvent(event);
	                
	              $mdDialog.show(confirm).then( function( result ) {
	            	  console.log( result );
	                  $scope.excluirMetas(event,lista);
	              });
	          };
			  
			  /**
	          * Realiza os procedimentos iniciais (prepara o estado)
	          * para a tela de exclusão.
	          * Antes de excluir, o usuário notificado para confirmação e só então o registro é excluido.
	          */
	          $scope.changeToRemoveAtividade = function( event, lista ) {
	              console.debug("changeToRemoveAtividade", lista);
	              var confirm = $mdDialog.confirm()
	                .title('Exclusão de Atividades')
	                .content('Tem certeza que deseja excluir o(s) registros(s)? Todos os investimentos e custeios associados à atividade serão excluídos. Esta operação não poderá ser desfeita.')
	                .ariaLabel('Exclusão de Atividades')
	                .ok('Sim')
	                .cancel('Cancelar')
	                .targetEvent(event);
	                
	              $mdDialog.show(confirm).then( function( result ) {
	            	  console.log( result );
	                  $scope.excluirAtividades(event,lista);
	              });
	          };
	
	         /*-------------------------------------------------------------------
	          * 		 				 	  OPERATIONS
	          *-------------------------------------------------------------------*/
	          /**
	           * Realiza a inserção de um novo registro 
	           */
	            $scope.insertMeta = function () {
	                console.debug("insertMeta");
	                if ($scope.model.form.$invalid) {
	                     $mdToast.showSimple( "Fomulário inválido" );
	    		         return;
	                }
					var necessidade = new Necessidade();
					necessidade.id = $scope.necessidadeId;
					$scope.model.meta.necessidade = necessidade;
	                planoAcoesService.insertMeta($scope.model.meta, {
	                    callback: function (result) {
	                        $mdToast.showSimple( "Registro salvo com sucesso!" );							
							$scope.changeToListMeta(false);							
	                        $scope.$apply();
	                    },
	                    errorHandler: function (message, error) {
	                        $mdToast.showSimple( message );
	                        $log.error(message);
	                        $scope.$apply();
	                    }
	                });            
	            };
	            
	           /**
	           * Realiza a atualização de registro já existente           
	           */
	            $scope.updateMeta = function () {
	                console.debug("updateMeta");
	                if ($scope.model.form.$invalid) {
	                    $mdToast.showSimple( "Fomulário inválido" );
	        		    return;
	                 }
	                 planoAcoesService.updateMeta($scope.model.meta, {
	                    callback: function (result) {
	                        $mdToast.showSimple( "Registro salvo com sucesso!" );
							$scope.changeToListMeta(false);
	                        $scope.$apply();
	                    },
	                    errorHandler: function (message, error) {
	                        $mdToast.showSimple( message );
	                        $log.error(message);
	                        $scope.$apply();
	                    }
	                });            
	            };
	            
	           /**
	           * Realiza a consulta de registros, consirando filtro, paginação e sorting.
	           *
	           * @see $scope.LIST_METAS_STATE
	           * @see $scope.filters
	           * @see $scope.page
	           */
	           
	           $scope.carregarListaMeta = function (filter, pageRequest) {
	                console.debug("carregarListaMeta");    
	                planoAcoesService.listMetaByNecessidadeId( filter, $scope.necessidadeId, pageRequest, {                    
	                    callback: function (result) {
	                        console.debug("n: ", result);
	                        $scope.model.page.content = $scope.model.page = result;
	                        $state.go( $scope.LIST_METAS_STATE );				
	                        $scope.$apply();
	                    },
	                    errorHandler: function (message, exception) {
	                        $mdToast.showSimple( message );                        
	                        $log.error(message);
	                        $scope.$apply();
	                    }
	                });
	            };
				
				$scope.carregarListaAtividades = function(filter, metaId, pageRequest) {
					planoAcoesService.listAtividadeByMetaId(filter, metaId,pageRequest, {
						callback:function(result) {
							console.debug("a: ",result);
							$scope.model.pageAtividades.content = $scope.model.pageAtividades = result;
							$state.go($scope.EDIT_META_STATE, {metaId:metaId});							
	                        $scope.$apply();							
						},
	                    errorHandler : function(message, exception) {
	                    	$mdToast.showSimple( message );
	                    	$state.go( $scope.LIST_META_STATE );
	                        $scope.$apply();
	                    }
					});	
				};
	            
	           /**
	           * Remove as metas, consirando a lista de metas passadas.
	           * Este operação remove as metas, atividades e investimentos e custeios
	           * em efeito cascasta	           
	           */
	            $scope.excluirMetas = function (ev, lista) {            
	              var listaCopia = angular.copy(lista);            
	              planoAcoesService.removeMeta(lista, {
	                callback: function (result) {
	                var toast = $mdToast.simple()
	                    .content('Registro(s) excluído(s) com sucesso!')
	                    .action('Fechar')
	                    .highlightAction(false)
	                    .position('bottom left right');
	                    $mdToast.show(toast).then(function () {});
	                    $scope.limparSelecao();
	                    for (var x = 0; x < listaCopia.length; x++) {
	                        var i = $scope.findByIdInArray($scope.model.page.content, listaCopia[x]);
	                        $scope.model.page.content.splice(i, 1);
	                    }
	                    $scope.$apply();
	                },
	                errorHandler: function (message, exception) {
	                    $log.error("Erro ao excluir registro(s)", message);
	                }
	            });
	          };
			  
			  /**
	           * Remove atividades, consirando a lista de metas passadas.
	           * Este operação remove as metas, atividades e investimentos e custeios
	           * em efeito cascasta	           
	           */
			   $scope.excluirAtividades = function (ev, lista) {            
	              var listaCopia = angular.copy(lista);            
	              planoAcoesService.removeAtividade(lista, {
	                callback: function (result) {
	                var toast = $mdToast.simple()
	                    .content('Registro(s) excluído(s) com sucesso!')
	                    .action('Fechar')
	                    .highlightAction(false)
	                    .position('bottom left right');
	                    $mdToast.show(toast).then(function () {});
	                    $scope.limparSelecao();
	                    for (var x = 0; x < listaCopia.length; x++) {
	                        var i = $scope.findByIdInArray($scope.model.pageAtividades.content, listaCopia[x]);
	                        $scope.model.pageAtividades.content.splice(i, 1);
	                    }
	                    $scope.$apply();
	                },
	                errorHandler: function (message, exception) {
	                    $log.error("Erro ao excluir registro(s)", message);
	                }
	            });
	          };
	    });
		
		/*-------------------------------------------------------------------
	     * 		 				 	  ATIVIDADE POP UP CONTROLLER
	     *-------------------------------------------------------------------*/
		/**
	     * Controller da popup de Atividade
	     */
	    function AtividadeDialogController($scope, $mdDialog, $mdToast, $log, atividade) {
			
			/*-------------------------------------------------------------------
	         * 		 				 	ATTRIBUTES
	         *-------------------------------------------------------------------*/
	
			//----FORM MODEL
	
	        /**
	         * Contém os estados dos filtros na tela
	         * Contém estado da paginação inicial contendo sorting
	         */
	        $scope.atividadePopUpModel = {
	            atividadeForm: null,
				modoAlteracao: false,
	            atividade: null,
				investimentosCusteios: []        
	        };
			
			var criarInvestimentosCusteiosAtividade = function() {
				var anoInicio = $scope.atividadePopUpModel.atividade.meta.necessidade.planoDiretor.anoInicio.getFullYear();
				var anoFim = $scope.atividadePopUpModel.atividade.meta.necessidade.planoDiretor.anoTermino.getFullYear();
				for(var i = anoInicio; i <= anoFim; i++) {
					var investimentoCusteio = new InvestimentoCusteio();
					investimentoCusteio.ano = new Date().setFullYear(i);
					investimentoCusteio.investimento = 0.0;
					investimentoCusteio.custeio = 0.0;
					investimentoCusteio.atividade = $scope.atividadePopUpModel.atividade; 
					$scope.atividadePopUpModel.investimentosCusteios.push(investimentoCusteio);
				}
				console.debug("vigencia",$scope.atividadePopUpModel.arrayVigenciaPDTI);					
			};
			
			var carregarInvestimentosCusteiosAtividade = function() {
				planoAcoesService.listInvestimentoCusteioByFilters("",atividade.id,null, {
					callback: function (result) {
					console.debug("investimentos",result);
                    $scope.atividadePopUpModel.investimentosCusteios = result.content;
                    $scope.$apply();
                	},
					errorHandler: function (message, exception) {
	                    $log.error(message);
                	}
				});
			};
	
			$scope.atividadePopUpModel.atividade = atividade;
	        if (atividade.id != null) {	            
	            $scope.atividadePopUpModel.modoAlteracao = true;
				carregarInvestimentosCusteiosAtividade();
	        } else {	            
	            $scope.atividadePopUpModel.modoAlteracao = false;
				criarInvestimentosCusteiosAtividade();
	        }
	
	        $scope.cancelarPopUp = function () {
	            $mdDialog.cancel();
	        };
	
	        $scope.validaFormAtividade = function () {
	            if (!$scope.atividadePopUpModel.atividadeForm.$valid) {
	                $mdToast.show($mdToast.simple()
	                    .content('Preencha todos os campos obrigatórios!')
	                    .action('Fechar')
	                    .highlightAction(false)
	                    .position('top')).then(function () {
	                });
	                return false;
	            } 
	            return true;
	        };
			
			/**
			 * 
			 */
	        $scope.salvarAtividade = function () {
	            if ($scope.validaFormAtividade()) {										
					console.debug($scope.atividadePopUpModel.atividade.meta.id);
					if($scope.atividadePopUpModel.modoAlteracao == false) {
						planoAcoesService.insertAtividadeComInvestimentoCusteio($scope.atividadePopUpModel.atividade, $scope.atividadePopUpModel.investimentosCusteios, {
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
					else if($scope.atividadePopUpModel.modoAlteracao == true) {
						planoAcoesService.updateAtividadeComInvestimentoCusteio($scope.atividadePopUpModel.atividade, $scope.atividadePopUpModel.investimentosCusteios, {
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
	
	        // $('html').bind('keypress', function (e) {
	        //     if (e.keyCode == 13) {
	        //         return false;
	        //     }
	        // });
	    }
	
	} (window.angular));