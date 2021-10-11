<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

    <nav class="navbar navbar-expand-lg navbar-light " style="background-color: #e3f2fd;">
        <div class="container-fluid">

            <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent_2"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent_2">

                <form id="mainFrom" action="edit_test" method="POST">

                    <input type="hidden" name="testId" value="${test.getId()}"/>

                    <span style="font-size:15.0pt"><fmt:message key="text.testName"/></span>

                    <span id="textName">"${test.getName()}"</span>

                    <img class="mx-2" id="nameMark" class ="clickable" onclick = "changeTestName()"
                    src="<c:url value="/img/pencil.png"/>" width="30" height="30"/>
                    <br>
                    <span style="font-size:15.0pt"><fmt:message key="text.testCategory"/></span>
                    <select class=" mx-2" style="width:auto;" name="category">

                        <option disabled selected hidden><c:out value="${testsCategory}"/></option>

                        <c:forEach var="category" items="${categories}" varStatus="status">
                            <option value="${category.getId()}"><c:out value="${category.getName()}"/></option>
                        </c:forEach>

                    </select>
                    <button class="btn btn-outline-success" type="submit"><fmt:message key="button.editTest"/></button>
                </form>
                <a style="margin-top: 35px" class="btn btn-outline-success mx-2" href="add_question"><fmt:message key="button.addQuestion"/></a>
                <a style="margin-top: 35px" class="btn btn-outline-success" href="view_my_tests"><fmt:message key="button.back"/></a>
            </div>
        </div>
    </nav>

    <div class="text-center">
        <p class="text-danger"><c:out value="${errorMsg}"/></p>
    </div>

    <c:forEach var="question" items="${questions}" varStatus="status">
            <nav class="navbar navbar-expand-lg navbar-light justify-content-center">
                <ul class="navbar-nav  ">
                    <li class="nav-item ">
                        <div class="p-2 me-2 bg-light border mx-auto line_block" style="width: 700px;">
                            <c:out value="${question.getTitle()}"/>
                        </div>
                     </li>
                    <li class="nav-item me-2">
                        <a class="nav-link active " aria-current="page" href="delete_question?questionId=${question.getId()}">
                            <img src="<c:url value="/img/trash_can.png"/>" width="30" height="30"/>
                        </a>
                    </li>
                    <li class="nav-item me-2">
                        <a class="nav-link active " aria-current="page" href="edit_question?questionId=${question.getId()}">
                            <img src="<c:url value="/img/pencil.png"/>" width="30" height="30"/>
                        </a>
                    </li>
                </ul>
            </nav>
    </c:forEach>
