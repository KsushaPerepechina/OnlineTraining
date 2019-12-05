<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="training.table.name" var="name"/>
<fmt:message bundle="${naming}" key="training.table.startDate" var="startDate"/>
<fmt:message bundle="${naming}" key="training.table.endDate" var="endDate"/>
<fmt:message bundle="${naming}" key="training.table.mentor" var="mentor"/>
<fmt:message bundle="${naming}" key="mainHeader.label.trainings" var="trainings"/>
<fmt:message bundle="${naming}" key="training.section.finished" var="finished"/>
<fmt:message bundle="${naming}" key="training.section.inProcess" var="inProcess"/>
<fmt:message bundle="${naming}" key="training.section.registrationOpened" var="registrationOpened"/>
<fmt:message bundle="${naming}" key="training.table.button.showInfo" var="showInfo"/>
<fmt:message bundle="${naming}" key="training.message.added" var="addedTraining"/>
<fmt:message bundle="${naming}" key="training.message.edited" var="editedTraining"/>
<fmt:message bundle="${naming}" key="training.message.invalidTraining" var="invalidTraining"/>
<fmt:message bundle="${naming}" key="button.add" var="add"/>
<fmt:message bundle="${naming}" key="button.delete" var="delete"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/favicon.png" type="image/png"> <!-- TODO replace all icons-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tabStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/notifyStyle.css">
    <script src="${pageContext.request.contextPath}/js/tab.js"></script>
    <script src="${pageContext.request.contextPath}/js/adminTrainings.js"></script>
    <script src="${pageContext.request.contextPath}/js/editTraining.js"></script>
    <title>${trainings}</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragment/header/mainHeader.jsp"/>
<div class="container">
    <div class="title">
        ${trainings}
    </div>
    <c:if test="${not empty sessionScope && sessionScope.role ne 'STUDENT'}">
        <div class="tab">
            <button class="tabLinks" onclick="openTab(event, 'finished')">${finished}</button>
            <button class="tabLinks active" onclick="openTab(event, 'inProcess')">${inProcess}</button>
            <c:if test="${sessionScope.role ne 'MENTOR'}">
                <button class="tabLinks" onclick="openTab(event, 'registrationOpened')">${registrationOpened}</button>
            </c:if>
        </div>
    </c:if>

    <c:choose>
        <c:when test="${sessionScope.role eq 'ADMIN' || sessionScope.role eq 'MAIN_ADMIN'}">
            <div class="leftColumn">
                <jsp:include page="/WEB-INF/fragment/header/adminHeader.jsp"/>
            </div>
        </c:when>
        <c:when test="${sessionScope.role eq 'MENTOR'}">
            <div class="leftColumn">
                <jsp:include page="/WEB-INF/fragment/header/mentorHeader.jsp"/>
            </div>
        </c:when>
        <c:when test="${sessionScope.role eq 'STUDENT'}">
            <div class="leftColumn">
                <jsp:include page="/WEB-INF/fragment/header/studentHeader.jsp"/>
            </div>
        </c:when>
    </c:choose>

    <div id="finished" class="rightColumn" style="display: none;">
        <div class="tableScroll">
            <table>
                <tr>
                    <th>${name}</th>
                    <th>${startDate}</th>
                    <th>${endDate}</th>
                    <c:if test="${sessionScope.role ne 'MENTOR'}">
                        <th>${mentor}</th>
                    </c:if>
                    <th></th>
                </tr>
                <jsp:useBean id="finishedTrainingList" scope="request" type="java.util.List"/>
                <c:forEach items="${finishedTrainingList}" var="training">
                    <tr>
                        <td>
                            <div class="data">
                                    ${training.name}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <tags:localDate date="${training.startDate}"/>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <tags:localDate date="${training.endDate}"/>
                            </div>
                        </td>
                        <c:if test="${sessionScope.role ne 'MENTOR'}">
                            <td>
                                <div class="data">
                                        ${training.mentor.firstName} ${training.mentor.lastName}
                                </div>
                            </td>
                        </c:if>
                        <td>
                            <div class="showTrainingInfoButton">
                                <a href="${pageContext.servletContext.contextPath}/controller?command=showTrainingInfo&trainingId=${training.id}"
                                   class="showTrainingInfo">
                                    <img class="tableImage" src="img/icon/info.png" alt="${showInfo}" title="${showInfo}">
                                </a>
                            </div>
                            <c:if test="${sessionScope.role ne 'MENTOR'}">
                                <div class="deleteTrainingButton">
                                    <a href="${pageContext.servletContext.contextPath}/controller?command=deleteTraining&trainingId=${training.id}"
                                       class="deleteTraining">
                                        <img class="tableImage" src="img/icon/delete.png" alt="${delete}"
                                             title="${delete}">
                                    </a>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div id="inProcess" class="rightColumn"
         <c:choose>
             <c:when test="${sessionScope.role eq 'MENTOR'}">
                 style="display: block;"
             </c:when>
             <c:otherwise>
                 style="display: none;"
             </c:otherwise>
         </c:choose>>
        <div class="tableScroll">
            <table>
                <tr>
                    <th>${name}</th>
                    <th>${startDate}</th>
                    <th>${endDate}</th>
                    <c:if test="${sessionScope.role ne 'MENTOR'}">
                        <th>${mentor}</th>
                    </c:if>
                    <th></th>
                </tr>
                <jsp:useBean id="trainingInProcessList" scope="request" type="java.util.List"/>
                <c:forEach items="${trainingInProcessList}" var="training">
                    <tr>
                        <td>
                            <div class="data">
                                    ${training.name}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <tags:localDate date="${training.startDate}"/>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <tags:localDate date="${training.endDate}"/>
                            </div>
                        </td>
                        <c:if test="${sessionScope.role ne 'MENTOR'}">
                            <td>
                                <div class="data">
                                        ${training.mentor.firstName} ${training.mentor.lastName}
                                </div>
                            </td>
                        </c:if>
                        <td>
                            <div class="showTrainingInfoButton">
                                <a href="${pageContext.servletContext.contextPath}/controller?command=showTrainingInfo&trainingId=${training.id}"
                                   class="showTrainingInfo">
                                    <img class="tableImage" src="img/icon/info.png" alt="${showInfo}" title="${showInfo}">
                                </a>
                            </div>
                            <c:if test="${sessionScope.role ne 'MENTOR'}">
                                <div class="deleteTrainingButton">
                                    <a href="${pageContext.servletContext.contextPath}/controller?command=deleteTraining&trainingId=${training.id}"
                                       class="deleteTraining">
                                        <img class="tableImage" src="img/icon/delete.png" alt="${delete}"
                                             title="${delete}">
                                    </a>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
    <div id="registrationOpened" class="rightColumn"
            <c:choose>
                <c:when test="${sessionScope.role eq 'MENTOR'}">
                    style="display: none;"
                </c:when>
                <c:otherwise>
                    style="display: block;"
                </c:otherwise>
            </c:choose>>
        <div class="tableScroll">
            <table>
                <tr>
                    <th>${name}</th>
                    <th>${startDate}</th>
                    <th>${endDate}</th>
                    <th>${mentor}</th>
                    <th></th>
                </tr>
                <jsp:useBean id="registrationOpenedTrainingList" scope="request" type="java.util.List"/>
                <c:forEach items="${registrationOpenedTrainingList}" var="training">
                    <tr>
                        <td>
                            <div class="data">
                                    ${training.name}
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <tags:localDate date="${training.startDate}"/>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <tags:localDate date="${training.endDate}"/>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${training.mentor.firstName} ${training.mentor.lastName}
                            </div>
                        </td>
                        <td>
                            <jsp:useBean id="studentTrainingList" scope="request" type="java.util.List"/>
                            <c:choose>
                                <c:when test="${not empty sessionScope.role && sessionScope.role ne 'STUDENT'}">
                                    <div class="showTrainingInfoButton">
                                        <a href="${pageContext.servletContext.contextPath}/controller?command=showTrainingInfo&trainingId=${training.id}"
                                           class="showTrainingInfo">
                                            <img class="tableImage" src="img/icon/info.png" alt="${showInfo}" title="${showInfo}">
                                        </a>
                                    </div>
                                    <c:if test="${sessionScope.role ne 'MENTOR'}">
                                        <div class="deleteTrainingButton">
                                            <a href="${pageContext.servletContext.contextPath}/controller?command=deleteTraining&trainingId=${training.id}"
                                               class="deleteTraining">
                                                <img class="tableImage" src="img/icon/delete.png" alt="${delete}"
                                                     title="${delete}">
                                            </a>
                                        </div>
                                    </c:if>
                                </c:when>
                                <c:when test="${studentTrainingList.stream().filter(tr -> tr.id == training.id).count() != 1}">
                                    <div class="applyForTrainingButton">
                                        <a
                                                <c:choose>
                                                    <c:when test="${empty sessionScope.role}">
                                                        href="${pageContext.servletContext.contextPath}/controller?command=startLogIn"
                                                    </c:when>
                                                    <c:otherwise>
                                                        href="${pageContext.servletContext.contextPath}/controller?command=applyForTraining&trainingId=${training.id}"
                                                    </c:otherwise>
                                                </c:choose>
                                                class="applyForTraining">
                                            <img class="tableImage" src="img/icon/apply.png" alt="${apply}" title="${apply}">
                                        </a>
                                    </div>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <c:if test="${sessionScope.role eq 'ADMIN' || sessionScope.role eq 'MAIN_ADMIN'}">
        <div class="addPanel">
            <button class="addButton"
                    onclick="document.getElementById('addTraining').style.display='block'">${add}
            </button>
        </div>
        </c:if>
        <c:if test="${not empty requestScope.notifyMessage}">
            <div class="notify-modal" id="refileBalanceNotify" style="display: block;">
                <div class="notify-modal-content animate">
                    <div class="notify-resultButtons">
                        <c:choose>
                            <c:when test="${requestScope.notifyMessage eq 'added'}">
                                <label>${addedTraining}</label>
                            </c:when>
                            <c:when test="${requestScope.notifyMessage eq 'edited'}">
                                <label>${editedTraining}</label>
                            </c:when>
                            <c:when test="${requestScope.notifyMessage eq 'invalidTraining'}">
                                <label>${invalidTraining}</label>
                            </c:when>
                            <c:when test="${requestScope.notifyMessage eq 'deletedTraining'}">
                                <label>${isDeleted}</label>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="notify-resultButtons">
                        <a class="okButton" id="okButton" type="submit"
                           href="${pageContext.servletContext.contextPath}/controller?command=showTrainings">Ok
                        </a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/training/addTraining.jsp"/>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
