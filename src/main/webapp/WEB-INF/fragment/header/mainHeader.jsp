<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="mainHeader.label.home" var="home"/>
<fmt:message bundle="${naming}" key="mainHeader.label.signIn" var="signIn"/>
<fmt:message bundle="${naming}" key="mainHeader.label.logOut" var="signOut"/>
<fmt:message bundle="${naming}" key="mainHeader.label.trainings" var="trainings"/>
<fmt:message bundle="${naming}" key="mainHeader.label.profile" var="profile"/>
<fmt:message bundle="${naming}" key="mainHeader.user.label.requests" var="requests"/>
<fmt:message bundle="${naming}" key="mainHeader.user.label.learning" var="learning"/>
<fmt:message bundle="${naming}" key="mainHeader.user.label.mentoring" var="mentoring"/>
<fmt:message bundle="${naming}" key="mainHeader.label.lang" var="lang"/>
<fmt:message bundle="${naming}" key="user.balance.label" var="balance"/>
<fmt:message bundle="${naming}" key="mainHeader.admin.label.administrate" var="admininstrate"/>

<html lang="${sessionScope.language}">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/mainHeaderStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<div class="navigationBar">
    <div class="requiredButton">
        <a href="${pageContext.servletContext.contextPath}/controller?command=showMainPage">${home}</a>
    </div>

    <div class="dropDown">
        <button class="dropButton-language">${sessionScope.language}
        </button>
        <c:set var="test" value="${pageContext.servletContext.contextPath}"/>
        <div class="dropDownContent-language">
            <a href="${pageContext.servletContext.contextPath}/controller?command=changeLanguage&lang=RU&current${pageContext.request.queryString}">Русский</a>
            <a href="${pageContext.servletContext.contextPath}/controller?command=changeLanguage&lang=EN&current${pageContext.request.queryString}">English</a>
        </div>
    </div>

    <c:choose>
        <c:when test="${empty sessionScope.role}">
            <div class="optionalButton">
                <a href="${pageContext.request.contextPath}/controller?command=startLogIn">${signIn}</a>
            </div>
        </c:when>
        <c:when test="${sessionScope.role == 'STUDENT'}">
            <div class="dropDown">
                <button class="dropButton-profile">${sessionScope.name}
                </button>
                <div class="dropDownContent-profile">
                    <a href="${pageContext.servletContext.contextPath}/controller?command=showProfile">${profile}</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=showStudentRequests&pageNumber=1&limit=5">${requests}</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=showAssignments&pageNumber=1&limit=5">${learning}</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=showBalance">${balance}</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=signOut">${signOut}</a>
                </div>
            </div>
        </c:when>
        <c:when test="${sessionScope.role == 'MENTOR'}">
            <div class="dropDown">
                <button class="dropButton-profile">${sessionScope.name}
                </button>
                <div class="dropDownContent-profile">
                    <a href="${pageContext.servletContext.contextPath}/controller?command=showProfile">${profile}</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=showTrainings">${mentoring}</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=signOut">${signOut}</a>
                </div>
            </div>
        </c:when>
        <c:when test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'MAIN_ADMIN'}">
            <div class="dropDown">
                <button class="dropButton-profile">${sessionScope.name}
                </button>
                <div class="dropDownContent-profile">
                    <a href="${pageContext.servletContext.contextPath}/controller?command=showProfile">${profile}</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=showTrainings&pageNumber=1&limit=5">${admininstrate}</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=signOut">${signOut}</a>
                </div>
            </div>
        </c:when>
    </c:choose>

    <c:if test="${empty sessionScope.role || sessionScope.role eq 'STUDENT'}">
        <div class="requiredButton">
            <a href="${pageContext.servletContext.contextPath}/controller?command=showTrainings&pageNumber=1&limit=5">${trainings}</a>
        </div>
    </c:if>
</div>

</body>
</html>
