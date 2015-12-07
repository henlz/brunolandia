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
        <span>Contas a receber</span>
    </h2>

    <md-button class="md-fab md-primary md-hue-2 grid-add-button" aria-label="Adicionar"
               ui-sref="conta-receber.cadastrar">
        <i class="md-icon md-icon-add md-icon-lg"></i>
    </md-button>
    </md-toolbar>

    <md-data-table-container>
    <table md-data-table>
        <thead md-order="model.query.order" md-trim-column-names>
        <tr>
            <th style="width: 5px;">Número da parcela</th>
            <th width="30">Status</th>
            <th width="100">Cliente</th>
            <th>Descrição</th>
            <th width="70">Valor</th>
            <th style="width: 20px;">Ações</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="contaReceber in model.content | orderBy: model.query.order">
            <td>{{::contaReceber.numeroParcela}}</td>
            <td>{{::contaReceber.statusConta | capitalize}}</td>
            <td>{{::contaReceber.cliente.nome}}</td>
            <td>{{::contaReceber.descricao }}</td>
            <td>{{::contaReceber.valor | currency: 'R$ '}}</td>
            <td layout="row">
                <md-button class="md-icon-button" ui-sref="conta-receber.pagar({id: contaReceber.id})" aria-label="Pagar Conta a Receber" ng-if="contaReceber.statusConta != 'CANCELADA' && contaReceber.statusConta != 'PAGA'">
                    <i class="md-icon md-icon-attach-money"></i>
                </md-button>
                <md-button class="md-icon-button" ui-sref="conta-receber.cancelar({id: contaReceber.id})" aria-label="Cancelar Conta a Receber" ng-if="contaReceber.statusConta != 'CANCELADA' && contaReceber.statusConta != 'PAGA'">
                    <i class="md-icon md-icon-cancel"></i>
                </md-button>
            </td>
        </tr>
        </tbody>
    </table>
    </md-data-table-container>

</html>