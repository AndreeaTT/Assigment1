<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<body>

<div class="container">

    <h1>Transfer money</h1>


    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>ClientID</th>
            <th>TypeAccount</th>
            <th>Amount</th>
            <th>CreateData</th>
            <th>Action</th>
        </tr>
        </thead>
        <c:forEach var="account" items="${accountList}">
            <tr>
                <td>${account.id}</td>
                <td>${account.clientID}</td>
                <td>${account.typeAccount}</td>
                <td>${account.amount}</td>
                <td>${account.createData}</td>

            </tr>
        </c:forEach>
        </tbody>

    </table>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn-lg btn-primary pull-right" onclick="location.href='${pageContext.request.contextPath}/account/create.html'">Add</button>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>