<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">

    <h1>Add a Transfer</h1>

    <spring:url value="/transfer/create" var="transferUrl" />

    <form:form class="form-horizontal" method="POST" commandName="transfer" action="${transferUrl}">

        <form:hidden path="id" />

        <spring:bind path="senderID">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Sender ID Account</label>
                <div class="col-sm-10">
                    <form:input path="senderID" type="text" class="form-control" id="senderID" placeholder="Sender ID Account"/>
                    <form:errors path="senderID" class="control-label" />
                </div>
            </div>
        </spring:bind>



        <spring:bind path="receiverID">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Receiver ID Account</label>
                <div class="col-sm-10">
                    <form:input path="receiverID" type="text" class="form-control" id="receiverID" placeholder="Receiver ID Account"/>
                    <form:errors path="receiverID" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="value">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Transfer value</label>
                <div class="col-sm-10">
                    <form:input path="value" type="text" class="form-control " id="value" placeholder="Value"/>
                    <form:errors path="value" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn-lg btn-primary pull-right">Add</button>
            </div>
        </div>

    </form:form>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>