<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>
<fmt:message bundle="${naming}" key="training.label.name" var="name"/>
<fmt:message bundle="${naming}" key="assignment.table.type" var="type"/>
<fmt:message bundle="${naming}" key="assignment.table.type.task" var="task"/>
<fmt:message bundle="${naming}" key="assignment.table.type.topic" var="topic"/>
<fmt:message bundle="${naming}" key="button.save" var="save"/>
<fmt:message bundle="${naming}" key="button.cancel" var="cancel"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/modalStyle.css">
    <script src="${pageContext.request.contextPath}/js/add.js"></script>
</head>
<body>
<div id="add" class="modal">
    <div class="modal-content animate">
        <form action="${pageContext.servletContext.contextPath}/controller?command=saveAssignment&trainingId=${requestScope.trainingId}" method="post">
            <label for="assignmentName"><b>${name}</b></label>
            <input type="text" id="assignmentName" name="assignmentName"
                   maxlength="30" required>
            <label for="type"><b>${type}</b></label>
            <select id="type" name="type" required>
                <option selected disabled>${type}</option>
                <option value='TASK'>${task}</option>
                <option value='TOPIC'>${topic}</option>
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
