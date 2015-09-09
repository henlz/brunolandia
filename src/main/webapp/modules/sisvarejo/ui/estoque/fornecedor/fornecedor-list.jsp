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

<md-data-table-container>
    <table md-data-table>
        <thead md-order="model.query.order" md-trim-column-names>
        <tr>
            <th order-by="razaoSocial">Razão Social</th>
            <th order-by="email">Email</th>
            <th order-by="telefone">Telefone</th>
            <th order-by="cidade.nome">Cidade</th>
            <th order-by="ativo">Status</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="fornecedor in model.content | orderBy: model.query.order">
            <td>{{::fornecedor.razaoSocial}}</td>
            <td>{{::fornecedor.email}}</td>
            <td>{{::fornecedor.telefone}}</td>
            <td>{{::fornecedor.cidade.nome}}</td>
            <td>{{::fornecedor.ativo ? 'Ativo' : 'Inativo'}}</td>
            <td layout="row">
                <md-button ng-if="fornecedor.ativo != true" class="md-icon-button"
                           ng-click="mudarStatusFornecedor(fornecedor, true)"><i class="md-icon md-icon-check"></i>
                </md-button>
                <md-button ng-if="fornecedor.ativo == true" class="md-icon-button"
                           ng-click="mudarStatusFornecedor(fornecedor, false)"><i class="md-icon md-icon-clear"></i>
                </md-button>
                <md-button class="md-icon-button" ui-sref="fornecedor.alterar({id: fornecedor.id})"
                           aria-label="Alterar fornecedor">
                    <i class="md-icon md-icon-edit"></i>
                </md-button>
<!--                 <md-button class="md-icon-button" ng-click="excluirFornecedor($event, fornecedor)" -->
<!--                            aria-label="Excluir fornecedor"> -->
<!--                     <i class="md-icon md-icon-delete"></i> -->
<!--                 </md-button> -->
            </td>
        </tr>
        </tbody>
    </table>
</md-data-table-container>

</html>