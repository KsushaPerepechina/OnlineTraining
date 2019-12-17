<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="consultation.payment" var="payment"/>
<fmt:message bundle="${naming}" key="consultation.table.cost" var="cost"/>
<fmt:message bundle="${naming}" key="consultation.message.payed" var="payed"/>
<fmt:message bundle="${naming}" key="consultation.message.notEnoughMoney" var="notEnoughMoney"/>
<fmt:message bundle="${naming}" key="training.table.mentor" var="mentor"/>
<fmt:message bundle="${naming}" key="button.pay" var="pay"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/balanceStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tabStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/notifyStyle.css">
    <jsp:useBean id="consultation" scope="request" type="by.epam.onlinetraining.entity.Consultation"/>
    <script src="${pageContext.request.contextPath}/js/tab.js"></script>
    <title>${payment}</title>
</head>
<body>
<jsp:include page="../../fragment/header/mainHeader.jsp"/>
<div class="container">
    <div class="title">
        ${payment}
    </div>
    <div class="leftColumn">
        <jsp:include page="../../fragment/header/studentHeader.jsp"/>
    </div>
    <div id="balance" class="rightColumn" style="display: block;">
        <div class="balanceBar">
            <div class="balanceStatus">
                ${consultation.training.name}
            </div>
            <form action="${pageContext.request.contextPath}/controller?command=payForConsultation&consultationId=${consultation.id}" method="post">
                <div class="inputBalance">
                    <input type="text" id="sum" name="sum" placeholder="${cost}: ${consultation.cost}" readonly>
                </div>
                <div class="balanceButton">
                    <button class="addBalance" type="submit">${pay}</button>
                </div>
            </form>
        </div>

        <c:if test="${not empty requestScope.message}">
            <div class="notify-modal" id="refillBalanceNotify" style="display: block;">
                <div class="notify-modal-content animate">
                    <div class="notify-resultButtons">
                        <c:choose>
                            <c:when test="${requestScope.message eq 'payed'}">
                                <label>${payed}</label>
                            </c:when>
                            <c:when test="${requestScope.message eq 'notEnoughMoney'}">
                                <label>${notEnoughMoney}</label>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="notify-resultButtons">
                        <a class="okButton" id="okButton" type="submit"
                           href="${pageContext.servletContext.contextPath}/controller?command=showConsultations&pageNumber=1&limit=5">Ok
                        </a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
