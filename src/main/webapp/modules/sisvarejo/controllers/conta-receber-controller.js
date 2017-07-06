(function (angular) {
    'use strict';


    angular.module('sisvarejo').controller('ContaReceberController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

        $importService("lojaService");
        $importService("financeiroService");

        /**
         * Injeta os métodos, atributos e seus estados herdados de AbstractCRUDController.
         * @see AbstractCRUDController
         */
        $injector.invoke(AbstractCRUDController, this, {$scope: $scope});

        /*-------------------------------------------------------------------
         * 		 				 	EVENT HANDLER
         *-------------------------------------------------------------------*/

        /*-------------------------------------------------------------------
         * 		 				 	  STATES
         *-------------------------------------------------------------------*/

        //STATES
        /**
         * Variável estática que representa
         * o estado de listagem de registros.
         */
        $scope.LIST_STATE = "conta-receber.listar";
        /**
         * Variável estática que representa
         * o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "conta-receber.detalhe";
        /**
         * Variável estática que representa
         * o estado para a criação de registros.
         */
        $scope.INSERT_STATE = "conta-receber.cadastrar";
        /**
         * Variável estática que representa
         * o estado para a edição de registros.
         */
        $scope.CANCEL_STATE = "conta-receber.cancelar";

        /**
         *
         * @type {string}
         */
        $scope.PAY_STATE = "conta-receber.pagar";


        /*-------------------------------------------------------------------
         * 		 				 	ATTRIBUTES
         *-------------------------------------------------------------------*/

        /**
         *
         */
        $scope.data = {
            itensExcluir: []
        };

        /**
         *
         * @type {{entidade: {}, content: Array, query: {order: string}}}
         */
        $scope.model = {
            entidade: {
                itensVenda: []
            },
            content: [],
            query: {
                order: 'descricao'
            },
            invalidNfe: true
        }

        /**
         *
         */
        $scope.ordenacao = {
            predicado: "-sigla",
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

            $scope.currentState = $state.current.name;
            $scope.pageRequest = pageRequest;

            switch ($scope.currentState) {
                case $scope.LIST_STATE: {
                    $scope.changeToList();
                }
                    break;
                case $scope.DETAIL_STATE: {
                    $scope.changeToDetail($state.params.id);
                }
                    break;
                case $scope.INSERT_STATE: {
                    $scope.changeToInsert();
                }
                    break;
                case $scope.CANCEL_STATE: {
                    $scope.changeToCancel($state.params.id);
                }
                case $scope.PAY_STATE: {
                    $scope.changeToPayment($state.params.id);
                }
                    break;
                default: {
                    $state.go($scope.LIST_STATE);
                }
            }
        };

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
            $scope.pageRequest = pageRequest;

            $scope.carregarLista();
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

            $scope.model.entidade = new ContaReceber();
            $scope.model.entidade.itensVenda = [];
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
        $scope.changeToCancel = function (id) {

            $log.info("changeToUpdate", id);

            if (id == null || id == "" || id == 0) {
                $scope.currentState = $scope.LIST_STATE;
                $state.go($scope.LIST_STATE);
                return;
            }

            financeiroService.findContaReceberById(id, {
                callback: function (result) {

                    if (result == null) {
                        $scope.currentState = $scope.LIST_STATE;
                        $state.go($scope.LIST_STATE);
                        return false;
                    }

                    $scope.model.entidade = result;

                    $scope.currentState = $scope.CANCEL_STATE;
                    $state.go($scope.CANCEL_STATE);
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
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

        $scope.changeToPayment = function (id) {

            $log.info("changeToPayment", id);

            if (id == null || id == "" || id == 0) {
                $scope.currentState = $scope.LIST_STATE;
                $state.go($scope.LIST_STATE);
                return;
            }

            financeiroService.findContaReceberById(id, {
                callback: function (result) {

                    if (result == null) {
                        $scope.currentState = $scope.LIST_STATE;
                        $state.go($scope.LIST_STATE);
                        return false;
                    }

                    $scope.model.entidade = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
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
         * @param filter
         * @param pageRequest
         */
        $scope.carregarLista = function (filter, pageRequest) {
            financeiroService.listContasReceber({
                callback: function (result) {
                    $scope.model.content = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $log.error(message);
                }
            })
        };

        $scope.salvarContaReceber = function (entidade) {
            financeiroService.insertContaReceber(entidade, {
                callback: function (result) {
                    $state.go($scope.LIST_STATE);
                },
                errorHandler: function (message, exception) {
                    $mdToast.showSimple(message)
                    $log.error(exception);
                }
            })
        }

        /**
         *
         */
        $scope.pagarContaReceber = function (contaReceber) {
            financeiroService.pagarContaReceber(contaReceber, {
                callback: function () {
                    $state.go($scope.LIST_STATE);
                },
                errorHandler: function (message, error) {
                    $mdToast.showSimple(message);
                    $log.error(error);
                }
            })
        };

        /**
         *
         */
        $scope.cancelarContaReceber = function (contaReceber) {
            financeiroService.cancelarContaReceber(contaReceber, {
                callback: function () {
                    $state.go($scope.LIST_STATE);
                },
                errorHandler: function (message, error) {
                    $mdToast.showSimple(message);
                    $log.error(error);
                }
            })
        };

        /**
         *
         */
        $scope.buscaClienteById = function () {
            lojaService.findClienteById($scope.model.codigoCliente, {
                callback: function (result) {
                    if (result != null) {
                        $scope.model.entidade.cliente = result;
                    }
                    $scope.$apply();
                },
                errorHandler: function () {
                    $mdToast.showSimple("Erro ao buscar o cliente");
                }
            })
        };

        /**
         *
         */
        $scope.buscaFormaPagamentoById = function () {
            financeiroService.findFormaPagamentoById($scope.model.codigoFormaPagamento, {
                callback: function (result) {
                    if (result != null) {
                        $scope.model.entidade.formaPagamento = result;
                    }
                    $scope.$apply();
                },
                errorHandler: function () {
                    $mdToast.showSimple("Erro ao buscar o cliente");
                }
            })
        };

        /**
         *
         * @param ev
         */
        $scope.abrirPopupCliente = function (ev, cidade, cliente) {

            if (cidade != null) {
                if ($scope.model.entidade.cliente == null) $scope.model.entidade.cliente = new Cliente();
                $scope.model.entidade.cliente.cidade = cidade;
            }

            cliente = cliente != null ? cliente : $scope.model.entidade.cliente;

            $scope.clienteDialog = $mdDialog;
            $scope.clienteDialog.show({
                controller: 'BuscaClienteDialogController',
                templateUrl: './modules/sisvarejo/ui/loja/venda/popup/popup-busca-cliente.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    local: [$scope, cliente]
                }
            })
                .then(function (result) {
                    $scope.model.entidade.cliente = result;
                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param ev
         */
        $scope.abrirPopupFormaPagamento = function (ev) {

            $scope.clienteDialog = $mdDialog;
            $scope.clienteDialog.show({
                controller: 'BuscaFormaPagamentoDialogController',
                templateUrl: './modules/sisvarejo/ui/financeiro/conta-receber/popup/popup-busca-forma-pagamento.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    local: [$scope]
                }
            })
                .then(function (result) {
                    $scope.model.entidade.formaPagamento = result;
                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param numero
         */
        $scope.verificarNfe = function (numero) {
            lojaService.verificarNfe(numero, {
                callback: function (result) {
                    if (result == false) {
                        $scope.model.invalidNfe = true;
                    } else {
                        $scope.model.invalidNfe = false;
                    }
                    $scope.$apply();

                }, errorHandler: function (message, error) {
                    $log.error(message);
                }
            });
        }
    });

    angular.module('sisvarejo').controller('BuscaFormaPagamentoDialogController', function ($scope, $mdDialog, $importService, $mdToast, local) {

        $importService("financeiroService");

        $scope.model = {
            entidade: new Cliente(),
            formaPagamentoDialog: local[0],
            filtros: {
                codigo: "",
                tipo: ""
            },
            content: []
        };

        // Habilita modo de edição se o botão de Exibir for acionado
        if (local[1] != null) {
            $scope.model.entidade = local[1];
            //$scope.model.viewMode = true;
        }

        // Identifica se a popup foi aberta voltando da popup de cidade
        $scope.model.entidade.cidade = local[2] == null ? $scope.model.entidade.cidade : local[2];

        /**
         *
         */
        $scope.listFormaPagamentoByFilters = function () {
            financeiroService.listFormasPagamentoByFilters($scope.model.filtros.id, $scope.model.filtros.tipo, {
                callback: function (result) {
                    $scope.model.content = result;
                    $scope.$apply();
                },
                errorHandler: function (message, error) {
                    $mdToast.showSimple(message);
                }
            });
        };

        /**
         *
         * @param entidade
         */
        $scope.salvarFormaPagamento = function (entidade) {
            financeiroService.insertFormaPagamento(entidade, {
                callback: function (result) {
                    var toast = $mdToast.simple()
                        .content('Registro salvo com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });
                    $mdDialog.hide(true);
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
         * @param formaPagamento
         */
        $scope.escolherFormaPagamento = function (formaPagamento) {
            $mdDialog.hide(formaPagamento);
        }

        /**
         *
         */
        $scope.cancelar = function () {
            $mdDialog.cancel();
        }

        $scope.abrirPopupCadastrar = function (ev) {
            $mdDialog.show({
                controller: 'FormaPagamentoDialogController',
                templateUrl: './modules/sisvarejo/ui/financeiro/forma-pagamento/popup/popup-forma-pagamento.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    entidadeExterna: null
                }
            })
                .then(function (result) {

                    $scope.model.formaPagamentoDialog.abrirPopupFormaPagamento(null);

                    var toast = $mdToast.simple()
                        .content('Registro salvo com sucesso!')
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right');
                    $mdToast.show(toast).then(function () {
                    });

                }, function () {
                    $scope.model.formaPagamentoDialog.abrirPopupFormaPagamento(null);
                });
        }

    })


}(window.angular));