<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
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
            <div class="menu_cell menu_title">${Contact}</div>
            <div class="menu_cell menu_title">${BuyTicket}</div>
            <div class="menu_cell menu_title">${Info}</div>
        </div>
        <div class="menu_row">
            <div class="menu_cell menu_icon"><a href="contacts"><img src="img/icon/mail.png" width="72" height="72"></a></div>
            <div class="menu_cell menu_icon"><a href="reservation"><img src="img/icon/money.png" width="72" height="72"></a></div>
            <div class="menu_cell menu_icon"><a href="info"><img src="img/icon/compose.png" width="72" height="72"></a></div>

        </div>
    </div></center>
</div>
    <div class="main">
        <div class="why_bus">
            <div class="bus_text"><p>${Line17}</p>
                <p>${Line18}</p>
                <p>${Line19}</p>
                <br>
                <p>Tickets Bus</p>
            </div>
        </div>
        <div class="info_main">
            <div class="info_one">
            <h1>${InfoDriver}</h1>
            <h2>${Rules}</h2>
            <h2>${ApplyJob}</h2>
            <h3><p>${Requirements1}</p>
                <p>${Requirements2}</p>
                <p>${Requirements3}</p>
                <p>4. </p>
                <p>.........................</p>
                <p>n. </p></h3>
        </div>

        <div class="info_two">
            <h1>${InfoPass}</h1>
            <h2>${Rules}</h2>
            <h2>${Documents}</h2>
            <h3><p>1. .....</p></h3>
        </div>
    </div>
        </div>
<center><div class="footer">
    <div class="footer_menu">
        <ul>
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
