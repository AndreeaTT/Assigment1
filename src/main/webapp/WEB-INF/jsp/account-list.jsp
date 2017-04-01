<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<body>

<div class="container">

    <c:if test="${not empty msg}">
    <div class="alert alert-${css} alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
        <strong>${msg}</strong>
    </div>
    </c:if>

    <h1>All Accounts</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>ClientID</th>
            <th>TypeAccount</th>
            <th>Amount</th>
            <th>CreateData</th>
            <th>Action</th>
        </tr>
        </thead>
    <c:forEach var="account" items="${accountList}">
        <tr>
            <td>${account.id}</td>
            <td>${account.clientID}</td>
            <td>${account.typeAccount}</td>
            <td>${account.amount}</td>
            <td>${account.createData}</td>

            <spring:url value="/account/${account.id}" var="accountUrl" />
            <spring:url value="/account/edit/${account.id}" var="updateUrl" />
            <spring:url value="/account/delete/${account.id}" var="deleteUrl" />

            <td><button class="btn btn-success" onclick="location.href='${accountUrl}'">Info</button>
            <button class="btn btn-warning" onclick="location.href='${updateUrl}'">Update</button>
            <button class="btn btn-danger" onclick="location.href='${pageContext.request.contextPath}/account/delete/${account.id}.html'">Delete</button>></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>