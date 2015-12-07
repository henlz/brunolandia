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
        <span>Clientes</span>
    </h2>

    <md-button class="md-fab md-primary md-hue-2 grid-add-button" aria-label="Adicionar" ui-sref="cliente.cadastrar">
        <i class="md-icon md-icon-add md-icon-lg"></i>
    </md-button>
</md-toolbar>

<md-content layout="row" layout-align="center center" layout-wrap>
    <md-input-container>
        <label>Nome</label>
        <input type="text" ng-model="model.filtros.nome">
    </md-input-container>
    <md-input-container>
        <label>Apelido</label>
        <input type="text" ng-model="model.filtros.apelido">
    </md-input-container>
    <md-input-container>
        <label>CPF/CNPJ</label>
        <input type="text" ng-model="model.filtros.cpf">
    </md-input-container>
    <md-input-container>
        <label>RG</label>
        <input type="text" ng-model="model.filtros.rg">
    </md-input-container>
    <md-button ng-click="carregarLista()" class="md-icon-button">
        <i class="md-icon md-icon-search"></i>
    </md-button>
</md-content>

<md-data-table-container>
    <table md-data-table>
        <thead md-order="model.query.order" md-trim-column-names>
        <tr>
            <th order-by="razaoSocial">Nome</th>
            <th order-by="email">Email</th>
            <th order-by="telefone">Telefone</th>
            <th order-by="cidade.nome">Cidade</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="cliente in model.content | orderBy: model.query.order">
            <td>{{::cliente.nome}}</td>
            <td>{{::cliente.email}}</td>
            <td>{{::cliente.telefone}}</td>
            <td>{{::cliente.cidade.nome}}</td>
            <td layout="row">
                <md-button class="md-icon-button" ui-sref="cliente.alterar({id: cliente.id})"
                           aria-label="Alterar cliente">
                    <i class="md-icon md-icon-edit"></i>
                </md-button>
                 <md-button class="md-icon-button" ng-click="excluirCliente($event, cliente)"
                            aria-label="Excluir cliente">
                     <i class="md-icon md-icon-delete"></i>
                 </md-button>
            </td>
        </tr>
        </tbody>
    </table>
</md-data-table-container>

</html>