<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">

    <h1>Add Account</h1>

    <spring:url value="/account/add" var="accountActionUrl" />

    <form:form class="form-horizontal" method="POST" commandName="account" action="${accountActionUrl}">

        <form:hidden path="${account.id}" />


            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">ID Client</label>
                <div class="col-sm-10">
                    <form:input path="${account.clientID}" type="text" class="form-control " />
                    <form:errors path="${account.clientID}" class="control-label" />
                </div>
            </div>


            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Type Account</label>
                <div class="col-sm-5">
                    <form:select path="${account.typeAccount}" class="form-control">
                        <form:option value="NONE" label="--- Select ---" />
                    </form:select>
                    <form:errors path="${account.typeAccount}" class="control-label" />
                </div>
                <div class="col-sm-5"></div>
            </div>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Balance</label>
                <div class="col-sm-10">
                    <form:input path="${account.amount}" type="text" class="form-control " />
                    <form:errors path="${account.amount}" class="control-label" />
                </div>
            </div>

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