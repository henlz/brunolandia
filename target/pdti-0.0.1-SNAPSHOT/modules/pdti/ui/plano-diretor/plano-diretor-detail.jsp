<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">
	
  <md-content class="md-padding">
    <md-tabs md-dynamic-height md-border-bottom>
      <md-tab label="informações">
        <md-content class="md-padding">
          <form name="planoForm" novalidate="true">
            <eits-hbox gap="30" width="65%">
                    <md-input-container flex>
                        <label>Ano de Início</label>
                        <input name="anoInicio" type="date" ng-model="entidade.anoInicio" readonly="readonly"></input>
                    </md-input-container>
                    <md-input-container flex>
                        <label>Ano de Término</label>
                        <input name="anoTermino" type="date" ng-model="entidade.anoTermino" readonly="readonly"></input>
                    </md-input-container>
                    <md-input-container flex>
                        <label>Versão</label>
                        <input type="text" ng-model="entidade.versao" readonly="readonly"></input>
                    </md-input-container>
                </eits-hbox>
                <eits-hbox width="65%">
                    <md-input-container flex>
                        <label>Descrição</label>
                        <textarea ng-model="entidade.descricao" columns="1" readonly="readonly"></textarea>
                    </md-input-container>
                </eits-hbox>
          </form>
        </md-content>
      </md-tab>
      <md-tab label="gerenciamento de colaboradores">
        <md-content class="md-padding">
          
                      <eits-hbox gap="275" layout="row" width="80%"> 
                          <h4><span>Colaboradores</span></h4>
                      </eits-hbox>    
                              
                      <eits-vbox style="float:left; width: 65%; margin: 0 auto; padding-top: 1px;">        
                                <eits-table id="table" content="organizacoesAssociadas" on-selection-change="selectionUpdate(selectedItens)" on-item-click="itemClicked(item)">       
                                    	<columns>
                                           <table-column header="Nome" sortable="false" width="60%">
                                                <column-template>
                                                     {{::row.elaborador.usuario}}
                                                </column-template>
                                            </table-column>
                                                   
                                            <table-column header="Cargo" sortable="false" width="30%">
                                                  <column-template>
                                                       {{::row.elaborador.cargo}}
                                                  </column-template>
                                            </table-column>     
                                                    
                                            <table-column header="Ação" sortable="false" width="15%"></table-column>   
                                      </columns> 
                                  </eits-table>                                  
                      </eits-vbox>                                        
        </md-content>
      </md-tab>
    </md-tabs>
  </md-content>
	
</html>