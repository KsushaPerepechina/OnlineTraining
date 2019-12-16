<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="studentHeader.label.assignments" var="assignments"/>
<fmt:message bundle="${naming}" key="training.table.name" var="name"/>
<fmt:message bundle="${naming}" key="assignment.table.type" var="type"/>
<fmt:message bundle="${naming}" key="assignment.table.type.task" var="task"/>
<fmt:message bundle="${naming}" key="assignment.table.type.topic" var="topic"/>
<fmt:message bundle="${naming}" key="assignment.table.training" var="training"/>
<fmt:message bundle="${naming}" key="button.add" var="add"/>
<fmt:message bundle="${naming}" key="assignment.message.added" var="added"/>
<fmt:message bundle="${naming}" key="assignment.message.invalid" var="invalid"/>
<fmt:message bundle="${naming}" key="assignment.message.deleted" var="deleted"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/notifyStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <jsp:useBean id="assignmentList" scope="request" type="java.util.List"/>
    <title>${assignments}</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragment/header/mainHeader.jsp"/>

<div class="container">
    <div class="title">
        ${assignments}
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
               href="${pageContext.servletContext.contextPath}/controller?command=showAssignments&trainingId=${requestScope.trainingId}&pageNumber=1&limit=15"
               formmethod="post" onclick=changeStatus(event)>15
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showAssignments&trainingId=${requestScope.trainingId}&pageNumber=1&limit=10"
               formmethod="post" onclick=changeStatus(event)>10
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showAssignments&trainingId=${requestScope.trainingId}&pageNumber=1&limit=5"
               formmethod="post" onclick=changeStatus(event)>5
            </a>
        </div>
        <div class="card">
            <table>
                <tr>
                    <th>${name}</th>
                    <th>${type}</th>
                    <th>${training}</th>
                    <c:if test="${sessionScope.role ne 'STUDENT'}">
                        <th></th>
                    </c:if>
                </tr>
                <c:forEach items="${assignmentList}" var="assignment">
                    <tr>
                        <td>
                            <div class="data">
                                    ${assignment.name}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${assignment.type == 'TASK'}">
                                        ${task}
                                    </c:when>
                                    <c:when test="${assignment.type == 'TOPIC'}">
                                        ${topic}
                                    </c:when>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${assignment.training.name}
                            </div>
                        </td>
                        <c:if test="${sessionScope.role ne 'STUDENT'}">
                            <td>
                                <div class="deleteTrainingButton">
                                    <a href="${pageContext.servletContext.contextPath}/controller?command=deleteAssignment&assignmentId=${assignment.id}&trainingId=${requestScope.trainingId}"
                                       class="deleteTraining">
                                        <img class="tableImage" src="img/icon/delete.png" alt="${delete}"
                                             title="${delete}">
                                    </a>
                                </div>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="pages">
            <jsp:useBean id="pages" scope="request" type="java.util.List"/>
            <c:forEach items="${pages}" var="pages">
                <a href="${pageContext.servletContext.contextPath}/controller?command=showAssignments&trainingId=${requestScope.trainingId}&pageNumber=${pages}&limit=${requestScope.limit}">${pages}</a>
            </c:forEach>
        </div>
        <c:if test="${sessionScope.role ne 'STUDENT'}">
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
                           href="${pageContext.servletContext.contextPath}/controller?command=showAssignments&trainingId=${requestScope.trainingId}&pageNumber=${requestScope.pageNumber}&limit=${requestScope.limit}">Ok
                        </a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/training/assignmentForm.jsp"/>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
