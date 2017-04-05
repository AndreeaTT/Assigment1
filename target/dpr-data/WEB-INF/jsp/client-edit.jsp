<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">

    <h1>Edit Client</h1>

    <form:form class="form-horizontal" method="POST" commandName="client" action="${pageContext.request.contextPath}/client/edit/${client.id}.html">

        <form:hidden path="id" />

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control " id="name" placeholder="Name" />
                    <form:errors path="name" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="cardNumber">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Card Number</label>
                <div class="col-sm-10">
                    <form:input path="cardNumber" type="text" class="form-control " id="cardNumber" placeholder="Card Number" />
                    <form:errors path="cardNumber" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="numericCode">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">CNP</label>
                <div class="col-sm-10">
                    <form:input path="numericCode" type="text" class="form-control " id="numericCode" placeholder="CNP" />
                    <form:errors path="numericCode" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="address">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Address</label>
                <div class="col-sm-10">
                    <form:input path="address" type="text" class="form-control " id="address" placeholder="Address" />
                    <form:errors path="address" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn-lg btn-primary pull-right"  onclick="location.href='${pageContext.request.contextPath}/client/edit/${client.id}.html'">Edit</button>
            </div>
        </div>

    </form:form>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>