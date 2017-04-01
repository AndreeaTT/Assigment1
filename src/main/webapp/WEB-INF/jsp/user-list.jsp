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

    <h1>All Users</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>

        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.rights}</td>

                <spring:url value="/user/${user.id}" var="userUrl" />
                <spring:url value="/user/delete${user.id}" var="deleteUrl" />
                <spring:url value="/user/edit/${user.id}" var="updateUrl" />
                <spring:url value="/history/list/${user.id}" var="historyUrl"/>


                <td><button class="btn btn-success" onclick="location.href='${userUrl}'">Info</button></td>
                <td><button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                <td><button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
                <td><button class="btn btn-warning" onclick="location.href('${historyUrl}')">History</button></td>

            </tr>
        </c:forEach>
    </table>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>