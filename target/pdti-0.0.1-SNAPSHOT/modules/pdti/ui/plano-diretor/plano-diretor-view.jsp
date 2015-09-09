<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">
    <div ng-switch on="currentState">
        <div ng-switch-when="plano-diretor.listar">
            <div ng-include="'modules/pdti/ui/plano-diretor/plano-diretor-list.jsp'"></div>
        </div>
        <div ng-switch-when="plano-diretor.detalhe">
            <div ng-include="'modules/pdti/ui/plano-diretor/plano-diretor-detail.jsp'"></div>
        </div>
        <div ng-switch-when="plano-diretor.criar">
            <div ng-include="'modules/pdti/ui/plano-diretor/plano-diretor-form.jsp'"></div>
        </div>
        <div ng-switch-when="plano-diretor.editar">
            <div ng-include="'modules/pdti/ui/plano-diretor/plano-diretor-form.jsp'"></div>
        </div>
        <div ng-switch-when="plano-diretor.secoes">
            <div ng-include="'modules/pdti/ui/plano-diretor/plano-diretor-sections.jsp'"></div>
        </div>
        <div ng-switch-default></div>
    </div>
    
    <md-button ng-if="currentState == LIST_STATE"  ui-sref="plano-diretor.criar" class="md-fab md-accent md-hue-2 low-add-button" aria-label="Adicionar">
        <i class="md-icon md-icon-add md-icon-lg"></i>
    </md-button>

</html>