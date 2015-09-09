(function (window, angular, undefined) {
    'use strict';

    //Start the AngularJS
    var module = angular.module('pdti', ['ngMaterial', 'ui.router', 'eits-dwr-broker', 'eits-bottomsheet', 'eits.material.core', 'md.data.table', 'eits.controls.table', 'eits.search.tags',
        'eits.controls.paper-sheet', 'eits.containers.box', 'eits.containers.hbox', 'eits.containers.vbox', 'eits.date.picker', 'froala']);

    module.config(function ($stateProvider, $urlRouterProvider, $importServiceProvider, $mdThemingProvider) {
        //-------
        //Broker configuration
        //-------
        $importServiceProvider.setBrokerURL("./broker/interface");

        //-------
        //Theme configuration
        //-------
        var eitsBlueMap = $mdThemingProvider.extendPalette('blue', {
            '500': '3949AB',
            'contrastDefaultColor': 'light'
        });

        var eitsOrangeMap = $mdThemingProvider.extendPalette('orange', {
            '500': 'FF8F00',
            'contrastDefaultColor': 'light'
        });

        $mdThemingProvider.definePalette('eitsBlue', eitsBlueMap);
        $mdThemingProvider.definePalette('eitsOrange', eitsOrangeMap);

        $mdThemingProvider.theme('default')
            .primaryPalette('eitsBlue')
            .accentPalette('eitsOrange');

        //-------
        //URL Router
        //-------

        //HOME
        $urlRouterProvider.otherwise("/");

        $stateProvider.state('organizacao-militar', {
            displayName: 'Organização Militar',
            url: "/organizacao-militar",
            controller: 'OrganizacaoMilitarController',
            templateUrl: "./modules/pdti/ui/organizacao-militar/organizacao-militar-view.jsp"
        });

        $stateProvider.state('documento-referencia', {
            displayName: 'Documento de Refêrencia',
            url: "/documento-referencia",
            controller: 'DocumentoReferenciaController',
            templateUrl: "./modules/pdti/ui/documento-referencia/documento-referencia-view.jsp"
        });

        $stateProvider.state('estrategia', {
            displayName: 'Estratégia',
            url: "/estrategia",
            controller: 'EstrategiaController',
            templateUrl: "./modules/pdti/ui/estrategia/estrategia-view.jsp"
        });

        $stateProvider.state('principio-diretriz', {
            displayName: 'Princípio/Diretriz',
            url: "/principio-diretriz",
            controller: 'PrincipioDiretrizController',
            templateUrl: "./modules/pdti/ui/principio-diretriz/principio-diretriz-view.jsp"
        });

        $stateProvider.state('item-swot', {
            displayName: 'Itens SWOT',
            url: "/item-swot",
            controller: 'ItemSwotController',
            templateUrl: "./modules/pdti/ui/item-swot/item-swot-view.jsp"
        });

        $stateProvider.state('criterio-priorizacao', {
            displayName: 'Critério de Priorização',
            url: "/criterio-priorizacao",
            controller: 'CriterioPriorizacaoController',
            templateUrl: "./modules/pdti/ui/criterio-priorizacao/criterio-priorizacao-view.jsp"
        });

        $stateProvider.state('necessidade-ti', {
            displayName: 'Necessidade de TI',
            url: "/:planoDiretorId/necessidade-ti",
            controller: 'NecessidadeTIController',
            templateUrl: "./modules/pdti/ui/necessidade-ti/necessidade-ti-view.jsp"
        })
            .state('necessidade-ti.detalhe', {
                displayName: 'Detalhe de Necessidade de TI',
                url: "/detalhe/:id"
            })
            .state('necessidade-ti.listar', {
                url: "/"
            })
            .state('necessidade-ti.criar', {
                displayName: 'Cadastrar Necessidade de TI',
                url: "/criar"
            })
            .state('necessidade-ti.editar', {
                displayName: 'Alterar Necessidade de TI',
                url: "/editar/:id"
            });

        $stateProvider.state('termo-abreviacao', {
            displayName: 'Termos e Abreviações',
            url: "/termo-abreviacao",
            controller: 'TermoAbreviacaoController',
            templateUrl: "./modules/pdti/ui/termo-abreviacao/termo-abreviacao-view.jsp"
        });

        $stateProvider.state('plano-diretor', {
            displayName: 'Plano Diretor de TI',
            url: "/plano-diretor",
            controller: 'PlanoDiretorController',
            templateUrl: "./modules/pdti/ui/plano-diretor/plano-diretor-view.jsp"
        }).state('plano-diretor.detalhe', {
            displayName: 'Detalhe de Plano Diretor',
            url: "/detalhe/:id"
        })
            .state('plano-diretor.listar', {
                url: "/listar"
            })
            .state('plano-diretor.criar', {
                displayName: 'Cadastrar Plano Diretor',
                url: "/criar"
            })
            .state('plano-diretor.editar', {
                displayName: 'Alterar Plano Diretor',
                url: "/editar/:id"
            })
            .state('plano-diretor.secoes', {
                displayName: 'Editar Seções',
                url: "/secoes/:id"
            });

        $stateProvider.state('plano-acoes', {
            displayName: 'Necessidade de TI',
            redirectPath: '/necesidade-ti',
            url: "/necessidade/:id/plano-acoes",
            controller: 'PlanoAcaoController',
            templateUrl: "./modules/pdti/ui/plano-acao/plano-acao-view.jsp"
        })
            .state('plano-acoes.listar-metas', {
                displayName: 'Plano de ações',
                url: "/listar-metas"
            })
            .state('plano-acoes.editar-meta', {
                displayName: 'Editar Meta',
                url: "/editar/:metaId"
            })
            .state('plano-acoes.criar-meta', {
                displayName: 'Criar metas',
                url: "/criar"
            });


    });

    module.run(function ($rootScope, $window, $state, $stateParams, $mdSidenav) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

        $rootScope.toogleMenu = function () {
            $mdSidenav("mainMenu")
                .toggle()
                .then(function () {
                });
        }
    });

    module.value('froalaConfig', {
        inlineMode: false,
        placeholder: 'Digite o texto aqui'
    });

    angular.element(document).ready(function () {
        angular.bootstrap(document, ['pdti']);
    });

})(window, window.angular);