<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Reporting Engine</title>
</head>
<body>

 <div align="center">
  <h1 style="margin-left: -450px; margin-bottom: 39px; margin-top: 50px;">Reporting Engine</h1>
  <form:form method="POST" action="/ReportingEngine/scheduleJob">
   <table>
    <tr>
        <td><form:label path="sqlQuery" >SQL Query</form:label></td>
        <td><form:input path="sqlQuery" /></td>
    </tr>
    <tr>
        <td><form:label path="emailId" >Email</form:label></td>
        <td><form:input path="emailId" /></td>
    </tr>
    <tr>
        <td width="180px"><form:label path="frequency" >Frequency</form:label></td>
        <td width="600px">
        <form:radiobutton path="frequency" value="1" onclick="showNone()"/>Daily 
        <form:radiobutton path="frequency" value="2" onclick="showDaysOfWeek()"/>Weekly
        <form:radiobutton path="frequency" value="3" onclick="showDateOfMonth()"/>Monthly
        <form:radiobutton path="frequency" value="4" onclick="showDateAndMonth()"/>Yearly
        </td>
    </tr>
    <tr>
        <td><form:label path="time" >Time (HH-MM)</form:label></td>
        <td><form:input path="time" /></td>
    </tr>
    <tr id="daysOfWeekDiv" style="display: none;">
        <td><form:label path="frequency" >Days</form:label></td>
        <td>
        <form:checkbox path="daysOfWeek" value="1" />Sunday
        <form:checkbox path="daysOfWeek" value="2" />Monday
        <form:checkbox path="daysOfWeek" value="3" />Tuesday
        <form:checkbox path="daysOfWeek" value="4" />Wednesday
        <form:checkbox path="daysOfWeek" value="5" />Thursday
        <form:checkbox path="daysOfWeek" value="6" />Friday
        <form:checkbox path="daysOfWeek" value="7" />Saturday

        </td>
    </tr>
    <tr id="dateOfMonthDiv" style="display: none;">
        <td><form:label path="dateOfMonth" >Date of Month (DD)</form:label></td>
        <td><form:input path="dateOfMonth" /></td>
    </tr>
    <tr id="dateAndMonthDiv" style="display: none;">
        <td><form:label path="dateAndMonth" >Date & Month (DD-MM)</form:label></td>
        <td><form:input path="dateAndMonth" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</div> 

<h2><font color='red' size='3'>${msg}</font></h2>

</body>
<script type="text/javascript">
var daysOfWeek = document.getElementById('daysOfWeekDiv');
var dateOfMonth = document.getElementById('dateOfMonthDiv');
var dateAndMonth = document.getElementById('dateAndMonthDiv');
function showDaysOfWeek(){
	daysOfWeek.style.display = "";
	dateOfMonth.style.display = "none";
	dateAndMonth.style.display = "none";
}
function showDateOfMonth(){
	daysOfWeek.style.display = "none";
    dateOfMonth.style.display = "";
    dateAndMonth.style.display = "none";
}
function showDateAndMonth(){
	daysOfWeek.style.display = "none";
    dateOfMonth.style.display = "none";
    dateAndMonth.style.display = "";
}
function showNone(){
    daysOfWeek.style.display = "none";
    dateOfMonth.style.display = "none";
    dateAndMonth.style.display = "none";
}
</script>

</html>