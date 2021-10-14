<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

<form action="get_question" method="POST">
    <div style="background-color: #e3f2fd;">
        <div class="d-flex justify-content-center">
            <button class="btn btn-outline-success mx-5" formaction="get_question?action=previous" type="submit">
                <span style="font-size:14.0pt">previous<span>
            </button>

            <button class="btn btn-outline-success mx-5" formaction="get_question?action=finish" type="submit" >
                <span style="font-size:14.0pt">finish test<span>
            </button>

            <button class="btn btn-outline-success mx-5" formaction="get_question?action=next"type="submit">
                <span style="font-size:14.0pt">next<span>
            </button>
        </div>
    </div>
        <span style="font-size:16.0pt;" class="d-flex justify-content-center"><c:out value="${questionName}"/></span>

        <c:forEach var="statement" items="${statementsList}" varStatus="status">
            <nav class="navbar navbar-expand-lg text-center" style="font-size:14.0pt ">
                <c:if test="${statement.isCorrect()}">
                    <input type="checkbox" checked class="largerCheckbox mx-5" id="correct" name="correct" value="${statement.getId()}"/>
                </c:if>
                <c:if test="${not statement.isCorrect()}">
                    <input type="checkbox" class="largerCheckbox mx-5" id="correct" name="correct" value="${statement.getId()}"/>
                </c:if>
                 <span class="mx-2" > <c:out value="${statement.getText()}"/></span>
            </nav>
        </c:forEach>

</form>