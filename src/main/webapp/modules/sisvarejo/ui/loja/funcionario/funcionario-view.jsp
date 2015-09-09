<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

    <div ng-switch on="currentState">
        <div ng-switch-when="fornecedor.listar">
            <div ng-include="'modules/sisvarejo/ui/estoque/fornecedor/fornecedor-list.jsp'"></div>
        </div>
        <div ng-switch-when="fornecedor.detalhe">
            <div ng-include="'modules/sisvarejo/ui/estoque/fornecedor/fornecedor-detail.jsp'"></div>
        </div>
        <div ng-switch-when="fornecedor.cadastrar">
            <div ng-include="'modules/sisvarejo/ui/estoque/fornecedor/fornecedor-form.jsp'"></div>
        </div>
        <div ng-switch-when="fornecedor.alterar">
            <div ng-include="'modules/sisvarejo/ui/estoque/fornecedor/fornecedor-form.jsp'"></div>
        </div>
        <div ng-switch-default></div>
    </div>
</html>