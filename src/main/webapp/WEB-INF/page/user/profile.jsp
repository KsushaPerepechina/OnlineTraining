<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="user.table.firstName" var="firstName"/>
<fmt:message bundle="${naming}" key="user.table.lastName" var="lastName"/>
<fmt:message bundle="${naming}" key="user.table.birthDate" var="birthDate"/>
<fmt:message bundle="${naming}" key="user.table.email" var="email"/>
<fmt:message bundle="${naming}" key="user.table.phoneNumber" var="phoneNumber"/>
<fmt:message bundle="${naming}" key="user.label.email" var="login"/>
<fmt:message bundle="${naming}" key="mainHeader.label.profile" var="profile"/>
<fmt:message bundle="${naming}" key="user.table.blockingStatus" var="blockingStatus"/>
<fmt:message bundle="${naming}" key="user.table.blockingStatus.active" var="active"/>
<fmt:message bundle="${naming}" key="user.table.blockingStatus.blocked" var="blocked"/>
<fmt:message bundle="${naming}" key="user.editing.role" var="role"/>
<fmt:message bundle="${naming}" key="user.editing.role.admin" var="admin"/>
<fmt:message bundle="${naming}" key="user.editing.role.student" var="student"/>
<fmt:message bundle="${naming}" key="training.table.mentor" var="mentor"/>
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
            <form action="controller?command=editProfile&userId=${user.id}" method="post">
                <div class="row">
                    <div class="label">
                        <label for="firstName">${firstName}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="firstName" name="firstName" value="${user.firstName}"
                               pattern="^(([A-z]([A-z]){1,16}([ -][A-z]([A-z]){1,16})?))$"
                        <c:choose>
                        <c:when test="${sessionScope.id == user.id}">
                               required
                        </c:when>
                        <c:otherwise>
                               readonly
                        </c:otherwise>
                        </c:choose>>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="lastName">${lastName}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="lastName" name="lastName" value="${user.lastName}"
                               pattern="^(([A-z]([A-z]){1,16}([ -][A-z]([A-z]){1,16})?))$"
                        <c:choose>
                        <c:when test="${sessionScope.id == user.id}">
                               required
                        </c:when>
                        <c:otherwise>
                               readonly
                        </c:otherwise>
                        </c:choose>>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="birthDate">${birthDate}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="birthDate" name="birthDate"
                               value="<tags:localDate date="${user.birthDate}"/>"
                        <c:choose>
                        <c:when test="${sessionScope.language eq 'EN'}">
                               pattern="^(((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))[-]?[0-9]{4}|02[-]?29[-]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
                        </c:when>
                        <c:when test="${sessionScope.language eq 'RU'}">
                               pattern="^(((0[1-9]|[12][0-9]|30)[.]?(0[13-9]|1[012])|31[.]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[.]?02)[.]?[0-9]{4}|29[.]?02[.]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
                        </c:when>
                        </c:choose>
                        <c:choose>
                        <c:when test="${sessionScope.id == user.id}">
                               required
                        </c:when>
                        <c:otherwise>
                               readonly
                        </c:otherwise>
                        </c:choose>>
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
                        <input type="text" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}"
                               pattern="^(\+?[1-9]{3}((\([1-9]{2}\))|([1-9]{2}))[1-9][0-9]{2}([ -]?[0-9]{2}){2})$"
                        <c:choose>
                        <c:when test="${sessionScope.id == user.id}">
                               required
                        </c:when>
                        <c:otherwise>
                               readonly
                        </c:otherwise>
                        </c:choose>>
                    </div>
                </div>
                <c:if test="${(sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN') && sessionScope.id != user.id}">
                    <div class="row">
                        <div class="label">
                            <label for="blockingStatus">${blockingStatus}:</label>
                        </div>
                        <div class="value">
                            <select id="blockingStatus" name="blockingStatus" required>
                                <c:choose>
                                    <c:when test="${user.blockingStatus == 'BLOCKED'}">
                                    <option selected>${blocked}</option>
                                        <option value="ACTIVE">${active}</option>
                                    </c:when>
                                    <c:when test="${user.blockingStatus == 'ACTIVE'}">
                                        <option selected>${active}</option>
                                        <option value="BLOCKED">${blocked}</option>
                                    </c:when>
                                </c:choose>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="label">
                            <label for="role">${role}:</label>
                        </div>
                        <div class="value">
                            <select id="role" name="role" required>
                                <option selected>
                                    <c:choose>
                                        <c:when test="${user.role == 'MENTOR'}">
                                            ${mentor}
                                        </c:when>
                                        <c:when test="${user.role == 'STUDENT'}">
                                            ${student}
                                        </c:when>
                                        <c:when test="${user.role == 'ADMIN'}">
                                            ${admin}
                                        </c:when>
                                    </c:choose>
                                </option>
                                <jsp:useBean id="roleSet" scope="request" type="java.util.EnumSet"/>
                                <c:forEach items="${roleSet}" var="role">
                                    <option value=${role}>
                                        <c:choose>
                                            <c:when test="${role == 'MENTOR'}">
                                                ${mentor}
                                            </c:when>
                                            <c:when test="${role == 'STUDENT'}">
                                                ${student}
                                            </c:when>
                                            <c:when test="${role == 'ADMIN'}">
                                                ${admin}
                                            </c:when>
                                        </c:choose>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </c:if>
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
                           href="${pageContext.servletContext.contextPath}/controller?command=showProfile&userId=${user.id}">Ok
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
