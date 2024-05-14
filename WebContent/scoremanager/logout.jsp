<%-- logou --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>



	<c:param name="content">
		<section class="w-75 text-center m-auto border pb-3">
			<form action = "LoginExecute.action" method="post">
				<div id="wrap_box">
					<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">ログアウト</h2>
					<c:if test="${errors.size()>0}">
						<div>
							<ul>
								<c:forEach var="error" items="${errors}">
									<li>${error}</li>

								</c:forEach>
							</ul>
						</div>
					</c:if>
				<div class="mt-4">
						<input class="w-25 btn btn-lg btn-primary" type="submit" name="logout" value="ログアウト"/>
					</div>
				</div>
			</form>
		</section>
	</c:param>
</c:import>