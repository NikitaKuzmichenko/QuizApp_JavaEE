<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

    <nav class="navbar navbar-expand-lg navbar-light " style="background-color: #e3f2fd;">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav me-auto ">
                    <li class="nav-item ">
                         <img src="<c:url value="/img/logo.png"/>" width="40" height="40"/>
                     </li>
                    <li class="nav-item">
                        <a class="nav-link active " aria-current="page" href="page?num=0"><fmt:message key="text.siteName"/></a>
                    </li>
                </ul>
                <c:if test="${not empty userRole}">
                    <nav class="navbar ">
                      <span class="navbar-brand mb-0 h1"><c:out value="${nickName}"/></span>
                    </nav>
                </c:if>

                <form class="d-flex">

                    <c:if test="${not empty userRole}">
                        <a class="btn btn-outline-success " href="log_out" type="submit"><fmt:message key="button.logOut"/></a>
                    </c:if>

                    <c:if test="${empty userRole}">
                        <a class="btn btn-outline-success " href="authorization" type="submit"><fmt:message key="button.logIn"/></a>
                    </c:if>

                    <select class="form-select mx-2" style="width:auto;" name="language" onchange="submit()">
                        <option value="en" disabled selected hidden><fmt:message key="dropDown.language"/></option>
                        <option value="en">En</option>
                        <option value="ru">Ru</option>
                    </select>
                </form>
            </div>
        </div>
    </nav>