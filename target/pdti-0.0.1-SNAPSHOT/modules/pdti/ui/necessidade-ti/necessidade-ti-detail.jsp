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
                    <eits-vbox style="border: none;" gap="10">

                            
                        <md-list layout="row">
                            <md-list-item class="md-2-line">
                                <div class="md-list-item-text">
                                    <p>Código</p>
                                    <h3>{{::entidade.codigo}}</h3>
                                </div>
                            </md-list-item>
                            <md-list-item class="md-2-line">
                                <div class="md-list-item-text">
                                    <p>Descrição</p>
                                    <h3>{{::entidade.descricao}}</h3>
                                </div>
                            </md-list-item>
                            <md-list-item class="md-2-line">
                                <div class="md-list-item-text">
                                    <p>Tipo de Necessidade</p>
                                    <h3>
                                        <span ng-if="entidade.tipoNecessidade == 'CLIENTE'">Cliente</span>
                                        <span ng-if="entidade.tipoNecessidade == 'FINANCEIRA'">Financeira</span>
                                        <span ng-if="entidade.tipoNecessidade == 'PROCESSOS_INTERNOS'">Processos Internos</span>
                                        <span ng-if="entidade.tipoNecessidade == 'APRENDIZADO_CRESCIMENTO'">Aprendizado Crescimento</span>
                                    </h3>
                                </div>
                            </md-list-item>
                            <md-list-item class="md-2-line">
                                <div class="md-list-item-text">
                                    <p>Origem</p>
                                    <h3>{{::entidade.origem.documentoReferencia.titulo}}</h3>
                                </div>
                            </md-list-item>
                        </md-list>
                            
                        <md-list layout="row">
                            <md-list-item class="md-2-line">
                                <div class="md-list-item-text">
                                    <p>Urgência</p>
                                    <h3>{{::entidade.urgencia.valor + " - " + entidade.urgencia.urgencia}}</h3>
                                </div>
                            </md-list-item>
                            <md-list-item class="md-2-line">
                                <div class="md-list-item-text">
                                    <p>Gravidade</p>
                                    <h3>{{::entidade.gravidade.valor + " - " + entidade.gravidade.gravidade}}</h3>
                                </div>
                            </md-list-item>
                            <md-list-item class="md-2-line">
                                <div class="md-list-item-text">
                                    <p>Tendência</p>
                                    <h3>{{::entidade.tendencia.valor + " - " + entidade.tendencia.tendencia}}</h3>
                                </div>
                            </md-list-item>
                        </md-list>

                    </eits-vbox>
                </content-opened>

            </eits-paper-sheet>
        </div>

        <div layout-align="center center" style="width: 65%; margin: 0 auto; padding-top: 1px;">

            <eits-paper-sheet id="myPaperSheet1">
                <content-closed>
                    <h4><span>Organização Militar</span></h4>
                </content-closed>

                <content-opened>    
                    <eits-table id="table" content="organizacoesAssociadas" on-selection-change="selectionUpdate(selectedItens)" on-item-click="itemClicked(item)">       
    					<columns>
                            <table-column header="Sigla" field="organizacaoMilitar['sigla']" sortable="false">
                                <column-template>
                                    {{::row.organizacaoMilitar.sigla}}
                                </column-template>
                            </table-column>
                            <table-column header="Área" sortable="false">
                                <column-template>
                                    {{::row.organizacaoMilitar.nome}}
                                </column-template>
                            </table-column>
                        </columns>  
                    </eits-table>                 
                </content-opened>
            </eits-paper-sheet>
        </div>

        <div layout-align="center center" style="width: 65%; margin: 0 auto; padding-top: 1px;">

            <eits-paper-sheet id="myPaperSheet2">
                <content-closed>
                    <h4><span>Necessidades Relacionadas</span></h4>
                </content-closed>

                <content-opened height="300">
                     
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