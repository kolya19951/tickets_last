var id;

function sendAdminPanel(ID) {
    id = ID;
    request(id);
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

var url;

function request(action) {
    if (action == "showconfig" || action == "showtranslator")
        return;
    url = getUrl(action);
    data = getData(action);
    callback = getCallBack(action);
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callback;
    req.send(data);
    event(action);
}

function getCallBack(action) {
    if (action == "add_bus")
        back = refresh_buses;
    if (action == "add_city")
        back = refresh_cities;
    if (action == "add_station")
        back = refresh_stations;
    if (action == "add_route")
        back = refresh_routes;
    if (action == "add_trip")
        back = refresh_trips;
    if (action == "addlang")
        back = refresh_langs;
    if (action == "showbus")
        back = show_bus;
    if (action == "showcity")
        back = show_city;
    if (action == "showstation")
        back = show_station;
    if (action == "showroute")
        back = show_routes;
    if (action == "showtrip")
        back = show_trips;
    if (action == "showseats")
        back = show_bus_config;
    if (action == "show_config")
        back = draw_bus;
    if (action == "showconfig")
        back = null;
    if (action == "showlang")
        back = show_langs;
    if (action == "showtickets")
        back = show_tickets;
    if (action == "translator1" || action == "translator2")
        back = show_translator;
    return back;
}

function callbackBus() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            document.getElementById(obj).click();
        }
    }
}

function show_bus(action) {
    if (req.readyState == 4) {
        if (req.status == 200) {
            var result = "";
            responseXML = req.responseXML;
            var buses = responseXML.getElementsByTagName("buses")[0];
            if (buses.childNodes.length > 0) {
                result = "<div class='classTable'><table>";
                for (i = 0; i < buses.childNodes.length; i++) {
                    var bus = buses.childNodes[i];
                    var id = bus.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    var name = bus.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                    var seats = bus.getElementsByTagName("seats")[0].childNodes[0].nodeValue;
                    result += "<tr>"
                    result += "<td><input type='text' class='inputAdmin' id='edit_bus_name" + id + "' value='" + name + "'></td>";
                    result += "<td><input type='text' class='inputAdmin' id='edit_bus_seats" + id + "' value='" + seats + "'></td>";
                    result += "<td><button onclick='delete_bus(this.id)' class='button_admin_add' id='" + id + "'>Удалить</button></td>";
                    result += "<td><button onclick='edit_bus(this.id)' class='button_admin_add' id='" + id + "'>Сохранить</button></td>";
                    result += "</tr>";
                }
                result += "</table></div>";
            }
            document.getElementById("result").innerHTML = result;

        }
    }
}

function show_city(action) {
    if (req.readyState == 4) {
        if (req.status == 200) {
            var result = "";
            responseXML = req.responseXML;
            var cities = responseXML.getElementsByTagName("cities")[0];
            if (cities.childNodes.length > 0) {
                result = "<div class='classTable'><table>";
                for (i = 0; i < cities.childNodes.length; i++) {
                    var city = cities.childNodes[i];
                    var id = city.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    var name = city.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                    result += "<tr>"
                    result += "<td><input type='text' class='inputAdmin' id='edit_city_name" + id + "' value='" + name + "'></td>";
                    result += "<td><button onclick='delete_city(this.id)' class='button_admin_add' id='" + id + "'>Удалить</button></td>";
                    result += "<td><button onclick='edit_city(this.id)' class='button_admin_add' id='" + id + "'>Сохранить</button></td>";
                    result += "</tr>";
                }
                result += "</table></div>";
            }
            document.getElementById("result").innerHTML = result;
        }
    }
}

function show_station(action) {
    if (req.readyState == 4) {
        if (req.status == 200) {
            var result = "";
            responseXML = req.responseXML;
            var stations = responseXML.getElementsByTagName("stations")[0];
            if (stations.childNodes.length > 0) {
                result = "<div class='classTable'><table>";
                for (i = 0; i < stations.childNodes.length; i++) {
                    var station = stations.childNodes[i];
                    var id = station.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    var name = station.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                    var city_name = station.getElementsByTagName("city_name")[0].childNodes[0].nodeValue;
                    var city_id = station.getElementsByTagName("city_id")[0].childNodes[0].nodeValue;
                    result += "<tr>"
                    result += "<td><input type='text' class='inputAdmin' id='edit_station_name" + id + "' value='" + name + "'></td>";
                    result += "<td><input type='text' class='inputAdmin' id='edit_station_city_name" + id + "' value='" + city_name + "'readonly></td>";
                    result += "<td><input style='display:none' type='text' class='inputAdmin' id='edit_station_city_id" + id + "' value='" + city_id + "'></td>";
                    result += "<td><button onclick='delete_station(this.id)' class='button_admin_add' id='" + id + "'>Удалить</button></td>";
                    result += "<td><button onclick='edit_station(this.id)' class='button_admin_add' id='" + id + "'>Сохранить</button></td>";
                    result += "</tr>";
                }
                result += "</table></div>";
            }
            document.getElementById("result").innerHTML = result;
        }
    }
}

function show_routes(action) {
    if (req.readyState == 4) {
        if (req.status == 200) {
            var result = "";
            responseXML = req.responseXML;
            var routes = responseXML.getElementsByTagName("routes")[0];
            if (routes.childNodes.length > 0) {
                result = "<div class='classTable'><table>";
                for (i = 0; i < routes.childNodes.length; i++) {
                    var route = routes.childNodes[i];
                    var id = route.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    var from_station_name = route.getElementsByTagName("from_station_name")[0].childNodes[0].nodeValue;
                    var from_station_id = route.getElementsByTagName("from_station_id")[0].childNodes[0].nodeValue;
                    var to_station_name = route.getElementsByTagName("to_station_name")[0].childNodes[0].nodeValue;
                    var to_station_id = route.getElementsByTagName("to_station_id")[0].childNodes[0].nodeValue;
                    var from_city_name = route.getElementsByTagName("from_city_name")[0].childNodes[0].nodeValue;
                    var from_city_id = route.getElementsByTagName("from_city_id")[0].childNodes[0].nodeValue;
                    var to_city_name = route.getElementsByTagName("to_city_name")[0].childNodes[0].nodeValue;
                    var to_city_id = route.getElementsByTagName("to_city_id")[0].childNodes[0].nodeValue;
                    result += "<tr>"
                    result += "<td><input type='text' class='inputAdmin' id='edit_from_station_name" + id + "' value='" + from_station_name + "' readonly></td>";
                    result += "<td><input type='text' class='inputAdmin' id='edit_from_city_name" + id + "' value='" + from_city_name + "' readonly></td>";
                    result += "<td><input type='text' class='inputAdmin' id='edit_to_station_name" + id + "' value='" + to_station_name + "' readonly></td>";
                    result += "<td><input type='text' class='inputAdmin' id='edit_from_city_nsme" + id + "' value='" + to_city_name + "' readonly></td>";
                    result += "<td><button onclick='delete_route(this.id)' class='button_admin_add' id='" + id + "'>Удалить</button></td>";
                    result += "</tr>";
                }
                result += "</table></div>";
            }
            document.getElementById("result").innerHTML = result;
        }
    }
}

function show_trips(action) {
    if (req.readyState == 4) {
        if (req.status == 200) {
            var result = "";
            responseXML = req.responseXML;
            var trips = responseXML.getElementsByTagName("trips")[0];
            if (trips.childNodes.length > 0) {
                result = "<div class='classTable'><table>";
                for (i = 0; i < trips.childNodes.length; i++) {
                    var trip = trips.childNodes[i];
                    var id = trip.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    var bus_name = trip.getElementsByTagName("bus_name")[0].childNodes[0].nodeValue;
                    var bus_id = trip.getElementsByTagName("bus_id")[0].childNodes[0].nodeValue;
                    var departure_date = trip.getElementsByTagName("departure_date")[0].childNodes[0].nodeValue;
                    var departure_time = trip.getElementsByTagName("departure_time")[0].childNodes[0].nodeValue;
                    var arrival_date = trip.getElementsByTagName("arrival_date")[0].childNodes[0].nodeValue;
                    var arrival_time = trip.getElementsByTagName("arrival_time")[0].childNodes[0].nodeValue;
                    var route_id = trip.getElementsByTagName("route_id")[0].childNodes[0].nodeValue;
                    var from_station_name = trip.getElementsByTagName("from_station_name")[0].childNodes[0].nodeValue;
                    var from_station_id = trip.getElementsByTagName("from_station_id")[0].childNodes[0].nodeValue;
                    var to_station_name = trip.getElementsByTagName("to_station_name")[0].childNodes[0].nodeValue;
                    var to_station_id = trip.getElementsByTagName("to_station_id")[0].childNodes[0].nodeValue;
                    var from_city_name = trip.getElementsByTagName("from_city_name")[0].childNodes[0].nodeValue;
                    var from_city_id = trip.getElementsByTagName("from_city_id")[0].childNodes[0].nodeValue;
                    var to_city_name = trip.getElementsByTagName("to_city_name")[0].childNodes[0].nodeValue;
                    var to_city_id = trip.getElementsByTagName("to_city_id")[0].childNodes[0].nodeValue;
                    result += "<tr>"
                    result += "<td>" + id + "&thinsp;&thinsp;&thinsp;</td>";
                    result += "<td><label id='bus_name" + id + "'>" + bus_name + "&thinsp;&thinsp;&thinsp;</label></td>";
                    result += "<td><label id='departure_date" + id + "'>" + departure_date + " " + departure_time + "&thinsp;&thinsp;&thinsp;</label></td>";
                    result += "<td><label id='arrival_date" + id + "'>" + arrival_date + " " + arrival_time + "&thinsp;&thinsp;&thinsp;</label></td>";
                    result += "<td><label id='from_station_name" + id + "'>" + from_station_name + "&thinsp;&thinsp;&thinsp;</label></td>";
                    result += "<td><label id='from_city_name" + id + "'>" + from_city_name + "&thinsp;&thinsp;&thinsp;</label></td>";
                    result += "<td><label id='to_station_name" + id + "'>" + to_station_name + "&thinsp;&thinsp;&thinsp;</label></td>";
                    result += "<td><label id='to_city_name" + id + "'>" + to_city_name + "&thinsp;&thinsp;&thinsp;</label></td>";

                    result += "<td><button onclick='delete_trip(this.id)' class='button_admin_add' id='" + id + "'>Удалить</button></td>";
                    result += "</tr>";
                }
                result += "</table></div>";
            }
            document.getElementById("result").innerHTML = result;
        }
    }
}

function show_bus_config(action) {
    if (req.readyState == 4) {
        if (req.status == 200) {
            var result = "Установить цену для всех мест: <input class='inputAdmin' type='text' id='globalPrice'><button class='button_admin_add' onClick='setGlobalPrice()'>Сохранить</button>";
            var selected = "";
            responseXML = req.responseXML;
            var seats = responseXML.getElementsByTagName("seats")[0];
            if (seats.childNodes.length > 0) {
                result += "<div class='classTable'><table style='position: absolute; left: 0px;'>";
                result += "<tr><td>Id</td><td>seat_num</td><td>price</td><td>availability</td><td>row</td><td>place</td></tr>";
                for (i = 0; i < seats.childNodes.length; i++) {
                    var seat = seats.childNodes[i];
                    var id = seat.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    var place_num = seat.getElementsByTagName("place_num")[0].childNodes[0].nodeValue;
                    var price = seat.getElementsByTagName("price")[0].childNodes[0].nodeValue;
                    var availability = seat.getElementsByTagName("availability")[0].childNodes[0].nodeValue;
                    var row = seat.getElementsByTagName("row")[0].childNodes[0].nodeValue;
                    var place = seat.getElementsByTagName("place")[0].childNodes[0].nodeValue;
                    result += "<tr>"
                    result += "<td><input type='text' class='inputAdmin' size='5' id='place_num" + id + "' value='" + place_num + "' readonly></td>";
                    result += "<td><input type='text' class='inputAdmin' size='10' id='price" + id + "' value='" + price + "'></td>";
                    result += "<td><select class='inputAdmin' id='availability" + id + "'>";
                    selected = "";
                    if (availability == "free")
                        selected = "selected";
                    result += "<option value='free' " + selected + ">free</option>";
                    selected = "";
                    if (availability == "reserved by admin")
                        selected = "selected";
                    result += "<option value='reserved by admin' " + selected + ">reserved by admin</option>";
                    selected = "";
                    if (availability == "reserved")
                        selected = "selected";
                    result += "<option value='reserved' " + selected + ">reserved</option>";
                    selected = "";
                    if (availability == "sales")
                        selected = "selected";
                    result += "<option value='sales' " + selected + ">sales</option></select></td>";
                    result += "<td><input type='text' size='5' class='inputAdmin' id='row" + id + "' value='" + row + "' readonly></td>";
                    result += "<td><input type='text' size='5' class='inputAdmin' id='place" + id + "' value='" + place + "' readonly></td>";
                    result += "<td><button class='button_admin_add'  onclick='edit_seat(this.id)' id='" + id + "'>Сохранить</button></td>";
                    result += "</tr>";
                }
                result += "</table></div>";
            }
            document.getElementById("result").innerHTML = result;
        }
    }
}

function show_langs() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            var result = "";
            responseXML = req.responseXML;
            var langs = responseXML.getElementsByTagName("languages")[0];
            if (langs.childNodes.length > 0) {
                result = "<table class='classTable'>";
                for (i = 0; i < langs.childNodes.length; i++) {
                    var lang = langs.childNodes[i];
                    var name = lang.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                    result += "<tr>"
                    result += "<td><input type='text' class='inputAdmin' value='" + name + "' readonly></td>";
                    result += "<td><button onclick='delete_lang(this.id)' class='button_admin_add' id='" + name + "'>Удалить</button></td>";
                    result += "</tr>";
                }
                result += "</table>";
            }
            document.getElementById("result").innerHTML = result;
        }
    }
}

function show_tickets() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            var result = "";
            responseXML = req.responseXML;
            var tickets = responseXML.getElementsByTagName("tickets")[0];
            if (tickets.childNodes.length > 0) {
                result = "<table class='classTable'>";
                for (i = 0; i < tickets.childNodes.length; i++) {
                    var ticket = tickets.childNodes[i];
                    var date = ticket.getElementsByTagName("date")[0].childNodes[0].nodeValue;
                    var from = ticket.getElementsByTagName("from")[0].childNodes[0].nodeValue;
                    var to = ticket.getElementsByTagName("to")[0].childNodes[0].nodeValue;
                    var seat_num = ticket.getElementsByTagName("seat_num")[0].childNodes[0].nodeValue;
                    var client = ticket.getElementsByTagName("client")[0].childNodes[0].nodeValue;
                    result += "<tr>"
                    result += "<td><input type='text' class='inputAdmin' value='" + date + "' readonly></td>";
                    result += "<td><input type='text' class='inputAdmin' value='" + from + "' readonly></td>";
                    result += "<td><input type='text' class='inputAdmin' value='" + to + "' readonly></td>";
                    result += "<td><input type='text' class='inputAdmin' value='" + seat_num + "' readonly></td>";
                    result += "<td><input type='text' class='inputAdmin' value='" + client + "' readonly></td>";
                    result += "</tr>";
                }
                result += "</table>";
            }
            document.getElementById("result").innerHTML = result;
        }
    }
}

var lang1, lang2;

function show_translator(){
    if (req.readyState == 4) {
        if (req.status == 200) {
            if (id == "translator1"){
                input_name = "1";
            }
            if (id == "translator2") {
                input_name = "2";
            }
            var result = "";
            responseXML = req.responseXML;
            var translators = responseXML.getElementsByTagName("translators")[0];
            if (translators.childNodes.length > 0) {
                result = "<div class='classTable'><table>";
                for (i = 0; i < translators.childNodes.length; i++) {
                    var translator = translators.childNodes[i];
                    var translator_id = translator.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    var name = translator.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                    result += "<tr>"
                    result += "<td><input type='text' class='inputAdmin' id='edit_translator" + input_name + translator_id + "' value='" + name + "'></td>";
                    result += "<td><button onclick='edit_translator(this.id)' class='button_admin_add' id='" + translator_id + id + "'>Сохранить</button></td>";
                    result += "</tr>";
                }
                result += "</table></div>";
            }
            if (id == "translator1"){
                document.getElementById("result").innerHTML = result;
                lang1 = document.getElementById("first_lang_name").value;
            }
            if (id == "translator2") {
                document.getElementById("result_bus").innerHTML = result;
                lang2 = document.getElementById("second_lang_name").value;
            }
        }
    }
}

function draw_bus() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            responseXML = req.responseXML;
            var seats = responseXML.getElementsByTagName("seats")[0];
            var indexableseats = new Array();
            for (i = 0; i < 20; i++) {
                indexableseats[i] = new Array();
                for (j = 0; j < 5; j++) {
                    indexableseats[i][j] = null;
                }
            }

            for (i = 0; i < seats.childNodes.length; i++) {
                var seat = seats.childNodes[i];
                var place_num = seat.getElementsByTagName("place_num")[0].childNodes[0].nodeValue;
                var row = seat.getElementsByTagName("row")[0].childNodes[0].nodeValue;
                var place = seat.getElementsByTagName("place")[0].childNodes[0].nodeValue;
                indexableseats[row - 1][place - 1] = seat;
            }
            result = "<table>";
            for (i = 0; i < 20; i++) {
                result += "<tr>";
                for (j = 0; j < 5; j++) {
                    if (indexableseats[i][j] != null) {
                        result += "<td class='" + j + "" + i + "' onmousedown='save_place(this.id)' onmouseup='swap_places(this)' " +
                            "id=" + indexableseats[i][j].getElementsByTagName("id")[0].childNodes[0].nodeValue + "" +
                            ">" + indexableseats[i][j].getElementsByTagName("place_num")[0].childNodes[0].nodeValue + "" +
                            "</td>";
                    }
                    else result += "<td class='" + j + "" + i + "' id='0' style='background-color: black' onmouseup='swap_places(this)'>0</td>";
                }
                result += "</tr>";
            }
            result += "</table>";
            document.getElementById("result").innerHTML = result;
        }
    }
}

function getUrl(action) {
    if (action == "add_bus")
        url = "bus_manager";
    if (action == "add_city")
        url = "city_manager";
    if (action == "add_station")
        url = "station_manager";
    if (action == "add_route")
        url = "route_manager";
    if (action == "add_trip")
        url = "trip_manager";
    if (action == "showbus")
        url = "show_bus";
    if (action == "showcity")
        url = "show_city";
    if (action == "showstation")
        url = "show_station";
    if (action == "showroute")
        url = "show_route";
    if (action == "showtrip")
        url = "show_trips";
    if (action == "show_config")
        url = "show_bus_config";
    if (action == "showconfig")
        url = "show_bus_config";
    if (action == "showseats")
        url = "show_seats";
    if (action == "showlang")
        url = "show_languages";
    if (action == "addlang")
        url = "lang_manager";
    if (action == "showtickets")
        url = "show_tickets";
    if (action == "translator1" || action == "translator2")
        url = "show_translator";
    return url;
}

var tripId = 0;

function getData(action) {
    data = "";
    if (action == "add_bus") {
        bus_name = document.getElementById("bus_name").value;
        places = document.getElementById("places").value;
        data = "bus_name=" + bus_name + "&places=" + places + "&action=add";
    }
    if (action == "add_city") {
        data = "name=" + document.getElementById("city_name").value + "&action=add";
    }
    if (action == "add_station") {
        station = document.getElementById("station_name").value;
        station_city = document.getElementById("station_city").accept;
        data = "name=" + station + "&station_city=" + station_city + "&action=add";
    }
    if (action == "add_route") {
        route_from = document.getElementById("route_from_station").accept;
        route_to = document.getElementById("route_to_station").accept;
        data = "from=" + route_from + "&to=" + route_to + "&action=add";
    }
    if (action == "add_trip") {
        trip_route = document.getElementById("trip_route").accept;
        trip_date_from = document.getElementById("trip_date_from").value;
        trip_time_from = document.getElementById("trip_time_from").value;
        trip_date_to = document.getElementById("trip_date_to").value;
        trip_time_to = document.getElementById("trip_time_to").value;
        value = document.getElementById("trip_bus").value
        trip_bus = document.getElementById(value).label;
        data = "route=" + trip_route + "&departure_date=" + trip_date_from + "&departure_time=" + trip_time_from + "&arrival_date=" + trip_date_to + "&arrival_time=" + trip_time_to + "&bus=" + trip_bus + "&action=add";
    }
    if (action == "showseats") {
        tripId = document.getElementById("seats_trip_id").value;
        data = "tripId=" + tripId;
    }
    if (action == "show_config") {
        value = document.getElementById("config_bus_name").value;
        data = "bus_id=" + document.getElementById(value).label;
    }
    if (action == "addlang")
        data = "action=add&lang=" + document.getElementById("new_lang_name").value;
    if (action == "translator1")
        data = "lang=" + document.getElementById("first_lang_name").value;
    if (action == "translator2")
        data = "lang=" + document.getElementById("second_lang_name").value;
    return data;
}

var obj;

function changeAdminPanel(object) {
    document.getElementById("event").innerHTML = "";
    document.getElementById("result").innerHTML = "";
    document.getElementById("result_bus").innerHTML = "";
    obj = object;
    document.getElementById("adminContent").innerHTML = document.getElementById("new_" + object).innerHTML;
    request("show" + object)
}

function delete_bus(id) {
    if(!confirm("Are you sure?"))
        return;
    url = "bus_manager";
    data = "action=delete&id=" + id;
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = refresh_buses;
    req.send(data);
    document.getElementById("event").innerHTML = "Bus deleting";
}

function edit_bus(id) {
    name = document.getElementById("edit_bus_name" + id).value;
    seats = document.getElementById("edit_bus_seats" + id).value;
    url = "bus_manager";
    data = "action=edit&id=" + id + "&name=" + name + "&seats=" + seats;
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = refresh_buses;
    req.send(data);
    document.getElementById("event").innerHTML = "Bus editing";
}

function delete_city(id) {
    if(!confirm("Are you sure?"))
        return;
    url = "city_manager";
    data = "action=delete&id=" + id;
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callback;
    req.send(data);
    document.getElementById("city").click();
    document.getElementById("event").innerHTML = "City deleting";
}

function delete_station(id) {
    if(!confirm("Are you sure?"))
        return;
    url = "station_manager";
    data = "action=delete&id=" + id;
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callback;
    req.send(data);
    document.getElementById("stations_for_sity").click();
    document.getElementById("event").innerHTML = "Station deleting";
}

function edit_city(id) {
    name = document.getElementById("edit_city_name" + id).value;
    url = "city_manager";
    data = "action=edit&id=" + id + "&name=" + name;
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callback;
    req.send(data);
    document.getElementById("city").click();
    document.getElementById("event").innerHTML = "City editing";
}

function edit_station(id) {
    name = document.getElementById("edit_station_name" + id).value;
    city = document.getElementById("edit_station_city_id" + id).value;
    url = "station_manager";
    data = "action=edit&id=" + id + "&name=" + name + "&city=" + city;
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = refresh_stations;
    req.send(data);
    document.getElementById("event").innerHTML = "Station editing";

}

function edit_seat(id) {
    availability = document.getElementById("availability" + id).value;
    price = document.getElementById("price" + id).value;
    url = "seats_manager";
    data = "action=update&seat_id=" + id + "&availability=" + availability + "&price=" + price;
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callback;
    req.send(data);
    document.getElementById("seats").click();
    document.getElementById("event").innerHTML = "Seat editing";
}

function delete_route(id) {
    if(!confirm("Are you sure?"))
        return;
    url = "route_manager";
    data = "action=delete&id=" + id;
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callback;
    req.send(data);
    document.getElementById("route").click();
    document.getElementById("event").innerHTML = "Route deleting";
}

function delete_trip(id) {
    if(!confirm("Are you sure?"))
        return;
    url = "trip_manager";
    data = "action=delete&id=" + id;
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callback;
    req.send(data);
    document.getElementById("trip").click();
    document.getElementById("event").innerHTML = "Trip deleting";

}

function delete_lang(id) {
    if(!confirm("Are you sure?"))
        return;
    url = "lang_manager";
    data = "action=delete&lang=" + id;
    req = initRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callback;
    req.send(data);
    document.getElementById("lang").click();
    document.getElementById("event").innerHTML = "Lang deleting";
}

function show_station_autocomplete() {
    document.getElementById("stations").innerHTML = "";
    if (req.readyState == 4) {
        if (req.status == 200) {
            responseXML = req.responseXML;
            var stations = responseXML.getElementsByTagName("stations")[0];
            if (stations.childNodes.length > 0) {
                for (i = 0; i < stations.childNodes.length; i++) {
                    var station = stations.childNodes[i];
                    var id = station.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    var name = station.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                    appendStations(name, id);
                }
            }
        }
    }
}

function show_routes_autocomplete() {
    document.getElementById("routes").innerHTML = "";
    if (req.readyState == 4) {
        if (req.status == 200) {
            responseXML = req.responseXML;
            var routes = responseXML.getElementsByTagName("routes")[0];
            if (routes.childNodes.length > 0) {

                for (i = 0; i < routes.childNodes.length; i++) {
                    var route = routes.childNodes[i];
                    var id = route.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    var from_station_name = route.getElementsByTagName("from_station_name")[0].childNodes[0].nodeValue;
                    var to_station_name = route.getElementsByTagName("to_station_name")[0].childNodes[0].nodeValue;
                    appendRoutes(from_station_name, to_station_name, id);
                }
            }
        }
    }
}

function show_buses_autocomplete() {
    document.getElementById("buses").innerHTML = "";
    if (req.readyState == 4) {
        if (req.status == 200) {
            responseXML = req.responseXML;
            var buses = responseXML.getElementsByTagName("buses")[0];
            if (buses.childNodes.length > 0) {

                for (i = 0; i < buses.childNodes.length; i++) {
                    var bus = buses.childNodes[i];
                    var id = bus.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    var name = bus.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                    appendBuses(name, id);
                }
            }
        }
    }
}

var first_id = 0;

function save_place(id) {
    first_id = id;
}

function swap_places(second) {
    second_id = second.id;
    if (second_id == 0) {
        XY = second.className;
        X = XY.substring(0, 1);
        Y = XY.substring(1);
        X++;
        Y++;
        data = "action=replace&id=" + first_id + "&row=" + Y + "&place=" + X;
    }
    else {
        data = "action=swap&first_id=" + first_id + "&second_id=" + second_id;
    }
    req = initRequest();
    req.open("POST", "swap_seats", true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = refresh_config;
    req.send(data);
}

function changeLang(value) {
    req = initRequest();
    req.open("POST", "/change_language", true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = alert;
    req.send("lang=" + value);
    location.reload()
}

function setGlobalPrice() {
    req = initRequest();
    req.open("POST", "/seats_manager", true);
    price = document.getElementById("globalPrice").value;
    data = "action=set_global_price&trip_id=" + tripId + "&price=" + price;
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = alert;
    req.send(data);
}

function filter(action) {
    from = "";
    date_from = document.getElementById("filter_date_from").value;
    date_to = document.getElementById("filter_date_to").value;
    from = document.getElementById("filter_from_place").value;
    to = document.getElementById("filter_to_place").value;
    seat_num = document.getElementById("filter_seat_num").value;
    client = document.getElementById("filter_client").value;
    sort = action.substring(7);
    req = initRequest();
    req.open("POST", "/show_tickets", true);
    data = "from_date=" + date_from;
    data += "&to_date=" + date_to;
    data += "&from=" + from;
    data += "&to=" + to;
    data += "&client=" + client;
    data += "&seat_num=" + seat_num;
    data += "&sort_criteria=" + sort;
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = show_tickets;
    req.send(data);
}

function Login() {
    pass = prompt();
    if (pass != "qwerty")
        document.location.href = "/";
}

function refresh_buses() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            document.getElementById("bus").click();
        }
    }
}

function refresh_cities() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            document.getElementById("city").click();
        }
    }
}

function refresh_stations() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            document.getElementById("event").innerHTML = "";
            document.getElementById("stations_for_sity").click();
        }
    }
}

function refresh_routes() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            document.getElementById("route").click();
        }
    }
}

function refresh_trips() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            document.getElementById("trip").click();
        }
    }
}

function refresh_langs() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            document.getElementById("lang").click();
        }
    }
}

function refresh_config() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            document.getElementById("show_config").click();
        }
    }
}

function show_station_city(){
    req = initRequest();
    req.open("POST", "show_station_with_filter", true);
    data = "filter=" + document.getElementById("station_city").value;
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = show_station;
    req.send(data);
}

function edit_translator(id){
    if (id.substr(id.indexOf("t")) == "translator1") {
        lang = lang1;
        name = document.getElementById("edit_translator1" + id.substr(0, id.indexOf("t"))).value;
    }
    if (id.substr(id.indexOf("t")) == "translator2") {
        lang = lang2;
        name = document.getElementById("edit_translator2" + id.substr(0, id.indexOf("t"))).value;
    }
    id = id.substr(0, id.indexOf("t"));
    data = "id=" + id;
    data += "&lang=" + lang;
    data += "&name=" + name;
    req = initRequest();
    req.open("POST", "translator_manager", true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send(data);
    document.getElementById("event").innerHTML = "String edited";
}

function event(action){
    if (action == "add_bus")
        document.getElementById("event").innerHTML = "Bus adding";
    if (action == "add_city")
        document.getElementById("event").innerHTML = "City adding";
    if (action == "add_station")
        document.getElementById("event").innerHTML = "Station adding";
    if (action == "add_route")
        document.getElementById("event").innerHTML = "Route adding";
    if (action == "add_trip")
        document.getElementById("event").innerHTML = "Trip adding";
    if (action == "addlang")
        document.getElementById("event").innerHTML = "Language adding";
}