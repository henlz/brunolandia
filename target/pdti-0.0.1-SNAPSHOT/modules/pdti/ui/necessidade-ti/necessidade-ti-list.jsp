<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

    <eits-table id="table" content="currentPage.content" on-selection-change="selectionUpdate(selectedItens)" on-item-click="itemClicked(item)">
         <columns>
            <table-column header="Código" field="codigo" sortable="true" width="10%"/>
            <table-column header="Descrição" field="descricao" width="70%"/>
            <table-column header="Tipo" field="tipoNecessidade" width="30%">
                <column-template>
                    <span ng-show="row.tipoNecessidade == 'CLIENTE'">Cliente</span>
                    <span ng-show="row.tipoNecessidade == 'FINANCEIRA'">Financeira</span>
                    <span ng-show="row.tipoNecessidade == 'PROCESSOS_INTERNOS'">Processos Internos</span>
                    <span ng-show="row.tipoNecessidade == 'APRENDIZADO_CRESCIMENTO'">Aprendizado Crescimento</span>
                </column-template>
            </table-column>
            <table-column header="Ações" width="30%"/>
                <column-template>
                    <a ui-sref="necessidade-ti.editar({id: row.id})">Alterar</a>
                    </br>
                    <a ui-sref="plano-acoes.listar-metas({id: row.id})">Plano de Ações</a>
                </column-template>
            </table-column>
        </columns>
    </eits-table>

</html>