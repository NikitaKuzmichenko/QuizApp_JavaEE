<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <style><%@include file="css/some.css"%></style>
    <title>Document</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light border border-primary" style="background-color: #e3f2fd;">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" >LOGO</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" >siteName</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active " aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Link</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown"
                           role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Dropdown
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" type="submit">Action</a></li>
                            <li><a class="dropdown-item" type="submit">Another action</a></li>
                            <li><a class="dropdown-item" type="submit">Something else here</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex">
                    <input class="form-control input-bg mx-2" type="search" placeholder="Search" aria-label="Search">

                    <button class="btn btn-outline-success mx-2" type="submit">Search</button>

                    <a class="btn btn-outline-success mx-2" href="#" type="submit">Log&nbspin</a>

                    <select class="form-select mx-2" style="width:auto;" id="language" name="language" onchange="submit()">
                        <option value="en">En</option>
                        <option value="ru">Ru</option>
                    </select>
                </form>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row flex-nowrap">
            <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 border-end border-primary" style="background-color: #e3f2fd;">
                <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                    <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                        <ul class="nav nav-pills flex-column mb-auto list-group list-group-flush" >
                            <li class="nav-item">
                                <a class="nav-link link-dark">
                                    last added tests
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="#" class="nav-link link-dark enable-action">
                                    Test Name 1<br>
                                    21.03.2021
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link link-dark enable-action">
                                    01234567890123456789 <br> 01234567890123456789<br>
                                    11.12.2000</a>
                            </li>
                            <li>
                                <a href="#" class="nav-link link-dark enable-action">
                                    Test Name 3<br>
                                    21.03.2021</a>
                            </li>
                            <li>
                                <a href="#" class="nav-link link-dark enable-action">
                                    Test Name 4<br>
                                    21.03.2021</a></a>
                            </li>
                            <li>
                                <a href="#" class="nav-link link-dark enable-action">
                                    Test Name 5<br>
                                    21.03.2021</a></a>
                            </li>
                        </ul>
                    </ul>
                </div>
            </div>
            <div class="col py-3">
                <p>some test</p>
            </div>
        </div>
    </div>

    <footer class="page-footer font-small">
        <div class="footer-copyright text-center py-3">footer:
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous">
    </script>
</body>
</html>