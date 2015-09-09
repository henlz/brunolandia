<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">
<md-content id="sectionsWindow" style="background: #EEEEEE" layout="row">
    <md-sidenav class="md-sidenav-left md-whiteframe-z2" md-component-id="left" md-is-locked-open="true">
        <md-content>
            <md-list class="md-no-sticky pdti-filters">
                <md-list-item ng-click="model.filters.statusDocumento = ''"
                              ng-class="{selected: model.filters.statusDocumento == ''}">
                    <span class="background-span"></span> Todos
                </md-list-item>
                <md-list-item ng-click="model.filters.statusDocumento = 'PUBLICADO'"
                              ng-class="{selected: model.filters.statusDocumento == 'PUBLICADO'}">
                    <span class="background-span"></span>Publicados
                </md-list-item>
                <md-list-item ng-click="model.filters.statusDocumento = 'RASCUNHO'"
                              ng-class="{selected: model.filters.statusDocumento == 'RASCUNHO'}">
                    <span class="background-span"></span>Rascunhos
                </md-list-item>
            </md-list>
        </md-content>
    </md-sidenav>
    <section layout="row" layout-fill layout-align="start start" layout-margin layout-padding layout-wrap>
        {{::calculateWindowSize()}}

        <md-card ng-repeat="planoDiretor in currentPage.content | filter: model.filters" height="260" width="209"
                 style="position:relative; padding-left: 10px">
            <h3 class="md-subhead" style="font-weight: bold;">{{::planoDiretor.anoInicio | date: 'yyyy'}} -
                {{::planoDiretor.anoTermino | date: 'yyyy'}}</h3>

            <p flex="30" class="plano-diretor-descricao">
                {{::planoDiretor.descricao}}
            </p>

            <p class="plano-diretor-versao">
                v {{::planoDiretor.versao}}
            </p>

            <div style="bottom: 0; position: absolute; width: 100%;" layout="row" layout-align="space-between center">
                <span class="plano-diretor-status">{{::planoDiretor.statusDocumento}}</span>
                <md-menu>
                    <md-button aria-label="Abrir opções de documento PDTI" class="md-icon-button"
                               ng-click="$mdOpenMenu()">
                        <i class="md-icon md-icon-more-horiz md-icon-lg"></i>
                    </md-button>
                    <md-menu-content width="4">
                        <md-menu-item ng-if="planoDiretor.statusDocumento != 'RASCUNHO'">
                            <md-button>
                                Nova versão
                            </md-button>
                        </md-menu-item>
                        <md-menu-item>
                            <md-button ui-sref="plano-diretor.secoes({id: planoDiretor.id})">
                                Editar seções
                            </md-button>
                        </md-menu-item>
                        <md-menu-item>
                            <md-button ui-sref="plano-diretor.editar({id: planoDiretor.id})">
                                Alterar Informações
                            </md-button>
                        </md-menu-item>
                        <md-menu-divider></md-menu-divider>
                        <md-menu-item>
                            <md-button ui-sref="necessidade-ti.listar({planoDiretorId: planoDiretor.id})">
                                Necessidades de TI
                            </md-button>
                        </md-menu-item>
                        <md-menu-divider></md-menu-divider>
                        <md-menu-item>
                            <md-button ng-click="excluirPlanoDiretor($event, planoDiretor)">
                                Excluir versão
                            </md-button>
                        </md-menu-item>
                    </md-menu-content>
                </md-menu>
            </div>
        </md-card>
    </section>
</md-content>
</html>