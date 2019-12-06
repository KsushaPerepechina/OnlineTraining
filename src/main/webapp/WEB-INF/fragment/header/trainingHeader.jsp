<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="trainingHeader.label.info" var="info"/>
<fmt:message bundle="${naming}" key="studentHeader.label.assignments" var="assignments"/>
<fmt:message bundle="${naming}" key="studentHeader.label.consultations" var="consultations"/>
<fmt:message bundle="${naming}" key="adminHeader.label.students" var="students"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/verticalMenuStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="vertical-menu">
    <div class="buttons">
        <a href="controller?command=showTrainingInfo&trainingId=${requestScope.trainingId}">${info}</a>
    </div>
        <div class="buttons">
            <a href="controller?command=showAssignments&trainingId=${requestScope.trainingId}&pageNumber=1&limit=5">
                    ${assignments}
            </a>
        </div>
    <div class="buttons">
        <a href="controller?command=showConsultations&trainingId=${requestScope.trainingId}&pageNumber=1&limit=5">
                ${consultations}
        </a>
    </div>
    <c:if test="${sessionScope.role != 'STUDENT'}">
        <div class="buttons">
            <a href="controller?command=showTrainingStudents&trainingId=${requestScope.trainingId}&pageNumber=1&limit=5">
                    ${students}
            </a>
        </div>
    </c:if>
</div>
</body>
</html>
