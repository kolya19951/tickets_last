<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Admin Menu</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/fonts.css">
    <script type='text/javascript' src='script.js'></script>
    <script type='text/javascript' src='javascript.js'></script>
    <script src="js/jquery/jquery-1.9.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/msdropdown/dd.css"/>
    <script src="js/msdropdown/jquery.dd.js"></script>
    <link rel="stylesheet" type="text/css" href="css/msdropdown/flags.css"/>

</head>
<body>
<div class="menu">
    <select name="countries" id="countries" style="width: auto;" onchange="changeLang(this.value)">
        <% ArrayList<String> languages = (ArrayList<String>) request.getAttribute("languages");
            session = request.getSession();
            if (session.isNew())
                session.setAttribute("lang", "gb");
            String lang = (String) session.getAttribute("lang");
            String select = "";
            for (int i = 0; i < languages.size(); i++) {
                if (lang.equals(languages.get(i)))
                    select = "selected";
        %>
        <option value='<%=languages.get(i)%>' data-image="images/msdropdown/icons/blank.gif"
                data-imagecss="flag <%=languages.get(i)%>" data-title="<%=languages.get(i)%>" <%=select%>></option>
        <%
                select = "";
            }
        %>
    </select>
    <center>
        <div class="menu_table">
            <div class="menu_row">
                <div class="menu_cell menu_title">${Home}</div>
                <div class="menu_cell menu_title">${Contact}</div>
                <div class="menu_cell menu_title">${BuyTicket}</div>
                <div class="menu_cell menu_title">${Info}</div>
            </div>
            <div class="menu_row">
                <div class="menu_cell menu_icon"><a href="contacts"><img src="img/icon/mail.png" width="72" height="72"></a>
                </div>
                <div class="menu_cell menu_icon"><a href="reservation"><img src="img/icon/money.png" width="72"
                                                                            height="72"></a></div>
                <div class="menu_cell menu_icon"><a href="info"><img src="img/icon/compose.png" width="72" height="72"></a>
                </div>
            </div>
        </div>
    </center>
</div>
<div class="main">
    <div class="classTable">
        <div id='adminBar' class="adminContent">
            <button onclick='changeAdminPanel(this.id)' id='bus' class="button_admin_panel">Добавить автобус</button>
            <button onclick='changeAdminPanel(this.id)' id='city' class="button_admin_panel">Добавить город</button>
            <button onclick='changeAdminPanel(this.id)' id='station' class="button_admin_panel">Добавить вокзал</button>
            <button onclick='changeAdminPanel(this.id)' id='route' class="button_admin_panel">Добавить маршрут</button>
            <button onclick='changeAdminPanel(this.id)' id='trip' class="button_admin_panel">Добавить рейс</button>
            <button onclick='changeAdminPanel(this.id)' id='config' class="button_conf">Конфигурация</button>
            <button onclick='changeAdminPanel(this.id)' id='seats' class="button_control">Управление местами</button>
            <button onclick='changeAdminPanel(this.id)' id='lang' class="button_admin_panel">Языки</button>
            <button onclick='changeAdminPanel(this.id)' id='tickets' class="button_admin_panel">Билеты</button>
            <button onclick='changeAdminPanel(this.id)' id='translator' class="button_admin_panel">Перевод
            </button>
        </div>
        <div id="event"></div>
        <div id='adminContent' class="adminContent">

        </div>
        <div style="display: table-row;" class="adminContent">
        <div id="result" class="adminContent" style="display:table-cell">
        </div>
        <div id="result_bus" style="display: table-cell">
        </div>
        </div>

        <div id='new_bus' class='adminAdd'>
            <div style="display: inline"><input type='text' placeholder="Автобус" id="bus_name" class="inputAdmin"
                                                required>
                <input type='text' placeholder='Количество мест' id="places" class="inputAdmin" required>
                <input type='submit' value='Добавить' onClick='sendAdminPanel(this.id)' id='add_bus'
                       class="button_admin_add"></div>
        </div>
        <div id='new_city' class='adminAdd'>

            <input type='text' placeholder='Город' id="city_name" class="inputAdmin">

            <input type='submit' value='Добавить' id='add_city' onClick='sendAdminPanel(this.id)'
                   class="button_admin_add">
        </div>
        <div id='new_station' class='adminAdd'>

            <input type='text' placeholder='Название вокзала' id="station_name" class="inputAdmin">
            <input type='text' placeholder='Город' id="station_city" oninput="doCompletion(this.id);" list="cities"
                   class="inputAdmin">
            <datalist id="cities">
            </datalist>

            <input type='submit' value='Добавить' id='add_station' onClick='sendAdminPanel(this.id)'
                   class="button_admin_add">
            <input type='submit' value='Показать станции' id='stations_for_sity' onClick='show_station_city()'
                   class="button_admin_add">
        </div>
        <div id='new_route' class='adminAdd'>

            <%--@declare id="stations"--%>
            <div style="display: inline"><input type='text' placeholder='Откуда город' id="route_from"
                                                oninput="doCompletion(this.id);" list="cities" class="inputAdmin">
                <input type='text' placeholder='Куда город' id="route_to" oninput="doCompletion(this.id);" list="cities"
                       class="inputAdmin">
                <input type='text' placeholder='Откуда станция' id="route_from_station"
                       onfocus="doCompletionStations(this.id);"
                       list="stations" class="inputAdmin">
                <input type='text' placeholder='Куда станция' id="route_to_station"
                       onfocus="doCompletionStations(this.id);"
                       list="stations" class="inputAdmin">
                <datalist id="stations">
                </datalist>
                <input onmousedown="" type='submit' value='Добавить' id='add_route' onclick='sendAdminPanel(this.id)'
                       class="button_admin_add"></div>
        </div>
        <div id='new_trip' class='adminAdd'>
            <%--@declare id="buses"--%>
            <div style="display: inline"><input type='text' placeholder='Откуда город' id="route_from_city"
                                                oninput="doCompletion(this.id);" list="cities" class="inputAdmin">
                <input type='text' placeholder='Куда город' id="route_to_city" oninput="doCompletion(this.id);"
                       list="cities" class="inputAdmin">
                <input type='text' placeholder='Маршрут' id="trip_route" onfocus="doCompletionRoutes(this.id)"
                       list="routes" class="inputAdmin">
                <datalist id="routes">
                </datalist>
                <input type='date' id="trip_date_from" class="inputAdmin">
                <input type='time' id="trip_time_from" class="inputAdmin">
                <input type='date' id="trip_date_to" class="inputAdmin">
                <input type='time' id="trip_time_to" class="inputAdmin">
                <input type='text' placeholder='Автобус' id="trip_bus" onfocus="doCompletionBuses(this.id)" list="buses"
                       class="inputAdmin">
                <datalist id="buses">
                </datalist>
                <input type='submit' value='Добавить' id='add_trip' onClick='sendAdminPanel(this.id)'
                       class="button_admin_add"></div>
        </div>
        <div id='new_config' class='adminAdd'>
            <input type='text' placeholder='Автобус' id="config_bus_name" onfocus="doCompletionBuses(this.id)"
                   list="buses" class="inputAdmin">

            <input type='submit' value='Показать' id='show_config' onClick='sendAdminPanel(this.id)'
                   class="button_admin_add">
        </div>
        <div id='new_seats' class='adminAdd'>

            <input type='text' placeholder='ID рейса' id="seats_trip_id" class="inputAdmin">

            <input type='submit' value='Показать' id='showseats' onClick='sendAdminPanel(this.id)'
                   class="button_admin_add">
        </div>
        <div id='new_lang' class='adminAdd'>

            <input type='text' placeholder='Язык' id="new_lang_name" class="inputAdmin">

            <input type='submit' value='Добавить' id='addlang' onClick='sendAdminPanel(this.id)'
                   class="button_admin_add">
        </div>
        <div id='new_tickets' class='adminAdd'>
            <table>
                <tr>
                    <td><input type='date' id="filter_date_from" value="2012-06-01" class="inputAdmin"></td>
                </tr>
                <tr style="padding: 0 0 0 2%">
                    <td><input type='date' id="filter_date_to" value="2050-06-01" class="inputAdmin"></td>
                    <td><input type="text" id="filter_from_place" onfocus="filter(this.id)" oninput="filter(this.id)"
                               class="inputAdmin"></td>
                    <td><input type="text" id="filter_to_place" onfocus="filter(this.id)" oninput="filter(this.id)"
                               class="inputAdmin"></td>
                    <td><input type="text" id="filter_seat_num" onfocus="filter(this.id)" oninput="filter(this.id)"
                               class="inputAdmin"></td>
                    <td><input type="text" id="filter_client" onfocus="filter(this.id)" oninput="filter(this.id)"
                               class="inputAdmin"></td>
                </tr>
                <tr>
                    <td>Дата</td>
                    <td>Откуда</td>
                    <td>Куда</td>
                    <td>Номер места</td>
                    <td>ФИО</td>
                </tr>
            </table>
        </div>

        <div id='new_translator' class='adminAdd'>

            <input type='text' placeholder='Язык 1' id="first_lang_name" class="inputAdmin">
            <input type='submit' value='Показать' id='translator1' onClick='sendAdminPanel(this.id)'
                   class="button_admin_add">

            <input type='text' placeholder='Язык 2' id="second_lang_name" class="inputAdmin">
            <input type='submit' value='Показать' id='translator2' onClick='sendAdminPanel(this.id)'
                   class="button_admin_add">



        </div>


    </div>
</div>
</body>
<script>
    $(document).ready(function (e) {
        try {

            var pagename = document.location.pathname.toString();
            pagename = pagename.split("/");
            pages.setIndexByValue(pagename[pagename.length - 1]);
            $("#ver").html(msBeautify.version.msDropdown);
        } catch (e) {
            //console.log(e);
        }

        $("#ver").html(msBeautify.version.msDropdown);

        //convert
        $("select").msDropdown({roundedBorder: false});
        createByJson();
        $("#tech").data("dd");
    });


    //
</script>
</html>