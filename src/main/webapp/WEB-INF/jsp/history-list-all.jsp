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

    <h1>All History</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>UserID</th>
            <th>Action</th>
            <th>Action Data</th>
        </tr>
        </thead>
        <c:forEach var="history" items="${historyList}">
            <tr>
                <td>${history.id}</td>
                <td>${history.userID}</td>
                <td>${history.action}</td>
                <td>${history.actionData}</td>

                <spring:url value="/history/${history.id}" var="historyUrl" />
                <td><button class="btn btn-success" onclick="location.href='${historyUrl}'">Info</button><tr>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>