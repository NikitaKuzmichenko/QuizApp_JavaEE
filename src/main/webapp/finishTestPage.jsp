<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

    <div class="text-center my-2">
        <span style="font-size:18.0pt"><c:out value="${testName}"/><span>
    </div>

    <div class="my-2"style="font-size:16.0pt">
         <fmt:message key="text.result"/>
         <c:out value="${result}%"/>
    </div>

    <c:forEach var="question" items="${questions}" varStatus="status">
        <nav class="navbar navbar-expand-lg mx-4" style="font-size:15.0pt">
            <ul class="navbar-nav">
                <li class="nav-item ">
                    <div class="me-2 mx-auto">
                        <c:out value="${question.getTitle()}"/>
                    </div>
                 </li>
                <li class="nav-item me-2">
                    <c:if test="${correctAnswer[status.index]}">
                        <font color="green"><fmt:message key="text.correctAnswer"/></font>
                    </c:if>
                    <c:if test="${ not correctAnswer[status.index]}">
                         <font color="red"><fmt:message key="text.wrongAnswer"/></font>
                    </c:if>
                </li>
            </ul>
        </nav>
    </c:forEach>

    <div class = "mx-4 my-5">
        <button class="btn btn-outline-success " formaction="get_question?action=next">
            <span style="font-size:14.0pt"><fmt:message key="button.selectNext"/><span>
        </button>
    </div>