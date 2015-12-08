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
        <span>Contas a pagar</span>
    </h2>

    <md-button class="md-fab md-primary md-hue-2 grid-add-button" aria-label="Adicionar"
               ui-sref="conta-pagar.cadastrar">
        <i class="md-icon md-icon-add md-icon-lg"></i>
    </md-button>
    </md-toolbar>

    <md-data-table-container>
    <table md-data-table>
        <thead md-order="model.query.order" md-trim-column-names>
        <tr>
            <th>Número</th>
            <th style="width: 5px;">Número da parcela</th>
            <th>Status</th>
            <th>Fornecedor</th>
            <th>Descrição</th>
            <th width="70">Valor</th>
            <th style="width: 20px;">Ações</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="contaPagar in model.content | orderBy: model.query.order">
            <td>{{::contaPagar.numeroNota}}</td>
            <td>{{::contaPagar.numeroParcela}}</td>
            <td>{{::contaPagar.statusConta | capitalize}}</td>
            <td>{{::contaPagar.fornecedor.razaoSocial}}</td>
            <td>{{::contaPagar.descricao }}</td>
            <td>{{::contaPagar.valor | currency: 'R$ '}}</td>
            <td layout="row">
                <md-button class="md-icon-button" ui-sref="conta-pagar.pagar({id: contaPagar.id})" aria-label="Pagar Conta a Pagar" ng-if="contaPagar.statusConta != 'CANCELADA' && contaPagar.statusConta != 'PAGA'">
                    <i class="md-icon md-icon-attach-money"></i>
                </md-button>
                <md-button class="md-icon-button" ui-sref="conta-pagar.cancelar({id: contaPagar.id})" aria-label="Cancelar Conta a Pagar" ng-if="contaPagar.statusConta != 'CANCELADA' && contaPagar.statusConta != 'PAGA'">
                    <i class="md-icon md-icon-cancel"></i>
                </md-button>
            </td>
        </tr>
        </tbody>
    </table>
    </md-data-table-container>

</html>