<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
    <hr>
    <footer>
        <p>&copy; Bank Manager</p>
    </footer>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<spring:url value="/resource/js/hello.js" var="coreJs" />
<spring:url value="/resource/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>