<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
    <title>Bank Manager</title>

    <spring:url value="/resources/css/hello.css" var="coreCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/user/list" var="urlUser" />
<spring:url value="/user/detail" var="urlInfo" />
<spring:url value="/logout" var="urlLogout" />

<nav class="navbar navbar-inverse ">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlUser}">User</a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="${urlLogout}">Logout</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="${urlInfo}">Info</a></li>
            </ul>
        </div>
    </div>
</nav>