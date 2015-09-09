<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

    <!--<md-toolbar class="md-tall md-toolbar-main">      
    </md-toolbar>-->

    <div ng-switch on="currentState">
        <div ng-switch-when="plano-acoes.listar-necessidades">
            <div ng-include="'modules/pdti/ui/plano-acao/necessidade-plano-acao-list.jsp'"></div>
        </div>
        <div ng-switch-when="plano-acoes.listar-metas">
            <div ng-include="'modules/pdti/ui/plano-acao/plano-acao-list.jsp'"></div>
        </div>
        <div ng-switch-when="plano-acoes.criar-meta">
            <div ng-include="'modules/pdti/ui/plano-acao/plano-acao-meta-form.jsp'"></div>
        </div>        
        <div ng-switch-when="plano-acoes.editar-meta">
            <div ng-include="'modules/pdti/ui/plano-acao/plano-acao-meta-form.jsp'"></div>
        </div>
        <div ng-switch-default></div>
    </div>

</html>