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
            <span>Acrescentar Plano de Ações às Necessidades</span>
        </h2>           
    </md-toolbar>

    <eits-table id="table" content="currentPage.content" multi-selection="false" on-item-click="itemClicked(item)">
        <columns>
            <table-column header="Código" field="codigo" sortable="true" width="10%"/>
            <table-column header="Descrição" field="descricao" sortable="true"/>            
            <table-column header="Tipo" sortable="true">
                <column-template>
                    <span ng-show="row.tipoNecessidade == 'CLIENTE'">Cliente</span>
                    <span ng-show="row.tipoNecessidade == 'FINANCEIRA'">Financeira</span>
                    <span ng-show="row.tipoNecessidade == 'PROCESSOS_INTERNOS'">Processos Internos</span>
                    <span ng-show="row.tipoNecessidade == 'APRENDIZADO_CRESCIMENTO'">Aprendizado e Crescimento</span>
                </column-template>
            </table-column>            
        </columns>
    </eits-table>    

</html>