<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Контакты</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/fonts.css">
    <script type='text/javascript' src='script.js'></script>
    <script src="js/jquery/jquery-1.9.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/msdropdown/dd.css" />
    <script src="js/msdropdown/jquery.dd.js"></script>
    <link rel="stylesheet" type="text/css" href="css/msdropdown/flags.css" />
</head>

<body>
<div class="menu">
    <select name="countries" id="countries" style="width: auto;" onchange="changeLang(this.value)">
        <% ArrayList<String> languages = (ArrayList<String>) request.getAttribute("languages");
            session = request.getSession();
            if(session.isNew())
                session.setAttribute("lang", "gb");
            String lang = (String) session.getAttribute("lang");
            String select = "";
            for (int i = 0; i < languages.size(); i++) {
                if (lang.equals(languages.get(i)))
                    select = "selected";
        %>
        <option value='<%=languages.get(i)%>' data-image="images/msdropdown/icons/blank.gif" data-imagecss="flag <%=languages.get(i)%>" data-title="<%=languages.get(i)%>" <%=select%>></option>
        <%
                select = "";
            }
        %>
    </select>
    <center><div class="menu_table">
        <div class="menu_row">
            <div class="menu_cell menu_title">${Home}</div>
            <div class="menu_cell menu_title">${Contact}</div>
            <div class="menu_cell menu_title">${BuyTicket}</div>
            <div class="menu_cell menu_title">${Info}</div>
        </div>
            <div class="menu_row">
                <div class="menu_cell menu_icon"><a href="/"><img src="img/icon/camera.png" width="72" height="72"></a></div>
                <div class="menu_cell menu_icon"><a href="contacts"><img src="img/icon/mail.png" width="72" height="72"></a></div>
                <div class="menu_cell menu_icon"><a href="reservation"><img src="img/icon/money.png" width="72" height="72"></a></div>
                <div class="menu_cell menu_icon"><a href="info"><img src="img/icon/compose.png" width="72" height="72"></a></div>

            </div>
        </div></center>
</div>
<div class="main">
    <<div class="content_contact">
    <form action="" method="post">
        <b>${Name}</b>
        <br>
        <input class="input_contact" type="text" name="name" maxlength="32" required>
        <br>
        <b>Email</b>
        <br>
        <input class="input_contact" type="emaii" name="email" maxlength="32" required>
        <br>
        <b>${Message}</b>
        <br>
        <textarea class="textarea" name="message" cols="50" rows="10" maxlength="512" required></textarea><br>
        <input type="submit" value="${Send}" class="contact_button">
    </form>
</div>
</div>
<center><div class="footer">
    <div class="footer_menu">
        <ul>
            <li><a href="/">${Home}</a></li>
            <li><a href="contacts">${Contact}</a></li>
            <li><a href="reservation">${BuyTicket}</a></li>
            <li><a href="info">${Info}</a></li>
        </ul>
    </div>
    <div class="footer_title">
        <p>${Site} 2015 ©</p>
    </div>
</div></center>
</body>
<script>
    $(document).ready(function(e) {
        try {

            var pagename = document.location.pathname.toString();
            pagename = pagename.split("/");
            pages.setIndexByValue(pagename[pagename.length-1]);
            $("#ver").html(msBeautify.version.msDropdown);
        } catch(e) {
            //console.log(e);
        }

        $("#ver").html(msBeautify.version.msDropdown);

        //convert
        $("select").msDropdown({roundedBorder:false});
        createByJson();
        $("#tech").data("dd");
    });


    //
</script>
</html>
