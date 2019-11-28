<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="user.table.id" var="id"/>
<fmt:message bundle="${naming}" key="user.table.firstName" var="firstName"/>
<fmt:message bundle="${naming}" key="user.table.lastName" var="lastName"/>
<fmt:message bundle="${naming}" key="user.table.birthDate" var="birthDate"/>
<fmt:message bundle="${naming}" key="user.table.email" var="email"/>
<fmt:message bundle="${naming}" key="user.table.phoneNumber" var="phoneNumber"/>
<fmt:message bundle="${naming}" key="user.table.blockingStatus" var="blockingStatus"/>
<fmt:message bundle="${naming}" key="user.table.blockingStatus.active" var="active"/>
<fmt:message bundle="${naming}" key="user.table.blockingStatus.blocked" var="blocked"/>
<fmt:message bundle="${naming}" key="adminHeader.mainAdmin.label.admins" var="admins"/>
<fmt:message bundle="${naming}" key="button.edit" var="edit"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <jsp:useBean id="userList" scope="request" type="java.util.List"/>
    <title>${admins}</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragment/header/mainHeader.jsp"/>
<div class="container">
    <div class="title">
        ${admins}
    </div>
    <div class="leftColumn">
        <jsp:include page="/WEB-INF/fragment/header/adminHeader.jsp"/>
    </div>
    <div class="rightColumn">
        <div class="itemLimit">
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showAdmins&pageNumber=1&limit=15"
               formmethod="post" onclick=changeStatus(event)>15
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showAdmins&pageNumber=1&limit=10"
               formmethod="post" onclick=changeStatus(event)>10
            </a>
            <a class=" "
               href="${pageContext.servletContext.contextPath}/controller?command=showAdmins&pageNumber=1&limit=5"
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
                    <th>${phoneNumber}</th>
                    <th>${blockingStatus}</th>
                    <th></th>
                </tr>
                <c:forEach items="${userList}" var="admin">
                    <tr>
                        <td>
                            #${admin.id}
                        </td>
                        <td>
                            <div class="data">
                                    ${admin.firstName}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${admin.lastName}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${sessionScope.language eq 'EN'}">
                                        <fmt:formatDate pattern="MM-dd-yyyy" value="${admin.birthDate}"/>
                                    </c:when>
                                    <c:when test="${sessionScope.language eq 'RU'}">
                                        <fmt:formatDate pattern="dd.MM.yyyy" value="${admin.birthDate}"/>
                                    </c:when>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${admin.email}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${admin.phoneNumber}
                            </div>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${admin.blockingStatus == 'BLOCKED'}">
                                    ${blocked}
                                </c:when>
                                <c:when test="${admin.blockingStatus == 'ACTIVE'}">
                                    ${active}
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <button id="${admin.id}" name="buttonStudent" value="${admin.id}"
                                    data-username="${admin.firstName} + ${admin.lastName}"
                                    data-userrole="${admin.role}"
                                    data-userblockingstatus="${admin.blockingStatus}"
                                    class="editButton" onclick="edit(this)">
                                <img class="tableImage" src="img/icon/edit.png" alt="${edit}" title="${edit}">
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="pages">
            <jsp:useBean id="pages" scope="request" type="java.util.List"/>
            <c:forEach items="${pages}" var="pages">
                <a href="${pageContext.servletContext.contextPath}/controller?command=showAdmins&pageNumber=${pages}&limit=${requestScope.limit}">${pages}</a>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
