<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%session = request.getSession();
    if(session.isNew())
        session.setAttribute("lang", "gb");
    session.setAttribute("rights", "user");
    String lang = (String) session.getAttribute("lang");%>
<!doctype html>

<%
    response.sendRedirect("/reservation");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>Welcome!</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/home.css">
</head>
<body>

<center><div class="image_table">
    <div class="image_row_one">
        <div class="bg_one icon">
            <a href="reservation"><img src="img/icon/money.png"></a>
        </div>
        <div class="bg_two icon">
            <a href="info"><img src="img/icon/compose.png"></a>
        </div>
    </div>
    <div class="image_row_two">
        <div class="bg_three icon">
            <a href="contacts"><img src="img/icon/mail.png"></a>
        </div>
        <div class="bg_four icon">
            <a href=""><img src="img/icon/home.png"></a>
        </div>
    </div>
</div></center>


</body>
</html>
