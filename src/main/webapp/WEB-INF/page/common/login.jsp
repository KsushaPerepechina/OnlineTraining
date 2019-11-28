<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="user.label.email" var="placeLogin"/>
<fmt:message bundle="${naming}" key="user.label.password.placeholder" var="placePassword"/>
<fmt:message bundle="${naming}" key="user.label.logIn" var="logIn"/>
<fmt:message bundle="${naming}" key="user.label.register" var="register"/>
<fmt:message bundle="${naming}" key="user.table.firstName" var="firstName"/>
<fmt:message bundle="${naming}" key="user.table.lastName" var="lastName"/>
<fmt:message bundle="${naming}" key="user.table.birthDate" var="birthDate"/>
<fmt:message bundle="${naming}" key="user.table.email" var="email"/>
<fmt:message bundle="${naming}" key="user.table.phoneNumber" var="phoneNumber"/>
<fmt:message bundle="${naming}" key="login.label.wrongParams" var="wrongParams"/>
<fmt:message bundle="${naming}" key="login.label.blockingAccount" var="blockingAccount"/>
<fmt:message bundle="${naming}" key="mainHeader.label.signUp" var="signUp"/>
<fmt:message bundle="${naming}" key="mainHeader.label.signIn" var="signIn"/>
<fmt:message bundle="${naming}" key="signUp.valid.validName" var="validName"/>
<fmt:message bundle="${naming}" key="signUp.valid.validEmail" var="validEmail"/>
<fmt:message bundle="${naming}" key="signUp.valid.errorLogin" var="errorLogin"/>
<fmt:message bundle="${naming}" key="signUp.valid.validPassword" var="validPassword"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/loginStyle.css" type="text/css">
    <script src="${pageContext.request.contextPath}/js/startPage.js"></script>
    <title>${logIn}</title>
</head>

<body>
<c:if test="${(not empty requestScope.signUpError) || (not empty requestScope.loginError)}">
<body onload="changeForm('signUpForm')">
</c:if>

<jsp:include page="../../fragment/header/mainHeader.jsp"/>
<div class="startPageContainer">
    <div class="infoLabel">
        <input class="chosenForm" checked id="signIn" name="action" type="radio" value="signIn"
               onclick="changeForm('signInForm')">
        <label class="info" for="signIn">${signIn}</label>
        <input class="chosenForm" id="signUp" name="action" type="radio" value="signUp"
               onclick="changeForm('signUpForm')">
        <label class="info" for="signUp">${signUp}</label>
    </div>

    <div class="formType" id="signInForm" style="display: block;">
        <form action="${pageContext.servletContext.contextPath}/controller?command=logIn" method="post">
            <div class="logInForm">
                <div class="inputText">
                    <input class="signInForm" type="text" id="login" name="login" placeholder="${placeLogin}"
                           pattern="^.+$" required><!-- TODO -->
                </div>
                <div class="inputText">
                    <input class="signInForm" type="password" id="password" name="password" placeholder="${placePassword}"
                           required><!-- TODO -->
                </div>
                <c:if test="${not empty requestScope.errorMessage}">
                    <div class="wrongParameters">
                        <c:choose>
                            <c:when test="${requestScope.errorMessage eq 'Wrong login or password.'}">
                                <label>${wrongParams}</label>
                            </c:when>
                            <c:when test="${requestScope.errorMessage eq 'Your account is blocked.'}">
                                <label>${blockingAccount}</label>
                            </c:when>
                        </c:choose>
                    </div>
                </c:if>
                <input id="signInButton" class="submitButton" type="submit" value=${logIn}>
            </div>
        </form>
    </div>

    <div class="formType" id="signUpForm" style="display: none;">
        <form action="${pageContext.servletContext.contextPath}/controller?command=signUp" method="post">
            <div class="inputText">
                <input class="signUpForm" type="text" id="lastName" name="lastName" placeholder="${lastName}"
                       pattern="^([a-zA-Z]){3,44}$" autocomplete="off" required>
            </div>

            <c:if test="${(not empty requestScope.signUpError) and (requestScope.signUpError eq 'lastName')}">
                <div class="wrongSignUpParameters">
                    <label>${validName}</label>
                </div>
            </c:if>

            <div class="inputText">
                <input class="signUpForm" type="text" id="firstName" name="firstName" placeholder="${firstName}"
                       pattern="^([a-zA-Z]){3,44}$" autocomplete="off" required>
            </div>

            <c:if test="${(not empty requestScope.signUpError) and (requestScope.signUpError eq 'firstName')}">
                <div class="wrongSignUpParameters">
                    <label>${validName}</label>
                </div>
            </c:if>

            <div class="inputText">
                <input class="signUpForm" type="text" id="email" name="email" placeholder="${email}"
                       pattern="^(\w+[\.-]?\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6})$" autocomplete="off" required>
            </div>

            <div class="inputText">
                <input class="signUpForm" type="text" id="phoneNumber" name="phoneNumber" placeholder="${phoneNumber}"
                       pattern="^(\+?[1-9]{3}((\([1-9]{2}\))|([1-9]{2}))[1-9][0-9]{2}([ -]?[0-9]{2}){2})$"
                       autocomplete="off" required>
            </div>

            <c:if test="${(not empty requestScope.signUpError) and (requestScope.signUpError eq 'email')}">
                <div class="wrongSignUpParameters">
                    <label>${validEmail}</label>
                </div>
            </c:if>

            <c:if test="${(not empty requestScope.loginError) and (requestScope.loginError eq 'login')}">
                <div class="wrongSignUpParameters">
                    <label>${errorLogin}</label>
                </div>
            </c:if>

            <div class="inputText">
                <input class="signUpForm" type="text" id="birthDate" name="birthDate" placeholder="${birthDate}"
                       onfocus="(this.type='date')" onblur="(this.type='text')"
                       pattern="^([1|2]{1}[0-9]{3}-[0-9]{1,2}-[0-9]{1,2})$"required>
                <!-- TODO дата вида 1999-6-3 не парситься -->
            </div>

            <div class="inputText">
                <input class="signUpForm" type="password" id="userPassword" name="userPassword" placeholder="${placePassword}"
                       autocomplete="off" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,15}$"
                       required>
            </div>

            <c:if test="${(not empty requestScope.signUpError) and (requestScope.signUpError eq 'userPassword')}">
                <div class="wrongSignUpParameters">
                    <label>${validPassword}</label>
                </div>
            </c:if>

            <div class="submitButton">
                <input class="submitButton" id="signUpButton" type="submit" value=${register}>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
