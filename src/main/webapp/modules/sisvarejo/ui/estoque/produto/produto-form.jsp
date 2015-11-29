<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout-align="center center" layout="column" layout-margin>

    <md-whiteframe class="md-whiteframe-z1" width="500" layout="column" layout-align="center center"
                   style="  margin-bottom: 5px;" layout-padding>
        <section>
            <md-content layout-padding layout="column" layout-align="center center">
                <md-subheader>
                    <h3>Novo Produto</h3>
                </md-subheader>

                <md-input-container width="100%">
                    <label>Código</label>
                    <input name="codigo" type="text" ng-model="model.entidade.codigo" required/>
                </md-input-container>

                <md-input-container width="100%">
                    <label>Código de Barras</label>
                    <input name="codigoBarra" type="text" ng-model="model.entidade.codigoBarra" required/>
                </md-input-container>

                <md-input-container width="100%">
                    <label>Descrição</label>
                    <input name="descricao" type="text" ng-model="model.entidade.descricao"/>
                </md-input-container>

                <md-input-container flex>
                    <label>Preço venda</label>
                    <input name="precoVenda" type="number" ng-model="model.entidade.precoVenda"/>
                </md-input-container>

                <md-input-container flex>
                    <label>Quantidade</label>
                    <input name="quantidade" type="number" ng-model="model.entidade.quantidade" required/>
                </md-input-container>

                <md-input-container>
                    <label>Gênero</label>
                    <md-select ng-model="model.entidade.grupo" width="100%">
                        <md-option value="MASCULINO">Masculino</md-option>
                        <md-option value="FEMININO">Feminino</md-option>
                        <md-option value="INFANTIL">Infantil</md-option>
                    </md-select>
                </md-input-container>

                <div layout="row">
                    <md-select ng-model="model.entidade.cor" placeholder="Cor">
                        <md-option ng-repeat="cor in model.cores" ng-value="cor"
                                   ng-selected="cor.id == model.entidade.cor.id">{{cor.nome}}
                        </md-option>
                    </md-select>

                    <md-select ng-model="model.entidade.tamanho" placeholder="Tamanho">
                        <md-option ng-repeat="tamanho in model.tamanhos" ng-value="tamanho"
                                   ng-selected="tamanho.id == model.entidade.tamanho.id">{{tamanho.nome}}
                        </md-option>
                    </md-select>

                    <!--<md-select ng-model="model.entidade.fornecedor" placeholder="Fornecedor">-->
                        <!--<md-option ng-repeat="fornecedor in model.fornecedores" ng-value="fornecedor"-->
                                   <!--ng-selected="fornecedor.id == model.entidade.fornecedor.id">{{fornecedor.razaoSocial}}-->
                        <!--</md-option>-->
                    <!--</md-select>-->

                    <div layout="row" layout-align="center center">

                        <md-input-container>
                            <label>Fornecedor</label>
                            <input type="text" ng-model="model.entidade.fornecedor.razaoSocial" disabled/>
                        </md-input-container>

                        <md-button class="md-icon-button" ng-click="abrirPopupFornecedor($event)">
                            <i class="md-icon md-icon-search"></i>
                        </md-button>

                    </div>

                </div>

            </md-content>
        </section>
        <section layout="row" layout-align="end center" layout-fill layout-margin>
            <md-button class="md-icon-button md-fab" ui-sref="produto.listar" aria-label="Cancelar cadastro">
                <i class="md-icon md-icon-cancel md-icon-lg"></i>
            </md-button>
            <md-button class="md-primary md-icon-button md-fab" ng-click="salvarProduto(model.entidade)" aria-label="Salvar produto">
                <i class="md-icon md-icon-save md-icon-lg"></i>
            </md-button>
        </section>
    </md-whiteframe>

</md-content>

</html>