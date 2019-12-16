<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="assignment.title" var="assignment"/>
<fmt:message bundle="${naming}" key="assignment.table.type" var="type"/>
<fmt:message bundle="${naming}" key="assignment.table.type.task" var="task"/>
<fmt:message bundle="${naming}" key="assignment.table.type.topic" var="topic"/>
<fmt:message bundle="${naming}" key="button.save" var="save"/>
<fmt:message bundle="${naming}" key="button.cancel" var="cancel"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/modalStyle.css">
    <script src="${pageContext.request.contextPath}/js/add.js"></script>
    <jsp:useBean id="assignmentList" scope="request" type="java.util.List"/>
</head>
<body>
<div id="add" class="modal">
    <div class="modal-content animate">
        <form action="${pageContext.servletContext.contextPath}/controller?command=includeAssignment&consultationId=${requestScope.consultationId}" method="post">
            <label for="assignment"><b>${assignment}</b></label>
            <select id="assignment" name="assignment" required>
                <option selected disabled>${assignment}</option>
                <c:forEach items="${assignmentList}" var="assignment">
                    <option value=${assignment.id}>${assignment.name}</option>
                </c:forEach>
            </select>
            <div>
                <input class="processButton" type="submit" value="${save}"/>
            </div>
        </form>
        <div>
            <button class="cancelButton" onclick="document.getElementById('add').style.display='none'">${cancel}
            </button>
        </div>
    </div>
</div>
</body>
</html>
