<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout-align="center center" layout="column" layout-margin>

    <md-whiteframe class="md-whiteframe-z1" width="50%" layout="column" layout-align="center center"
                   style="  margin-bottom: 5px;" layout-padding>
        <section layout-fill>
            <md-content layout-padding layout="column" layout-align="center center" width="90%">
                <md-subheader>
                    <h3>Nova Condição de Pagamento</h3>
                </md-subheader>

                <md-input-container width="100%">
                    <label>Descrição</label>
                    <input name="descricao" ng-model="model.entidade.descricao" required type="text">

                    <div ng-messages="condicaoForm.condicao.$error">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

            </md-content>
        </section>
        <md-divider></md-divider>
        <section layout="column" layout-align="start start" layout-fill layout-margin>

            <md-subheader width="100%">
                <h3>Parcelas</h3>
                <md-button style="position: absolute; left: 74px; bottom: 0px;" class="md-icon-button md-raised" ng-click="abrirPopupNovaParcela($event)">
                    <i class="md-icon md-icon-add"></i>
                </md-button>
            </md-subheader>

            <md-divider></md-divider>

            <md-content>
                <md-data-table-container>
                    <table md-data-table>
                        <thead md-trim-column-names>
                        <tr>
                            <th>Número</th>
                            <th>Dias</th>
                            <th>Percentual</th>
                            <th>Forma de Pagamento</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="parcela in model.entidade.parcelas | orderBy: 'numeroDaParcela'">
                            <td>{{::parcela.numeroDaParcela}}</td>
                            <td>{{::parcela.dias}}</td>
                            <td>{{:: parcela.percentual}} %</td>
                            <td>{{:: parcela.formaPagamento.tipo}}</td>
                            <td layout="row">
                                <md-button class="md-icon-button" ng-click="abrirPopupAlterarParcela($event, parcela)"
                                           aria-label="Alterar parcela">
                                    <i class="md-icon md-icon-edit"></i>
                                </md-button>
                                <md-button class="md-icon-button" ng-click="excluirParcela($event, parcela)"
                                           aria-label="Excluir parcela">
                                    <i class="md-icon md-icon-delete"></i>
                                </md-button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </md-data-table-container>
            </md-content>
        </section>

        <section layout="row" layout-align="start center" layout-fill layout-margin>
            <md-divider></md-divider>
            <md-content width="100%" layout-padding layout="row" layout-align="end end">

                <md-button class="md-icon-button md-fab" ui-sref="condicao.listar" aria-label="Cancelar cadastro">
                    <i class="md-icon md-icon-cancel md-icon-lg"></i>
                </md-button>
                <md-button class="md-primary md-icon-button md-fab" ng-if="currentState == 'condicao.cadastrar'"
                           ng-click="salvarCondicao(model.entidade)"
                           aria-label="Salvar condição">
                    <i class="md-icon md-icon-save md-icon-lg"></i>
                </md-button>
                <md-button class="md-primary md-icon-button md-fab" ng-if="currentState == 'condicao.alterar'"
                           ng-click="alterarCondicao(model.entidade)"
                           aria-label="Alterar condição">
                    <i class="md-icon md-icon-save md-icon-lg"></i>
                </md-button>
            </md-content>
        </section>
    </md-whiteframe>

</md-content>

</html>