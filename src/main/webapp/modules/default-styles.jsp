<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="./webjars/angular-material/<spring:eval expression="@environment.getProperty('angular-material.version')"/>/angular-material.min.css">
<link rel="stylesheet" href="./webjars/eits-md/<spring:eval expression="@environment.getProperty('eits-webjars.version')"/>/controls/table/style/style.css">
<link rel="stylesheet" href="./static/css/theme.css">
<link rel="stylesheet" href="./static/font-icons/css/material-design-iconic-font.css">
<link rel="stylesheet" type="text/css" href="./static/js/md-data-table/md-data-table.min.css">