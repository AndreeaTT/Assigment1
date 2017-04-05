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
        <c:if test="#{message eq 'Pay bill made with success.'}">
            <div class="alert alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong>${message}</strong>
            </div>
        </c:if>
    </c:if>


    <h1>All Bills</h1>


    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Company</th>
            <th>Value</th>
            <th>Start Date</th>
            <th>Due date</th>
            <th>Client ID</th>
            <th>Action</th>
        </tr>
        </thead>
        <c:forEach var="bill" items="${billList}">
            <tr>
                <td>${bill.id}</td>
                <td>${bill.companyName}</td>
                <td>${bill.value}</td>
                <td>${bill.startDate}</td>
                <td>${bill.dueDate}</td>
                <td>${bill.clientID}</td>

                <td>
                    <button class="btn btn-danger" onclick="location.href='${pageContext.request.contextPath}/bill/select/${bill.id}.html'">Select account</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn-lg btn-primary pull-right" onclick="location.href='${pageContext.request.contextPath}/index.html'">Back</button>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>