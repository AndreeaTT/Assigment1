<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">

    <h1>Add User</h1>

    <spring:url value="/user/create" var="userActionUrl" />

    <form:form class="form-horizontal" method="POST" commandName="user"  action="${userActionUrl}">

        <form:hidden path="${user.id}" />

            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Username</label>
                <div class="col-sm-10">
                    <form:input path="${account.username}" type="text" class="form-control " id="username" placeholder="Username" />
                    <form:errors path="${account.username}" class="control-label" />
                </div>
            </div>


            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <form:password path="${account.password}" class="form-control" id="password" placeholder="Password" />
                    <form:errors path="${account.password}" class="control-label" />
                </div>
            </div>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Country</label>
                <div class="col-sm-5">
                    <form:select path="${account.rights}" class="form-control">
                        <form:option value="NONE" label="--- Select ---" />
                        <form:options item ="Employee"/>
                    </form:select>
                    <form:errors path="${account.rights}" class="control-label" />
                </div>
                <div class="col-sm-5"></div>
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