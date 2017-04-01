<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Transfer money page</title>
</head>
<body>
<h1>Transfer money</h1>
<form:form method="POST" commandName="transfer" action="${pageContext.request.contextPath}/transfer/money.html" >
    <table>
        <tbody>
        <tr>
            <td>Id-ul contului ce trimite bani:</td>
            <td><form:input path="senderID" /></td>
            <td><form:errors path="senderID" cssStyle="color: red;"/></td>
        </tr>
        <tr>
            <td>Id-ul contului care ii primeste:</td>
            <td><form:input path="receiverID" /></td>
            <td><form:errors path="receiverID" cssStyle="color: red;"/></td>
        </tr>
        <tr>
            <td>Suma transmisa:</td>
            <td><form:input path="value" /></td>
            <td><form:errors path="value" cssStyle="color: red;"/></td>
        </tr>
        <td><input type="submit" value="Create" /></td>
        <td></td>
        <td></td>
        </tr>
        </tbody>
    </table>
</form:form>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>