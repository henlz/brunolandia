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
            <form name="produtoForm" layout-padding layout="column" layout-align="center center">
                <md-subheader>
                    <h3>Novo Produto</h3>
                </md-subheader>

                <md-input-container width="100%">
                    <label>Código</label>
                    <input name="codigo" type="text" maxlength="255" ng-model="model.entidade.codigo" required/>

                    <div ng-messages="produtoForm.codigo.$error">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <md-input-container width="100%">
                    <label>Código de Barras</label>
                    <input name="codigoBarra" type="number" ng-model="model.entidade.codigoBarra"/>
                </md-input-container>

                <md-input-container width="100%">
                    <label>Descrição</label>
                    <input name="descricao" type="text" maxlength="255" ng-model="model.entidade.descricao" required/>

                    <div ng-messages="produtoForm.fornecedor.$error">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <md-content layout="row">
                    <md-input-container flex>
                        <label>Peso líquido</label>
                        <input name="pesoLiquido" type="number" min="1" ng-max="model.entidade.pesoBruto"
                               ng-model="model.entidade.pesoLiquido" required/>

                        <div ng-messages="produtoForm.pesoLiquido.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-input-container flex>
                        <label>Peso bruto</label>
                        <input name="pesoBruto" type="number" min="1" ng-min="model.entidade.pesoLiquido"
                               ng-model="model.entidade.pesoBruto" required/>

                        <div ng-messages="produtoForm.pesoBruto.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>
                </md-content>

                <md-content layout="row">
                    <md-input-container flex>
                        <label>Preço venda</label>
                        <input name="precoVenda" type="number" ng-model="model.entidade.precoVenda" required/>

                        <div ng-messages="produtoForm.codigo.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-input-container flex>
                        <label>Unidade</label>
                        <input name="unidade" type="text" maxlength="255" ng-model="model.entidade.unidade"/>
                    </md-input-container>
                </md-content>

                <md-content layout="row" layout-align="center center">
                    <input style="width: 40px;" type="text" maxlength="255" ng-model="model.codigoGenero"
                           ng-change="buscaGeneroByCodigo()">

                    <md-input-container>
                        <label>Gênero</label>
                        <input type="text" name="genero" ng-model="model.entidade.genero.genero" readonly/>

                        <div ng-messages="produtoForm.genero.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupGenero($event)">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>
                </md-content>

                <md-content layout="row" layout-align="center center">
                    <input style="width: 40px;" type="text" maxlength="255" ng-model="model.codigoCor"
                           ng-change="buscaCorByCodigo()">

                    <md-input-container>
                        <label>Cor</label>
                        <input type="text" name="cor" ng-model="model.entidade.cor.nome" readonly/>

                        <div ng-messages="produtoForm.cor.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupCor($event)">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>
                </md-content>

                <md-content layout="row" layout-align="center center">
                    <input style="width: 40px;" type="text" maxlength="255" ng-model="model.codigoTamanho"
                           ng-change="buscaTamanhoByCodigo()">

                    <md-input-container>
                        <label>Tamanho</label>
                        <input type="text" name="tamanho" ng-model="model.entidade.tamanho.nome" readonly/>

                        <div ng-messages="produtoForm.tamanho.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupTamanho($event)">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>
                </md-content>

                <md-content layout="row" layout-align="center center">
                    <input style="width: 40px;" type="text" maxlength="255" ng-model="model.codigoICMS"
                           ng-change="buscaIcmsByCodigo(false)">

                    <md-input-container>
                        <label>ICMS</label>
                        <input type="text" ng-model="model.entidade.icms.descricao" name="icms" readonly/>

                        <div ng-messages="produtoForm.icms.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupIcms($event)">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>
                </md-content>

                <md-content layout="row" layout-align="center center">
                    <input style="width: 40px;" type="text" maxlength="255" ng-model="model.codigoNCM"
                           ng-change="buscaNcmByCodigo(false)">

                    <md-input-container>
                        <label>NCM</label>
                        <input type="text" ng-model="model.entidade.ncm.descricao" readonly name="ncm"/>

                        <div ng-messages="produtoForm.ncm.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupNcm($event)">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>
                </md-content>

                <md-content layout="row" layout-align="center center">
                    <input style="width: 40px;" type="text" maxlength="255" ng-model="model.codigoFornecedor"
                           ng-change="buscaFornecedorByCodigo(false)">

                    <md-input-container>
                        <label>Fornecedor</label>
                        <input type="text" name="fornecedor" ng-model="model.entidade.fornecedor.razaoSocial" readonly/>

                        <div ng-messages="produtoForm.fornecedor.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupFornecedor($event)">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>
                </md-content>

            </form>
        </section>
        <section layout="row" layout-align="end center" layout-fill layout-margin>
            <md-button class="md-icon-button md-fab" ui-sref="produto.listar" aria-label="Cancelar cadastro">
                <i class="md-icon md-icon-cancel md-icon-lg"></i>
            </md-button>
            <md-button class="md-primary md-icon-button md-fab" type="submit" ng-click="salvarProduto(model.entidade)"
                       aria-label="Salvar produto">
                <i class="md-icon md-icon-save md-icon-lg"></i>
            </md-button>
        </section>
    </md-whiteframe>

</md-content>

</html>