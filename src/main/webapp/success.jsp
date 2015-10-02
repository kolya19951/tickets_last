<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>


<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="stylesheet.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Успех</title>
</head>
<body onload="window.print()">
<table align="center" border="1px" cellpadding="2" cellspacing="0" height="250px" width="900px">
    <tbody>
    <tr>
        <td colspan="3">
            <center>Посадочный документ</center>
        </td>
    </tr>
    <tr>
        <td>
            ФИО:
        </td>
        <td colspan="2">
            ${Client}
        </td>
    </tr>
    <tr>
        <td>
            Рейс:
        </td>
        <td height="60px">
            ${From}
        </td>
        <td height="60px">
            ${To}
        </td>
    </tr>
    <tr>
        <td>
            Номер рейса:
        </td>
        <td colspan="3">
            ${ID}
        </td>
    </tr>
    <tr>
        <td>
            Время отправления:
        </td>
        <td colspan="3">
            ${From_Date}
        </td>
    </tr>
    <tr>
        <td>
            Время прибытия:
        </td>
        <td colspan="3">
            ${To_Date}
        </td>
    </tr>
    <tr>
        <td>
            Номер места:
        </td>
        <td colspan="2">
            ${Seat_Num}
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
