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
                   style="margin-bottom: 5px;" layout-padding>
        <section width="90%">
            <form name="clienteForm" novalidate="true">
                <md-content layout-padding layout="column" layout-align="center center">
                    <md-subheader>
                        <h3>Novo Cliente</h3>
                    </md-subheader>

                    <div layout="row" width="100%">
                        <md-input-container>
                            <label>Nome</label>
                            <input name="nome" type="text" ng-model="model.entidade.nome" required/>
                        </md-input-container>

                        <md-input-container>
                            <label>Apelido</label>
                            <input name="apelido" type="text" ng-model="model.entidade.apelido"/>
                        </md-input-container>

                        <md-input-container>
                            <label>Tipo de pessoa</label>
                            <md-select ng-model="model.entidade.tipoPessoa" required>
                                <md-option value="FISICA">Física</md-option>
                                <md-option value="JURIDICA">Juríridca</md-option>
                            </md-select>
                        </md-input-container>
                    </div>

                    <div layout="row" width="100%">
                        <md-input-container width="100%">
                            <label>CNPJ/CPF</label>
                            <input name="cnpj" type="text" ng-model="model.entidade.cpf" required/>
                        </md-input-container>

                        <md-input-container width="100%">
                            <label>RG</label>
                            <input name="rg" type="text" ng-model="model.entidade.rg"
                                   ng-disabled="model.entidade.tipoPessoa == 'JURIDICA'"/>
                        </md-input-container>
                    </div>

                    <div layout="row" width="100%">
                        <div layout="row" layout-align="center center">
                            <md-input-container>
                                <label>Cidade</label>
                                <input type="text" ng-model="model.entidade.cidade.nome" disabled/>
                            </md-input-container>

                            <md-button class="md-icon-button" ng-click="abrirPopupCidade()">
                                <i class="md-icon md-icon-search"></i>
                            </md-button>
                        </div>

                        <md-input-container>
                            <label>Endereço</label>
                            <input name="endereco" type="text" ng-model="model.entidade.endereco"/>
                        </md-input-container>

                        <md-input-container>
                            <label>Numero</label>
                            <input name="numero" type="text" ng-model="model.entidade.numero"/>
                        </md-input-container>
                    </div>

                    <div layout="row" width="100%">
                        <md-input-container width="100%">
                            <label>Telefone</label>
                            <input name="telefone" type="text" ng-model="model.entidade.telefone"/>
                        </md-input-container>

                        <md-input-container width="100%">
                            <label>Email</label>
                            <input name="email" type="email" ng-model="model.entidade.email" required/>
                        </md-input-container>
                    </div>
                    <div layout="row" layout-align="center center">

                        <input style="width: 40px;" type="text" ng-model="model.codigoCondicao"
                               ng-change="buscaCondicaoById()">

                        <md-input-container>
                            <label>Condição de pagamento</label>
                            <input type="text" name="condicao" ng-model="model.entidade.condicaoPagamento.descricao"
                                   readonly/>

                            <div ng-messages="compraForm.condicao.$error">
                                <div ng-message="required">
                                    Campo obrigatório.
                                </div>
                            </div>
                        </md-input-container>

                        <md-button class="md-icon-button" ng-click="abrirPopupBuscaCondicao($event)"
                                   aria-label="Procurar condicao">
                            <i class="md-icon md-icon-search"></i>
                        </md-button>
                    </div>

                </md-content>
            </form>
        </section>
        <section layout="row" layout-align="end center" layout-fill layout-margin>
            <md-button class="md-icon-button md-fab" ui-sref="cliente.listar" aria-label="Cancelar cadastro">
                <i class="md-icon md-icon-cancel md-icon-lg"></i>
            </md-button>
            <md-button class="md-primary md-icon-button md-fab" ng-click="salvarCliente(model.entidade)"
                       aria-label="Salvar produto">
                <i class="md-icon md-icon-save md-icon-lg"></i>
            </md-button>
        </section>
    </md-whiteframe>

</md-content>

</html>