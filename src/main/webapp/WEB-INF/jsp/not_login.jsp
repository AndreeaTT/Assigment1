<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
    <title>Bank Manager</title>

    <spring:url value="/resources/css/hello.css" var="coreCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse ">
    <div class="container">
        <div class="navbar-header">
        </div>
    </div>
</nav>