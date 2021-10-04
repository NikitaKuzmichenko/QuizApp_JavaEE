<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="d-flex justify-content-center align-items-center container">
    <div class="row">
        <form action="authorization" method="post" class="text-center ">

          <h1 class="h3 mb-3 font-weight-normal">Вход</h1>

          <label for="inputEmail" class="sr-only mb-2">Email адрес</label>
          <input type="email" name="email" id="inputEmail" class="form-control mb-2" placeholder="Email address" required style="width: 300px;">

          <label for="inputPassword" class="sr-only mb-2" >Пароль</label>
          <input type="password" name="password" id="inputPassword" class="form-control mb-2" placeholder="Password" required style="width: 300px;">

          <div class="checkbox mb-3">
            <label>
              <input type="checkbox" name="remember" value="true"> Запомнить меня
            </label>
          </div>

          <button class="btn btn-lg btn-primary btn-block mb-3" type="submit" >Войти</button>
          <br>
          <p>Если у вас нет аккауна <a href="registration">зарегестрируйтесь</a>!</p>

          <p class="text-danger"> <c:out value="${errorMsg}"/></p>
        </form>
    </div>
</div>