<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Bank Manager</title>

    <link href="/resources/css/home.css" rel="stylesheet">

    <link href="resources/css/menu.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>


</head>

<body>

<spring:url value="/client/create" var="addClientUrl" />
<spring:url value="/client/list" var="updateClientUrl" />
<spring:url value="/account/create" var="addAccountUrl" />
<spring:url value="/account/list" var="updateAccountUrl" />
<spring:url value="/user/create" var="addUserUrl" />
<spring:url value="/user/list" var="updateUserUrl" />


<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown active">
                    <a href="/">User</a>
                    <ul class="dropdown-content">
                        <li><a href="${pageContext.request.contextPath}/user/create.html">Add</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/list.html">List</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li class="dropdown active">
                    <a href="/">Client</a>
                    <ul class="dropdown-content">
                        <li><a href="${pageContext.request.contextPath}/client/create.html">Add</a></li>
                        <li><a href="${pageContext.request.contextPath}/client/list.html">List</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li class="dropdown active">
                    <a href="/">Account</a>
                    <ul class="dropdown-content">
                        <li><a href="${pageContext.request.contextPath}/account/create.html">Add</a></li>
                        <li><a href="${pageContext.request.contextPath}/account/list.html">List</a></li>
                    </ul>
                </li >
            </ul>
        </div>
    </div>
</nav>

<script src="/resources/js/jquery.js"></script>

<script src="/resources/js/bootstrap.min.js"></script>

</body>

</html>