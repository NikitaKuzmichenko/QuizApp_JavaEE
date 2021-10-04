<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

                    <c:when test="${userRole == 1}">
                        <form class="d-flex">
                             <a class="btn btn-outline-success" href="createTest" type="submit">Создать</a>
                        </form>
                        <form class="d-flex">
                        </form>
                        <form class="d-flex mx-auto">
                             <a class="btn btn-outline-success" href="createdBy?id=self" type="submit">Созданные мной</a>
                        </form>
                        <form class="d-flex">
                             <a class="btn btn-outline-success" href="takeTests?type=passed" type="submit">Посмотреть пройденые</a>
                        </form>
                    </c:when>

                    <c:when test="${userRole == 2}">
                        <form class="d-flex">
                             <a class="btn btn-outline-success" href="takeTests?type=passed" type="submit">Посмотреть пройденые</a>
                        </form>
                    </c:when>
                </c:choose>

                <form class="d-flex mx-auto">
                    <select class="form-select mx-auto" style="width:auto;" name="sortType" onchange="submit()">
                        <option value="date" disabled selected hidden>Сортировать по</option>
                        <option value="date">Дате</option>
                        <option value="name">Названию</option>
                        <option value="popularity">Популярности</option>
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
                        <option disabled selected hidden>Категории</option>
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
            <c:out value="Создатель : "/>
            <c:out value="${users[status.index].getName()}"/>
            <br>    
            <c:out value="Средний результат : "/>
            <c:if test="${results[status.index] < 0}">
                <c:out value="нет данных"/>
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