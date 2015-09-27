var completeField;
var completeTable;
var autoRow;
var req;
var isIE;

function init() {
    completeField = document.getElementById("complete-field");
    completeTable = document.getElementById("complete-table");
    autoRow = document.getElementById("auto-row");
}

var itog = "";
var id = "";
function doCompletion(ID) {
    id = ID;
    completeField = document.getElementById(id);
    //ishodn = completeField.value.toLowerCase();
    //for (i = 0; i < ishodn.length; i++) {
    //    var perem = ishodn.charCodeAt(i);
    //    itog = perem.toString();
    //}
    //var url = "action=complete&name=" + itog;
    var url = "action=complete&name=" + completeField.value.toLowerCase();
    req = initRequest();
    req.open("POST", "autocomplete", true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callbackAutocomletion;
    req.send(url);
}

function doCompletionStations(ID) {
    id = ID;
    if (id == "route_from_station")
        field = document.getElementById("route_from");
    if (id == "route_to_station")
        field = document.getElementById("route_to");
    var data = "action=complete&city_id=" + field.accept;
    req = initRequest();
    req.open("POST", "show_station_by_city", true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = show_station_autocomplete;
    req.send(data);
}

function doCompletionRoutes(ID) {
    id = ID;
    from_field = document.getElementById("route_from_city");
    to_field = document.getElementById("route_to_city");
    var data = "action=complete&from=" + from_field.accept + "&to=" + to_field.accept;
    req = initRequest();
    req.open("POST", "show_route_by_cities", true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = show_routes_autocomplete;
    req.send(data);
}

function doCompletionBuses(ID) {
    id = ID;
    var data = "";
    req = initRequest();
    req.open("POST", "show_bus", true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = show_buses_autocomplete;
    req.send(data);
}

function search_trips() {
    from = document.getElementById("from").value;
    to = document.getElementById("to").value;
    date = document.getElementById("date").value;
    var str = "from=" + from + "&to=" + to + "&date=" + date;
    req = initRequest();
    req.open("POST", "reservation", true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callbackTrips;
    req.send(str);
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

function callbackTrips() {

    clearTable();


    if (req.readyState == 4) {
        if (req.status == 200) {
            buildTripTable(req.responseXML);
        }
    }
}

function callbackAutocomletion() {

    clearTable();


    if (req.readyState == 4) {
        if (req.status == 200) {
            var respXML = req.responseXML;
            parseMessages(respXML);
        }
    }
}

function appendCity(name, cityId) {
    option = document.createElement("option");
    document.getElementById(id).accept = cityId;
    option.setAttribute("value", name);
    document.getElementById("cities").appendChild(option);
}

function appendStations(name, stationId) {
    option = document.createElement("option");
    document.getElementById(id).accept = stationId;
    option.setAttribute("value", name);
    document.getElementById("stations").appendChild(option);
}

function appendRoutes(name_from, name_to, stationId) {
    option = document.createElement("option");
    document.getElementById(id).accept = stationId;
    option.setAttribute("value", name_from + " - " + name_to);
    document.getElementById("routes").appendChild(option);
}

function appendBuses(name, busId) {
    option = document.createElement("option");
    option.setAttribute("value", name);
    option.setAttribute("id", name);
    option.setAttribute("label", busId);
    document.getElementById("buses").appendChild(option);
}

function clearTable() {
    document.getElementById("cities").innerHTML = "";
}


function parseMessages(responseXML) {

    // no matches returned
    if (responseXML == null) {
        return false;
    } else {

        var cities = responseXML.getElementsByTagName("cities")[0];
        var actionTag = responseXML.getElementsByTagName("action")[0];
        var action = actionTag.getElementsByTagName("name")[0];
        if (action == "autocomplete") {
        }
        if (cities.childNodes.length > 0) {
            for (loop = 0; loop < cities.childNodes.length; loop++) {
                var city = cities.childNodes[loop + 1];
                var name = city.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                var cityId = city.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                appendCity(name, cityId);
            }
        }
        if (action == "trips") {
            buildTripTable(responseXML);
        }
    }
}

function buildTripTable(responseXML) {
    var trips = responseXML.getElementsByTagName("trips")[0];
    document.getElementById("tripsTable").innerHTML = "";
    if (trips.childNodes.length > 0) {
        for (j = 0; j < trips.childNodes.length; j++) {
            divRow = document.createElement("div");
            divRow.setAttribute("class", "row");
            divRow.setAttribute("onClick", "chooseTrip(this.id)");
            divColDateFrom = document.createElement("div");
            divColDateFrom.setAttribute("class", "col date");
            divRow.appendChild(divColDateFrom);
            bColDateFrom = document.createElement("b");
            h3ColDateFrom = document.createElement("h3");
            divColDateFrom.appendChild(bColDateFrom);
            divColDateFrom.appendChild(h3ColDateFrom);
            divColCFrom = document.createElement("div");
            divColCFrom.setAttribute("class", "col c");
            divRow.appendChild(divColCFrom);
            h2ColCFrom = document.createElement("h2");
            divColCFrom.appendChild(h2ColCFrom);
            divColDateTo = document.createElement("div");
            divColDateTo.setAttribute("class", "col date");
            divRow.appendChild(divColDateTo);
            bColDateTo = document.createElement("b");
            h3ColDateTo = document.createElement("h3");
            divColDateTo.appendChild(bColDateTo);
            divColDateTo.appendChild(h3ColDateTo);
            divColCTo = document.createElement("div");
            divColCTo.setAttribute("class", "col c");
            divRow.appendChild(divColCTo);
            h2ColCTo = document.createElement("h2");
            divColCTo.appendChild(h2ColCTo);
            divColTimeInWay = document.createElement("div");
            divColTimeInWay.setAttribute("class", "col time_in_way");
            h2ColTimeInWay = document.createElement("h2");
            divColTimeInWay.appendChild(h2ColTimeInWay);
            document.getElementById("tripsTable").appendChild(divRow);
            //divRow.appendChild(divColTimeInWay);

            divColCCost = document.createElement("div");
            divColCCost.setAttribute("class", "col c");
            h2ColCCost = document.createElement("h2");
            bColCCost = document.createElement("b");
            divColCCost.appendChild(h2ColCCost);
            h2ColCCost.appendChild(bColCCost);
            divRow.appendChild(divColCCost);

            trip = trips.childNodes[j];

            id = trip.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            divRow.setAttribute("id", id);

            h3ColDateFrom.innerHTML = trip.getElementsByTagName("datefrom")[0].childNodes[0].nodeValue;
            h2ColCFrom.innerHTML = trip.getElementsByTagName("from")[0].childNodes[0].nodeValue;
            h3ColDateTo.innerHTML = trip.getElementsByTagName("dateto")[0].childNodes[0].nodeValue;
            h2ColCTo.innerHTML = trip.getElementsByTagName("to")[0].childNodes[0].nodeValue;
            bColDateFrom.innerHTML = trip.getElementsByTagName("datefromtime")[0].childNodes[0].nodeValue;
            bColDateTo.innerHTML = trip.getElementsByTagName("datetotime")[0].childNodes[0].nodeValue;
            bColCCost.innerHTML = "100 €";
        }
    }
    else{
        field = document.getElementById("tripsTable");
        block = document.createElement("div");
        block.innerHTML = "По вашему запросу билетов не найдено";
        block.setAttribute("class", "no_trips");
        field.appendChild(block);
    }
}

function chooseTrip(id) {
    document.getElementById("tripId").value = id;
    document.getElementById("sendIdButton").click();
}

var previous, element;

function choosePlace(element) {
    if (previous != null)
        previous.style.backgroundColor = "#98FB98";
    element.style.backgroundColor = "green";
    previous = element;
    document.getElementById("placeCost").innerHTML = ": " + element.title;
    document.getElementById("num").innerHTML = ": " + element.innerHTML;
}

function goToStep3() {
    document.getElementById("placeId").value = previous.id;
    document.getElementById("sendIdButton").click();
}


function goToPay() {
    desc = "";
    desc += document.getElementById("departureCity").innerHTML + ", ";
    desc += document.getElementById("departureStation").innerHTML + ", ";
    desc += document.getElementById("departureDate").innerHTML + ", ";
    desc += document.getElementById("departureTime").innerHTML + ", ";
    desc += document.getElementById("arrivalCity").innerHTML + ", ";
    desc += document.getElementById("arrivalStation").innerHTML + ", ";
    desc += document.getElementById("arrivalDate").innerHTML + ", ";
    desc += document.getElementById("arrivalTime").innerHTML + ", ";
    desc += document.getElementById("price").innerHTML + ", ";
    document.getElementById("ik_desc").value = desc;
    sendPaymentToServer();
}

function sendPaymentToServer() {
    ik_co_id = document.getElementById("ik_co_id").value;
    ik_pm_no = document.getElementById("ik_pm_no").value;
    ik_am = document.getElementById("ik_am").value;
    ik_cur = document.getElementById("ik_cur").value;
    ik_desc = document.getElementById("ik_desc").value;
    phone = document.getElementById("phone").value;
    email = document.getElementById("email").value;
    cost = document.getElementById("price").innerHTML;
    name = document.getElementById("surname").value;
    surname = document.getElementById("name").value;
    var str = "ik_co_id=" + ik_co_id + "&ik_pm_no=" + ik_pm_no + "&ik_am=" + ik_am + "&ik_cur=" + ik_cur + "&ik_desc=" + ik_desc + "&cost=" + cost + "&name=" + name + " " + surname;
    if (phone.length > 0) {
        str += "&phone=" + phone;
        action = 1;
    }
    else if (email.length > 0) {
        str += "&email=" + email;
        action = 2;
    }
    else if (phone.length > 0 && email.length > 0)
        action = 3;
    else action = 0;
    str += "&action=" + action;
    req = initRequest();
    req.open("POST", "/newPayment", true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callbackPay;
    req.send(str);
}

function callbackPay() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            goToCassa(req.responseXML);
        }
    }
}

function goToCassa(responseXML) {
    id = responseXML.getElementsByTagName("ids")[0].childNodes[0].getElementsByTagName("Id")[0].childNodes[0].nodeValue;
    document.getElementById("ik_pm_no").value = id;
    document.getElementById("pay").click();
}

function createTicket(seat_id){
    name = document.getElementById("name").value;
    surname = document.getElementById("surname").value;
    data = "seat_id=" + seat_id + "&client=" + surname + " " + name;
    req = initRequest();
    req.open("POST", "/tickets_manager", true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.onreadystatechange = callbackTicket;
    req.send(data);
}

function callbackTicket(){
    if (req.readyState == 4) {
        if (req.status == 200) {
            responseXML = req.responseXML;
            var tickets = responseXML.getElementsByTagName("tickets")[0];
            var ticket = tickets.childNodes[0];
            var id = ticket.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            document.getElementById("ik_x_id").value = id;
            goToPay();
        }
    }
}