<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

    <md-toolbar class="md-tall md-toolbar-main">
        <h2 class="md-toolbar-tools" style="padding-left: 133px;">
            <span>Produtos</span>
        </h2>

        <md-button class="md-fab md-primary md-hue-2 grid-add-button" aria-label="Adicionar" ui-sref="produto.cadastrar">
            <i class="md-icon md-icon-add md-icon-lg"></i>
        </md-button>
    </md-toolbar>

    <md-data-table-container>
        <table md-data-table>
            <thead md-order="model.query.order" md-trim-column-names>
            <tr>
                <th order-by="codigo">Código</th>
                <th order-by="descricao">Descrição</th>
                <th order-by="grupo">Grupo</th>
                <th order-by="precoVenda">Preço de Venda</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="produto in model.content | orderBy: model.query.order">
                <td>{{::produto.id}}</td>
                <td>{{::produto.descricao}}</td>
                <td>
                    {{:: produto.genero.genero}}
                </td>
                <td>{{:: produto.precoVenda | currency: "R$ "}}</td>
                <td layout="row">
                    <md-button class="md-icon-button" ui-sref="produto.alterar({id: produto.id})" aria-label="Alterar produto">
                        <i class="md-icon md-icon-edit"></i>
                    </md-button>
                    <md-button class="md-icon-button" ng-click="excluirProduto($event, produto)" aria-label="Excluir produto">
                        <i class="md-icon md-icon-delete"></i>
                    </md-button>
                </td>
            </tr>
            </tbody>
        </table>
    </md-data-table-container>

</html>