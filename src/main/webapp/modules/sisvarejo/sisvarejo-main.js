(function(window, angular, undefined) {
	'use strict';

	//Start the AngularJS
	var module = angular.module('sisvarejo', ['ngMaterial', 'ui.router', 'eits-dwr-broker', 'eits-bottomsheet', 'eits.material.core', 'eits.controls.table', 'md.data.table']);

	module.config( function( $stateProvider, $urlRouterProvider, $importServiceProvider ) {
		//-------
		//Broker configuration
		//-------
		$importServiceProvider.setBrokerURL("./broker/interface");
		
		//-------
		//URL Router
		//-------

		//HOME
        $urlRouterProvider.otherwise("/");

		/**
		 * 
		 */
        $stateProvider.state('pais',{
			url : "/pais",
			controller : 'PaisController',
			templateUrl : "./modules/sisvarejo/ui/localizacao/pais/pais-view.jsp"
		});
		
		/**
		 * 
		 */
		$stateProvider.state('estado',{
			url : "/estado",
			controller : 'EstadoController',
			templateUrl : "./modules/sisvarejo/ui/localizacao/estado/estado-view.jsp"
		});
		
		/**
		 * 
		 */
		$stateProvider.state('cidade',{
			url : "/cidade",
			controller : 'CidadeController',
			templateUrl : "./modules/sisvarejo/ui/localizacao/cidade/cidade-view.jsp"
		});
		
		/**
		 * 
		 */
		$stateProvider.state('cor',{
			url : "/cor",
			controller : 'CorController',
			templateUrl : "./modules/sisvarejo/ui/caracteristica/cor/cor-view.jsp"
		});
		
		/**
		 * 
		 */
		$stateProvider.state('tamanho',{
			url : "/tamanho",
			controller : 'TamanhoController',
			templateUrl : "./modules/sisvarejo/ui/caracteristica/tamanho/tamanho-view.jsp"
		});

		/**
		 *
		 */
		$stateProvider.state('forma-pagamento',{
			url : "/forma-pagamento",
			controller : 'FormaPagamentoController',
			templateUrl : "./modules/sisvarejo/ui/financeiro/forma-pagamento/forma-pagamento-view.jsp"
		});

		/**
		 *
		 */
		$stateProvider.state('condicao',{
			url : "/condicao",
			controller : 'CondicaoController',
			templateUrl : "./modules/sisvarejo/ui/financeiro/condicao/condicao-view.jsp"
		}).state('condicao.listar',{
			url: '/listar'
		}).state('condicao.cadastrar',{
			url: '/cadastrar'
		}).state('condicao.alterar',{
			url: '/alterar/:id'
		}).state('condicao.detalhe',{
			url: '/detalhe/:id'
		});

		/**
		 *
		 */
		$stateProvider.state('produto',{
			url : "/produto",
			controller : 'ProdutoController',
			templateUrl : "./modules/sisvarejo/ui/estoque/produto/produto-view.jsp"
		}).state('produto.listar',{
            url: '/listar'
        }).state('produto.cadastrar',{
            url: '/cadastrar'
        }).state('produto.alterar',{
            url: '/alterar/:id'
        }).state('produto.detalhe',{
            url: '/detalhe/:id'
        });

		/**
		 *
		 */
		$stateProvider.state('fornecedor',{
			url : "/fornecedor",
			controller : 'FornecedorController',
			templateUrl : "./modules/sisvarejo/ui/estoque/fornecedor/fornecedor-view.jsp"
		}).state('fornecedor.listar',{
            url: '/listar'
        }).state('fornecedor.cadastrar',{
            url: '/cadastrar'
        }).state('fornecedor.alterar',{
            url: '/alterar/:id'
        }).state('fornecedor.detalhe',{
            url: '/detalhe/:id'
        });

		/**
		 *
		 */
		$stateProvider.state('cliente',{
			url : "/cliente",
			controller : 'ClienteController',
			templateUrl : "./modules/sisvarejo/ui/loja/cliente/cliente-view.jsp"
		}).state('cliente.listar',{
			url: '/listar'
		}).state('cliente.cadastrar',{
			url: '/cadastrar'
		}).state('cliente.alterar',{
			url: '/alterar/:id'
		}).state('cliente.detalhe',{
			url: '/detalhe/:id'
		});

		/**
		 *
		 */
		$stateProvider.state('venda',{
			url : "/venda",
			controller : 'VendaController',
			templateUrl : "./modules/sisvarejo/ui/loja/venda/venda-view.jsp"
		}).state('venda.listar',{
            url: '/listar'
        }).state('venda.cadastrar',{
            url: '/cadastrar'
        }).state('venda.alterar',{
            url: '/alterar/:id'
        }).state('venda.detalhe',{
            url: '/detalhe/:id'
        });

		/**
		 *
		 */
		$stateProvider.state('compra',{
			url : "/compra",
			controller : 'CompraController',
			templateUrl : "./modules/sisvarejo/ui/estoque/compra/compra-view.jsp"
		}).state('compra.listar',{
			url: '/listar'
		}).state('compra.cadastrar',{
			url: '/cadastrar'
		}).state('compra.alterar',{
			url: '/alterar/:id'
		}).state('compra.detalhe',{
			url: '/detalhe/:id'
		});

		/**
		 *
		 */
		$stateProvider.state('funcionario',{
			url : "/funcionario",
			controller : 'FornecedorController',
			templateUrl : "./modules/sisvarejo/ui/loja/funcionario/funcionario-view.jsp"
		}).state('funcionario.listar',{
			url: '/listar'
		}).state('funcionario.cadastrar',{
			url: '/cadastrar'
		}).state('funcionario.alterar',{
			url: '/alterar/:id'
		}).state('funcionario.detalhe',{
			url: '/detalhe/:id'
		});

	});

	module.run( function( $rootScope, $window ) {
		
	});
	
	module.controller('MainController', function($mdSidenav){
		this.closeSidenav = function(){
			$mdSidenav('left').close().then(function () {});
		};
		this.toggleSidenav = function(){
			$mdSidenav('left').toggle();
		}
	});

	angular.element(document).ready( function() {
		angular.bootstrap( document, ['sisvarejo']);
	});

})(window, window.angular);