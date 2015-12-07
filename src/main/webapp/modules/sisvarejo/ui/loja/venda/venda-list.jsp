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
        <span>Vendas</span>
    </h2>

    <md-button class="md-fab md-primary md-hue-2 grid-add-button" aria-label="Adicionar"
               ui-sref="venda.cadastrar">
        <i class="md-icon md-icon-add md-icon-lg"></i>
    </md-button>
    </md-toolbar>

    <md-data-table-container>
    <table md-data-table>
        <thead md-order="model.query.order" md-trim-column-names>
        <tr>
            <th>Número</th>
            <th>Total</th>
            <th>Data</th>
            <th>Cliente</th>
            <th style="width: 20px;">Ações</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="venda in model.content | orderBy: model.query.order">
            <td>{{::venda.numeroNfe}} {{:: venda.cancelada == true ? '(Cancelada)': ''}}</td>
            <td>{{::getVendaTotal(venda) | currency: 'R$ '}}</td>
            <td>{{::venda.created | date}}</td>
            <td>{{::venda.cliente.nome}}</td>
            <td layout="row">
                <md-button class="md-icon-button" ui-sref="venda.cancelar({id: venda.id})" aria-label="Cancelar venda" ng-if="venda.cancelada != true">
                    <i class="md-icon md-icon-cancel"></i>
                </md-button>
            </td>
        </tr>
        </tbody>
    </table>
    </md-data-table-container>

</html>