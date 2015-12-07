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
    <title>Brunolândia</title>

    <!-- Styles -->
    <jsp:include page="/modules/default-styles.jsp"/>

    <!-- EITS Bottom Sheet -->
    <link rel="stylesheet" type="text/css" href="./static/js/eits-bottomsheet/eits-bottomsheet.css">

    <!-- Scripts -->
    <jsp:include page="/modules/default-scripts.jsp"/>

    <!-- Main -->
    <script type="text/javascript" src="./modules/sisvarejo/sisvarejo-main.js?v=${version}"/>
    </script>

    <!-- Custom Directive -->
    <script type="text/javascript" src="./static/js/eits-bottomsheet/eits-bottomsheet.js?v=${version}"></script>

    <!-- Controllers -->
    <script type="text/javascript" src="./modules/abstract-crud-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/pais-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/estado-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/cidade-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/cor-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/tamanho-controller.js?v=${version}"></script>
    <script type="text/javascript"
            src="./modules/sisvarejo/controllers/forma-pagamento-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/condicao-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/produto-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/fornecedor-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/cliente-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/venda-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/compra-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/fiscal-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/conta-receber-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/sisvarejo/controllers/conta-pagar-controller.js?v=${version}"></script>

</head>

<body>
<div ng-controller="MainController as mainController">
    <md-sidenav class="md-sidenav-left md-whiteframe-z2" md-component-id="left">
        <md-toolbar class="md-theme-light">
            <h1 class="md-toolbar-tools">Menu</h1>
        </md-toolbar>
        <md-content layout-padding>
            <md-list>
                <div ui-sref="pais">
                    <md-list-item>
                        País
                    </md-list-item>
                </div>
                <div ui-sref="estado">
                    <md-list-item>
                        Estado
                    </md-list-item>
                </div>
                <div ui-sref="cidade">
                    <md-list-item>
                        Cidade
                    </md-list-item>
                </div>
                <md-divider></md-divider>
                <div ui-sref="cor">
                    <md-list-item>
                        Cor
                    </md-list-item>
                </div>
                <div ui-sref="tamanho">
                    <md-list-item>
                        Tamanho
                    </md-list-item>
                </div>
                <md-divider></md-divider>
                <div ui-sref="forma-pagamento">
                    <md-list-item>
                        Formas de Pagamento
                    </md-list-item>
                </div>
                <div ui-sref="condicao.listar">
                    <md-list-item>
                        <p>Condição de Pagamento</p>
                    </md-list-item>
                </div>
                <md-divider></md-divider>
                <div ui-sref="cson">
                    <md-list-item>
                        <p>CSON</p>
                    </md-list-item>
                </div>
                <div ui-sref="icms">
                    <md-list-item>
                        <p>ICMS</p>
                    </md-list-item>
                </div>
                <div ui-sref="ncm">
                    <md-list-item>
                        <p>NCM</p>
                    </md-list-item>
                </div>
                <md-divider></md-divider>
                <div ui-sref="produto.listar">
                    <md-list-item>
                        Produto
                    </md-list-item>
                </div>
                <div>
                    <md-list-item>
                        <p ui-sref="fornecedor.listar">
                            Fornecedor
                        </p>
                    </md-list-item>
                </div>
                <md-divider></md-divider>
                <div>
                    <md-list-item>
                        <p ui-sref="conta-receber.listar">
                            Contas a Receber
                        </p>
                    </md-list-item>
                </div>
                <div>
                    <md-list-item>
                        <p ui-sref="conta-pagar.listar">
                            Contas a Pagar
                        </p>
                    </md-list-item>
                </div>
                <md-divider></md-divider>
                <div ui-sref="cliente.listar">
                    <md-list-item>
                        Cliente
                    </md-list-item>
                </div>
                <div ui-sref="venda.listar">
                    <md-list-item>
                        Venda
                    </md-list-item>
                </div>
                <div ui-sref="compra.listar">
                    <md-list-item>
                        Compra
                    </md-list-item>
                </div>
                <md-divider></md-divider>
                <md-list-item class="md-primary" ng-click="mainController.closeSidenav()">
                    Fechar
                </md-list-item>
            </md-list>
            <md-divider/>
        </md-content>
    </md-sidenav>


    <md-toolbar layout="row" layout-align="start center">
        <md-button class="md-icon-button" ng-click="mainController.toggleSidenav()" aria-label="Abrir menu">
            <i class="md-icon md-icon-menu md-icon-lg"></i>
        </md-button>
        <h2 class="md-toolbar-tools">
            <span>Sisvarejo</span>
        </h2>
    </md-toolbar>

    <ui-view/>
</div>
</body>
</html>
