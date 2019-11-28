<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="training.table.name" var="name"/>
<fmt:message bundle="${naming}" key="training.table.startDate" var="startDate"/>
<fmt:message bundle="${naming}" key="training.table.endDate" var="endDate"/>
<fmt:message bundle="${naming}" key="training.label.progress" var="progress"/>
<fmt:message bundle="${naming}" key="training.option.finished" var="finished"/>
<fmt:message bundle="${naming}" key="training.option.inProgress" var="inProgress"/>
<fmt:message bundle="${naming}" key="training.option.registrationOpened" var="registrationOpened"/>
<fmt:message bundle="${naming}" key="training.table.mentor" var="mentor"/>
<fmt:message bundle="${naming}" key="button.save" var="save"/>

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
            <form action="controller?command=editTraining" method="post">
                <div class="row">
                    <div class="label">
                        <label for="name">${name}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="name" name="name" value="${training.name}"
                               pattern="^[A-z0-9:&;+#., ]{5,50}$"
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
                        <c:choose>
                        <c:when test="${sessionScope.language eq 'EN'}">
                               value="<fmt:formatDate pattern="MM-dd-yyyy" value="${training.startDate}"/>"
                        </c:when>
                        <c:when test="${sessionScope.language eq 'RU'}">
                               value="<fmt:formatDate pattern="dd.MM.yyyy" value="${training.startDate}"/>"
                        </c:when>
                        </c:choose>

                        <c:choose>
                                <c:when test="${sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN'}">
                                    required
                                </c:when>
                                <c:otherwise>
                                    readonly
                                </c:otherwise>
                        </c:choose>
                        >
                        <!-- TODO не парситься назад в дату-->
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="endDate">${endDate}:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="endDate" name="endDate"
                        <c:choose>
                        <c:when test="${sessionScope.language eq 'EN'}">
                               value="<fmt:formatDate pattern="MM-dd-yyyy" value="${training.endDate}"/>"
                        </c:when>
                        <c:when test="${sessionScope.language eq 'RU'}">
                               value="<fmt:formatDate pattern="dd.MM.yyyy" value="${training.endDate}"/>"
                        </c:when>
                        </c:choose>

                        <c:choose>
                        <c:when test="${sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN'}">
                               required
                        </c:when>
                        <c:otherwise>
                               readonly
                        </c:otherwise>
                        </c:choose>
                        >
                        <!-- TODO не парситься назад в дату-->
                    </div>
                </div>
                <div class="row">
                    <jsp:useBean id="progressSet" scope="request" type="java.util.EnumSet"/>
                    <div class="label">
                        <label for="progress">${progress}:</label>
                    </div>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN'}">
                            <select id="progress" name="progress" required>
                            <option selected>${fn:toLowerCase(training.progress).replace('_', ' ')}</option>
                                <c:forEach items="${progressSet}" var="progress">
                                    <option value=${progress}>${fn:toLowerCase(progress).replace('_', ' ')}</option>
                                </c:forEach>
                        </select>
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="progress" name="progress"
                                   value="${training.progress}" readonly>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="row">
                    <jsp:useBean id="mentorList" scope="request" type="java.util.List"/>
                    <div class="label">
                        <label for="mentor">${mentor}:</label>
                    </div>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'MAIN_ADMIN' || sessionScope.role == 'ADMIN'}">
                            <select id="mentor" name="mentor" required>
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
                            <c:when test="${requestScope.message eq 'editedTrainingInfo'}">
                                <label>${editedTrainingInfo}</label>
                            </c:when>
                            <c:when test="${requestScope.message eq 'trainingInfoError'}">
                                <label>${trainingInfoError}</label>
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
