<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="studentHeader.label.consultations" var="consultations"/>
<fmt:message bundle="${naming}" key="user.editing.role.student" var="student"/>
<fmt:message bundle="${naming}" key="assignment.table.training" var="training"/>
<fmt:message bundle="${naming}" key="user.balance.table.operationDate" var="date"/>
<fmt:message bundle="${naming}" key="consultation.table.cost" var="cost"/>
<fmt:message bundle="${naming}" key="student.table.performance" var="performance"/>
<fmt:message bundle="${naming}" key="consultation.table.quality" var="quality"/>
<fmt:message bundle="${naming}" key="student.table.status" var="status"/>
<fmt:message bundle="${naming}" key="consultation.table.status.requested" var="requested"/>
<fmt:message bundle="${naming}" key="consultation.table.status.scheduled" var="scheduled"/>
<fmt:message bundle="${naming}" key="consultation.table.status.completed" var="completed"/>
<fmt:message bundle="${naming}" key="button.rate" var="rate"/>
<fmt:message bundle="${naming}" key="button.schedule" var="schedule"/>
<fmt:message bundle="${naming}" key="button.add" var="add"/>
<fmt:message bundle="${naming}" key="consultation.message.added" var="added"/>
<fmt:message bundle="${naming}" key="consultation.message.invalid" var="invalid"/>
<fmt:message bundle="${naming}" key="consultation.message.deleted" var="deleted"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/notifyStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <jsp:useBean id="consultationList" scope="request" type="java.util.List"/>
    <title>${consultations}</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragment/header/mainHeader.jsp"/>

<div class="container">
    <div class="title">
        ${consultations}
    </div>
    <div class="leftColumn">
        <c:choose>
            <c:when test="${sessionScope.role eq 'STUDENT'}">
                <jsp:include page="/WEB-INF/fragment/header/studentHeader.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="/WEB-INF/fragment/header/trainingHeader.jsp"/>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="rightColumn">
        <div class="itemLimit">
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showConsultations&trainingId=${requestScope.trainingId}&pageNumber=1&limit=15"
               formmethod="post" onclick=changeStatus(event)>15
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showConsultations&trainingId=${requestScope.trainingId}&pageNumber=1&limit=10"
               formmethod="post" onclick=changeStatus(event)>10
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showConsultations&trainingId=${requestScope.trainingId}&pageNumber=1&limit=5"
               formmethod="post" onclick=changeStatus(event)>5
            </a>
        </div>
        <div class="card">
            <table>
                <tr>
                    <c:if test="${sessionScope.role eq 'MENTOR' && requestScope.trainingId == null}">
                        <th>${training}</th>
                    </c:if>
                    <c:choose>
                        <c:when test="${sessionScope.role eq 'STUDENT'}">
                            <th>${training}</th>
                        </c:when>
                        <c:otherwise>
                            <th>${student}</th>
                        </c:otherwise>
                    </c:choose>
                    <th>${date}</th>
                    <th>${status}</th>
                    <th>${performance}</th>
                    <th>${quality}</th>
                    <th></th>
                </tr>
                <c:forEach items="${consultationList}" var="consultation">
                    <tr>
                        <c:if test="${sessionScope.role eq 'MENTOR' && requestScope.trainingId == null}">
                            <td>
                                <div class="data">
                                        ${consultation.training.name}
                                </div>
                            </td>
                        </c:if>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.role eq 'STUDENT'}">
                                    <div class="data">
                                            ${consultation.training.name}
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="data">
                                            ${consultation.student.firstName} ${consultation.student.lastName}
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${consultation.dateTime == null}">
                                        <c:choose>
                                            <c:when test="${sessionScope.role eq 'ADMIN' || sessionScope.role eq 'MAIN_ADMIN'}">
                                                <form action="${pageContext.servletContext.contextPath}/controller?command=scheduleConsultation&consultationId=${consultation.id}&pageNumber=${requestScope.pageNumber}&limit=${requestScope.limit}&trainingId=${requestScope.trainingId}"
                                                      method="post">
                                                    <div class="value">
                                                        <input type="text" id="dateTime" name="dateTime"
                                                        <c:choose>
                                                        <c:when test="${sessionScope.language eq 'EN'}">
                                                               pattern="^(((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))[-]?[0-9]{4}|02[-]?29[-]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
                                                        </c:when>
                                                        <c:when test="${sessionScope.language eq 'RU'}">
                                                               pattern="^(((0[1-9]|[12][0-9]|30)[.]?(0[13-9]|1[012])|31[.]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[.]?02)[.]?[0-9]{4}|29[.]?02[.]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
                                                        </c:when>
                                                        </c:choose>
                                                               required>
                                                    </div>
                                                    <div class="submitButton">
                                                        <input class="submitButton" type="submit" value="${schedule}">
                                                    </div>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="data">
                                                    -
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="data">
                                            <tags:localDate date="${consultation.dateTime}"/>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${consultation.status eq 'REQUESTED'}">
                                        ${requested}
                                    </c:when>
                                    <c:when test="${consultation.status eq 'SCHEDULED'}">
                                        ${scheduled}
                                    </c:when>
                                    <c:when test="${consultation.status eq 'COMPLETED'}">
                                        ${completed}
                                    </c:when>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${consultation.performance == 0}">
                                        <c:choose>
                                            <c:when test="${consultation.training.mentor.id eq sessionScope.id && consultation.status eq 'SCHEDULED'}">
                                                <form action="${pageContext.servletContext.contextPath}/controller?command=rateStudentPerformance&consultationId=${consultation.id}&pageNumber=${requestScope.pageNumber}&limit=${requestScope.limit}&trainingId=${requestScope.trainingId}"
                                                      method="post">
                                                    <div class="value">
                                                        <input type="text" id="performance" name="performance"
                                                               pattern="^([1-9]|10)$" required>
                                                    </div>
                                                    <div class="submitButton">
                                                        <input class="submitButton" type="submit" value="${rate}">
                                                    </div>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="data">
                                                    -
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="data">
                                                ${consultation.performance}
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${consultation.quality == 0}">
                                        <c:choose>
                                            <c:when test="${consultation.student.id eq sessionScope.id && consultation.status eq 'SCHEDULED'}">
                                                <form action="${pageContext.servletContext.contextPath}/controller?command=rateConsultationQuality&consultationId=${consultation.id}&pageNumber=${requestScope.pageNumber}&limit=${requestScope.limit}"
                                                      method="post">
                                                    <div class="value">
                                                        <input type="text" id="quality" name="quality"
                                                               pattern="^([1-9]|10)$" required>
                                                    </div>
                                                    <div class="submitButton">
                                                        <input class="submitButton" type="submit" value="${rate}">
                                                    </div>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="data">
                                                    -
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="data">
                                                ${consultation.quality}
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="showConsultationInfoButton">
                                <a href="${pageContext.servletContext.contextPath}/controller?command=showConsultationInfo&consultationId=${consultation.id}&pageNumber=1&limit=5"
                                   class="showConsultationInfo">
                                    <img class="tableImage" src="img/icon/info.png">
                                </a>
                            </div>
                            <c:if test="${sessionScope.role eq 'ADMIN' || sessionScope.role eq 'MAIN_ADMIN' || (sessionScope.role eq 'STUDENT' && consultation.status eq 'REQUESTED')}">
                                <div class="deleteConsultationButton">
                                    <a href="${pageContext.servletContext.contextPath}/controller?command=deleteConsultation&consultationId=${consultation.id}"
                                       class="showConsultationInfo">
                                        <img class="tableImage" src="img/icon/delete.png">
                                    </a>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="pages">
            <jsp:useBean id="pages" scope="request" type="java.util.List"/>
            <c:forEach items="${pages}" var="pages">
                <a href="${pageContext.servletContext.contextPath}/controller?command=showConsultations&trainingId=${requestScope.trainingId}&pageNumber=${pages}&limit=${requestScope.limit}">${pages}</a>
            </c:forEach>
        </div>
        <c:if test="${sessionScope.role eq 'STUDENT'}">
            <div class="addPanel">
                <button class="addButton"
                        onclick="document.getElementById('add').style.display='block'">${add}
                </button>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.notifyMessage}">
            <div class="notify-modal" id="refileBalanceNotify" style="display: block;">
                <div class="notify-modal-content animate">
                    <div class="notify-resultButtons">
                        <c:choose>
                            <c:when test="${requestScope.notifyMessage eq 'added'}">
                                <label>${added}</label>
                            </c:when>
                            <c:when test="${requestScope.notifyMessage eq 'invalid'}">
                                <label>${invalid}</label>
                            </c:when>
                            <c:when test="${requestScope.notifyMessage eq 'deleted'}">
                                <label>${deleted}</label>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="notify-resultButtons">
                        <a class="okButton" id="okButton" type="submit"
                           href="${pageContext.servletContext.contextPath}/controller?command=showConsultations&trainingId=${requestScope.trainingId}&pageNumber=${requestScope.pageNumber}&limit=${requestScope.limit}">Ok
                        </a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/training/consultationForm.jsp"/>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
