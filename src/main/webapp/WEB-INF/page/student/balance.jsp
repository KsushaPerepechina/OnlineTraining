<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="user.balance.label" var="balance"/>
<fmt:message bundle="${naming}" key="user.balance.textField.enterSum" var="enterSum"/>
<fmt:message bundle="${naming}" key="user.balance.table.operation" var="operation"/>
<fmt:message bundle="${naming}" key="user.balance.table.operationDate" var="operationDate"/>
<fmt:message bundle="${naming}" key="user.balance.table.sum" var="sum"/>
<fmt:message bundle="${naming}" key="user.balance.button.history" var="history"/>
<fmt:message bundle="${naming}" key="user.balance.button.refill" var="refillButton"/>
<fmt:message bundle="${naming}" key="user.balance.textField.enterSum" var="enterSum"/>
<fmt:message bundle="${naming}" key="user.balance.table.operation.refill" var="refill"/>
<fmt:message bundle="${naming}" key="user.balance.table.operation.payment" var="payment"/>
<fmt:message bundle="${naming}" key="user.balance.message.refilled" var="refilled"/>
<fmt:message bundle="${naming}" key="user.balance.message.invalidSum" var="invalidSum"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/balanceStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tabStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/notifyStyle.css">
    <script src="${pageContext.request.contextPath}/js/tab.js"></script>
    <title>${balance}</title>
</head>
<body>
<jsp:include page="../../fragment/header/mainHeader.jsp"/>
<div class="container">
    <div class="title">
        ${balance}
    </div>
    <div class="tab">
        <button class="tabLinks active" onclick="openTab(event, 'balance')">${balance}</button>
        <button class="tabLinks" onclick="openTab(event, 'showHistory')">${history}</button>
    </div>
    <div class="leftColumn">
        <jsp:include page="../../fragment/header/studentHeader.jsp"/>
    </div>
    <div id="balance" class="rightColumn" style="display: block;">
        <div class="balanceBar">
            <jsp:useBean id="student" scope="request" type="by.epam.onlinetraining.entity.User"/>
            <div class="balanceStatus">
                ${balance}: ${student.balance}$
            </div>
            <form action="${pageContext.request.contextPath}/controller?command=refillBalance" method="post">
                <div class="inputBalance">
                    <input type="text" id="sum" name="sum" placeholder="${enterSum}"
                           pattern="^([1-9][0-9]{0,2})$" required>
                </div>
                <div class="balanceButton">
                    <button class="addBalance" type="submit">${refillButton}</button>
                </div>
            </form>
        </div>

        <c:if test="${not empty requestScope.message}">
            <div class="notify-modal" id="refillBalanceNotify" style="display: block;">
                <div class="notify-modal-content animate">
                    <div class="notify-resultButtons">
                        <c:choose>
                            <c:when test="${requestScope.message eq 'refilledBalance'}">
                                <label>${refilled}</label>
                            </c:when>
                            <c:when test="${requestScope.message eq 'invalidSum'}">
                                <label>${invalidSum}</label>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="notify-resultButtons">
                        <a class="okButton" id="okButton" type="submit"
                           href="${pageContext.servletContext.contextPath}/controller?command=showBalance">Ok
                        </a>
                    </div>
                </div>
            </div>
        </c:if>

    </div>
    <div id="showHistory" class="rightColumn" style="display: none;">
        <div class="tableScroll">
            <table>
                <tr>
                    <th>${operation}</th>
                    <th>${operationDate}</th>
                    <th>${sum}</th>
                </tr>
                <jsp:useBean id="transactionList" scope="request" type="java.util.List"/>
                <c:forEach items="${transactionList}" var="transaction">
                    <tr>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${transaction.operationType == 'REFILL'}">
                                        ${refill}
                                    </c:when>
                                    <c:when test="${transaction.operationType == 'PAYMENT'}">
                                        ${payment}
                                    </c:when>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                <c:choose>
                                    <c:when test="${sessionScope.language eq 'EN'}">
                                        <fmt:formatDate pattern="MM-dd-yyyy" value="${transaction.date}"/>
                                    </c:when>
                                    <c:when test="${sessionScope.language eq 'RU'}">
                                        <fmt:formatDate pattern="dd.MM.yyyy" value="${transaction.date}"/>
                                    </c:when>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="data">
                                    ${transaction.sum}
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
