<%@ tag body-content="empty" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="date" required="true" type="java.time.LocalDate" %>

<fmt:parseDate value="${date}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
<c:choose>
    <c:when test="${sessionScope.language eq 'EN'}">
        <fmt:formatDate value="${parsedDate}" type="date" pattern="MM-dd-yyyy"/>
    </c:when>
    <c:when test="${sessionScope.language eq 'RU'}">
        <fmt:formatDate value="${parsedDate}" type="date" pattern="dd.MM.yyyy"/>
    </c:when>
</c:choose>
