<%@ page pageEncoding="UTF-8" %>
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
                        <a class="nav-link active " aria-current="page" href="page?num=1">Название сайта</a>
                    </li>
                </ul>

                <form class="d-flex">
                    <a class="btn btn-outline-success mx-2" href="authorization" type="submit">Войти</a>

                    <select class="form-select mx-2" style="width:auto;" name="language" onchange="submit()">
                        <option value="en" disabled selected hidden>LANG</option>
                        <option value="en">En</option>
                        <option value="ru">Ru</option>
                    </select>
                </form>
            </div>
        </div>
    </nav>