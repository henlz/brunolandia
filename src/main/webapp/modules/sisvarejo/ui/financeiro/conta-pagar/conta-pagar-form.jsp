<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout-align="center center" layout="column" layout-margin layout-padding>

    <section layout-fill>
        <form name="contaPagarForm" layout-padding layout="column" layout-align="start center" width="90%">
            <md-subheader>
                <h3 ng-if="currentState == INSERT_STATE">Nova Conta a Pagar</h3>
                <h3 ng-if="currentState == CANCEL_STATE">Cancelar Conta a Pagar</h3>
                <h3 ng-if="currentState == PAY_STATE">Informar pagamento - Conta a Pagar</h3>
            </md-subheader>

            <md-content layout="row" layout-margin ng-if="currentState == CANCEL_STATE || currentState == PAY_STATE">
                <md-input-container class="md-block">
                    <label>Observação</label>
                    <textarea width="400" name="observacao" ng-model="model.entidade.observacao" required></textarea>

                    <div ng-messages="contaPagarForm.observacao.$error">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <md-button class="md-primary md-fab" ng-click="cancelarContaPagar(model.entidade)"
                           ng-if="currentState == CANCEL_STATE">
                    <i class="md-icon md-icon-cancel"></i>
                </md-button>
                <md-button class="md-primary md-fab" ng-click="pagarContaPagar(model.entidade)"
                           ng-if="currentState == PAY_STATE">
                    <i class="md-icon md-icon-attach-money"></i>
                </md-button>
            </md-content>

            <md-content layout="row" layout-margin>

                <md-input-container>
                    <label>Número NFE</label>
                    <input type="text" name="numeroNota" ng-model="model.entidade.numeroNota"
                           ng-blur="verificarNfe(model.entidade.numeroNota)"
                           ng-readonly="currentState == CANCEL_STATE || currentState == PAY_STATE"
                           required>

                    <div ng-messages="contaPagarForm.numero.$error" ng-if="currentState == INSERT_STATE">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <md-input-container>
                    <label>Série</label>
                    <input name="serie" type="text" ng-model="model.entidade.serie" required
                           ng-readonly="currentState == CANCEL_STATE || currentState == PAY_STATE">

                    <div ng-messages="contaPagarForm.serie.$error" ng-if="currentState == INSERT_STATE">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <md-input-container>
                    <label>Modelo</label>
                    <input type="text" ng-model="model.entidade.modelo" required
                           ng-readonly="currentState == CANCEL_STATE || currentState == PAY_STATE">

                    <div ng-messages="contaPagarForm.modelo.$error" ng-if="currentState == INSERT_STATE">
                        <div ng-message="required">
                            Campo obrigatório.
                        </div>
                    </div>
                </md-input-container>

                <div layout="row" layout-align="center center">

                    <input style="width: 40px;"
                           type="text"
                           ng-model="model.codigoFornecedor"
                           ng-change="buscaFornecedorById()"
                           ng-if="currentState == INSERT_STATE">

                    <md-input-container>
                        <label>Fornecedor</label>
                        <input type="text" name="fornecedor" ng-model="model.entidade.fornecedor.razaoSocial" readonly/>

                        <div ng-messages="contaPagarForm.fornecedor.$error">
                            <div ng-message="required">
                                Campo obrigatório.
                            </div>
                        </div>
                    </md-input-container>

                    <md-button class="md-icon-button" ng-click="abrirPopupFornecedor($event, null, null, false)"
                               ng-if="currentState == INSERT_STATE"
                               aria-label="Procurar fornecedor">
                        <i class="md-icon md-icon-search"></i>
                    </md-button>
                </div>

                <md-icon class="md-icon md-icon-error"
                         ng-if="currentState == INSERT_STATE && model.invalidNfe == true && model.entidade.numeroNfe != ''"
                         style="position: absolute; top: 18px; left: 188px;">
                    <md-tooltip>
                        Número de NFE já existente!
                    </md-tooltip>
                </md-icon>

            </md-content>
        </form>
    </section>

    <md-divider width="100%"></md-divider>

    <md-content layout="row" layout-align="center center" style="width:100%;">

        <md-input-container>
            <label>Valor</label>
            <input name="valor" type="text" ng-model="model.entidade.valor" required
                   ng-readonly="currentState == CANCEL_STATE || currentState == PAY_STATE">

            <div ng-messages="contaPagarForm.valor.$error" ng-if="currentState == INSERT_STATE">
                <div ng-message="required">
                    Campo obrigatório.
                </div>
            </div>
        </md-input-container>

        <md-input-container>
            <label>Nº parcela</label>
            <input name="numeroParcela" type="text" ng-model="model.entidade.numeroParcela" required
                   ng-readonly="currentState == CANCEL_STATE || currentState == PAY_STATE">

            <div ng-messages="contaPagarForm.numeroParcela.$error" ng-if="currentState == INSERT_STATE">
                <div ng-message="required">
                    Campo obrigatório.
                </div>
            </div>
        </md-input-container>


        <md-input-container>
            <label>Data de pagamento</label>
            <input type="date" ng-model="model.entidade.dataPagamento" name="dataPagamento"
                   ng-readonly="currentState == CANCEL_STATE || currentState == PAY_STATE">
        </md-input-container>

        <md-input-container>
            <label>Data de emissão</label>
            <input type="date" ng-model="model.entidade.dataEmissao" name="dataEmissao" required
                   ng-readonly="currentState == CANCEL_STATE || currentState == PAY_STATE">

            <div ng-messages="contaPagarForm.dataEmissao.$error" ng-if="currentState == INSERT_STATE">
                <div ng-message="required">
                    Campo obrigatório.
                </div>
            </div>
        </md-input-container>

        <md-input-container>
            <label>Data de vencimento</label>
            <input type="date" name="dataVencimento" ng-model="model.entidade.dataVencimento"
                   ng-readonly="currentState == CANCEL_STATE || currentState == PAY_STATE">
        </md-input-container>

    </md-content>

    <md-content layout="row" layout-align="center center" style="width:100%;">

        <md-input-container>
            <label>Descrição</label>
            <input name="descricao" type="text" ng-model="model.entidade.descricao" required
                   ng-readonly="currentState == CANCEL_STATE || currentState == PAY_STATE">

            <div ng-messages="contaPagarForm.descricao.$error" ng-if="currentState == INSERT_STATE">
                <div ng-message="required">
                    Campo obrigatório.
                </div>
            </div>
        </md-input-container>

        <div layout="row" layout-align="center center">

            <input style="width: 40px;" type="text" ng-model="model.codigoFormaPagamento"
                   ng-change="buscaFormaPagamentoById()" ng-if="currentState == INSERT_STATE">

            <md-input-container>
                <label>Forma de pagamento</label>
                <input type="text" name="formaPagamento" ng-model="model.entidade.formaPagamento.tipo" readonly
                       required/>

                <div ng-messages="contaPagarForm.formaPagamento.$error" ng-if="currentState == INSERT_STATE">
                    <div ng-message="required">
                        Campo obrigatório.
                    </div>
                </div>
            </md-input-container>

            <md-button class="md-icon-button" ng-click="abrirPopupFormaPagamento($event)"
                       aria-label="Procurar forma de pagamento" ng-if="currentState == INSERT_STATE">
                <i class="md-icon md-icon-search"></i>
            </md-button>
        </div>

    </md-content>

    <md-divider width="100%"></md-divider>

    <section layout="row" layout-align="start center" layout-fill layout-margin>
        <md-divider></md-divider>
        <md-content width="100%" layout-padding layout="row" layout-align="end end">

            <md-button class="md-icon-button md-fab" ui-sref="conta-pagar.listar" aria-label="Cancelar cadastro">
                <i class="md-icon md-icon-cancel md-icon-lg"></i>
            </md-button>
            <md-button class="md-primary md-icon-button md-fab" ng-if="currentState == INSERT_STATE"
                       ng-click="salvarContaPagar(model.entidade)"
                       aria-label="Salvar conta a pagar">
                <i class="md-icon md-icon-save md-icon-lg"></i>
            </md-button>
        </md-content>
    </section>

</md-content>

</html>