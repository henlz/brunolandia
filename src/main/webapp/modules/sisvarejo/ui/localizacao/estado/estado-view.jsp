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
            <span>Estado</span>
        </h2>

        <md-button class="md-fab md-primary md-hue-2 grid-add-button" aria-label="Adicionar" ng-click="abrirPopupNovaEntidade($event)">
            <i class="md-icon md-icon-add md-icon-lg"></i>
        </md-button>
    </md-toolbar>

    <md-data-table-container>
        <table md-data-table>
            <thead md-order="model.query.order" md-trim-column-names>
            <tr>
                <th order-by="id">Código</th>
                <th order-by="nome">Estado</th>
                <th order-by="pais.nome">País</th>
                <th style="width:20px;">Ações</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="estado in model.content | orderBy: model.query.order">
                <td>{{::estado.id}}</td>
                <td>{{::estado.nome}}</td>
                <td>{{::estado.pais.nome}}</td>
                <td layout="row">
                    <md-button class="md-icon-button" ng-click="itemClicked($event, estado)">
                        <i class="md-icon-edit"></i>
                    </md-button>
                    <md-button class="md-icon-button" ng-click="excluirEstado($event, estado)">
                        <i class="md-icon-delete"></i>
                    </md-button>
                </td>
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
                <md-button class="md-raised" aria-label="Excluir" ng-click="excluirEstados($event, model.itensExcluir)"><i class="md-icon-delete md-icon-lg"></i></md-button>
            </div>
        </div>
    </bottomsheet>

</html>