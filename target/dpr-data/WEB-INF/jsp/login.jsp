<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<link href="/resources/css/login_bootstrap.css" rel="stylesheet">

<body>

<c:if test="${not empty message}">
    <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
        <strong>${message}</strong>
    </div>
</c:if>

<jsp:include page="/WEB-INF/jsp/not_login.jsp" />
<spring:url value="/" var="clientActionUrl" />

<div class="container">
    <div class="card card-container">
        <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
        <p id="profile-name" class="profile-name-card"></p>
        <form:form class="form-horizontal" method="POST" commandName="user" action="${clientActionUrl}">

        <span id="reauth-email" class="reauth-email"></span>
        <spring:bind path="username">
            <form:input path="username" type="text" class="form-control " id="username" placeholder="Username" />
            <form:errors path="username" class="control-label" cssStyle="color: red;"/>
        </spring:bind>
            <spring:bind path="password">
                <form:input path="password" type="password" class="form-control " id="password" placeholder="Password" />
                <form:errors path="password" class="control-label" cssStyle="color: red;"/>
            </spring:bind>
            <button class="btn btn-lg btn-success btn-block btn-signin" type="submit">Login</button>
        </form:form>
    </div><!-- /card-container -->
</div><!-- /container -->

<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>