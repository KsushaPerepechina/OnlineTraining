<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="training.table.name" var="name"/>
<fmt:message bundle="${naming}" key="training.table.startDate" var="startDate"/>
<fmt:message bundle="${naming}" key="training.table.endDate" var="endDate"/>
<fmt:message bundle="${naming}" key="training.label.progress" var="progress"/>
<fmt:message bundle="${naming}" key="training.table.progress.finished" var="finished"/>
<fmt:message bundle="${naming}" key="training.section.inProcess" var="inProcess"/>
<fmt:message bundle="${naming}" key="training.section.registrationOpened" var="registrationOpened"/>
<fmt:message bundle="${naming}" key="training.table.mentor" var="mentor"/>
<fmt:message bundle="${naming}" key="button.save" var="save"/>
<fmt:message bundle="${naming}" key="training.message.edited" var="edited"/>
<fmt:message bundle="${naming}" key="training.message.invalid" var="invalid"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/profileStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/notifyStyle.css">
    <jsp:useBean id="training" scope="request" type="by.epam.onlinetraining.entity.Training"/>
    <title>${name}</title>
</head>
<body>
<jsp:include page="../../fragment/header/mainHeader.jsp"/>
<div class="container">
    <div class="leftColumn">
        <jsp:include page="../../fragment/header/trainingHeader.jsp"/>
    </div>
    <div class="rightColumn">
        <div class="card">
            <form action="controller?command=saveTraining&trainingId=${training.id}" method="post">
                <div class="row">
                    <div class="label">
                        <label for="trainingName">${name}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="trainingName" name="trainingName" value="${training.name}"
                               pattern="^[A-z0-9:&+#., ]{2,50}$"
                        <c:choose>
                        <c:when test="${sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN'}">
                               required
                        </c:when>
                        <c:otherwise>
                               readonly
                        </c:otherwise>
                        </c:choose>
                        >
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="startDate">${startDate}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="startDate" name="startDate"
                               value="<tags:localDate date="${training.startDate}"/>"

                        <c:choose>
                                <c:when test="${sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN'}">
                        <c:choose>
                        <c:when test="${sessionScope.language eq 'EN'}">
                               pattern="^(((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))[-]?[0-9]{4}|02[-]?29[-]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
                        </c:when>
                        <c:when test="${sessionScope.language eq 'RU'}">
                               pattern="^(((0[1-9]|[12][0-9]|30)[.]?(0[13-9]|1[012])|31[.]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[.]?02)[.]?[0-9]{4}|29[.]?02[.]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
                        </c:when>
                        </c:choose>
                               required
                                </c:when>
                                <c:otherwise>
                                    readonly
                                </c:otherwise>
                        </c:choose>
                        >
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="endDate">${endDate}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="endDate" name="endDate"
                               value="<tags:localDate date="${training.endDate}"/>"

                        <c:choose>
                        <c:when test="${sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN'}">
                        <c:choose>
                        <c:when test="${sessionScope.language eq 'EN'}">
                               pattern="^(((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))[-]?[0-9]{4}|02[-]?29[-]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
                        </c:when>
                        <c:when test="${sessionScope.language eq 'RU'}">
                               pattern="^(((0[1-9]|[12][0-9]|30)[.]?(0[13-9]|1[012])|31[.]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[.]?02)[.]?[0-9]{4}|29[.]?02[.]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
                        </c:when>
                        </c:choose>
                               required
                        </c:when>
                        <c:otherwise>
                               readonly
                        </c:otherwise>
                        </c:choose>
                        >
                    </div>
                </div>
                <div class="row">
                    <jsp:useBean id="progressSet" scope="request" type="java.util.EnumSet"/>
                    <div class="label">
                        <label for="progress">${progress}:</label>
                    </div>
                    <div class="value">
                    <c:choose>
                        <c:when test="${sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN'}">
                            <select id="progress" name="progress" required>
                            <option selected value="${training.progress}">
                                <c:choose>
                                    <c:when test="${training.progress == 'REGISTRATION_OPENED'}">
                                        ${registrationOpened}
                                    </c:when>
                                    <c:when test="${training.progress == 'IN_PROCESS'}">
                                        ${inProcess}
                                    </c:when>
                                    <c:when test="${training.progress == 'FINISHED'}">
                                        ${finished}
                                    </c:when>
                                </c:choose>
                            </option>
                                <c:forEach items="${progressSet}" var="progress">
                                    <option value=${progress}>
                                        <c:choose>
                                            <c:when test="${progress == 'REGISTRATION_OPENED'}">
                                                ${registrationOpened}
                                            </c:when>
                                            <c:when test="${progress == 'IN_PROCESS'}">
                                                ${inProcess}
                                            </c:when>
                                            <c:when test="${progress == 'FINISHED'}">
                                                ${finished}
                                            </c:when>
                                        </c:choose>
                                    </option>
                                </c:forEach>
                        </select>
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="progress" name="progress"
                            <c:choose>
                            <c:when test="${training.progress == 'REGISTRATION_OPENED'}">
                                value="${registrationOpened}"
                            </c:when>
                            <c:when test="${training.progress == 'IN_PROCESS'}">
                                value="${inProcess}"
                            </c:when>
                            <c:when test="${training.progress == 'FINISHED'}">
                                value="${finished}"
                            </c:when>
                            </c:choose>
                                   readonly>
                        </c:otherwise>
                    </c:choose>
                    </div>
                </div>
                <div class="row">
                    <jsp:useBean id="mentorList" scope="request" type="java.util.List"/>
                    <div class="label">
                        <label for="mentor">${mentor}:</label>
                    </div>
                    <div class="value">
                    <c:choose>
                        <c:when test="${sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN'}">
                            <select id="mentorId" name="mentorId" required>
                            <option selected value=${training.mentor.id}>${training.mentor.firstName} ${training.mentor.lastName}</option>
                            <c:forEach items="${mentorList}" var="mentor">
                                <option value=${mentor.id}>${mentor.firstName} ${mentor.lastName}</option>
                            </c:forEach>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="mentor" name="mentor"
                                   value="${training.mentor.firstName} ${training.mentor.lastName}" readonly>
                        </c:otherwise>
                    </c:choose>
                    </div>
                </div>
                <c:if test="${sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN'}">
                    <div class="submitButton">
                        <input class="submitButton" type="submit" value="${save}">
                    </div>
                </c:if>
            </form>
        </div>
        <c:if test="${not empty requestScope.message}">
            <div class="notify-modal" id="refillBalanceNotify" style="display: block;">
                <div class="notify-modal-content animate">
                    <div class="notify-resultButtons">
                        <c:choose>
                            <c:when test="${requestScope.message eq 'edited'}">
                                <label>${edited}</label>
                            </c:when>
                            <c:when test="${requestScope.message eq 'invalid'}">
                                <label>${invalid}</label>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="notify-resultButtons">
                        <a class="okButton" id="okButton" type="submit"
                           href="${pageContext.servletContext.contextPath}/controller?command=showTrainingInfo&trainingId=${training.id}">Ok
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
