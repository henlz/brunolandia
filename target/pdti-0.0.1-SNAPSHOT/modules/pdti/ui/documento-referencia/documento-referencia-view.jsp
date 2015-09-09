<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

    <md-toolbar class="md-tall md-toolbar-main" layout="row" layout-align="center center">
        <eits-search-tags width="100%" placeholder="Digite sua pesquisa" change-tag="changeTags" tags="tags" tags-string="model.tagsString"></eits-search-tags>
        <md-button class="md-fab md-accent md-hue-2 grid-add-button" aria-label="Adicionar" ng-click="abrirPopupNovaEntidade($event)">
            <i class="md-icon md-icon-add md-icon-lg"></i>
        </md-button>
    </md-toolbar>


    <!--<eits-table id="table" content="currentPage.content" multi-selection="true" on-selection-change="selectionUpdate(selectedItens)" on-item-click="itemClicked(item)">-->
        <!--<columns>-->
            <!--<table-column header="Sigla" field="sigla" sortable="true" width="10%"/>-->
            <!--<table-column header="Título" field="titulo" sortable="true"/>-->
            <!--<table-column header="Início de vigência" sortable="true" width="15%">-->
                <!--<column-template>-->
                    <!--{{::row.dataInicio | date: 'dd/MM/yyyy'}}-->
                <!--</column-template>-->
            <!--</table-column>-->
            <!--<table-column header="Término de vigência" sortable="true" width="15%">-->
                <!--<column-template>-->
                    <!--{{::row.dataFim | date: 'dd/MM/yyyy'}}-->
                <!--</column-template>-->
            <!--</table-column>-->
            <!--<table-column header="Anexo" sortable="true" width="10%">-->
                <!--<column-template>-->

                    <!--<a class="md-icon-lg md-icon-file-download" href="{{row.anexo}}">[Nome do Arquivo]</a>-->

                <!--</column-template>-->
            <!--</table-column>-->
        <!--</columns>-->
    <!--</eits-table>-->

    <md-data-table-container>
        <table md-data-table md-row-select="model.selected">
            <thead md-order="model.query.order" md-trim-column-names>
                <tr>
                    <th order-by="sigla">Sigla</th>
                    <th order-by="titulo" style="width: 30%;">Título</th>
                    <th order-by="dataInicio">Data Início</th>
                    <th order-by="dataFim">Data Fim</th>
                    <th>Anexo</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="documento in currentPage.content | orderBy: model.query.order" ng-click="itemClicked($event, documento)">
                    <td>{{::documento.sigla}}</td>
                    <td>{{::documento.titulo}}</td>
                    <td>{{::documento.dataInicio | date: 'dd/MM/yyyy'}}</td>
                    <td>{{::documento.dataFim | date: 'dd/MM/yyyy'}}</td>
                    <td ng-if="documento.anexo != null">
                        <md-button class="md-icon-button md-fab">
                            <a href="{{::documento.anexo}}"><i class="md-icon md-icon-file-download md-icon-lg"></i></a>
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
                <md-button class="md-raised" aria-label="Excluir" ng-click="excluirDocumentoReferencia($event, model.itensExcluir)"><i class="md-icon-delete md-icon-lg"></i></md-button>
            </div>
        </div>
    </bottomsheet>

</html>