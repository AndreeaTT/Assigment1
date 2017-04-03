<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">

    <c:if test="${not empty message}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${message}</strong>
        </div>
    </c:if>

    <h1 cssStyle="color: red;">Client Detail</h1>
    <br />

    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${account.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Client ID</label>
        <div class="col-sm-10">${account.clientID}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Type</label>
        <div class="col-sm-10">${account.typeAccount}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Create data</label>
        <div class="col-sm-10">${account.createData}</div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>