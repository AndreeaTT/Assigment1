<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">

    <h1>Edit Account</h1>

    <form:form class="form-horizontal" method="POST" commandName="account" action="${pageContext.request.contextPath}/account/edit/${account.id}.html">

        <form:hidden path="id" />

        <spring:bind path="iban">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">ID Client</label>
                <div class="col-sm-10">
                    <form:input path="iban" type="text" class="form-control " id="iban" placeholder="IBAN" />
                    <form:errors path="iban" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="clientID">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">ID Client</label>
                <div class="col-sm-10">
                    <form:input path="clientID" type="text" class="form-control " id="clientID" placeholder="ClientID" />
                    <form:errors path="clientID" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="typeAccount">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Type Account</label>
                <div class="col-sm-5">
                    <form:select path="typeAccount" class="form-control">
                        <form:option selected="selected" value="None">SELECT TYPE</form:option>
                        <form:option value="Saving">Saving Account</form:option>
                        <form:option value="Spending">Spending Account</form:option>
                    </form:select>
                    <form:errors path="typeAccount" class="control-label" />
                </div>
                <div class="col-sm-5"></div>
            </div>
        </spring:bind>

        <spring:bind path="amount">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Balance</label>
                <div class="col-sm-10">
                    <form:input path="amount" type="text" class="form-control " id="amount" placeholder="0.0" />
                    <form:errors path="amount" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn-lg btn-primary pull-right" onclick="location.href='${pageContext.request.contextPath}/account/edit/${account.id}.html'">Edit</button>
            </div>
        </div>

    </form:form>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>