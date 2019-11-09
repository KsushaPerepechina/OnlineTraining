<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="table.label.id" var="id"/>
<fmt:message bundle="${naming}" key="table.label.firstName" var="firstName"/>
<fmt:message bundle="${naming}" key="table.label.lastName" var="lastName"/>
<fmt:message bundle="${naming}" key="table.label.birthDate" var="birthDate"/>
<fmt:message bundle="${naming}" key="table.label.email" var="email"/>
<fmt:message bundle="${naming}" key="table.label.block" var="block"/>
<fmt:message bundle="${naming}" key="table.label.unblock" var="unblock"/>
<fmt:message bundle="${naming}" key="admin.label.students" var="students"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <title>${students}</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragments/header/mainHeader.jsp"/>

<div class="container">
    <div class="leftColumn">
        <jsp:include page="/WEB-INF/fragments/header/adminHeader.jsp"/>
    </div>
    <div class="rightColumn">
        <div class="itemLimit">
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showStudents&pageNumber=1&limit=15"
               formmethod="post" onclick=changeStatus(event)>15
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showStudents&pageNumber=1&limit=10"
               formmethod="post" onclick=changeStatus(event)>10
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showStudents&pageNumber=1&limit=5"
               formmethod="post" onclick=changeStatus(event)>5
            </a>
        </div>
        <div class="card">
            <table>
                <tr>
                    <th>${id}</th>
                    <th>${firstName}</th>
                    <th>${lastName}</th>
                    <th>${birthDate}</th>
                    <th>${email}</th>
                    <th></th>
                </tr>
                <jsp:useBean id="userList" scope="request" type="java.util.List"/>

                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>
                            #${user.id}
                        </td>
                        <td>
                            <div class="data">
                                    ${user.firstName}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${user.lastName}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${sessionScope.language eq 'EN'}">
                                        <fmt:formatDate pattern="MM-dd-yyyy" value="${user.birthDate}"/>
                                    </c:when>
                                    <c:when test="${sessionScope.language eq 'RU'}">
                                        <fmt:formatDate pattern="dd.MM.yyyy" value="${user.birthDate}"/>
                                    </c:when>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${user.email}
                            </div>
                        </td>
                        <td width=20%>
                            <div class="blockButton">
                                <c:choose>
                                    <c:when test="${user.blocked == false}">
                                        <a class="block"
                                           href="${pageContext.servletContext.contextPath}/controller?command=changeBlockingStatus&idClient=${user.id}&blockingStatus=blocked">
                                                ${block} </a>
                                    </c:when>
                                    <c:when test="${user.blocked == true}">
                                        <a class="unblock"
                                           href="${pageContext.servletContext.contextPath}/controller?command=changeBlockingStatus&idClient=${user.id}&blockingStatus=unblocked">
                                                ${unblock} </a>
                                    </c:when>
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
                <a href="${pageContext.servletContext.contextPath}/controller?command=showStudents&pageNumber=${pages}&limit=${limit}">${pages}</a>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
