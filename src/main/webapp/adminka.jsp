<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Документ без названия</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
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
        <option value='' data-image="images/msdropdown/icons/blank.gif" data-imagecss="" data-title=""></option>
        <option value='en' data-image="images/msdropdown/icons/blank.gif" data-imagecss="flag gb"
                data-title="Great Britain (UK)"></option>
        <option value='ru' data-image="images/msdropdown/icons/blank.gif" data-imagecss="flag ru"
                data-title="Russian Federation"></option>
    </select>
    <ul>
        <li><a href="Index.html">Главная</a></li>
        <li><a href="Reservations.html">Бронирование</a></li>
        <li><a href="Info.html">Информация</a></li>
        <li><a href="Contacts.html">Связь</a></li>
    </ul>
</div>
<div class="main">
    <br>

    <div id="logo">
        <center><img src="img/logo.png" width="180" height="174"></center>
    </div>
    <div class="step">

    </div>
    <div id='adminBar'>
        <button onclick='changeAdminPanel(this.id)' id='bus'>Добавить автобус</button>
        <button onclick='changeAdminPanel(this.id)' id='city'>Добавить город</button>
        <button onclick='changeAdminPanel(this.id)' id='station'>Добавить вокзал</button>
        <button onclick='changeAdminPanel(this.id)' id='route'>Добавить маршрут</button>
        <button onclick='changeAdminPanel(this.id)' id='trip'>Добавить рейс</button>
        <button onclick='changeAdminPanel(this.id)' id='config'>Конфигурация</button>
        <button onclick='changeAdminPanel(this.id)' id='seats'>Управление местами</button>
        <button onclick='changeAdminPanel(this.id)' id='lang'>Языки</button>
        <button onclick='changeAdminPanel(this.id)' id='tickets'>Билеты</button>

    </div>
    <div id='adminContent'>

    </div>
    <div id="result">
    </div>
    <div id="result_bus">
    </div>

</div>
</body>
<div id='new_bus' class='adminAdd'>
    <input type='text' placeholder="Автобус" id="bus_name">
    <input type='text' placeholder='Количество мест' id="places"><br>
    <br>
    <input type='submit' value='Добавить' onClick='sendAdminPanel(this.id)' id='add_bus'>
</div>
<div id='new_city' class='adminAdd'>

    <input type='text' placeholder='Город' id="city_name">

    <input type='submit' value='Добавить' id='add_city' onClick='sendAdminPanel(this.id)'>
</div>
<div id='new_station' class='adminAdd'>

    <input type='text' placeholder='Название вокзала' id="station_name">
    <input type='text' placeholder='Город' id="station_city" oninput="doCompletion(this.id);" list="cities">
    <datalist id="cities">
    </datalist>

    <input type='submit' value='Добавить' id='add_station' onClick='sendAdminPanel(this.id)'>
</div>
<div id='new_route' class='adminAdd'>

    <%--@declare id="stations"--%>
    <input type='text' placeholder='Откуда город' id="route_from" oninput="doCompletion(this.id);" list="cities">
    <input type='text' placeholder='Куда город' id="route_to" oninput="doCompletion(this.id);" list="cities">
    <input type='text' placeholder='Откуда станция' id="route_from_station" onfocus="doCompletionStations(this.id);"
           list="stations">
    <input type='text' placeholder='Куда станция' id="route_to_station" onfocus="doCompletionStations(this.id);"
           list="stations">
    <datalist id="stations">
    </datalist>
    <br>
    <input onmousedown="" type='submit' value='Добавить' id='add_route' onclick='sendAdminPanel(this.id)'>
</div>
<div id='new_trip' class='adminAdd'>
    <%--@declare id="buses"--%><input type='text' placeholder='Откуда город' id="route_from_city"
                                      oninput="doCompletion(this.id);" list="cities">
    <input type='text' placeholder='Куда город' id="route_to_city" oninput="doCompletion(this.id);" list="cities">
    <input type='text' placeholder='Маршрут' id="trip_route" onfocus="doCompletionRoutes(this.id)" list="routes">
    <datalist id="routes">
    </datalist>
    <input type='date' id="trip_date_from">
    <input type='time' id="trip_time_from">
    <input type='date' id="trip_date_to">
    <input type='time' id="trip_time_to">
    <input type='text' placeholder='Автобус' id="trip_bus" onfocus="doCompletionBuses(this.id)" list="buses">
    <datalist id="buses">
    </datalist>
    <br>
    <input type='submit' value='Добавить' id='add_trip' onClick='sendAdminPanel(this.id)'>
</div>
<div id='new_config' class='adminAdd'>
    <input type='text' placeholder='Автобус' id="config_bus_name" onfocus="doCompletionBuses(this.id)" list="buses">

    <input type='submit' value='Показать' id='show_config' onClick='sendAdminPanel(this.id)'>
</div>
<div id='new_seats' class='adminAdd'>

    <input type='text' placeholder='ID рейса' id="seats_trip_id">

    <input type='submit' value='Показать' id='showseats' onClick='sendAdminPanel(this.id)'>
</div>
<div id='new_lang' class='adminAdd'>

    <input type='text' placeholder='Язык' id="new_lang_name">

    <input type='submit' value='Добавить' id='addlang' onClick='sendAdminPanel(this.id)'>
</div>
<div id='new_tickets' class='adminAdd'>
    <table>
        <tr>
            <td><input type='date' id="filter_date_from" value="2012-06-01"></td>
        </tr>
        <tr>
            <td><input type='date' id="filter_date_to" value="2050-06-01"></td>
            <td><input type="text" id="filter_from_place" onfocus="filter(this.id)" oninput="filter(this.id)"></td>
            <td><input type="text" id="filter_to_place" onfocus="filter(this.id)" oninput="filter(this.id)"></td>
            <td><input type="text" id="filter_seat_num" onfocus="filter(this.id)" oninput="filter(this.id)"></td>
            <td><input type="text" id="filter_client" onfocus="filter(this.id)" oninput="filter(this.id)"></td>
        </tr>
        <tr>
            <td>Дата</td>
            <td>Откуда</td>
            <td>Куда</td>
            <td>Ноер места</td>
            <td>ФИО</td>
        </tr>
    </table>

</div>
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