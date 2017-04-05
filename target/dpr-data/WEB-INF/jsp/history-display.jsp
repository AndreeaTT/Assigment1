<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header-admin.jsp" />

<div class="container">

    <h1>Period for reports</h1>

    <spring:url value="/history/history" var="ActionUrl" />

    <form:form class="form-horizontal" method="POST" commandName="history" action="${ActionUrl}">


        <spring:bind path="actionData">
            <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Data for started</label>
            <div class="col-sm-10">
                <form:input path="actionData" type="text" class="form-control" id="actionData" placeholder="DD-MM-YYYY"/>
                <form:errors path="actionData" class="control-label" />
            </div>
             </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn-lg btn-primary pull-right">Display</button>
            </div>
        </div>

    </form:form>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>