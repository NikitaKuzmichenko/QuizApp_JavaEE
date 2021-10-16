<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />

<div class="d-flex justify-content-center align-items-center container">
    <div class="row">
        <form action="authorization" method="post" class="text-center ">

          <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="text.authorization"/></h1>

          <label for="inputEmail" class="sr-only mb-2"><fmt:message key="text.email"/></label>
          <input type="email" name="email" id="inputEmail" class="form-control mb-2" required style="width: 300px;">

          <label for="inputPassword" class="sr-only mb-2" ><fmt:message key="text.password"/></label>
          <input type="password" name="password" id="inputPassword" class="form-control mb-2" required style="width: 300px;">

          <div class="checkbox mb-3">
            <label>
              <input type="checkbox" name="remember" value="true"> <fmt:message key="text.rememberMe"/>
            </label>
          </div>

          <button class="btn btn-lg btn-primary btn-block mb-3" type="submit" ><fmt:message key="button.logIn"/></button>
          <br>
          <p><fmt:message key="text.noAccount"/> <a href="registration"><fmt:message key="text.register"/></a>!</p>

          <p class="text-danger"> <c:out value="${errorMsg}"/></p>
        </form>
    </div>
</div>