<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="user.table.id" var="id"/>
<fmt:message bundle="${naming}" key="user.table.firstName" var="firstName"/>
<fmt:message bundle="${naming}" key="user.table.lastName" var="lastName"/>
<fmt:message bundle="${naming}" key="student.table.status" var="status"/>
<fmt:message bundle="${naming}" key="student.table.mark" var="mark"/>
<fmt:message bundle="${naming}" key="adminHeader.label.students" var="students"/>
<fmt:message bundle="${naming}" key="button.edit" var="edit"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <jsp:useBean id="training" scope="request" type="by.epam.onlinetraining.entity.Training"/>
    <jsp:useBean id="studentList" scope="request" type="java.util.List"/>
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
               href="${pageContext.servletContext.contextPath}/controller?command=showTrainingStudents&trainingId=${training.id}&pageNumber=1&limit=15"
               formmethod="post" onclick=changeStatus(event)>15
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showTrainingStudents&trainingId=${training.id}&pageNumber=1&limit=10"
               formmethod="post" onclick=changeStatus(event)>10
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showTrainingStudents&trainingId=${training.id}&pageNumber=1&limit=5"
               formmethod="post" onclick=changeStatus(event)>5
            </a>
        </div>
        <div class="card">
            <table>
                <tr>
                    <th>${id}</th>
                    <th>${firstName}</th>
                    <th>${lastName}</th>
                    <th>${status}</th>
                    <c:if test="${training.progress != 'REGISTRATION_OPENED'}">
                        <th>${mark}</th>
                    </c:if>
                    <th></th>
                </tr>
                <c:forEach items="${studentList}" var="studentRecord">
                    <tr>
                        <td>
                            #${studentRecord.student.id}
                        </td>
                        <td>
                            <div class="data">
                                    ${studentRecord.student.firstName}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${studentRecord.student.lastName}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${fn:toLowerCase(studentRecord.status).replace('_', ' ')}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${studentRecord.mark}
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="pages">
            <jsp:useBean id="pages" scope="request" type="java.util.List"/>
            <c:forEach items="${pages}" var="pages">
                <a href="${pageContext.servletContext.contextPath}/controller?command=showTrainingStudents&trainingId=${training.id}&pageNumber=${pages}&limit=${requestScope.limit}">${pages}</a>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
