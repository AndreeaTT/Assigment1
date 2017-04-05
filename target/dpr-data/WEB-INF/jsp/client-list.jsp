<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<body>

<div class="container">

    <c:if test="${not empty message}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${message}</strong>
        </div>
    </c:if>

    <h1>All Clients</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>CardNumber</th>
            <th>CNP</th>
            <th>Address</th>
            <th>Action</th>
        </tr>
        </thead>
    <c:forEach var="client" items="${clientList}">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.cardNumber}</td>
            <td>${client.numericCode}</td>
            <td>${client.address}</td>

            <spring:url value="/client/${client.id}" var="clientUrl" />
            <spring:url value="/client/create" var="addUrl" />
            <spring:url value="/client/edit/${client.id}" var="updateUrl" />
            <spring:url value="/client/accounts/${client.id}" var="actionUrl" />

            <td><button class="btn btn-success" onclick="location.href='${clientUrl}'">Info</button>
            <button class="btn btn-danger" onclick="location.href='${updateUrl}'">Update</button>
            <button class="btn btn-warning" onclick="location.href='${actionUrl}'">Accounts</button></td>
        </tr>
    </c:forEach>
</table>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn-lg btn-primary pull-right" onclick="location.href='${pageContext.request.contextPath}/client/create.html'">Add</button>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>