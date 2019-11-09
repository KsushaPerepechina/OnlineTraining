<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="admin.label.students" var="students"/>
<fmt:message bundle="${naming}" key="admin.label.skills" var="skills"/>
<fmt:message bundle="${naming}" key="admin.label.trainings" var="trainings"/>

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
        <a href="controller?command=showAllUsers&pageNumber=1&limit=5">${students}</a>
    </div>
    <div class="buttons">
        <a href="controller?command=showAllSkills&pageNumber=1&limit=5">${skills}</a>
    </div>
    <div class="buttons">
        <a href="controller?command=showAllTrainings&pageNumber=1&limit=5">${trainings}</a>
    </div>
    <div class="buttons">
        <a href="controller?command=showProfile">${profile}</a>
    </div>
</div>
</body>
</html>

