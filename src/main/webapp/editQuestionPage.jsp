<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

    <form action="edit_question" method="post">
        <nav class="navbar navbar-expand-lg navbar-light justify-content-left" style="background-color: #e3f2fd;">

            <input type="hidden" name="questionId" value="${questionId}"/>

            <span class="mx-2" id="questionName">"${questionName}"</span>
            <img class="mx-2" id="nameMark" class ="clickable" onclick = "changeQuestionName()"
            src="<c:url value="/img/pencil.png"/>" width="30" height="30"/>

            <a class="btn btn-outline-success mx-3" onclick="addInput()"> <fmt:message key="button.addStatement"/></a>
            <button class="btn btn-outline-success mx-3" type="submit" type="submit"><fmt:message key="button.update"/></button>
            <a class="btn btn-outline-success mx-3" href="edit_test"><fmt:message key="button.back"/></a>
        </nav>

        <span style=" margin-left: 250px; font-size:15.0pt;"><fmt:message key="text.statement"/></span>
        <span style=" margin-left: 265px; font-size:15.0pt;"><fmt:message key="text.correct"/></span>
        <span style=" margin-left: 45px; font-size:15.0pt;"><fmt:message key="text.remove"/></span>

        <hr>
        <div id="statements"></div>

        <c:forEach var="statement" items="${statementsList}" varStatus="status">
            <nav class="navbar navbar-expand-lg">
                <span class="mx-5 cuted_text" > <c:out value="${statement.getText()}"/></span>
                <c:if test="${statement.isCorrect()}">
                    <input type="checkbox" checked class="largerCheckbox mx-5" id="correct" name="correct" value="${statement.getId()}"/>
                </c:if>
                <c:if test="${not statement.isCorrect()}">
                    <input type="checkbox" class="largerCheckbox mx-5" id="correct" name="correct" value="${statement.getId()}"/>
                </c:if>
                <a href="delete_statement?statementId=${statement.getId()}">
                <img class="mx-5" class ="clickable" src="<c:url value="/img/trash_can.png"/>" width="30" height="30"/>
                </a>
            </nav>
        </c:forEach>

    </form>