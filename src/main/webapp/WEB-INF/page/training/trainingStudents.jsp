<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="user.table.firstName" var="firstName"/>
<fmt:message bundle="${naming}" key="user.table.lastName" var="lastName"/>
<fmt:message bundle="${naming}" key="student.table.status" var="status"/>
<fmt:message bundle="${naming}" key="student.table.status.requested" var="requested"/>
<fmt:message bundle="${naming}" key="student.table.status.approved" var="approved"/>
<fmt:message bundle="${naming}" key="student.table.status.inProcess" var="inProcess"/>
<fmt:message bundle="${naming}" key="student.table.status.expelled" var="expelled"/>
<fmt:message bundle="${naming}" key="student.table.status.completed" var="completed"/>
<fmt:message bundle="${naming}" key="student.table.status.rejected" var="rejected"/>
<fmt:message bundle="${naming}" key="student.table.mark" var="mark"/>
<fmt:message bundle="${naming}" key="adminHeader.label.students" var="students"/>
<fmt:message bundle="${naming}" key="button.edit" var="edit"/>
<fmt:message bundle="${naming}" key="button.rate" var="rate"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <jsp:useBean id="recordList" scope="request" type="java.util.List"/>
    <title>${students}</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragment/header/mainHeader.jsp"/>

<div class="container">
    <div class="title">
        ${students}
    </div>
    <div class="leftColumn">
        <jsp:include page="/WEB-INF/fragment/header/trainingHeader.jsp"/>
    </div>
    <div class="rightColumn">
        <div class="itemLimit">
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showTrainingStudents&trainingId=${requestScope.trainingId}&pageNumber=1&limit=15"
               formmethod="post" onclick=changeStatus(event)>15
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showTrainingStudents&trainingId=${requestScope.trainingId}&pageNumber=1&limit=10"
               formmethod="post" onclick=changeStatus(event)>10
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showTrainingStudents&trainingId=${requestScope.trainingId}&pageNumber=1&limit=5"
               formmethod="post" onclick=changeStatus(event)>5
            </a>
        </div>
        <div class="card">
            <table>
                <tr>
                    <th>${firstName}</th>
                    <th>${lastName}</th>
                    <th>${status}</th>
                    <c:if test="${requestScope.progress != 'REGISTRATION_OPENED'}">
                        <th>${mark}</th>
                    </c:if>
                    <c:if test="${(requestScope.progress == 'REGISTRATION_OPENED' && (sessionScope.role == 'ADMIN' || sessionScope.role == 'MAIN_ADMIN')) || (sessionScope.id == recordList.get(0).training.mentor.id && requestScope.progress == 'IN_PROCESS')}">
                    <th></th>
                    </c:if>
                </tr>
                <c:forEach items="${recordList}" var="record">
                    <tr>
                        <td>
                            <div class="data">
                                    ${record.student.firstName}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${record.student.lastName}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${record.status == 'REQUESTED'}">
                                        ${requested}
                                    </c:when>
                                    <c:when test="${record.status == 'APPROVED'}">
                                        ${approved}
                                    </c:when>
                                    <c:when test="${record.status == 'REJECTED'}">
                                        ${rejected}
                                    </c:when>
                                    <c:when test="${record.status == 'IN_PROCESS'}">
                                        ${inProcess}
                                    </c:when>
                                    <c:when test="${record.status == 'EXPELLED'}">
                                        ${expelled}
                                    </c:when>
                                    <c:when test="${record.status == 'COMPLETED'}">
                                        ${completed}
                                    </c:when>
                                </c:choose>
                            </div>
                        </td>
                        <c:choose>
                            <c:when test="${requestScope.progress != 'REGISTRATION_OPENED'}">
                                <td>
                                    <div class="data">
                                        <c:choose>
                                            <c:when test="${record.mark == 0}">
                                                <c:choose>
                                                    <c:when test="${record.training.mentor.id == sessionScope.id && record.status ne 'EXPELLED'}">
                                                        <form action="${pageContext.servletContext.contextPath}/controller?command=rateStudent&recordId=${record.id}&pageNumber=${requestScope.pageNumber}&limit=${requestScope.limit}&trainingId=${requestScope.trainingId}"
                                                              method="post">
                                                            <div class="value">
                                                                <input type="text" id="mark" name="mark"
                                                                   pattern="^([1-9]|10)$" required>
                                                            </div>
                                                            <div class="submitButton">
                                                                <input class="submitButton" type="submit" value="${rate}">
                                                            </div>
                                                        </form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        -
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>
                                                ${record.mark}
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </td>
                                <td>
                                <c:if test="${record.training.mentor.id == sessionScope.id && record.status eq 'IN_PROCESS'}">
                                    <td>
                                        <div class="data">
                                            <div class="expelButton">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=changeStudentStatus&recordId=${record.id}&pageNumber=${requestScope.pageNumber}&limit=${requestScope.limit}&trainingId=${requestScope.trainingId}&status=expelled"
                                                   class="showTrainingInfo">
                                                    <img class="tableImage" src="img/icon/reject.png">
                                                </a>
                                            </div>
                                        </div>
                                    </td>
                                </c:if>
                            </c:when>
                            <c:when test="${sessionScope.role eq 'ADMIN' || sessionScope.role eq 'MAIN_ADMIN'}">
                                <td>
                                    <c:if test="${record.status eq 'REQUESTED'}">

                                            <div class="approveRequestButton">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=changeStudentStatus&recordId=${record.id}&pageNumber=${requestScope.pageNumber}&limit=${requestScope.limit}&trainingId=${requestScope.trainingId}&status=approved"
                                                   class="showTrainingInfo">
                                                    <img class="tableImage" src="img/icon/approve.png">
                                                </a>
                                            </div>
                                            <div class="rejectRequestButton">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=changeStudentStatus&recordId=${record.id}&pageNumber=${requestScope.pageNumber}&limit=${requestScope.limit}&trainingId=${requestScope.trainingId}&status=rejected"
                                                   class="showTrainingInfo">
                                                    <img class="tableImage" src="img/icon/reject.png">
                                                </a>
                                            </div>

                                    </c:if>
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="pages">
            <jsp:useBean id="pages" scope="request" type="java.util.List"/>
            <c:forEach items="${pages}" var="pages">
                <a href="${pageContext.servletContext.contextPath}/controller?command=showTrainingStudents&trainingId=${requestScope.trainingId}&pageNumber=${pages}&limit=${requestScope.limit}">${pages}</a>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>