<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="mainPage.label.title" var="title"/>
<fmt:message bundle="${naming}" key="mainPage.label.welcoming" var="welcoming"/>
<fmt:message bundle="${naming}" key="mainPage.label.quote" var="quote"/>

<html>
<head>
    <title>${title}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/mainPageStyle.css">
</head>
<body>
<jsp:include page="../../fragment/header/mainHeader.jsp"/>
<div class="infoError">
    <H1>${welcoming}</H1>
    <h2>${quote}</h2>
</div>
<jsp:include page="/WEB-INF/fragment/header/footer.jsp"/>
</body>
</html>
