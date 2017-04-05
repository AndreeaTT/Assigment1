<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header-admin.jsp" />

<div class="container">

    <c:if test="${not empty message}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${message}</strong>
        </div>
    </c:if>

    <h1>User Detail</h1>
    <br />

    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${user.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Username</label>
        <div class="col-sm-10">${user.username}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Password</label>
        <div class="col-sm-10">${user.password}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Role</label>
        <div class="col-sm-10">${user.rights}</div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>