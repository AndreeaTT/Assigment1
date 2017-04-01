<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>History List page</title>
</head>
<body>
<h1>History List page</h1>
<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
    <thead>
    <tr>
        <th width="150px">HistoryID</th><th width="150px">UserID</th><th width="150px">Action</th><th width="150px">Date of action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="history" items="${historyList}">
        <tr>
            <td>${history.id}</td>
            <td>${history.userID}</td>
            <td>${history.action}</td>
            <td>${history.actionData}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>