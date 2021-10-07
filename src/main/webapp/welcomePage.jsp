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
                <c:choose>
                    <c:when test="${empty userRole}">
                    </c:when>

                    <c:when test="${userRole == 2}">
                        <form class="d-flex">
                             <a class="btn btn-outline-success" href="createTest" type="submit"><fmt:message key="button.create"/></a>
                        </form>
                        <form class="d-flex">
                        </form>
                        <form class="d-flex mx-auto">
                             <a class="btn btn-outline-success" href="createdBy?id=self" type="submit"><fmt:message key="button.createdByMe"/></a>
                        </form>
                        <form class="d-flex">
                             <a class="btn btn-outline-success" href="takeTests?type=passed" type="submit"><fmt:message key="button.viewPasses"/></a>
                        </form>
                    </c:when>

                    <c:when test="${userRole == 1}">
                        <form class="d-flex">
                             <a class="btn btn-outline-success" href="takeTests?type=passed" type="submit"><fmt:message key="button.viewPasses"/></a>
                        </form>
                    </c:when>
                </c:choose>

                <form class="d-flex mx-auto">
                    <select class="form-select mx-auto" style="width:auto;" name="sortType" onchange="submit()">
                        <option value="date" disabled selected hidden><fmt:message key="dropDown.sortBy"/></option>
                        <option value="date"><fmt:message key="dropDown.sortType.byDate"/></option>
                        <option value="name"><fmt:message key="dropDown.sortType.byName"/></option>
                        <option value="popularity"><fmt:message key="dropDown.sortType.byPopularity"/></option>
                    </select>
                </form>
                <form class="d-flex mx-auto">
                    <select class="form-select mx-auto" style="width:auto;" name="direction" onchange="submit()">
                        <option disabled selected hidden>↕</option>
                        <option value="true">&#8593</option>
                        <option value="false">&#8595</option>
                    </select>
                </form>
                <form class="d-flex mx-auto">
                    <select class="form-select mx-auto" style="width:auto;" name="category" onchange="submit()">
                        <option disabled selected hidden><fmt:message key="dropDown.categories"/></option>
                        <c:forEach var="category" items="${categories}" varStatus="status">
                            <option value="${category.getId()}"><c:out value="${category.getName()}"/></option>
                        </c:forEach>
                        <option value="all">Все категории</option>
                    </select>
                </form>
            </div>
        </div>
    </nav>
    <br>
    <c:forEach var="test" items="${tests}" varStatus="status">
        <div class="p-2 bg-light border mx-auto" style="width: 900px;">
            <c:out value="${test.getName()}"/>
            <br>
              <fmt:message key="test.creator"/>
            <c:out value="${users[status.index].getName()}"/>
            <br>    
            <fmt:message key="test.averageResult"/>
            <c:if test="${results[status.index] < 0}">
                <fmt:message key="text.noData"/>
            </c:if>
            <c:if test="${results[status.index] > 0}">
                <c:out value="${results[status.index]}"/>
            </c:if>
            <br>
        </div>
    </c:forEach>
    <br>

    <ul class="pagination justify-content-center ">
        <c:forEach var="pagination" items="${paginationList}">
            <li class="page-item">
                <c:if test="${pagination < 0 }">
                <a class="page-link"><c:out value="..."/></a>
                </c:if>
                <c:if test="${pagination > 0 }">
                    <a href="page?num=${pagination}" class="page-link"><c:out value="${pagination}"/></a>
                </c:if>
            </li>
        </c:forEach>
     </ul>