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
        <md-content layout-padding layout="column" layout-align="start center" width="90%">
            <md-subheader>
                <h3>{{:: currentState == INSERT_STATE ? 'Nova Compra' : 'Alterar Compra' }}</h3>
            </md-subheader>

            <md-content layout="row" layout-margin>
                <div layout="row" layout-align="center center">

                    <div layout="column">
                        <b>Fornecedor</b>

                        <div layout="row" layout-align="center center">
                            <span>{{model.entidade.fornecedor.nomeFantasia}}</span>
                            <md-button class="md-icon-button" ng-click="abrirPopupFornecedor()"
                                       aria-label="Procurar fornecedor">
                                <i class="md-icon md-icon-search"></i>
                            </md-button>
                        </div>
                    </div>
                </div>

            </md-content>

        </md-content>
    </section>

    <md-divider width="100%"></md-divider>

    <md-content layout="row" layout-align="center center" style="width:100%;">

        <md-input-container>
            <label>Número NFE</label>
            <input type="text" ng-model="model.entidade.numeroNfe" ng-blur="verificarNfe(model.entidade.numeroNfe)"
                   required>
        </md-input-container>

        <md-icon class="md-icon md-icon-error" ng-if="model.invalidNfe == true" style="position: absolute; top: 31px;">
            <md-tooltip>
                Número de NFE já existente!
            </md-tooltip>
        </md-icon>

    </md-content>

    <md-divider width="100%"></md-divider>

    <md-content layout="row" layout-wrap>

        <md-input-container>
            <label>Dados NFE</label>
            <input type="text" ng-model="model.entidade.dadosNfe" required>
        </md-input-container>

        <md-input-container>
            <label>Base ICMS</label>
            <input type="text" ng-model="model.entidade.baseIcms" required>
        </md-input-container>

        <md-input-container>
            <label>Base ICMS St</label>
            <input type="text" ng-model="model.entidade.baseIcmsSt" required>
        </md-input-container>

        <md-input-container>
            <label>Valor ICMS</label>
            <input type="text" ng-model="model.entidade.valorIcms" required>
        </md-input-container>

        <md-input-container>
            <label>Valor ICMS Substituição</label>
            <input type="text" ng-model="model.entidade.valorIcmsSubstituicao" required>
        </md-input-container>

        <md-input-container>
            <label>Valor Frete</label>
            <input type="text" ng-model="model.entidade.valorFrete" required>
        </md-input-container>

        <md-input-container>
            <label>Valor Seguro</label>
            <input type="text" ng-model="model.entidade.valorSeguro" required>
        </md-input-container>

        <md-input-container>
            <label>Desconto</label>
            <input type="text" ng-model="model.entidade.desconto" required>
        </md-input-container>

        <md-input-container>
            <label>Outras Despesas</label>
            <input type="text" ng-model="model.entidade.outrasDespesas" required>
        </md-input-container>

        <md-input-container>
            <label>Valor Total IPI</label>
            <input type="text" ng-model="model.entidade.valorTotalIpi" required>
        </md-input-container>

        <md-input-container>
            <label>Chave Acesso</label>
            <input type="text" ng-model="model.entidade.chaveAcesso" required>
        </md-input-container>

    </md-content>

    <section layout="column" layout-align="start start" layout-fill layout-margin>

        <md-subheader>
            <h3>Produtos</h3>
            <md-button style="position: absolute; left: 74px; bottom: -18px;" class="md-icon-button md-raised"
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
                        <th>Em estoque</th>
                        <th>Quantidade a comprar</th>
                        <th>Unidade</th>
                        <th style="width: 25px;">CST</th>
                        <th>NCM</th>
                        <th>Preço unitário (custo)</th>
                        <th>Preço total</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="itemVenda in model.entidade.itensCompra | orderBy: 'codigo'">
                        <td>
                            {{:: itemVenda.produto.codigo}}
                        </td>
                        <td>
                            {{:: itemVenda.produto.descricao}}
                        </td>
                        <td>
                            {{:: itemVenda.produto.quantidade}}
                        </td>
                        <td>
                            <input style="width: 95px;" type="number" ng-model="itemVenda.quantidade">
                        </td>
                        <td>
                            <input style="width: 95px;" type="text" ng-model="itemVenda.unidade">
                        </td>
                        <td>
                            <input style="width: 95px;" type="number" ng-model="itemVenda.cst">
                        </td>
                        <td>
                            <input style="width: 95px;" type="number" ng-model="itemVenda.ncm">
                        </td>
                        <td>
                            <input style="width: 95px;" type="number" ng-model="itemVenda.precoCompra">
                        </td>
                        <td>
                            {{ itemVenda.precoCompra * itemVenda.quantidade | currency: 'R$ '}}
                        </td>
                        <td layout="row">
                            <md-button class="md-icon-button" ng-click="excluirProduto($event, itemVenda)"
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