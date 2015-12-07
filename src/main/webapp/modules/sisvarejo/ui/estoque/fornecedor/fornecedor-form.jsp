<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout-align="center center" layout="column" layout-margin>

    <md-whiteframe class="md-whiteframe-z1" layout="column" layout-align="center center" style="margin-bottom: 5px;" layout-padding>
        <section>
            <form name="fornecedorForm" layout-padding layout="column" layout-align="center center">
                <md-subheader>
                    <h3>Novo Fornecedor</h3>
                </md-subheader>

                <div layout="row" width="100%">
                    <md-input-container>
                        <label>Código</label>
                        <input name="codigo" type="text" ng-model="model.entidade.codigo" required/>

                        <div ng-messages="fornecedorForm.codigo.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-input-container>
                        <label>Fornecedor</label>
                        <input name="razaoSocial" type="text" ng-model="model.entidade.razaoSocial" required/>
                        <div ng-messages="fornecedorForm.razaoSocial.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-input-container>
                        <label>Tipo de Pessoa</label>
                        <md-select name="tipoPessoa" ng-model="model.entidade.tipoPessoa" required>
                            <md-option value="FISICA">Física</md-option>
                            <md-option value="JURIDICA">Jurídica</md-option>
                        </md-select>

                        <div ng-messages="fornecedorForm.tipoPessoa.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-input-container width="100%">
                        <label>Nome Fantasia</label>
                        <input name="nomeFantasia" type="text" ng-model="model.entidade.nomeFantasia"/>
                        <div ng-messages="fornecedorForm.nomeFantasia.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>
                </div>

                <div layout="row" width="100%" layout-align="space-between center">
                    <md-input-container>
                        <label>Endereço</label>
                        <input name="endereco" type="text" ng-model="model.entidade.endereco" required/>

                        <div ng-messages="fornecedorForm.endereco.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-input-container flex>
                        <label>Numero</label>
                        <input name="numero" type="number" ng-model="model.entidade.numero" required/>

                        <div ng-messages="fornecedorForm.numero.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-input-container flex>
                        <label>Bairro</label>
                        <input name="bairro" type="text" ng-model="model.entidade.bairro" required/>

                        <div ng-messages="fornecedorForm.bairro.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-input-container flex>
                        <label>Complemento</label>
                        <input name="complemento" type="text" ng-model="model.entidade.complemento"/>
                    </md-input-container>
                </div>

                <div layout="row" width="100%" layout-align="space-between center">

                    <md-input-container>
                        <label>CEP</label>
                        <input name="cep" type="text" ng-model="model.entidade.cep" required/>

                        <div ng-messages="fornecedorForm.cep.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <div layout="row" layout-align="center center">

                        <input style="width: 40px;" type="text" ng-model="model.codigoCidade" ng-change="buscaCidadeByCodigo()">

                        <md-input-container>
                            <label>Cidade</label>
                            <input type="text" name="cidade" ng-model="model.entidade.cidade.nome" disabled/>

                            <div ng-messages="fornecedorForm.cidade.$error">
                                <div ng-message="required">
                                    Campo obrigatório.
                                </div>
                            </div>
                        </md-input-container>

                        <md-button class="md-icon-button" ng-click="abrirPopupCidade()">
                            <i class="md-icon md-icon-search"></i>
                        </md-button>
                    </div>

                    <md-input-container>
                        <label>UF</label>
                        <input name="uf" type="text" ng-model="model.entidade.uf" required/>

                        <div ng-messages="fornecedorForm.uf.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>
                </div>

                <div layout="row" width="100%">

                    <md-input-container width="100%">
                        <label>Telefone</label>
                        <input name="telefone" type="text" ng-model="model.entidade.telefone" required/>

                        <div ng-messages="fornecedorForm.telefone.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-input-container width="100%">
                        <label>Fax</label>
                        <input name="fax" type="text" ng-model="model.entidade.fax"/>
                    </md-input-container>

                    <md-input-container width="100%">
                        <label>Email</label>
                        <input name="email" type="email" ng-model="model.entidade.email"/>
                    </md-input-container>
                </div>

                <div layout="row" width="100%" layout-align="space-between center">

                    <md-input-container>
                        <label>CPF/CNPJ</label>
                        <input name="cnpj" type="text" ng-model="model.entidade.cnpj" required/>

                        <div ng-messages="fornecedorForm.cnpj.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-input-container>
                        <label>Inscrição Estadual</label>
                        <input name="inscricaoEstadual" type="text" ng-model="model.entidade.inscricaoEstadual"/>
                    </md-input-container>

                    <md-input-container>
                        <label style="margin-bottom: 27px;">Transportadora</label>
                        <md-radio-group name="transportadora" ng-model="model.entidade.transportadora" required>
                            <md-radio-button ng-value="true" class="md-primary">Sim</md-radio-button>
                            <md-radio-button ng-value="false" selected> Não</md-radio-button>
                        </md-radio-group>

                        <div ng-messages="fornecedorForm.transportadora.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>
                </div>

                <div layout="row" layout-align="center center">

                    <input style="width: 40px;" type="text" ng-model="model.codigoCondicao" ng-change="buscaCondicaoByCodigo()">

                    <md-input-container>
                        <label>Condição de pagamento</label>
                        <input type="text" name="condicaoPagamento" ng-model="model.entidade.condicaoPagamento.descricao" required disabled/>

                        <div ng-messages="fornecedorForm.condicaoPagamento.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupCondicao()">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>
                </div>

            </form>
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