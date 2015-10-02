<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Коля
  Date: 15.09.2015
  Time: 0:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Step 3</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
  <link rel="stylesheet" type="text/css" href="css/reset.css">
  <link rel="stylesheet" type="text/css" href="css/fonts.css">
  <script type="text/javascript" src="javascript.js"></script>
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
    <div class="bus_text"><p>${Line10}</p>
      <p>${Line11}</p>
      <p>${Line12}</p>
      <p>${Line13}</p>
      <p>${Line14}</p>
      <p>${Line15} <a href="/info">${Href1}</a></p>
      <br>
      <p>Bus Tickets</p>
    </div>
  </div>
  <div class="step" style="background: #1DCC99;">
    <div class="step_row">
    <div class="step_one" style="background: #0294B5;">
      <center><p>1. ${Step1}</p></center>
    </div>
    <div class="step_two" style="background: #0294B5;">
      <center><p>2. ${Step2}</p></center>
    </div>
    <div class="step_three">
      <center><p>3. ${Step3}</p></center>
    </div>
    </div>
  </div>
  <div class="payments">
    <div class="form_pay">
      <div id="pay_content">
        <h1>${Line}</h1>
        <br>
        <hr>
        <div>
          <div class="pay_row">
            <div class="name_cell">
              <h2>${Surname}</h2>
            </div>
            <div class="name_cell">
              <h2>${Name}</h2>
            </div>
          </div>
          <div class="pay_row">
            <div class="pay_cell">
              <div class="input_pay">
                <input class="input_design" type="text" required maxlength="32" id="surname"/>
              </div>
            </div>
            <div class="pay_cell">
              <div class="input_pay">
                <input class="input_design" type="text" required maxlength="32" id="name"/>
              </div>
            </div>
          </div>
          <br>
          <hr>
          <div class="pay_row">
            <div class="name_cell">
              <div class="name_pay">
                <h2>${Phone}</h2>
              </div>
            </div>
            <div class="pay_cell">
              <div class="input_pay">
              </div>
            </div>
          </div>
          <div class="pay_row">
            <div class="pay_cell">
              <div class="input_pay">
                <input type="text" placeholder="" required maxlength="32" class="input_design" id="phone" pattern="^[0-9]+$"/>
              </div>
            </div>
            <div class="pay_cell_text">
              <div class="pay_text">
                <h3>* ${Help1}.</h3>
              </div>
            </div>
          </div>
          <div class="pay_row">
            <div class="name_cell">
              <div class="name_pay">
                <h2>E-mail</h2>
              </div>
            </div>
            <div class="pay_cell">
              <div class="input_pay">
              </div>
            </div>
          </div>
          <div class="pay_row">
            <div class="pay_cell">
              <div class="input_pay">
                <input type="email" placeholder="" required maxlength="32" class="input_design" id="email"/>
              </div>
            </div>
            <div class="pay_cell_text">
              <div class="pay_text">
                <h3>* ${Help2}.</h3>
              </div>
            </div>
          </div>
          <div class="pay_row">
            <div class="pay_submit">
              <%
                long id = (Long)session.getAttribute("reserved_seat_id");
              %>
              <input type="submit" value="${Payments}" class="pay_button" onclick="
              createTicket(<%=id%>);
              ">
            </div>
          </div>
        </div>
      </div>

    </div>
    <div class="detals">
      <div id="detals_content">
        <h1>${Detals}</h1>
        <div class="detals_text"><b>${From}</b></div>
      <div id="departureCity" class="detals_text"><%=request.getAttribute("departureCity")%></div>
      <div id="departureStation" class="detals_text"><%=request.getAttribute("departureStation")%></div>
        <div class="detals_text"><b>${Date}</b></div>
      <div id="departureDate" class="detals_text"><%=request.getAttribute("departureDate")%></div>
      <div id="departureTime" class="detals_text"><%=request.getAttribute("departureTime")%></div>
        <div class="detals_text"><b>${To}</b></div>
      <div id="arrivalCity" class="detals_text"><%=request.getAttribute("arrivalCity")%></div>
      <div id="arrivalStation" class="detals_text"><%=request.getAttribute("arrivalStation")%></div>
        <div class="detals_text"><b>${Date}</b></div>
      <div id="arrivalDate" class="detals_text"><%=request.getAttribute("arrivalDate")%></div>
      <div id="arrivalTime" class="detals_text"><%=request.getAttribute("arrivalTime")%></div>
        <div class="detals_text"><b>${Price}</b></div>
      <div id="price" class="detals_text"><%=request.getAttribute("price")%></div>
    </div>
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
<form id="payment" name="payment" method="post" action="https://sci.interkassa.com/" enctype="utf-8" style="display: none">
  <input type="hidden" name="ik_co_id" id="ik_co_id" value="55f353513b1eaff4408b4567" />
  <input type="hidden" name="ik_pm_no" id="ik_pm_no" value="ID_4233" />
  <input type="hidden" name="ik_am" id="ik_am" value="<%=request.getAttribute("price")%>" />
  <input type="hidden" name="ik_cur" id="ik_cur" value="UAH" />
  <input type="hidden" name="ik_desc" id="ik_desc" value="Event Description" />
  <input type="hidden" name="ik_x_id" id="ik_x_id" value="" />
  <input type="hidden" name="ik_suc_u" id="ik_suc_u" value="" />
  <input type="submit" value="Pay" id="pay">
</form>
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
