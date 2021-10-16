<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

    <c:if test="${empty tests}">
        <div class="text-center">
            <span style="font-size:18.0pt">
               <fmt:message key="text.nothingHer"/>
            </span>
        </div>
    </c:if>

    <c:forEach var="test" items="${tests}" varStatus="status">
        <c:if test="${not test.isRemoved()}">
            <a class="mx-auto link" href="take_test?testId=${test.getId()}">
        </c:if>
            <div class="p-2 bg-light border mx-auto" style="width: 900px;">
                <c:out value="${test.getName()}"/>
                <br>
                  <fmt:message key="text.passingDate"/>
                  <fmt:formatDate value="${date[status.index]}" type="date" pattern="dd-MMM-yyyy"/>
                <br>
                <fmt:message key="text.urResult"/>
                 <c:out value="${results[status.index]}"/>
                <br>
            </div>
        <c:if test="${not test.isRemoved()}">
            </a>
        </c:if>
    </c:forEach>

    <ul class="pagination justify-content-center ">
        <c:forEach var="pagination" items="${paginationList}">
            <li class="page-item">
                <c:if test="${pagination < 0 }">
                <a class="page-link"><c:out value="..."/></a>
                </c:if>
                <c:if test="${pagination > 0 }">
                    <a href="view_results?num=${pagination - 1}" class="page-link"><c:out value="${pagination}"/></a>
                </c:if>
            </li>
        </c:forEach>
    </ul>