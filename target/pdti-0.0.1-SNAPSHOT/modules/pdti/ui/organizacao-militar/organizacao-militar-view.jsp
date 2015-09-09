<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

    <md-toolbar class="md-tall md-toolbar-main">   
        <eits-search-tags width="100%" placeholder="Digite sua pesquisa" change-tag="changeTags" tags="tags" tags-string="model.tagsString"></eits-search-tags>
    </md-toolbar>

    <eits-table id="table" content="currentPage.content" on-item-click="itemClicked(item)">
        <columns>
            <table-column header="Sigla" field="sigla" sortable="true" />
            <table-column header="Nome" field="nome" sortable="true" width="70%"/>
        </columns>
    </eits-table>

</html>