<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Auto-Completion using AJAX</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/fonts.css">
    <script type='text/javascript' src='script.js'></script>
    <script type="text/javascript" src="javascript.js"></script>
    <script src="js/calendar.js" type="text/javascript"></script>
    <script src="js/jquery/jquery-1.9.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/msdropdown/dd.css" />
    <script src="js/msdropdown/jquery.dd.js"></script>
    <link rel="stylesheet" type="text/css" href="css/msdropdown/flags.css" />
</head>
<body onload="init(); today();">
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
        <div class="bus_text"><p>${OneStepLine}</p>
            <p>${Line1}</p>
            <p>${Line2}</p>
            <p>${Line3}</p>
            <p>${Line4}</p>
            <br>
            <p>Ticket Bus</p>
        </div>
    </div>
    <div class="step">
        <div class="step_row">
        <div class="step_one" style="background:#1DCC99;">
            <center><p>1. ${Step1}</p></center>
        </div>
        <div class="step_two">
            <center><p>2. ${Step2}</p></center>
        </div>
        <div class="step_three">
            <center><p>3. ${Step3}</p></center>
        </div>
    </div>
        </div>
    <div class="frame">
        <%--@declare id="state_list"--%>
            <center><input type="text" class="input"  list="cities" placeholder="${From}" required max="64" id="from"
                           oninput="doCompletion(this.id);">
                <input type="text" class="input" placeholder="${To}" required max="64" id="to" oninput="doCompletion(this.id);" list="cities">
                <input type="text" class="input" value="${SelectDate}" onfocus="this.select();lcs(this)"
                       onclick="event.cancelBubble=true;this.select();lcs(this)" class="input" id="date">
                <input type="submit" class="button" value="${Search}" onClick="search_trips()">
            </center>

    </div>

    <table id="complete-table" />
    </td>
    </tr>
    </tbody>
    </table>

    <center>
        <div class="wrapper">
            <div class="table">
                <div class="row_info">
                    <div class="col date">
                        <b>${Date}:</b>
                    </div>

                    <div class="col c">
                        <b>${Departure}</b>
                    </div>

                    <div class="col date">
                        <b>${Date}:</b>
                    </div>

                    <div class="col c">
                        <b>${Arrival}</b>
                    </div>


                    <div class="col c">
                        <b>${Price}</b>
                    </div>
                </div>
            </div>
            <div class="table" id="tripsTable">
            </div>
        </div>
    </center>
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
    <form action="step2" method="get" style="display: none">
        <input type="text" value="0" id="tripId"name="id">
        <input type="submit" id="sendIdButton">
    </form>
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
<datalist id="cities"></datalist>
</html>
