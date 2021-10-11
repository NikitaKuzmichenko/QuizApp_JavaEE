<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

<!doctype html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <style><%@include file="css/styles.css"%></style>
    <script type="text/javascript"><%@include file="javaScript/script.js"%></script>
    <title>Document</title>
     <link rel="shortcut icon" type="image/png" href="<c:url value="/img/logo.png"/>"/>

     <base href="${pageContext.request.contextPath}/controller/">
</head>
<body>

   <%@include file="header.jsp"%>

    <div class="container-fluid">
        <div class="row flex-nowrap">
            <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0" style="background-color: #ffff;">
                <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                    <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                        <ul class="nav nav-pills flex-column mb-auto list-group list-group-flush">
                            <li class="nav-item">
                                <div class="bd-example">
                                    <table class="table table-hover table-light table-bordered">
                                        <thead>
                                            <tr>
                                              <th class="text-center" scope="col" style="width: 900px;"><fmt:message key="text.newTests"/></th>
                                            </tr>
                                        </thead>
                                    <tbody>
                                        <c:forEach var="test" items="${newTests}">
                                            <tr>
                                                <td scope="row" ><a href="take_test?testId=${test.getId()}"
                                                                    class="nav-link link-dark enable-action">
                                                    <c:out value="${test.getName()}"/>
                                                </td>
                                           </tr>
                                        </c:forEach>
                                    </tbody>
                                  </table>
                                </div>
                            </li>
                            <li class="nav-item">
                                <br>
                            </li>
                            <li class="nav-item">
                                <div class="bd-example">
                                    <table class="table table-hover table-light table-bordered">
                                        <thead>
                                            <tr>
                                              <th class="text-center" scope="col" style="width: 900px;"><fmt:message key="text.popularTests"/></th>
                                            </tr>
                                        </thead>
                                    <tbody>
                                        <c:forEach var="test" items="${popularTests}">
                                            <tr>
                                                <td scope="row" >
                                                    <a href="take_test?testId=${test.getId()}"class="nav-link link-dark enable-action"/>
                                                    <c:out value="${test.getName()}"/>
                                                </td>
                                           </tr>
                                        </c:forEach>
                                    </tbody>
                                  </table>
                                </div>
                            </li>
                        </ul>
                    </ul>
                </div>
            </div>
            <div class="col py-3">
               <jsp:include page="${content}.jsp"/>
            </div>
        </div>
    </div>

    <%@include  file="footer.jsp"%>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous">
    </script>
</body>
</html>