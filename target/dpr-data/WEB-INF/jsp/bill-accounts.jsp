<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<body>

<div class="container">

    <c:if test="${not empty message}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${message}</strong>
        </div>
    </c:if>

    <h1>Accounts for pay</h1>


    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>IBAN</th>
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
                <td>${account.iban}</td>
                <td>${account.clientID}</td>
                <td>${account.typeAccount}</td>
                <td>${account.amount}</td>
                <td>${account.createData}</td>

                <spring:url value="/account/${account.id}" var="accountUrl" />

                <td><button class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/bill/pay/${account.id}.html'">Pay</button></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>