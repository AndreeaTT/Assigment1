<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Bank Manager</title>

    <spring:url value="/resources/css/hello.css" var="coreCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
</head>

<body>
<spring:url value="/account/list" var="urlAccount" />
<spring:url value="/client/list" var="urlClient" />
<spring:url value="/transfer/create" var="urlTransfer" />
<spring:url value="/bill/list" var="urlBill" />
<spring:url value="/logout" var="urlLogout" />
<spring:url value="/user/detail" var="urlInfo" />

<nav class="navbar navbar-inverse ">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlAccount}">Account</a>
            <a class="navbar-brand" href="${urlClient}">Client</a>
            <a class="navbar-brand" href="${urlTransfer}">Transfer</a>
            <a class="navbar-brand" href="${urlBill}">Bill</a>
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
</body>
</html>