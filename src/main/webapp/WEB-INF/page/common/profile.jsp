<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="user.table.firstName" var="firstName"/>
<fmt:message bundle="${naming}" key="user.table.lastName" var="lastName"/>
<fmt:message bundle="${naming}" key="user.table.birthDate" var="birthDate"/>
<fmt:message bundle="${naming}" key="user.table.email" var="email"/>
<fmt:message bundle="${naming}" key="user.table.phoneNumber" var="phoneNumber"/>
<fmt:message bundle="${naming}" key="user.label.email" var="login"/>
<fmt:message bundle="${naming}" key="mainHeader.label.profile" var="profile"/>
<fmt:message bundle="${naming}" key="user.profile.message.editedProfile" var="editedProfile"/>
<fmt:message bundle="${naming}" key="user.profile.message.profileError" var="profileError"/>
<fmt:message bundle="${naming}" key="button.save" var="save"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/profileStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/notifyStyle.css">
    <jsp:useBean id="user" scope="request" type="by.epam.onlinetraining.entity.User"/>
    <title>${profile}</title>
</head>
<body>
<jsp:include page="../../fragment/header/mainHeader.jsp"/>
<div class="container">
    <div class="leftColumn">
        <c:choose>
            <c:when test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'MAIN_ADMIN'}"> <!--TODO mentor header-->
                <jsp:include page="../../fragment/header/adminHeader.jsp"/>
            </c:when>
            <c:when test="${sessionScope.role == 'STUDENT'}">
                <jsp:include page="../../fragment/header/studentHeader.jsp"/>
            </c:when>
        </c:choose>
    </div>
    <div class="rightColumn">
        <div class="card">
            <form action="controller?command=editProfile" method="post">
                <div class="row">
                    <div class="label">
                        <label for="firstName">${firstName}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="firstName" name="firstName" value="${user.firstName}"
                               pattern="^([a-zA-Z]){3,44}$" required>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="lastName">${lastName}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="lastName" name="lastName" value="${user.lastName}"
                               pattern="^([a-zA-Z]){3,44}$" required>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="birthDate">${birthDate}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="birthDate" name="birthDate"
                        <c:choose>
                        <c:when test="${sessionScope.language eq 'EN'}">
                               value="<fmt:formatDate pattern="MM-dd-yyyy" value="${user.birthDate}"/>"
                        </c:when>
                        <c:when test="${sessionScope.language eq 'RU'}">
                               value="<fmt:formatDate pattern="dd.MM.yyyy" value="${user.birthDate}"/>"
                        </c:when>
                        </c:choose>
                        required>
                        <!-- TODO не парситься назад в дату-->
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="email">${email}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="email" name="email" value="${user.email}" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="phoneNumber">${phoneNumber}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}" required>
                    </div>
                </div>
                <div class="submitButton">
                    <input class="submitButton" type="submit" value="${save}">
                </div>
            </form>
        </div>
        <c:if test="${not empty requestScope.message}">
            <div class="notify-modal" id="refillBalanceNotify" style="display: block;">
                <div class="notify-modal-content animate">
                    <div class="notify-resultButtons">
                        <c:choose>
                            <c:when test="${requestScope.message eq 'editedProfile'}">
                                <label>${editedProfile}</label>
                            </c:when>
                            <c:when test="${requestScope.message eq 'profileError'}">
                                <label>${profileError}</label>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="notify-resultButtons">
                        <a class="okButton" id="okButton" type="submit"
                           href="${pageContext.servletContext.contextPath}/controller?command=showProfile">Ok
                        </a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
