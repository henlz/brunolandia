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
        
         <md-button ng-if="currentState == LIST_STATE" class="md-fab md-primary md-hue-2 grid-add-button" aria-label="Adicionar" ng-click="changeToInsert()">
            <i class="md-icon md-icon-add md-icon-lg"></i>
          </md-button>
    </md-toolbar>

    <div ng-switch on="currentState">
        <div ng-switch-when="necessidade-ti.listar">
            <div ng-include="'modules/pdti/ui/necessidade-ti/necessidade-ti-list.jsp'"></div>
        </div>
        <div ng-switch-when="necessidade-ti.detalhe">
            <div ng-include="'modules/pdti/ui/necessidade-ti/necessidade-ti-detail.jsp'"></div>
        </div>
        <div ng-switch-when="necessidade-ti.criar">
            <div ng-include="'modules/pdti/ui/necessidade-ti/necessidade-ti-form.jsp'"></div>
        </div>
        <div ng-switch-when="necessidade-ti.editar">
            <div ng-include="'modules/pdti/ui/necessidade-ti/necessidade-ti-form.jsp'"></div>
        </div>
        <div ng-switch-default></div>
    </div>

</html>