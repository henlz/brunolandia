<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout-align="center center" layout="column" layout-margin>

    <md-whiteframe class="md-whiteframe-z1" layout="column" layout-align="center center"
                   style="  margin-bottom: 5px;" layout-padding>
        <section width="90%">
            <md-content layout-padding layout="column" layout-align="center center">
                <md-subheader>
                    <h3>Novo Fornecedor</h3>
                </md-subheader>

                <md-input-container width="100%">
                    <label>Razão Social</label>
                    <input name="razaoSocial" type="text" ng-model="model.entidade.razaoSocial" required/>
                </md-input-container>

                <md-input-container width="100%">
                    <label>Nome Fantasia</label>
                    <input name="nomeFantasia" type="text" ng-model="model.entidade.nomeFantasia"/>
                </md-input-container>

                <div layout="row" width="100%">
                    <md-input-container flex="70">
                        <label>Endereço</label>
                        <input name="endereco" type="text" ng-model="model.entidade.endereco"/>
                    </md-input-container>

                    <md-input-container flex>
                        <label>Numero</label>
                        <input name="numero" type="text" ng-model="model.entidade.numero"/>
                    </md-input-container>
                </div>

                <div layout="row" width="100%">
                    <md-input-container flex>
                        <label>Complemento</label>
                        <input name="complemento" type="text" ng-model="model.entidade.complemento"/>
                    </md-input-container>

                    <md-input-container flex>
                        <label>Bairro</label>
                        <input name="bairro" type="text" ng-model="model.entidade.bairro"/>
                    </md-input-container>
                </div>

                <md-input-container width="100%">
                    <label>CEP</label>
                    <input name="cep" type="text" ng-model="model.entidade.cep"/>
                </md-input-container>

                <div layout="row" layout-align="center center">

                    <md-input-container>
                        <label>Cidade</label>
                        <input type="text" ng-model="model.entidade.cidade.nome" disabled/>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupCidade()">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>

                </div>

                </div>

                <md-input-container width="100%">
                    <label>Representante</label>
                    <input name="representante" type="text" ng-model="model.entidade.representante"/>
                </md-input-container>

                <md-input-container width="100%">
                    <label>Telefone</label>
                    <input name="telefone" type="text" ng-model="model.entidade.telefone"/>
                </md-input-container>

                <md-input-container width="100%">
                    <label>Site</label>
                    <input name="site" type="text" ng-model="model.entidade.site"/>
                </md-input-container>

                <md-input-container width="100%">
                    <label>Email</label>
                    <input name="email" type="email" ng-model="model.entidade.email" required/>
                </md-input-container>

                <md-input-container width="100%">
                    <label>CNPJ</label>
                    <input name="cnpj" type="text" ng-model="model.entidade.cnpj" required/>
                </md-input-container>

            </md-content>
        </section>
        <section layout="row" layout-align="end center" layout-fill layout-margin>
            <md-button class="md-icon-button md-fab" ui-sref="fornecedor.listar" aria-label="Cancelar cadastro">
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