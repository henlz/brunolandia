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
                    <h3>Novo Funcionário</h3>
                </md-subheader>

                <md-input-container width="100%">
                    <label>Nome</label>
                    <input name="razaoSocial" type="text" ng-model="model.entidade.nome" required/>
                </md-input-container>

                <md-input-container width="100%">
                    <label>CPF</label>
                    <input name="cpf" type="text" ng-model="model.entidade.cpf"/>
                </md-input-container>

                <md-input-container flex>
                    <label>Email</label>
                    <input name="email" type="email" ng-model="model.entidade.email" required/>
                </md-input-container>

                <md-input-container flex>
                    <label>Telefone</label>
                    <input name="telefone" type="text" ng-model="model.entidade.telefone"/>
                </md-input-container>

                <md-select ng-model="model.pais" width="100%" placeholder="País" ng-change="paisChanged()">
                    <md-option ng-repeat="pais in model.paises track by pais.id" ng-selected="pais.id == model.pais.id" ng-value="pais">{{::pais.nome}}
                    </md-option>
                </md-select>

                <md-select ng-model="model.estado" width="100%" placeholder="Estado" ng-change="estadoChanged()">
                    <md-option ng-repeat="estado in model.estados track by estado.id" ng-selected="estado.id == model.estado.id" ng-value="estado">
                        {{::estado.nome}}
                    </md-option>
                </md-select>
                <md-select ng-model="model.entidade.cidade" width="100%" placeholder="Cidade">
                    <md-option ng-repeat="cidade in model.cidades track by cidade.id" ng-selected="cidade.id == model.entidade.cidade.id" ng-value="cidade">
                        {{::cidade.nome}}
                    </md-option>
                </md-select>

            </md-content>
        </section>
        <section layout="row" layout-align="end center" layout-fill layout-margin>
            <md-button class="md-icon-button md-fab" ui-sref="funcionario.listar" aria-label="Cancelar cadastro">
                <i class="md-icon md-icon-cancel md-icon-lg"></i>
            </md-button>
            <md-button class="md-primary md-icon-button md-fab" ng-click="salvarFornecedor(model.entidade)"
                       aria-label="Salvar produto">
                <i class="md-icon md-icon-save md-icon-lg"></i>
            </md-button>
        </section>
    </md-whiteframe>

</md-content>

</html>