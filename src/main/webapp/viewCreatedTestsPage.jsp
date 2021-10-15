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
        <nav class="navbar navbar-expand-lg navbar-light"  style="margin-left: 100px;">
            <ul class="navbar-nav  ">
                <li class="nav-item ">
                    <div class="p-2 me-4 bg-light border mx-auto" style="width: 700px;">
                        <c:out value="${test.getName()}"/>
                        <br>
                        <fmt:message key="text.passedNumber"/>
                        <c:out value="${passed[status.index]}"/>
                        <br>
                        <fmt:message key="text.averageResult"/>
                        <c:if test="${results[status.index] < 0}">
                            <fmt:message key="text.noData"/>
                        </c:if>
                        <c:if test="${results[status.index] >= 0}">
                            <c:out value="${results[status.index]}"/>
                        </c:if>
                    </div>
                 </li>
                <li class="nav-item me-2">
                    <a class="nav-link active " aria-current="page" href="edit_test?testId=${test.getId()}">
                        <img src="<c:url value="/img/pencil.png"/>" width="50" height="50"/>
                    </a>
                </li>
                <li class="nav-item me-2">
                    <a class="nav-link active " aria-current="page" href="delete_test?testId=${test.getId()}">
                        <img src="<c:url value="/img/trash_can.png"/>" width="50" height="50"/>
                    </a>
                </li>
                <li class="nav-item me-2" style="margin-top: 10px;">
                     <c:if test="${not test.isAvailable()}">
                        <a class="nav-link active " aria-current="page" href="change_status?testId=${test.getId()}">
                            <font color="red" style="font-size:14.0pt;"><fmt:message key="text.notAvailable"/></font>
                        </a>
                     </c:if>
                      <c:if test="${test.isAvailable()}">
                         <a class="nav-link active " aria-current="page" href="change_status?testId=${test.getId()}">
                             <font color="green" style="font-size:14.0pt;"><fmt:message key="text.available"/></font>
                         </a>
                      </c:if>
                </li>
            </ul>
        </nav>
    </c:forEach>

    <ul class="pagination justify-content-center ">
        <c:forEach var="pagination" items="${paginationList}">
            <li class="page-item">
                <c:if test="${pagination < 0 }">
                <a class="page-link"><c:out value="..."/></a>
                </c:if>
                <c:if test="${pagination > 0 }">
                    <a href="view_my_tests?num=${pagination - 1}" class="page-link"><c:out value="${pagination}"/></a>
                </c:if>
            </li>
        </c:forEach>
    </ul>