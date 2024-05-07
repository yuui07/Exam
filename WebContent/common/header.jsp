<%-- ヘッダー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .header-container {
        background-color: #b3e0ff; /* Light blue background */
        display: flex;
        justify-content: center; /* Centers the header content horizontally */
        align-items: center; /* Centers the header content vertically */
        margin-bottom: 0;
        padding: 10px 0;
    }
    .header-container h1 {
        margin: 0; /* Removes default margin from the h1 tag */
    }
</style>
<div class="header-container">
    <h1 class="fs-1">得点管理システム</h1>
</div>
<c:if test="${user.isAuthenticated()}">
<<<<<<< HEAD
    <div class="nav justify-content-center mt-3">
        <span class="nav-item px-2">${user.getName()}様</span>
        <a class="nav-item px-2" href="Logout.action">ログアウト</a>
    </div>
</c:if>