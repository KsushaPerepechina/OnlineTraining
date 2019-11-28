<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="adminHeader.mainAdmin.label.admins" var="admins"/>
<fmt:message bundle="${naming}" key="adminHeader.label.students" var="students"/>
<fmt:message bundle="${naming}" key="adminHeader.label.mentors" var="mentors"/>
<fmt:message bundle="${naming}" key="mainHeader.label.trainings" var="trainings"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/verticalMenuStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="vertical-menu">
    <c:if test="${sessionScope.role == 'MAIN_ADMIN'}">
        <div class="buttons">
            <a href="controller?command=showAdmins&pageNumber=1&limit=5">${admins}</a>
        </div>
    </c:if>
    <div class="buttons">
        <a href="controller?command=showMentors&pageNumber=1&limit=5">${mentors}</a>
    </div>
    <div class="buttons">
        <a href="controller?command=showStudents&pageNumber=1&limit=5">${students}</a>
    </div>
    <div class="buttons">
        <a href="controller?command=showTrainings&pageNumber=1&limit=5">${trainings}</a>
    </div>
</div>
</body>
</html>
