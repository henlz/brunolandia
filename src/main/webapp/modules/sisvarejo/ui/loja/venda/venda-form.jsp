<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout-align="center center" layout="column" layout-margin layout-padding>

    <section layout-fill>
        <form name="vendaForm" layout-padding layout="column" layout-align="start center" width="90%">
            <md-subheader>
                <h3 ng-if="currentState == INSERT_STATE">Nova Venda</h3>
                <h3 ng-if="currentState == CANCEL_STATE">Cancelar Venda</h3>
            </md-subheader>

            <md-content layout="row" layout-margin ng-if="currentState == CANCEL_STATE">
                <md-input-container class="md-block">
                    <label>Observação</label>
                    <textarea width="400" name="observacao" ng-model="model.entidade.observacao"></textarea>

                    <div ng-messages="vendaForm.observacao.$error">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <md-button class="md-primary md-fab" ng-click="cancelarVenda(model.entidade)">
                    <i class="md-icon md-icon-cancel"></i>
                </md-button>
            </md-content>

            <md-content layout="row" layout-margin>

                <md-input-container>
                    <label>Número NFE</label>
                    <input type="text" name="numero" ng-model="model.entidade.numeroNfe"
                           ng-blur="verificarNfe(model.entidade.numeroNfe)" ng-readonly="currentState == CANCEL_STATE"
                           required>

                    <div ng-messages="vendaForm.numero.$error" ng-if="currentState == INSERT_STATE">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <md-input-container>
                    <label>Série</label>
                    <input name="serie" type="text" ng-model="model.entidade.serie" required
                           ng-readonly="currentState == CANCEL_STATE">

                    <div ng-messages="vendaForm.serie.$error" ng-if="currentState == INSERT_STATE">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <md-input-container>
                    <label>Modelo</label>
                    <input type="text" ng-model="model.entidade.modelo" required
                           ng-readonly="currentState == CANCEL_STATE">

                    <div ng-messages="vendaForm.modelo.$error" ng-if="currentState == INSERT_STATE">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <div layout="row" layout-align="center center">

                    <input style="width: 40px;" type="text" ng-model="model.codigoCliente"
                           ng-change="buscaClienteByCodigo()" ng-if="currentState == INSERT_STATE">

                    <md-input-container>
                        <label>Cliente</label>
                        <input type="text" name="cliente" ng-model="model.entidade.cliente.nome" readonly required/>

                        <div ng-messages="vendaForm.cliente.$error" ng-if="currentState == INSERT_STATE">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupCliente($event, null, null)"
                               aria-label="Procurar fornecedor" ng-if="currentState == INSERT_STATE">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>
                </div>

                <md-icon class="md-icon md-icon-error"
                         ng-if="currentState == INSERT_STATE && model.invalidNfe == true && model.entidade.numeroNfe != ''"
                         style="position: absolute; top: 18px; left: 188px;">
                    <md-tooltip>
                        Número de NFE já existente!
                    </md-tooltip>
                </md-icon>

            </md-content>
        </form>
    </section>

    <md-divider width="100%"></md-divider>

    <md-content layout="row" layout-align="center center" style="width:100%;">

        <md-input-container>
            <label>Data de emissão</label>
            <input type="date" ng-model="model.entidade.dataEmissao" name="dataEmissao" required
                   ng-readonly="currentState == CANCEL_STATE" ng-change="gerarContasAReceber()">

            <div ng-messages="vendaForm.dataEmissao.$error" ng-if="currentState == INSERT_STATE">
                <div ng-message="required">
                    Campo obrigatório.
                </div>
            </div>
        </md-input-container>

        <div layout="row" layout-align="center center">

            <input style="width: 40px;" type="text" ng-model="model.codigoCondicao" ng-change="buscaCondicaoByCodigo()"
                   ng-if="currentState == INSERT_STATE">

            <md-input-container>
                <label>Condição de pagamento</label>
                <input type="text" name="condicao" ng-model="model.entidade.condicaoPagamento.descricao" readonly/>

                <div ng-messages="vendaForm.condicao.$error" ng-if="currentState == INSERT_STATE">
                    <div ng-message="required">
                        Campo obrigatório.
                    </div>
                </div>
            </md-input-container>

            <md-button class="md-icon-button" ng-click="abrirPopupCondicao()" aria-label="Procurar condicao"
                       ng-if="currentState == INSERT_STATE">
                <i class="md-icon md-icon-search"></i>
            </md-button>
        </div>

        <md-input-container>
            <label>Data de chegada</label>
            <input type="date" name="dataChegada" ng-model="model.entidade.dataChegada"
                   ng-readonly="currentState == CANCEL_STATE">
        </md-input-container>

    </md-content>

    <md-divider width="100%"></md-divider>

    <section layout="column" layout-align="start start" layout-fill layout-margin>

        <md-subheader>
            <h3>Produtos</h3>
            <md-button style="position: absolute; left: 74px; bottom: -18px;" class="md-icon-button md-raised"
                       ng-click="abrirPopupProduto($event)" aria-label="Buscar produto"
                       ng-if="currentState != CANCEL_STATE" ng-disabled="model.invalidNfe == true">
                <i class="md-icon md-icon-add"></i>
            </md-button>
        </md-subheader>

        <md-content width="100%">
            <md-data-table-container>
                <table md-data-table>
                    <thead md-trim-column-names>
                    <tr>
                        <th>Código</th>
                        <th>Descrição</th>
                        <th>Quantidade</th>
                        <th>Valor</th>
                        <th>Desconto (R$)</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="itemVenda in model.entidade.itensVenda | orderBy: 'codigo'">
                        <td>
                            {{:: itemVenda.produto.codigo}}
                        </td>
                        <td>
                            {{:: itemVenda.produto.descricao}}
                        </td>
                        <td>
                            <input style="width: 95px;" type="number" ng-model="itemVenda.quantidade" min="1"
                                   ng-change="gerarContasAReceber();" ng-if="currentState == INSERT_STATE">
                            <span ng-if="currentState == CANCEL_STATE">{{::itemVenda.quantidade}}</span>
                        </td>
                        <td>
                            {{ itemVenda.produto.precoVenda * itemVenda.quantidade - itemVenda.desconto | currency: 'R$
                            '}}
                        </td>

                        <td>
                            <input style="width: 95px;" type="number" ng-model="itemVenda.desconto" min="0"
                                   ng-change="gerarContasAReceber();" ng-if="currentState == INSERT_STATE">
                            <span ng-if="currentState == CANCEL_STATE">{{itemVenda.desconto}}</span>
                        </td>
                        <td layout="row">
                            <md-button class="md-icon-button" ng-click="excluirProduto($event, itemVenda)"
                                       aria-label="Excluir produto" ng-if="currentState != CANCEL_STATE">
                                <i class="md-icon md-icon-delete"></i>
                            </md-button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </md-data-table-container>
        </md-content>

        <md-content width="100%" layout="column" layout-align="start center">
            <h3>Sub-total </h3>

            <p>{{ getVendaTotal(model.entidade) | currency: 'R$ '}}</p>
        </md-content>

    </section>

    <section layout="column" layout-align="start start" layout-fill layout-margin>

        <md-subheader>
            <h3>Contas a receber</h3>
        </md-subheader>

        <md-content width="100%">
            <md-data-table-container>
                <table md-data-table>
                    <thead md-trim-column-names>
                    <tr>
                        <th>Nº parcela</th>
                        <th>Data de vencimento</th>
                        <th>Porcentagem</th>
                        <th>Valor R$</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="conta in model.entidade.contasAReceber | orderBy: 'numeroParcela'">
                        <td> {{ conta.numeroParcela}}</td>
                        <td> {{ conta.vencimento | date: 'dd/MM/yyyy'}}</td>
                        <td> {{ conta.percentual}}%</td>
                        <td> {{ conta.valor | currency}}</td>
                    </tr>
                    </tbody>
                </table>
            </md-data-table-container>
        </md-content>
    </section>

    <md-divider width="100%"></md-divider>
    <section layout="row" layout-align="start center" layout-fill layout-margin>
        <md-divider></md-divider>
        <md-content width="100%" layout-padding layout="row" layout-align="end end">

            <md-button class="md-icon-button md-fab" ui-sref="venda.listar" aria-label="Cancelar cadastro">
                <i class="md-icon md-icon-cancel md-icon-lg"></i>
            </md-button>
            <md-button class="md-primary md-icon-button md-fab" ng-if="currentState == INSERT_STATE"
                       ng-click="salvarVenda(model.entidade)"
                       aria-label="Salvar venda">
                <i class="md-icon md-icon-save md-icon-lg"></i>
            </md-button>
        </md-content>
    </section>

</md-content>

</html>