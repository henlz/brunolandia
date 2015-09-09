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
                <h3>Nova Venda</h3>
            </md-subheader>

            <md-content layout="row" layout-margin>
                <div layout="row" layout-align="center center">


                    <div layout="column">
                        <b>Cliente</b>
                        <div layout="row" layout-align="center center">
                            <span>{{model.entidade.cliente.nome}}</span>
                            <md-button class="md-icon-button" ng-click="abrirPopupCliente()" aria-label="Procurar cliente">
                                <i class="md-icon md-icon-search"></i>
                            </md-button>
                        </div>
                    </div>
                    <!--<md-input-container>-->
                        <!--<label>Cliente</label>-->
                        <!--<span ng-cloak>{{model.entidade.cliente.nome}}</span>-->
                    <!--</md-input-container>-->

                    <!--<md-button class="md-icon-button" ng-click="abrirPopupCliente()" aria-label="Procurar cliente">-->
                        <!--<i class="md-icon md-icon-search"></i>-->
                    <!--</md-button>-->
                </div>

                <div layout="column">
                    <b>Condição de pagamento</b>
                    <span>{{model.entidade.cliente.condicao.descricao}}</span>
                </div>


            </md-content>

        </md-content>
    </section>
    <md-divider width="100%"></md-divider>
    <section layout="column" layout-align="start start" layout-fill layout-margin>

        <md-subheader>
            <h3>Produtos</h3>
            <md-button style="position: absolute; left: 74px; bottom: 0px;" class="md-icon-button md-raised"
                       ng-click="abrirPopupProduto($event)" aria-label="Buscar produto">
                <i class="md-icon md-icon-add"></i>
            </md-button>
        </md-subheader>

        <md-content width="100%">
            <md-data-table-container>
                <table md-data-table>
                    <thead md-trim-column-names>
                    <tr>
                        <th style="width: 25px">Código</th>
                        <th>Descrição</th>
                        <th>Quantidade em estoque</th>
                        <th>Quantidade a vender</th>
                        <th>Preço unitário</th>
                        <th>Preço total</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="itemVenda in model.entidade.itensVenda | orderBy: 'codigo'">
                        <td>{{:: itemVenda.produto.codigo}}</td>
                        <td>{{:: itemVenda.produto.descricao}}</td>
                        <td>{{:: itemVenda.produto.quantidade}}</td>
                        <td><input type="number" ng-model="itemVenda.quantidade" min="0" max="{{itemVenda.produto.quantidade}}"></td>
                        <td>{{:: itemVenda.produto.precoVenda | currency: 'R$ '}}</td>
                        <td>{{ itemVenda.produto.precoVenda * itemVenda.quantidade | currency: 'R$ '}}</td>
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
            <p>{{ getVendaTotal(model.entidade) | currency: 'R$ '}}</p>
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
                       aria-label="Salvar condição">
                <i class="md-icon md-icon-save md-icon-lg"></i>
            </md-button>
            <md-button class="md-primary md-icon-button md-fab" ng-if="currentState == UPDATE_STATE"
                       ng-click="alterarCondicao(model.entidade)"
                       aria-label="Alterar condição">
                <i class="md-icon md-icon-save md-icon-lg"></i>
            </md-button>
        </md-content>
    </section>

</md-content>

</html>