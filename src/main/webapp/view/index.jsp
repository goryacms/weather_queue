<%--
  Created by IntelliJ IDEA.
  User: MGoriachev
  Date: 08.05.2018
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'/>

    <script type="text/javascript" src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>${title}</title>
</head>
<body>





<form role="form" action="weather.yahoo.com" method="post">
    <div class="form-group">
        <legend>Выбор погоды для города</legend>
        <label for="city">город</label>
        <input type="city" class="form-control" id="city" name="city" placeholder="Введите город">
    </div>
    <button type="submit" class="btn btn-success">Отправить</button>
</form>



</body>
</html>