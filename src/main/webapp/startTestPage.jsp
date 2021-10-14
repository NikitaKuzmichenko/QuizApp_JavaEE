<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

    <div class="content-justify-center text-center" style="margin-top:70px;">
        <span style="font-size:18.0pt"><c:out value="${testName}"/><span>
        <br>
        <c:if test="${questionsNumber > 0}">
            <form action="get_question?questionNumber=0">
                <button class="btn btn-outline-success "style="margin-top:30px;" style="font-size:18.0pt">
                    <span style="font-size:18.0pt"><fmt:message key="button.startTest"/><span>
                </button>
            </form>
        </c:if>
        <c:if test="${questionsNumber <= 0}">
            <span style="font-size:18.0pt; margin-top:30px;"><fmt:message key="text.testNotReady"/><span>
        </c:if>
    </div>
    <br>
    <br>
    <br>
    <div style="margin-left:300px; max-width:650px">
        <span style="font-size:14.0pt">
            <fmt:message key="text.testDescription"/>
            <br>
            <br>
            <fmt:message key="text.questionNumber"/>
            <c:out value="${questionsNumber}"/>
        </span>
        <br>
    </div>