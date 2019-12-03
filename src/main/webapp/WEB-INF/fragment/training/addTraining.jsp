<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>
<fmt:message bundle="${naming}" key="button.save" var="save"/>
<fmt:message bundle="${naming}" key="button.cancel" var="cancel"/>
<fmt:message bundle="${naming}" key="training.label.name" var="name"/>
<fmt:message bundle="${naming}" key="training.label.startDate" var="startDate"/>
<fmt:message bundle="${naming}" key="training.label.endDate" var="endDate"/>
<fmt:message bundle="${naming}" key="training.table.mentor" var="trainingMentor"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/modalStyle.css">
    <script src="${pageContext.request.contextPath}/js/addTraining.js"></script>
    <jsp:useBean id="mentorList" scope="request" type="java.util.List"/>
</head>
<body>
<div id="addTraining" class="modal">
    <div class="modal-content animate">
        <form action="${pageContext.servletContext.contextPath}/controller?command=saveTraining" method="post">
            <label for="trainingName"><b>${name}</b></label>
            <input type="text" id="trainingName" name="trainingName"
                   maxlength="30" required>
            <label for="startDate"><b>${startDate}</b></label>
            <input type="text" id="startDate" name="startDate"
            <c:choose>
            <c:when test="${sessionScope.language eq 'EN'}">
                   pattern="^(((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))[-]?[0-9]{4}|02[-]?29[-]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
            </c:when>
            <c:when test="${sessionScope.language eq 'RU'}">
                   pattern="^(((0[1-9]|[12][0-9]|30)[.]?(0[13-9]|1[012])|31[.]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[.]?02)[.]?[0-9]{4}|29[.]?02[.]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
            </c:when>
            </c:choose>
                   required>
            <label for="endDate"><b>${endDate}</b></label>
            <input type="text" id="endDate" name="endDate"
            <c:choose>
            <c:when test="${sessionScope.language eq 'EN'}">
                   pattern="^(((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))[-]?[0-9]{4}|02[-]?29[-]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
            </c:when>
            <c:when test="${sessionScope.language eq 'RU'}">
                   pattern="^(((0[1-9]|[12][0-9]|30)[.]?(0[13-9]|1[012])|31[.]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[.]?02)[.]?[0-9]{4}|29[.]?02[.]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
            </c:when>
            </c:choose>
                   required>
            <label for="mentorId"><b>${trainingMentor}</b></label>
            <select id="mentorId" name="mentorId" required>
                <option selected disabled>${trainingMentor}</option>
                <c:forEach items="${mentorList}" var="mentor">
                <option value=${mentor.id}>${mentor.firstName} ${mentor.lastName}</option>
                </c:forEach>
            </select>
            <div>
                <input class="processButton" type="submit" value="${save}"/>
            </div>
        </form>
        <div>
            <button class="cancelButton" onclick="document.getElementById('addTraining').style.display='none'">${cancel}
            </button>
        </div>
    </div>
</div>
</body>
</html>