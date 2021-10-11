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

                <form id="mainFrom" action="create_Test" method="POST">

                    <span style="font-size:15.0pt"><fmt:message key="text.testName"/></span>

                    <input required id="testName" type="text" name="testName" class="link"/>

                    <span style="font-size:15.0pt"><fmt:message key="text.testCategory"/></span>

                    <select class=" mx-2" style="width:auto;" name="category">
                        <c:if test="${empty test}">
                            <option disabled selected hidden><fmt:message key="dropDown.categories"/></option>
                        </c:if>
                        <c:if test="${not empty test}">
                            <option disabled selected hidden><c:out value="${testsCategory}"/></option>
                        </c:if>
                        <c:forEach var="category" items="${categories}" varStatus="status">
                            <option value="${category.getId()}"><c:out value="${category.getName()}"/></option>
                        </c:forEach>
                    </select>

                    <button class="btn btn-outline-success" type="submit"><fmt:message key="button.createTest"/></button>
                    <a class="btn btn-outline-success mx-3" href="page"><fmt:message key="button.back"/></a>
                </form>
            </div>
        </div>
    </nav>
    <div class="text-center">
        <p class="text-danger"><c:out value="${errorMsg}"/></p>
    </div>
