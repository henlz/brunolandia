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
        <form name="compraForm" layout-padding layout="column" layout-align="start center" width="90%">
            <md-subheader>
                <h3 ng-if="currentState == INSERT_STATE">Nova Compra</h3>
                <h3 ng-if="currentState == CANCEL_STATE">Cancelar Compra</h3>
            </md-subheader>

            <md-content layout="row" layout-margin ng-if="currentState == CANCEL_STATE">
                <md-input-container class="md-block">
                    <label>Observação</label>
                    <textarea width="400" name="observacao" ng-model="model.entidade.observacao"></textarea>
                </md-input-container>

                <md-button class="md-primary md-fab" ng-click="cancelarCompra(model.entidade)">
                    <i class="md-icon md-icon-cancel"></i>
                </md-button>
            </md-content>

            <md-content layout="row" layout-margin>

                <md-input-container>
                    <label>Número NFE</label>
                    <input type="text" maxlength="255" name="numero" ng-model="model.entidade.numeroNfe" ng-readonly="currentState == CANCEL_STATE"
                           ng-blur="verificarNfe(model.entidade.numeroNfe, model.entidade.serie, model.entidade.modelo)"
                           required>

                    <div ng-messages="compraForm.numero.$error">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <md-input-container>
                    <label>Série</label>
                    <input name="serie" ng-blur="verificarNfe(model.entidade.numeroNfe, model.entidade.serie, model.entidade.modelo)" type="text" maxlength="255" ng-model="model.entidade.serie" ng-readonly="currentState == CANCEL_STATE" required>

                    <div ng-messages="compraForm.serie.$error">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <md-input-container>
                    <label>Modelo</label>
                    <input type="text" maxlength="255" ng-model="model.entidade.modelo" required ng-blur="verificarNfe(model.entidade.numeroNfe, model.entidade.serie, model.entidade.modelo)" ng-readonly="currentState == CANCEL_STATE">

                    <div ng-messages="compraForm.modelo.$error">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <div layout="row" layout-align="center center">

                    <input style="width: 40px;" type="text" maxlength="255" ng-model="model.codigoFornecedor" ng-if="currentState == INSERT_STATE"
                           ng-change="buscaFornecedorByCodigo(false)">

                    <md-input-container>
                        <label>Fornecedor</label>
                        <input type="text" maxlength="255" name="fornecedor" ng-model="model.entidade.fornecedor.razaoSocial" readonly/>

                        <div ng-messages="compraForm.fornecedor.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupFornecedor($event, null, null, false)" ng-if="currentState == INSERT_STATE"
                               aria-label="Procurar fornecedor">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>
                </div>

                <md-icon class="md-icon md-icon-error" ng-if="currentState == INSERT_STATE && model.invalidNfe == true && model.entidade.numeroNfe != ''"
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
            <input type="date" ng-model="model.entidade.dataEmissao" name="dataEmissao" ng-change="gerarContasPagar()" required ng-readonly="currentState == CANCEL_STATE" max="today">

            <div ng-messages="compraForm.dataEmissao.$error">
                <div ng-message="required">
                    Campo obrigatório.
                </div>
            </div>
        </md-input-container>

        <div layout="row" layout-align="center center">

            <input style="width: 40px;" type="text" maxlength="255" ng-model="model.codigoCondicao" ng-change="buscaCondicaoByCodigo()" ng-if="currentState == INSERT_STATE">

            <md-input-container>
                <label>Condição de pagamento</label>
                <input type="text" maxlength="255" name="condicao" ng-model="model.entidade.condicaoPagamento.descricao" readonly ng-readonly="currentState == CANCEL_STATE"/>

                <div ng-messages="compraForm.condicao.$error">
                    <div ng-message="required">
                        Campo obrigatório.
                    </div>
                </div>
            </md-input-container>

            <md-button class="md-icon-button" ng-click="abrirPopupCondicao()" aria-label="Procurar condicao" ng-if="currentState == INSERT_STATE">
                <i class="md-icon md-icon-search"></i>
            </md-button>
        </div>

        <md-input-container>
            <label>Data de chegada</label>
            <input type="date" name="dataChegada" ng-model="model.entidade.dataChegada" ng-readonly="currentState == CANCEL_STATE">
        </md-input-container>

    </md-content>

    <md-divider width="100%"></md-divider>

    <section layout="column" layout-align="start start" layout-fill layout-margin>

        <md-subheader>
            <h3>Produtos</h3>
            <md-button style="position: absolute; left: 74px; bottom: -18px;" class="md-icon-button md-raised" ng-disabled="model.invalidNfe == true"
                       ng-click="abrirPopupProduto($event)" aria-label="Buscar produto">
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
                        <th>Quantidade a comprar</th>
                        <th>Preço unitário (custo)</th>
                        <th>Preço total</th>
                        <th>Aliq. ICMS</th>
                        <th>Valor ICMS</th>
                        <th>Aliq. IPI</th>
                        <th>Valor IPI</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="itemCompra in model.entidade.itensCompra | orderBy: 'codigo'">
                        <td>
                            {{:: itemCompra.produto.codigo}}
                        </td>
                        <td>
                            {{:: itemCompra.produto.descricao}}
                        </td>
                        <td>
                            <input ng-if="currentState == INSERT_STATE" style="width: 95px;" type="number" ng-model="itemCompra.quantidade" ng-change="calculaImpostos(); gerarContasPagar();" min="1">
                            <span ng-if="currentState == CANCEL_STATE">{{::itemCompra.quantidade}}</span>
                        </td>
                        <td>
                            <input ng-if="currentState == INSERT_STATE" style="width: 95px;" type="number" ng-model="itemCompra.precoCompra" ng-change="calculaImpostos(); gerarContasPagar();" min="1">
                            <span ng-if="currentState == CANCEL_STATE">{{::itemCompra.precoCompra}}</span>
                        </td>
                        <td>
                            {{ itemCompra.precoCompra * itemCompra.quantidade | currency: 'R$ '}}
                        </td>
                        <td>
                            {{ itemCompra.produto.icms.porcentagem }} %
                        </td>
                        <td>
                            {{ itemCompra.produto.icms.porcentagem * itemCompra.precoCompra / 100 | currency}}
                        </td>
                        <td>
                            {{ itemCompra.produto.IPI }} %
                        </td>
                        <td>
                            {{ itemCompra.produto.IPI * itemCompra.precoCompra / 100 | currency }}
                        </td>
                        <td layout="row" ng-if="!currentState == CANCEL_STATE">
                            <md-button class="md-icon-button" ng-click="excluirProduto($event, itemCompra)"
                                       aria-label="Excluir produto">
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

            <p>{{ getCompraTotal(model.entidade) | currency: 'R$ '}}</p>
        </md-content>

    </section>

    <md-content layout="row" layout-wrap>

        <md-input-container>
            <label>Base de calculo ICMS</label>
            <input type="number" ng-model="model.fiscal.baseCalculo" readonly>
        </md-input-container>

        <md-input-container>
            <label>Total ICMS</label>
            <input type="number" ng-model="model.fiscal.totalIcms" readonly>
        </md-input-container>

        <md-input-container>
            <label>Total IPI</label>
            <input type="number" ng-model="model.fiscal.totalIpi" readonly>
        </md-input-container>

        <md-input-container>
            <label>Totais Produto</label>
            <input type="number" ng-model="model.fiscal.totalProduto" readonly>
        </md-input-container>

    </md-content>

    <md-content layout="row" layout-margin>

        <div layout="row" layout-align="center center">

            <input style="width: 40px;" type="text" maxlength="255" ng-model="model.codigoTransportadora" ng-change="buscaFornecedorByCodigo(true)" ng-if="currentState == INSERT_STATE">

            <md-input-container>
                <label>Transportadora</label>
                <input type="text" maxlength="255" name="transportadora" ng-model="model.entidade.transportadora.razaoSocial" readonly/>
            </md-input-container>

            <md-button class="md-icon-button" ng-click="abrirPopupFornecedor($event, null, null, true)"
                       aria-label="Procurar transportadora" ng-if="currentState == INSERT_STATE">
                <i class="md-icon md-icon-search"></i>
            </md-button>
        </div>

        <md-input-container>
            <label>Outras Despesas</label>
            <input type="number" ng-model="model.entidade.outrasDespesas" min="0" ng-readonly="currentState == CANCEL_STATE" ng-change="calculaImpostos(); gerarContasPagar();">
        </md-input-container>

        <md-input-container>
            <label>Frete</label>
            <input name="frete" type="number" ng-model="model.entidade.valorFrete" min="0" ng-readonly="currentState == CANCEL_STATE" ng-change="calculaImpostos(); gerarContasPagar();">
        </md-input-container>

    </md-content>

    <section layout="column" layout-align="start start" layout-fill layout-margin>

        <md-subheader>
            <h3>Contas a pagar</h3>
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
                    <tr ng-repeat="conta in model.entidade.contasAPagar | orderBy: 'numeroParcela'">
                        <td> {{ conta.numeroParcela}}</td>
                        <td> {{ conta.vencimento | date: 'dd/MM/yyyy'}}</td>
                        <td> {{ conta.percentual}}</td>
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

            <md-button class="md-icon-button md-fab" ui-sref="compra.listar" aria-label="Cancelar cadastro">
                <i class="md-icon md-icon-cancel md-icon-lg"></i>
            </md-button>
            <md-button class="md-primary md-icon-button md-fab" ng-if="currentState == INSERT_STATE"
                       ng-click="salvarCompra(model.entidade)"
                       aria-label="Salvar condição">
                <i class="md-icon md-icon-save md-icon-lg"></i>
            </md-button>
        </md-content>
    </section>

</md-content>

</html>