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
            <span>País</span>
        </h2>

        <md-button class="md-fab md-primary md-hue-2 grid-add-button" aria-label="Adicionar" ng-click="abrirPopupNovaEntidade($event)">
            <i class="md-icon md-icon-add md-icon-lg"></i>
        </md-button>
    </md-toolbar>

    <md-data-table-container>
        <table md-data-table md-row-select="model.selected">
            <thead md-order="model.query.order" md-trim-column-names>
            <tr>
                <th order-by="id">Código</th>
                <th order-by="nome">Nome</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="pais in model.content | orderBy: model.query.order" ng-click="itemClicked($event, pais)">
                <td>{{::pais.id}}</td>
                <td>{{::pais.nome}}</td>
            </tr>
            </tbody>
        </table>
    </md-data-table-container>

    <bottomsheet>
        <div class="bottom-sheet-content">
            <div class="left-content" layout="row">
                <div>{{model.itensExcluir.length}} {{model.itensExcluir.length > 1 ? 'itens' : 'item'}} selecionado{{model.itensExcluir.length > 1 ? 's' : ''}}</div>
                <div class="limpar-selecao" ng-click="limparSelecao()">Limpar seleção</div>
            </div>
            <div class="right-content">
                <md-button class="md-raised" aria-label="Excluir" ng-click="excluirPaises($event, model.itensExcluir)"><i class="md-icon-delete md-icon-lg"></i></md-button>
            </div>
        </div>
    </bottomsheet>

</html>