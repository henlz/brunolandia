<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

    <form name="necessidadeForm" novalidate="true">

        <div layout-align="center center" style="width: 65%; margin: 0 auto; padding-top: 1px;">
            
            <eits-paper-sheet id="informacoesPaperSheet">
                <content-closed>
                    <h4><span>Necessidade</span></h4>
                </content-closed>

                <content-opened>
                    <eits-hbox style="border: none;" gap="50" padding-left="'50%'" horizontal-align="center" padding-right="'50%'">

                        <eits-vbox gap="10" width="290" horizontal-align="'center'">
                            <md-input-container>
                                <label>Código</label>
                                <input name="codigo" ng-model="entidade.codigo" required maxlength=144>

                                <div ng-messages="necessidadeForm.nome.$error">
                                    <div ng-message="required">
                                        Campo obrigatório.
                                    </div>
                                </div>
                            </md-input-container>

                            <md-input-container>
                                <label>Descrição</label>
                                <input name="descricao" ng-model="entidade.descricao" required maxlength=144>

                                <div ng-messages="necessidadeForm.nome.$error">
                                    <div ng-message="required">
                                        Campo obrigatório.
                                    </div>
                                </div>
                            </md-input-container>

                            <md-select ng-model="entidade.tipoNecessidade" placeholder="Tipo" required>
                                <md-option value="CLIENTE">Cliente</md-option>
                                <md-option value="FINANCEIRA">Financeira</md-option>
                                <md-option value="PROCESSOS_INTERNOS">Processos Internos</md-option>
                                <md-option value="APRENDIZADO_CRESCIMENTO">Aprendizado Crescimento</md-option>
                            </md-select>
                            <div class="observacao-select">Campo obrigatório.</div>

                            <md-select ng-model="entidade.origem" placeholder="Origem" required>
                                <md-option ng-repeat="itemDocumento in listaDocumentos" ng-value="itemDocumento"
                                    ng-selected="itemDocumento.documentoReferencia.id == entidade.origem.id">{{itemDocumento.documentoReferencia.titulo}}
                                </md-option>
                            </md-select>
                            <div class="observacao-select">Campo obrigatório.</div>
                        </eits-vbox>

                        <eits-vbox gap="15" width="290" horizontal-align="'center'">

                            <md-select ng-model="entidade.urgencia" placeholder="Urgência" required>
                                <md-option ng-repeat="criterio in listaCriteriosPriorizacao" ng-value="criterio"
                                    ng-selected="criterio.id == entidade.urgencia.id">{{criterio.urgencia}}
                                </md-option>
                            </md-select>
                            <div class="observacao-select">Campo obrigatório.</div>

                            <md-select ng-model="entidade.gravidade" placeholder="Gravidade" required>
                                <md-option ng-repeat="criterio in listaCriteriosPriorizacao" ng-value="criterio"
                                    ng-selected="criterio.id == entidade.gravidade.id">{{criterio.gravidade}}
                                </md-option>
                            </md-select>
                            <div class="observacao-select">Campo obrigatório.</div>

                            <md-select ng-model="entidade.tendencia" placeholder="Tendência" required>
                                <md-option ng-repeat="criterio in listaCriteriosPriorizacao" ng-value="criterio"
                                    ng-selected="criterio.id == entidade.tendencia.id">{{criterio.tendencia}}
                                </md-option>
                            </md-select>
                            <div class="observacao-select">Campo obrigatório.</div>

                        </eits-vbox>

                    </eits-hbox>
                    
                    <md-button class="md-fab md-primary" ng-click="saveNecessidadeInfo()" ng-if="currentState == 'necessidade-ti.criar'" aria-label="Salvar informações de Necessidade de TI">
                      <i class="md-icon md-icon-save"></i>
                    </md-button>
                    
                    <md-button class="md-fab md-primary" ng-click="alterarNecessidade(entidade)" ng-if="currentState == 'necessidade-ti.editar'" aria-label="Atualizar Necessidade de TI">
                      <i class="md-icon md-icon-save"></i>
                    </md-button>
                </content-opened>

            </eits-paper-sheet>
        </div>

        <!--<div layout-align="center center" style="width: 65%; margin: 0 auto; padding-top: 1px;">-->

            <!--<eits-paper-sheet id="myPaperSheet1">-->
                <!--<content-closed>-->
                    <!--<h4><span>Organização Militar</span></h4>-->
                <!--</content-closed>-->

                <!--<content-opened>-->
                <!---->
                    <!--<div class="md-actions" layout="row" style="float:right; padding-right: 5px;">-->
                        <!--<md-button class="md-raised" ng-click="abrirPopupAssociarOrganizacaoMilitar($event)">Associar organização militar</md-button>-->
                    <!--</div>-->
                    <!---->
                    <!--<eits-table id="table" content="organizacoesAssociadas" on-selection-change="selectionUpdate(selectedItens)" on-item-click="itemClicked(item)">       -->
    					<!--<columns>-->
                            <!--<table-column header="Sigla" field="organizacaoMilitar['sigla']" sortable="false">-->
                                <!--<column-template>-->
                                    <!--{{::row.organizacaoMilitar.sigla}}-->
                                <!--</column-template>-->
                            <!--</table-column>-->
                            <!--<table-column header="Área" sortable="false">-->
                                <!--<column-template>-->
                                    <!--{{::row.organizacaoMilitar.nome}}-->
                                <!--</column-template>-->
                            <!--</table-column>-->
    					    <!--<table-column header="Ação" sortable="false" width="15%">-->
                                <!--<column-template>-->
                                    <!--<button ng-click="removerNecessidadeOrganizacaoMilitar(row)">Remover</button>-->
                                <!--</column-template>-->
                            <!--</table-column>-->
                        <!--</columns>  -->
                    <!--</eits-table>                 -->
                <!--</content-opened>-->
            <!--</eits-paper-sheet>-->
        <!--</div>-->

        <div layout-align="center center" style="width: 65%; margin: 0 auto; padding-top: 1px;">

            <eits-paper-sheet id="myPaperSheet2">
                <content-closed>
                    <h4><span>Necessidades Relacionadas</span></h4>
                </content-closed>

                <content-opened height="300">
                     <div class="md-actions" layout="row" style="float:right; padding-right: 5px;">
                        <md-button class="md-raised" ng-click="abrirPopupAssociarNecessidadesRelacionadas($event)">Associar necessidades relacionadas</md-button>
                        <md-button ng-click="zerarNecessidades()">Zerar necessidade</md-button> 
                     </div>
                     
                    <eits-table id="table" content="necessidadesRelacionadas" on-selection-change="selectionUpdate(selectedItens)" on-item-click="itemClicked(item)">
    					<columns>
                        <table-column header="Código" width="30%">,
                            <column-template>
                                {{::row.codigo}}
                            </column-template>
                        </table-column>
                        <table-column header="Descrição" width="40%">
                            <column-template>
                                <span ng-show="row.tipo == 'ESTRATEGIA'">{{::row.estrategia.descricao}}</span>
                                <span ng-show="row.tipo == 'PRINCIPIO_DIRETRIZ'">{{::row.principioDiretriz.descricao}}</span>
                                <span ng-show="row.tipo == 'ITEM_SWOT'">{{::row.itemSwot.descricao}}</span>
                            </column-template>
                        </table-column>
                        <table-column header="Tipo" field="tipo" width="40%">
                            <column-template>
                                <span ng-show="row.tipo == 'ESTRATEGIA'">Estratégia</span>
                                <span ng-show="row.tipo == 'PRINCIPIO_DIRETRIZ'">Princípio/Diretriz</span>
                                <span ng-show="row.tipo == 'ITEM_SWOT'">Item Swot</span>
                            </column-template>
                        </table-column>
                    </columns>
                    </eits-table>
  
                </content-opened>
            </eits-paper-sheet>
        </div>
    </form>
</html>