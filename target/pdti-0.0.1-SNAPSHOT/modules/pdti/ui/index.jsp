<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

	<head>
		<title>Plano Diretor de Tecnologia de TI</title>

		<!-- Styles -->
		<jsp:include page="/modules/default-styles.jsp"/>

		<!-- Scripts -->
		<jsp:include page="/modules/default-scripts.jsp"/>

		<!-- Main -->
		<script type="text/javascript" src="./modules/pdti/pdti-main.js?v=${version}"/></script>

		<!-- Controllers -->
		<script type="text/javascript" src="./modules/abstract-crud-controller.js?v=${version}"></script>
		<script type="text/javascript" src="./modules/pdti/controllers/organizacao-militar-controller.js?v=${version}"></script>
		<script type="text/javascript" src="./modules/pdti/controllers/documento-referencia-controller.js?v=${version}"></script>
	    <script type="text/javascript" src="./modules/pdti/controllers/estrategia-controller.js?v=${version}"></script>
	  	<script type="text/javascript" src="./modules/pdti/controllers/principio-diretriz-controller.js?v=${version}"></script>
		<script type="text/javascript" src="./modules/pdti/controllers/item-swot-controller.js?v=${version}"></script>
		<script type="text/javascript" src="./modules/pdti/controllers/criterio-priorizacao-controller.js?v=${version}"></script>
		<script type="text/javascript" src="./modules/pdti/controllers/necessidade-ti-controller.js?v=${version}"></script>
		<script type="text/javascript" src="./modules/pdti/controllers/criterio-priorizacao-controller.js?v=${version}"></script>
		<script type="text/javascript" src="./modules/pdti/controllers/termo-abreviacao-controller.js?v=${version}"></script>
		<script type="text/javascript" src="./modules/pdti/controllers/plano-acao-controller.js?v=${version}"></script>
		<script type="text/javascript" src="./modules/pdti/controllers/plano-diretor-controller.js?v=${version}"></script>

	</head>

	<body>

		<md-toolbar class="md-toolbar-nav-top">

			<md-button ng-click="toogleMenu()" class="md-icon-button main-menu-button">
				<i class="md-icon md-icon-menu md-icon-lg"></i>
			</md-button>



			<div layout="row" layout-align="start center">

				<h2 class="md-toolbar-tools">
					<span ng-if="$state.$current.parent.self.name != ''">
						<a ng-if="$state.$current.parent.self.redirectPath == undefined" ng-href="{{ '#/' }}{{ $state.$current.parent.self.name}}">{{$state.$current.parent.self.displayName}}</a>
						<a ng-if="$state.$current.parent.self.redirectPath != undefined" ng-href="{{ '#/' }}{{ $state.$current.parent.self.redirectPath}}">{{$state.$current.parent.self.displayName}}</a>
						 
            			<i ng-if="$state.current.displayName != undefined" class="md-icon md-icon-chevron-right md-icon-lg"></i>
					</span>

					<span>{{$state.current.displayName}}</span>
				</h2>

			</div>
		</md-toolbar>

        <div ui-view></div>

        <md-sidenav class="md-sidenav-left md-whiteframe-z2" md-component-id="mainMenu">
            <md-subheader class="md-primary">
                <h3>Menu</h3>
            </md-subheader>
            <md-divider></md-divider>
            <md-content>
                <md-list class="md-no-sticky">
					<md-list-item ng-click="null">
						<p ui-sref="plano-diretor.listar">Plano Diretor de TI</p>
					</md-list-item>
					<md-divider></md-divider>
					<md-list-item ng-click="null">
						<p ui-sref="item-swot">Análise SWOT</p>
					</md-list-item>
					<md-list-item ng-click="null">
						<p ui-sref="documento-referencia">Documentos de Referência</p>
					</md-list-item>
					<md-list-item ng-click="null">
						<p ui-sref="criterio-priorizacao">Critério de Priorização</p>
					</md-list-item>
                    <md-list-item ng-click="null">
                        <p ui-sref="estrategia">Estratégia</p>
                    </md-list-item>
                    <md-list-item ng-click="null">
                        <p ui-sref="principio-diretriz">Princípios/Diretrizes</p>
                    </md-list-item>
                    <md-list-item ng-click="null">
                        <p ui-sref="termo-abreviacao">Termos e Abreviações</p>
                    </md-list-item>
                </md-list>
				<md-divider></md-divider>
				<md-list-item ng-click="toogleMenu()">
					<p>Fechar menu</p>
				</md-list-item>
            </md-content>
        </md-sidenav>

	</body>
</html>
