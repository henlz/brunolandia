<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">
			
		<md-button aria-label="Adicionar Meta" class="md-fab md-fab-top-right" ng-click="insertMeta()">
	    	<md-icon class="md-icon-save md-icon-lg"></md-icon>
		</md-button>
		<md-content layout="row" layout-padding layout-align="center start" class="content">
			<div layout-align="center start" style="max-width:1000px; min-width: 1000px">
				<form name="model.form">
					<div class="eits-paper-sheet-item">
						<div class="paper-sheet-closed">
							<div layout="row" layout-margin layout-align="start center">								
								<h4><span>Informações da Meta</span></h4>
							</div>
							<eits-hbox style="border: none; padding-left:30px; padding-right:30px;" gap="50" padding-left="'50%'" horizontal-align="left" padding-right="'50%'">
								<eits-vbox gap="10" width="100%" horizontal-align="'center'">
                            		<md-input-container width="50%">
                                		<label>Código</label>
                                		<input name="codigo" ng-model="model.meta.codigo" required maxlength=144>
		                                <div ng-messages="model.form.codigo.$error">
		                                    <div ng-message="required" ng-show="!model.form.codigo.$valid">
		                                        Campo obrigatório.
		                                    </div>
		                                </div>									
                            		</md-input-container>
									
									<eits-hbox style="border: none;" gap="30" padding-left="'50%'" horizontal-align="left" padding-right="'50%'">									
			                            <md-input-container width="50%">
			                                <label>Indicador</label>
			                                <input name="indicador" ng-model="model.meta.indicador" required maxlength=144>		
			                                <div ng-messages="model.form.$error" ng-show="!model.form.indicador.$valid">
			                                    <div ng-message="required">
			                                        Campo obrigatório.
			                                    </div>
			                                </div>
			                            </md-input-container>
	
			                            <md-input-container width="50%">
			                                <label>Valor Indicador</label>
			                                <input name="valorIndicador" ng-model="model.meta.valorIndicador" required maxlength=144>		
			                                <div ng-messages="model.form.valorIndicador.$error" ng-show="!model.form.valorIndicador.$valid">
			                                    <div ng-message="required">
			                                        Campo obrigatório.
			                                    </div>
			                                </div>
			                            </md-input-container>										
									</eits-hbox>
									
									<md-input-container>
			                                <label>Descrição</label>
			                                <input name="descricao" ng-model="model.meta.descricao" required maxlength=144>		
			                                <div ng-messages="model.form.descricao.$error" ng-show="!model.form.descricao.$valid">
			                                    <div ng-message="required">
			                                        Campo obrigatório.
			                                    </div>
			                                </div>
			                        </md-input-container>
									
									<md-input-container width="30%">
			                                <label>Prazo</label>
			                                <input name="prazo" type="date" ng-model="model.meta.prazo" required maxlength=144>		
			                                <div ng-messages="model.form.prazo.$error" ng-show="!model.form.prazo.$valid">
			                                    <div ng-message="required">
			                                        Campo obrigatório.
			                                    </div>
			                                </div>
			                        </md-input-container>                            
                        		</eits-vbox>
							</eits-hbox>
						</div>
					</div>						
				
					<md-content layout="row" layout-padding layout-align="center start" class="content">
						<div layout-align="center start" style="max-width:1000px; min-width: 1000px">					
							<div class="eits-paper-sheet-item">
								<div class="paper-sheet-closed">
									<div layout="row" layout-margin layout-align="start center">								
										<h4><span>Atividades</span></h4>
									</div>
									<div layout="row" layout-margin layout-align="center center">									
										<md-button title="Você precisa salvar a meta antes de criar uma nova atividade" aria-label="Nova Atividade" class="md-fab md-accent md-fab-top-right" ng-disabled="currentState == INSERT_META_STATE"  ng-click="abrirPopUpNovaAtividade($event)" >
											<md-icon class="md-icon-add md-icon-lg"></md-icon>
											<div class="md-ripple-container"></div>
										</md-button>
									</div>
									<div layout="row" layout-margin layout-align="center center">
										<eits-table id="table" content="model.pageAtividades.content" multi-selection="true" on-selection-change="selectionUpdate(selectedItens)" on-item-click="abrirPopupAlterarAtividade(null,item)">
									         <columns>
									            <table-column header="Código" field="codigo" sortable="true" width="5%"/>
									            <table-column header="Descrição" field="descricao" width="50%"/>									            
												<table-column header="Início" field="inicio" sortable="true">
                									<column-template>
                    									{{::row.inicio | date: 'dd/MM/yyyy'}}
                									</column-template>
												</table-column>
												<table-column header="Término" field="termino" sortable="true">
                									<column-template>
                    									{{::row.termino | date: 'dd/MM/yyyy'}}
                									</column-template>
												</table-column>												
									            <table-column header="Ações" width="5%"/>
									                <column-template>
									                    <a ui-sref="necessidade-ti.editar({id: row.id})">Alterar</a>								                    
									                </column-template>
									            </table-column>
									        </columns>
	    								</eits-table>										
									</div>	
								</div>
							</div>					
						</div>				
					</md-content>
				</form>
			</div>			
		</md-content>
		<md-content  class="content">
		<bottomsheet>
			<div class="bottom-sheet-content">
				<div class="left-content" layout="row">
					<div>{{model.itensExcluir.length}} {{model.itensExcluir.length > 1 ? 'itens' : 'item'}} selecionado{{model.itensExcluir.length > 1 ? 's' : ''}}</div>
					<div class="limpar-selecao" ng-click="limparSelecao()">Limpar seleção</div>
				</div>
				<div class="right-content">
					<md-button class="md-raised" aria-label="Excluir" ng-click="changeToRemoveAtividade($event, model.itensExcluir)"><i class="md-icon-delete md-icon-lg"></i></md-button>
				</div>
			</div>
    	</bottomsheet>
		</md-content>									
</html>