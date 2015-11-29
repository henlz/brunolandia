(function (angular) {
    'use strict';


    angular.module('sisvarejo').controller('CompraController', function ($scope, $rootScope, $state, $http, $mdToast, $window, $log, $injector, $importService, $mdDialog, $mdBottomSheet) {

        $importService("estoqueService");

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
        $scope.LIST_STATE = "compra.listar";
        /**
         * Variável estática que representa
         * o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "compra.detalhe";
        /**
         * Variável estática que representa
         * o estado para a criação de registros.
         */
        $scope.INSERT_STATE = "compra.cadastrar";
        /**
         * Variável estática que representa
         * o estado para a edição de registros.
         */
        $scope.UPDATE_STATE = "compra.alterar";


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
                itensCompra: []
            },
            content: [],
            query: {
                order: 'descricao'
            }
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
            pageRequest.size = 10;
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

            $scope.model.entidade = new Compra();
            $scope.model.entidade.itensCompra = [];
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

            estoqueService.findCompraById(id, {
                callback: function (result) {
                    $scope.model.entidade = result;

                    $scope.currentState = $scope.UPDATE_STATE;
                    $state.go($scope.UPDATE_STATE);
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
            estoqueService.listCompras({
                callback: function (result) {
                    $scope.model.content = result;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $log.error(message);
                }
            })
        }

        /**
         *
         * @param compra
         * @returns {number}
         */
        $scope.getCompraTotal = function(compra) {
            if (compra.itensCompra == null || compra.itensCompra.length == 0) {
                return 0;
            }
            var total = 0;
            for (var i = 0; compra.itensCompra.length > i; i++){
                total += compra.itensCompra[i].quantidade * compra.itensCompra[i].precoCompra;
            }
            return total;
        }

        /**
         *
         * @param ev
         */
        $scope.abrirPopupFornecedor = function (ev, cidade, fornecedor) {

            if (cidade != null) {
                if ($scope.model.entidade.fornecedor == null) $scope.model.entidade.fornecedor = new Fornecedor();
                $scope.model.entidade.fornecedor.cidade = cidade;
            }

            fornecedor = fornecedor != null ? fornecedor : $scope.model.entidade.fornecedor;

            $scope.fornecedorDialog = $mdDialog;
            $scope.fornecedorDialog.show({
                controller: BuscaFornecedorDialogController,
                templateUrl: './modules/sisvarejo/ui/loja/cliente/popup/popup-busca-fornecedor.html',
                targetEvent: ev,
                hasBackdrop: true,
                locals: {
                    local: [$scope, fornecedor]
                }
            })
                .then(function (result) {
                    $scope.model.entidade.fornecedor = result;
                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param ev
         */
        $scope.abrirPopupProduto = function (ev) {
            $mdDialog.show({
                controller: ProdutoDialogController,
                templateUrl: './modules/sisvarejo/ui/loja/venda/popup/popup-busca-produto.html',
                targetEvent: ev,
                hasBackdrop: true
            })
                .then(function (result) {

                    var itemCompra = new ItemCompra();
                    itemCompra.produto = result;
                    itemCompra.quantidade = 1;
                    $scope.model.entidade.itensCompra.push(itemCompra);
                }, function () {
                    //tratar o "cancelar" da popup
                });
        }

        /**
         *
         * @param numero
         */
        $scope.verificarNfe = function(numero) {

            $log.log(numero);

            estoqueService.verificarNfe(numero, {
                callback:function(result) {
                if (result == false) {
                    $scope.model.invalidNfe = true;
                }  else {
                    $scope.model.invalidNfe = false;
                }


            }, errorHandler: function(message, error){
                $log.error(message);
            }});
        }

        /**
         *
         * @param ev
         * @param entidade
         */
        $scope.abrirPopupAlterarProduto = function (ev, entidade) {
            $mdDialog.show({
                controller: CompraDialogController,
                templateUrl: './modules/sisvarejo/ui/estoque/compra/popup/popup-compra.html',
                targetEvent: ev,
                hasBackdrop: true,
                bindToController: true,
                locals: {
                    entidadeExterna: angular.copy(entidade),
                    isEditing: true
                }
            })
                .then(function (result) {

                }, function () {

                });
        }

        /**
         *
         * @param ev
         * @param parcela
         */
        $scope.excluirProduto = function (ev, itemCompra) {
            var i = $scope.findByIdInArray($scope.model.entidade.itensCompra, itemCompra);
            $scope.model.entidade.itensCompra.splice(i, 1);
        }

        /**
         *
         * @param entidade
         * @returns {boolean}
         */
        $scope.validarProdutos = function (entidade) {
            for (var i = 0; entidade.itensCompra.length > i; i++) {
                if (entidade.itensCompra[i].quantidade >  entidade.itensCompra[i].produto.quantidade){
                    return false
                }
            }

            return true;
        }

        /**
         *
         * @param entidade
         */
        $scope.salvarCompra = function (entidade) {
            if ($scope.validarProdutos(entidade)) {
                estoqueService.insertCompra(entidade, {
                    callback: function (result) {
                        var toast = $mdToast.simple()
                            .content('Registro salvo com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });
                        $state.go($scope.LIST_STATE);
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
                var toast = $mdToast.simple()
                    .content('O percentual das parcelas devem totalizar em 100%!')
                    .action('Fechar')
                    .highlightAction(false)
                    .position('bottom left right');
                $mdToast.show(toast).then(function () {
                });
            }
        };

        /**
         *
         * @param entidade
         */
        $scope.alterarCompra = function (entidade) {
            return null;
            if ($scope.validarProdutos(entidade)) {
                estoqueService.updateCompra(entidade, {
                    callback: function (result) {
                        var toast = $mdToast.simple()
                            .content('Registro atualizado com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });
                        $state.go($scope.LIST_STATE);
                        $scope.$apply();
                    },
                    errorHandler: function (message, error) {
                        $log.error(message);
                    }
                });
            } else {
                var toast = $mdToast.simple()
                    .content('O percentual das produtos devem totalizar em 100%!')
                    .action('Fechar')
                    .highlightAction(false)
                    .position('bottom left right');
                $mdToast.show(toast).then(function () {
                });
            }
        };

        /**
         *
         * @param ev
         * @param id
         */
        $scope.excluirCompra = function (ev, compra) {
            return null;
            var confirm = $mdDialog.confirm()
                .title('Exclusão de Compra')
                .content('Tem certeza que deseja excluir o(s) registros(s)? Esta operação não poderá ser desfeita.')
                .ariaLabel('Exclusão de Compra')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(ev);

            $mdDialog.show(confirm).then(function () {
                estoqueService.removeCondicoes([compra], {
                    callback: function (result) {
                        var toast = $mdToast.simple()
                            .content('Registro(s) excluído(s) com sucesso!')
                            .action('Fechar')
                            .highlightAction(false)
                            .position('bottom left right');
                        $mdToast.show(toast).then(function () {
                        });

                        var i = $scope.findByIdInArray($scope.model.content, compra);
                        $scope.model.content.splice(i, 1);
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

            if ($scope.data.itensExcluir.length == 0 && selectedItens.length > 0) {
                $rootScope.$broadcast('showEitsBottomSheetEvent');
            } else if ($scope.data.itensExcluir.length > 0 && selectedItens.length == 0) {
                $rootScope.$broadcast('showEitsBottomSheetEvent');
            }

            $scope.data.itensExcluir = angular.copy(selectedItens);
        }

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
            $scope.abrirPopupAlterarEntidade(null, item);
        }
    });

    /**
     * Controller da popup de Princípio e Diretriz
     */
    function CompraDialogController($scope, $mdDialog, $importService, $mdToast, entidadeExterna, isEditing) {

        $importService("estoqueService");

        /**
         *
         */
        $scope.entidade = entidadeExterna;
        $scope.modoAlteracao = isEditing;

        /**
         *
         */
        $scope.cancelar = function () {
            $mdDialog.cancel();
        };

        /**
         *
         */
        $scope.carregarListaFormasPagamento = function () {
            return null;
            estoqueService.listFormasPagamentoByFilters(null, {
                callback: function (result) {
                    $scope.formasPagamento = result;
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
            })
        }

        /**
         *
         * @returns {boolean}
         */
        $scope.validaForm = function () {
            if (!$scope.produtoForm.$valid) {
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

                $mdDialog.hide($scope.entidade);

            }
        };
    }

    /**
     * Controller da popup de Buscar Fornecedores
     */
    function BuscaFornecedorDialogController($scope, $mdDialog, $importService, $mdToast, local) {

        $importService("estoqueService");

        $scope.model = {
            entidade: new Fornecedor(),
            fornecedorDialog: local[0],
            filtros: {
                nome: "",
                apelido: "",
                cpf: "",
                rg: ""
            },
            content: []
        };

        // Habilita modo de edição se o botão de Exibir for acionado
        if (local[1] != null) {
            $scope.model.entidade = local[1];
            //$scope.model.viewMode = true;
        }

        // Identifica se a popup foi aberta voltando da popup de cidade
        $scope.model.entidade.cidade = local[2] == null ? $scope.model.entidade.cidade: local[2];

        /**
         *
         */
        $scope.listFornecedoresByFilters = function() {

            estoqueService.listFornecedoresByFilters($scope.model.filtros.razaoSocial, $scope.model.filtros.nomeFantasia,
                $scope.model.filtros.telefone, $scope.model.filtros.cnpj, $scope.model.filtros.representante, {
                callback: function(result) {
                    $scope.model.content = result;
                    $scope.$apply();
                },
                errorHandler: function(message, error) {
                    $mdToast.showSimple(message);
                }
            });
        };

        /**
         *
         * @param entidade
         */
        $scope.salvarFornecedor = function (entidade) {
            estoqueService.insertFornecedor(entidade, {
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
         * @param cliente
         */
        $scope.escolherFornecedor = function(fornecedor) {
            $mdDialog.hide(fornecedor);
        }

        /**
         *
         */
        $scope.cancelar = function() {
            $mdDialog.cancel();
        }

        /**
         *
         * @param ev
         */
        $scope.abrirPopupCidade = function (ev) {
            $scope.model.flag = false;
            $mdDialog.show({
                    controller: 'CidadeDialogController',
                    templateUrl: './modules/sisvarejo/ui/localizacao/cidade/popup/popup-busca-cidade.html',
                    targetEvent: ev,
                    hasBackdrop: true,
                    preserveScope: true,
                    clickOutsideToClose: false,
                    locals: {
                        entidadeExterna: null
                    }
                })
                .then(function (result) {

                    $scope.abrirPopupCadastrar($scope.model.entidade, result, false);

                }, function () {
                    $scope.abrirPopupCadastrar($scope.model.entidade, null, false);
            });
        };

        /**
         *
         * @param ev
         */
        $scope.abrirPopupCadastrar = function (entidade, cidade, flag) {
            $mdDialog.show({
                    controller: BuscaFornecedorDialogController,
                    templateUrl: './modules/sisvarejo/ui/estoque/fornecedor/popup/popup-cadastra-fornecedor.html',
                    hasBackdrop: true,
                    preserveScope: true,
                    clickOutsideToClose: false,
                    locals: {
                        local: [$scope.model.fornecedorDialog, entidade, cidade]
                    }
                })
                .then(function (result) {
                    if (result == true)
                        $scope.model.fornecedorDialog.abrirPopupFornecedor(null, null);
                }, function () {
                    $scope.model.fornecedorDialog.abrirPopupFornecedor(null, null);
                });
        };
    }

    /**
     * Controller da popup de Buscar Clientes
     */
    function ProdutoDialogController($scope, $mdDialog, $importService, $mdToast) {

        $importService('estoqueService');

        $scope.model = {
            filtros: {
                codigo: "",
                descricao: ""
            },
            page: null
        };

        /**
         *
         */
        $scope.listProdutosByFilters = function () {
            var pageRequest = new PageRequest();
            pageRequest.size = 20;
            pageRequest.page = 0;
            pageRequest.sort = {
                orders: [
                    {direction: 'ASC', property: 'id'}
                ]
            }
            estoqueService.listProdutosByFilters($scope.model.filtros.codigo, $scope.model.filtros.descricao, pageRequest, {
                //estoqueService.listProdutos({
                callback: function (result) {
                    $scope.model.page = result;
                    console.log(result.content.length)
                },
                errorHandler: function (error, message) {
                    $mdToast.showSimple(message);
                }
            })
        };

        $scope.cancelar = function () {
            $mdDialog.cancel();
        }

        $scope.escolherProduto = function (produto) {
            $mdDialog.hide(produto);
        }
    }


}(window.angular));