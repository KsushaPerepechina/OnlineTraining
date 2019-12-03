<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="studentHeader.label.consultations" var="consultations"/>
<fmt:message bundle="${naming}" key="user.editing.role.student" var="student"/>
<fmt:message bundle="${naming}" key="user.balance.table.operationDate" var="date"/>
<fmt:message bundle="${naming}" key="consultation.table.cost" var="cost"/>
<fmt:message bundle="${naming}" key="student.table.performance" var="performance"/>
<fmt:message bundle="${naming}" key="consultation.table.quality" var="quality"/>
<fmt:message bundle="${naming}" key="student.table.status" var="status"/>
<fmt:message bundle="${naming}" key="consultation.table.details" var="details"/>
<fmt:message bundle="${naming}" key="consultation.table.status.requested" var="requested"/>
<fmt:message bundle="${naming}" key="consultation.table.status.scheduled" var="scheduled"/>
<fmt:message bundle="${naming}" key="consultation.table.status.completed" var="completed"/>
<fmt:message bundle="${naming}" key="button.edit" var="edit"/>
<fmt:message bundle="${naming}" key="button.rate" var="rate"/>
<fmt:message bundle="${naming}" key="button.schedule" var="schedule"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png">
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
        <jsp:include page="/WEB-INF/fragment/header/trainingHeader.jsp"/>
    </div>
    <div class="rightColumn">
        <div class="itemLimit">
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showTrainingConsultations&trainingId=${requestScope.trainingId}&pageNumber=1&limit=15"
               formmethod="post" onclick=changeStatus(event)>15
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showTrainingConsultations&trainingId=${requestScope.trainingId}&pageNumber=1&limit=10"
               formmethod="post" onclick=changeStatus(event)>10
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showTrainingConsultations&trainingId=${requestScope.trainingId}&pageNumber=1&limit=5"
               formmethod="post" onclick=changeStatus(event)>5
            </a>
        </div>
        <div class="card">
            <table>
                <tr>
                    <th>${student}</th>
                    <th>${date}</th>
                    <th>${status}</th>
                    <th>${performance}</th>
                    <th>${quality}</th>
                    <th>${details}</th>
                </tr>
                <c:forEach items="${consultationList}" var="consultation">
                    <tr>
                        <td>
                            <div class="data">
                                    ${consultation.student.firstName} ${consultation.student.lastName}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${consultation.dateTime == null}">
                                        <c:choose>
                                            <c:when test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'MAIN_ADMIN'}">
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
                                    <c:when test="${consultation.status == 'REQUESTED'}">
                                        ${requested}
                                    </c:when>
                                    <c:when test="${consultation.status == 'SCHEDULED'}">
                                        ${scheduled}
                                    </c:when>
                                    <c:when test="${consultation.status == 'COMPLETED'}">
                                        ${completed}
                                    </c:when>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${consultation.performance == 0 && consultation.status == 'SCHEDULED'}">
                                        <c:choose>
                                            <c:when test="${consultation.training.mentor.id == sessionScope.id}">
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
                                    <c:when test="${consultation.quality == 0 && consultation.status == 'SCHEDULED'}">
                                        <c:choose>
                                            <c:when test="${consultation.student.id == sessionScope.id}">
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
                                <a href="${pageContext.servletContext.contextPath}/controller?command=showConsultationInfo&consultationId=${consultation.id}"
                                   class="showConsultationInfo">
                                    <img class="tableImage" src="img/icon/info.png">
                                </a>
                            </div>
                            <div class="deleteConsultationButton">
                                <a href="${pageContext.servletContext.contextPath}/controller?command=deleteConsultation&consultationId=${consultation.id}"
                                   class="showConsultationInfo">
                                    <img class="tableImage" src="img/icon/delete.png">
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="pages">
            <jsp:useBean id="pages" scope="request" type="java.util.List"/>
            <c:forEach items="${pages}" var="pages">
                <a href="${pageContext.servletContext.contextPath}/controller?command=showTrainingConsultations&trainingId=${requestScope.trainingId}&pageNumber=${pages}&limit=${requestScope.limit}">${pages}</a>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
