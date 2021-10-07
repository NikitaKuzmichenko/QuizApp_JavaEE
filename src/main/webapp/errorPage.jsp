<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isErrorPage = "true" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

<html>
   <head>
       <meta charset="UTF-8">
       <meta name="viewport"
             content="width=device-width,initial-scale=1.0">
       <meta http-equiv="X-UA-Compatible" content="ie=edge">
       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
             rel="stylesheet"
             integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
             crossorigin="anonymous">
      <title>Error Page</title>
   </head>

   <body>
    <div class="row h-100">
       <div class="col-sm-12 my-auto text-center">
            <h1><fmt:message key="error.header"/></h1>
            </br>
            <h4><fmt:message key="error.text"/></h4>
            </br>
            <h5>
                <fmt:message key="error.request"/>
                <c:out value="${pageContext.errorData.requestURI}"/>
                </br>
                <fmt:message key="error.code"/>
                <c:out value="${pageContext.errorData.statusCode}"/>
            </h5>
       </div>
   </body>
</html>

