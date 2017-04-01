<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <spring:url value="/resources/css/login.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
</head>

<body>
<div class="body"></div>
<div class="grad"></div>
<div class="header">
    <div>BANK <span>MANAGER</span></div>
</div>
<br>
<div class="login">
    <input type="text" placeholder="username" name="user"><br>
    <input type="password" placeholder="password" name="password"><br>
    <input type="button" value="Login">
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
</body>
</html>