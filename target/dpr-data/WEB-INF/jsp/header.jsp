<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
    <title>Bank Manager</title>

    <spring:url value="/resources/css/hello.css" var="coreCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/user/create" var="urlAddUser" />

<nav class="navbar navbar-inverse ">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlHome}">Meniu</a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="${urlAddUser}">Add User</a></li>
            </ul>
        </div>
    </div>
</nav>