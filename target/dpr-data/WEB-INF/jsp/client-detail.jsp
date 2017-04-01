<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>Client Detail</h1>
    <br />

    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${client.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Name</label>
        <div class="col-sm-10">${client.name}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Card Number</label>
        <div class="col-sm-10">${client.cardNumber}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">CNP</label>
        <div class="col-sm-10">${client.numericCode}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Address</label>
        <div class="col-sm-10">${client.address}</div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>