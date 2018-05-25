<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='stylesheet' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css'/>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>${title}</title>
</head>
<body>


<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header" style="color: white;">
            Выбор погоды для города
        </div>
    </div>
</div>

<br><br><br>
<br><br><br>

<div class="container body-content">
    <form role="form" action="${pageContext.request.contextPath}/jms" method="POST">
        <div class="form-group">
            <label for="city">город</label>
            <input type="city" class="form-control" id="city" name="city" placeholder="Введите город">
        </div>
        <button type="submit" class="btn btn-success">Отправить</button>
    </form>

    <hr />
    <footer>
        <p>&copy; <%  Date date = new Date(); out.print( date.toString()); %> </p>
    </footer>
</div>

</body>
</html>