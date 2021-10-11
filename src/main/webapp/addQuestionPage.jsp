<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

    <form action="add_question" method="post">
        <nav class="navbar navbar-expand-lg navbar-light justify-content-left" style="background-color: #e3f2fd;">

            <span class="mx-2"><fmt:message key="text.questionName"/></span>
            <input required type="text" name="questionName">

            <a class="btn btn-outline-success mx-3" onclick="addInput()"> <fmt:message key="button.addStatement"/></a>
            <button class="btn btn-outline-success mx-3" type="submit" type="submit"><fmt:message key="button.create"/></button>
            <a class="btn btn-outline-success mx-3" href="edit_test"><fmt:message key="button.back"/></a>
        </nav>

        <span style=" margin-left: 250px; font-size:15.0pt;"><fmt:message key="text.statement"/></span>
        <span style=" margin-left: 265px; font-size:15.0pt;"><fmt:message key="text.correct"/></span>
        <span style=" margin-left: 45px; font-size:15.0pt;"><fmt:message key="text.remove"/></span>

        <hr>
        <div id="statements"></div>
    </form>
