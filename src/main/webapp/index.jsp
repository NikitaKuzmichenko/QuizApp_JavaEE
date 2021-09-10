<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
  <meta charset="utf-8">
  <title>Тег INPUT</title>
 </head>
 <body>

<form action="upload" method="post" enctype="multipart/form-data">
    <input type="text" name="description" />
    <input type="file" name="file" />
    <input type="submit" />
</form>

 </body>
</html>