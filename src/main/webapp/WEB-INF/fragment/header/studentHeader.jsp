<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="studentHeader.label.assignments" var="assignments"/>
<fmt:message bundle="${naming}" key="studentHeader.label.consultations" var="consultations"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/verticalMenuStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<div class="vertical-menu">
    <div class="buttons">
        <a href="controller?command=showAssignments&pageNumber=1&limit=5">${assignments}</a>
    </div>
    <div class="buttons">
        <a href="controller?command=showConsultations&pageNumber=1&limit=5">${consultations}</a>
    </div>
</div>
</body>
</html>
