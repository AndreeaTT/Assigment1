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

    <h1>All Users</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Role</th>
            <th>Action</th>
        </tr>
        </thead>

        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.rights}</td>

                <spring:url value="/user/${user.id}" var="userUrl" />
                <spring:url value="/user/create" var="createUrl" />
                <spring:url value="/user/delete${user.id}" var="deleteUrl" />
                <spring:url value="/user/edit/${user.id}" var="updateUrl" />
                <spring:url value="/history/list/${user.id}" var="historyUrl"/>


                <td><button class="btn btn-success" onclick="location.href='${userUrl}'">Info</button>
                <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                <button class="btn btn-danger" onclick="location.href='${pageContext.request.contextPath}/user/delete/${user.id}.html'">Delete</button>
                <button class="btn btn-warning" onclick="location.href='${historyUrl}'">History</button></td>

            </tr>
        </c:forEach>
    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn-lg btn-primary pull-right" onclick="location.href='${pageContext.request.contextPath}/user/create.html'">Add</button>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>