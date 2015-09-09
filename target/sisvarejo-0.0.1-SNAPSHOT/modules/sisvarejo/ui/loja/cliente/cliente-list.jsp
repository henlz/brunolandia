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

<md-data-table-container>
    <table md-data-table>
        <thead md-order="model.query.order" md-trim-column-names>
        <tr>
            <th order-by="razaoSocial">Nome</th>
            <th order-by="email">Email</th>
            <th order-by="telefone">Telefone</th>
            <th order-by="cidade.nome">Cidade</th>
            <th order-by="ativo">Status</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="cliente in model.content | orderBy: model.query.order">
            <td>{{::cliente.nome}}</td>
            <td>{{::cliente.email}}</td>
            <td>{{::cliente.telefone}}</td>
            <td>{{::cliente.cidade.nome}}</td>
            <td>{{::cliente.ativo ? 'Ativo' : 'Inativo'}}</td>
            <td layout="row">
                <md-button ng-if="cliente.ativo != true" class="md-icon-button"
                           ng-click="mudarStatusCliente(cliente, true)"><i class="md-icon md-icon-check"></i>
                </md-button>
                <md-button ng-if="cliente.ativo == true" class="md-icon-button"
                           ng-click="mudarStatusCliente(cliente, false)"><i class="md-icon md-icon-clear"></i>
                </md-button>
                <md-button class="md-icon-button" ui-sref="cliente.alterar({id: cliente.id})"
                           aria-label="Alterar cliente">
                    <i class="md-icon md-icon-edit"></i>
                </md-button>
<!--                 <md-button class="md-icon-button" ng-click="excluirFornecedor($event, cliente)" -->
<!--                            aria-label="Excluir cliente"> -->
<!--                     <i class="md-icon md-icon-delete"></i> -->
<!--                 </md-button> -->
            </td>
        </tr>
        </tbody>
    </table>
</md-data-table-container>

</html>