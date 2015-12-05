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
        <span>Fornecedores</span>
    </h2>

    <md-button class="md-fab md-primary md-hue-2 grid-add-button" aria-label="Adicionar" ui-sref="fornecedor.cadastrar">
        <i class="md-icon md-icon-add md-icon-lg"></i>
    </md-button>
</md-toolbar>

<md-content layout="row" layout-align="center center" layout-wrap>
    <md-input-container>
        <label>Razão Social</label>
        <input type="text" ng-model="model.filtros.razaoSocial">
    </md-input-container>
    <md-input-container>
        <label>Nome Fantasia</label>
        <input type="text" ng-model="model.filtros.nomeFantasia">
    </md-input-container>
    <md-input-container>
        <label>Telefone</label>
        <input type="text" ng-model="model.filtros.telefone">
    </md-input-container>
    <md-input-container>
        <label>CNPJ</label>
        <input type="text" ng-model="model.filtros.cnpj">
    </md-input-container>
    <md-button ng-click="carregarLista()" class="md-icon-button">
        <i class="md-icon md-icon-search"></i>
    </md-button>
</md-content>

<md-data-table-container>
    <table md-data-table>
        <thead md-order="model.query.order" md-trim-column-names>
        <tr>
            <th order-by="razaoSocial">Código</th>
            <th order-by="razaoSocial">Nome</th>
            <th order-by="email">Tipo de pessoa</th>
            <th order-by="telefone">Telefone</th>
            <th order-by="cidade.nome">E-mail</th>
            <th style="width: 20px;">Ações</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="fornecedor in model.content | orderBy: model.query.order">
            <td>{{::fornecedor.codigo}}</td>
            <td>{{::fornecedor.razaoSocial}}</td>
            <td>{{::fornecedor.tipoPessoa | capitalize}}</td>
            <td>{{::fornecedor.telefone}}</td>
            <td>{{::fornecedor.cidade.nome}}</td>
            <td layout="row">
                <md-button class="md-icon-button" ui-sref="fornecedor.alterar({id: fornecedor.id})"
                           aria-label="Alterar fornecedor">
                    <i class="md-icon md-icon-edit"></i>
                </md-button>
                 <md-button class="md-icon-button" ng-click="excluirFornecedor($event, fornecedor)"
                            aria-label="Excluir fornecedor">
                     <i class="md-icon md-icon-delete"></i>
                 </md-button>
            </td>
        </tr>
        </tbody>
    </table>
</md-data-table-container>

</html>