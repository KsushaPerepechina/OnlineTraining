<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="training.table.name" var="name"/>
<fmt:message bundle="${naming}" key="training.table.startDate" var="startDate"/>
<fmt:message bundle="${naming}" key="training.table.endDate" var="endDate"/>
<fmt:message bundle="${naming}" key="student.table.status" var="status"/>
<fmt:message bundle="${naming}" key="student.table.mark" var="mark"/>
<fmt:message bundle="${naming}" key="student.table.status.requested" var="requested"/>
<fmt:message bundle="${naming}" key="student.table.status.approved" var="approved"/>
<fmt:message bundle="${naming}" key="student.table.status.inProcess" var="inProcess"/>
<fmt:message bundle="${naming}" key="student.table.status.expelled" var="expelled"/>
<fmt:message bundle="${naming}" key="student.table.status.completed" var="completed"/>
<fmt:message bundle="${naming}" key="student.table.status.rejected" var="rejected"/>
<fmt:message bundle="${naming}" key="mainHeader.user.label.requests" var="requests"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <jsp:useBean id="recordList" scope="request" type="java.util.List"/>
    <title>${requests}</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragment/header/mainHeader.jsp"/>

<div class="container">
    <div class="title">
        ${requests}
    </div>
    <div class="leftColumn">
        <jsp:include page="/WEB-INF/fragment/header/studentHeader.jsp"/>
    </div>
    <div class="rightColumn">
        <div class="itemLimit">
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showStudentRequests&pageNumber=1&limit=15"
               formmethod="post" onclick=changeStatus(event)>15
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showStudentRequests&pageNumber=1&limit=10"
               formmethod="post" onclick=changeStatus(event)>10
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showStudentRequests&pageNumber=1&limit=5"
               formmethod="post" onclick=changeStatus(event)>5
            </a>
        </div>
        <div class="card">
            <table>
                <tr>
                    <th>${name}</th>
                    <th>${startDate}</th>
                    <th>${endDate}</th>
                    <th>${status}</th>
                    <th>${mark}</th>
                </tr>
                <c:forEach items="${recordList}" var="record">
                    <tr>
                        <td>
                            <div class="data">
                                    ${record.training.name}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <tags:localDate date="${record.training.startDate}"/>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <tags:localDate date="${record.training.endDate}"/>
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
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${record.mark == 0}">
                                        -
                                    </c:when>
                                    <c:otherwise>
                                        ${record.mark}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="pages">
            <jsp:useBean id="pages" scope="request" type="java.util.List"/>
            <c:forEach items="${pages}" var="pages">
                <a href="${pageContext.servletContext.contextPath}/controller?command=showStudentRequests&pageNumber=${pages}&limit=${requestScope.limit}">${pages}</a>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>