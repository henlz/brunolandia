<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<md-content>
    <div layout="row" ng-style="model.windowHeight" id="sectionsWindow">
        <!-- Função que calcula o tamanho da tela -->
        {{::calculateWindowSize()}}
        <div layout="column" flex="20" style="background: #EEEEEE">
            <div flex>
                <md-content>
                    <md-list style="background: #FFFFFF" class="secoes-list">
                        <md-list-item ng-repeat="secao in secoes track by secao.id | orderBy: 'posicao'"
                                      ng-click="selectSecao(secao, $event)"
                                      ng-class="{selected: secao.id == secaoAtual.id}">
                            <p ng-cloak>
                                <i ng-show="secao.secaoSuperior != null" class="md-icon md-icon-keyboard-arrow-right"
                                   style="margin: 0 15px;"></i>
                                {{secao.secaoSuperior != null ? secao.secaoSuperior.posicao : secao.posicao
                                }}.{{secao.secaoSuperior != null ? secao.posicao + '.' : '' }} {{ secao.titulo }}
                            </p>
                        </md-list-item>
                    </md-list>
                </md-content>
            </div>
            <div bottom="0" height="80" style="background: rgb(0,78,161); color: white; font-size: 25px;">
                <eits-hbox vertical-align="center" horizontal-align="center" gap="0" height="100%">
                    <md-button class="md-icon-button" aria-label="Nova seção" ng-click="abrirPopupNovaSecao($event)">
                        <i class="md-icon md-icon-add"></i>
                    </md-button>
                    <md-button class="md-icon-button" ng-disabled="secaoAtual.secaoSuperior == null || secaoAtual == undefined"
                               aria-label="Identar para esquerda" ng-click="indentSecaoToLeft(secaoAtual)">
                        <i class="md-icon md-icon-format-indent-decrease"></i>
                    </md-button>
                    <md-button class="md-icon-button"
                               ng-disabled="secaoAtual.secaoSuperior != null || secaoAtual.posicao == 1 || isFatherSection(secaoAtual) || secaoAtual == undefined"
                               aria-label="Identar para direita" ng-click="indentSecaoToRight(secaoAtual)">
                        <i class="md-icon md-icon-format-indent-increase"></i>
                    </md-button>
                    <md-button class="md-icon-button"
                               ng-disabled="secaoAtual.posicao == 1 || secaoAtual == undefined"
                               aria-label="Subir seção" ng-click="subirSecao(secaoAtual)">
                        <i class="md-icon md-icon-keyboard-arrow-up"></i>
                    </md-button>
                    <md-button class="md-icon-button"
                               ng-disabled="!isAbleToDescend(secaoAtual)"
                               aria-label="Descer seção" ng-click="descerSecao(secaoAtual)">
                        <i class="md-icon md-icon-keyboard-arrow-down"></i>
                    </md-button>
                    <md-button class="md-icon-button" ng-disabled="isFatherSection(secaoAtual) || secaoAtual == undefined"
                               aria-label="Excluir seção"
                               ng-click="excluirSecao($event)">
                        <i class="md-icon md-icon-delete"></i>
                    </md-button>
                </eits-hbox>
            </div>
        </div>

        <div layout="column" flex layout-align="start center" style="padding: 32px 0; background-color: #E0E0E0;">
            <div layout="column" width="60%" class="md-whiteframe-z4"
                 style="background-color: white; position:relative; width: 600px;" ng-show="secaoAtual != null"
                 ng-cloak>
                <md-content>
                    <md-subheader class="md-primary">
                        <h3>{{ secaoAtual.titulo }}</h3>
                    </md-subheader>

                    <md-divider></md-divider>

                    <form name="sectionForm" layout="column" layout-align="start start" layout-fill>
                        <md-input-container width="80%">
                            <label> {{ !isTabular(secaoAtual) ? 'Conteúdo' : 'Texto Introdutório'
                                }}</label>
                            <textarea froala="froalaOptions" ng-model="secaoAtual.conteudo" ng-change="triggerChangeHandler()"/>
                        </md-input-container>

                        <md-input-container ng-show="isTabular(secaoAtual)" width="80%">
                            <label>Texto de conclusão</label>
                            <textarea ng-model="secaoAtual.posConteudo" ng-change="triggerChangeHandler()"/>
                        </md-input-container>
                    </form>
                    <div ng-show="isTabular(secaoAtual)">
                        <md-divider></md-divider>
                        <div layout="row">
                            <md-subheader class="md-accent">
                                <h3>Itens Associados à Seção</h3>
                            </md-subheader>
                            <div style="position: absolute; right: 0px;">
                                <md-button ng-show="secaoAtual.tipoSecao !=  'TEXTO'" class="md-fab"
                                           aria-label="Associar itens de documento"
                                           flex="20" ng-click="attachRegistersToSection(secaoAtual, $event)">
                                    <i class="md-icon md-icon-attach-file"></i>
                                </md-button>
                            </div>
                        </div>

                        <md-divider></md-divider>

                        <eits-table id="tableItensAssociados" content="itensDocumento">
                            <columns>
                                <table-column header="Código" field="codigo" sortable="true" width="10%">
                                    <column-template>
                                        <span ng-show="row.secao.tipoSecao == 'TERMO_ABREVIACAO'"> - </span>
                                        <span ng-show="row.secao.tipoSecao != 'TERMO_ABREVIACAO'">{{ row.codigo }}</span>
                                    </column-template>
                                </table-column>
                                <table-column header="Descrição" width="30%">
                                    <column-template>
                                        <span ng-show="row.secao.tipoSecao == 'ANALISE_SWOT'">{{ row.itemSwot.descricao }}</span>
                                        <span ng-show="row.secao.tipoSecao == 'TERMO_ABREVIACAO'">{{ row.termoAbreviacao.termo }}</span>
                                        <span ng-show="row.secao.tipoSecao == 'ESTRATEGIA'">{{ row.estrategia.descricao }}</span>
                                        <span ng-show="row.secao.tipoSecao == 'PRINCIPIO_DIRETRIZ'">{{ row.principioDiretriz.descricao }}</span>
                                        <span ng-show="row.secao.tipoSecao == 'DOCUMENTO_REFERENCIA'">{{ row.documentoReferencia.titulo }}</span>
                                    </column-template>
                                </table-column>
                                <table-column header="Ações" width="30%">
                                    <column-template>
                                        <button ng-click="">Excluir</button>
                                    </column-template>
                                </table-column>
                            </columns>
                        </eits-table>
                    </div>
                </md-content>
                <md-subheader>
                    <div layout="row" layout-align="end center">
                        <md-divider></md-divider>
                        <md-button class="md-fab md-hue-2 md-primary" aria-label="Salvar seção"
                                   ng-click="salvarSecaoAtual()" ng-disabled="!model.dirtyForm">
                            <i class="md-icon md-icon-save"></i>
                        </md-button>
                    </div>
                </md-subheader>

            </div>
        </div>
    </div>
</md-content>