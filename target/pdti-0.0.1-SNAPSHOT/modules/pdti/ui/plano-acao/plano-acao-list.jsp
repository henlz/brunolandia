<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">
    
     <md-toolbar class="md-tall md-toolbar-main" layout="row" layout-align="center center">
        
        <eits-search-tags width="100%" placeholder="Digite sua pesquisa" change-tag="changeTags" tags="model.tags" tags-string="model.filter"></eits-search-tags>
        
        <md-button class="md-fab md-primary md-hue-2 grid-add-button" aria-label="Adicionar" ui-sref="plano-acoes.criar-meta">
            <i class="md-icon md-icon-add md-icon-lg"></i>
        </md-button>
    </md-toolbar>

    <eits-table id="table" content="model.page.content" multi-selection="true" on-selection-change="selectionUpdate(selectedItens)" on-item-click="itemClicked(item)">
        <columns>
            <table-column header="Código" field="codigo" sortable="true" width="10%"/>
            <table-column header="Descrição" field="descricao" sortable="true"/>
			<table-column header="Indicador" field="indicador" sortable="true"/>
			<table-column header="Valor" field="valorIndicador" sortable="true"/>
			<table-column header="Prazo" field="prazo" sortable="true">
                <column-template>
                    {{::row.prazo | date: 'dd/MM/yyyy'}}
                </column-template>
			</table-column>            
            <table-column header="Ações" sortable="false"/>
                <column-template>
                    <a ui-sref="plano-acoes.editar-meta({metaId: row.id})">Alterar</a>                    
                </column-template>
            </table-column>
                                    
        </columns>
    </eits-table>    
    
    <bottomsheet>
        <div class="bottom-sheet-content">
            <div class="left-content" layout="row">
                <div>{{model.itensExcluir.length}} {{model.itensExcluir.length > 1 ? 'itens' : 'item'}} selecionado{{model.itensExcluir.length > 1 ? 's' : ''}}</div>
                <div class="limpar-selecao" ng-click="limparSelecao()">Limpar seleção</div>
            </div>
            <div class="right-content">
                <md-button class="md-raised" aria-label="Excluir" ng-click="changeToRemoveMeta($event, model.itensExcluir)"><i class="md-icon-delete md-icon-lg"></i></md-button>
            </div>
        </div>
    </bottomsheet>

</html>