<%-- ログアウトJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="session" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="content">
        <section class="w-75 text-center m-auto border pb-3">
            <div id="wrap_box">
                <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">ログアウト</h2>
                <%-- ログアウト処理 --%>
                <% session.invalidate(); %>
                <p>ログアウトしました。</p>
                <p><a href="/login.jsp">ログイン</a></p>
            </div>
        </section>
    </c:param>
</c:import>
