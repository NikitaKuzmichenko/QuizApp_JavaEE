<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

<div class="d-flex justify-content-center container d-flex ">
    <div class="row justify-content-center align-self-center">
        <form action="registration" method="post" class="text-center ">

          <h1 class="h3 mb-3 font-weight-normal error"><fmt:message key="text.registration"/></h1>

          <label for="inputName" class="sr-only mb-2"><fmt:message key="text.userName"/></label>
          <input type="text" name="nickName" id="inputName" class="form-control mb-2" placeholder="name" required style="width: 300px;">

          <label for="inputEmail" class="sr-only mb-2"><fmt:message key="text.email"/></label>
          <input type="email" name="email" id="inputEmail" class="form-control mb-2" placeholder="Email address" required style="width: 300px;">

          <label for="inputPassword" class="sr-only mb-2" ><fmt:message key="text.password"/></label>
          <input type="password" name="password" id="inputPassword" class="form-control mb-2" placeholder="Password" required style="width: 300px;">

          <button class="btn btn-lg btn-primary btn-block mb-3" type="submit"><fmt:message key="button.registration"/></button>
           <p class="text-danger"> <c:out value="${errorMsg}"/></p>
        </form>
    </div>

</div>