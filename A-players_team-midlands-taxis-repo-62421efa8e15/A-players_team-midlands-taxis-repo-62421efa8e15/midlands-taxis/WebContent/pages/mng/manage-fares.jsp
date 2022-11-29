<!DOCTYPE html>
<html>
<head>
<style>

  td{
    text-align:center;
  }
</style>

	<title>Manage fares</title>
	<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>

<body style="width:100%; height:100%">

<div style="width:70%; margin: 0 auto">
	<div class="header-container">
		<img src="../../imgs/taxi-logo.jpg" height="40">
		<span class="header-title" style="margin-left:15px;">Midlands</span>
		<span class="header-title" style="color: orange">Taxis</span>
		<div class="logout">
			<a href="${pageContext.request.contextPath}/staff-login">Logout</a>
		</div>
	</div>
	<div id="main-container" style="width:100%; height:400px">

		<div class="staff-mng-menu">
			<div>
				<a href="${pageContext.request.contextPath}/manage-staff">Management Staff</a>
			</div>
			<div class="selected">
				<a href="${pageContext.request.contextPath}/pages/mng/manage-fares.jsp">Manage Fares</a>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/taxi-bookings">View taxi bookings</a>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/driver-journeys">View driver journeys</a>
			</div>					
		</div>

		<div class="page-container">
			<table style= "width:100%;" border="1">

<tr>

<th> Taxi Rate </th>
<th> Travel Time </th>
<th> Unit </th>
<th> Price (&#8364;) </th>

</tr>

<tr>
  <td> 1 </td>
  <td> 0 - 15  </td>
  <td> mins </td>
  <td> 5.0 </td>
</tr>

<tr>
  <td> 2 </td>
  <td> 16 - 30  </td>
  <td> mins </td>
  <td> 7.5 </td>
</tr>

<tr>
  <td> 3</td>
  <td> 31 - 45 </td>
  <td> mins </td>
  <td> 10.0 </td>
</tr>

<tr>
  <td> 4 </td>
  <td> 46 - 1   </td>
  <td> Hour </td>
  <td> 12.5 </td>
</tr>

<tr>
  <td> 5 </td>
  <td> 1  - 1:15  </td>
  <td> Hour </td>
  <td> 15.0 </td>
</tr>

<tr>
  <td> 6 </td>
  <td> 1:15 - 1:30  </td>
  <td> Hour </td>
  <td> 17.5 </td>
</tr>

<tr>
  <td> 7 </td>
  <td> 1:30 - 1:45  </td>
  <td> Hour </td>
  <td> 20.0 </td>
</tr>

<tr>
  <td> 8 </td>
  <td> 1:45 - 2  </td>
  <td> Hour </td>
  <td> 22.5 </td>
</tr>

<tr>
  <td> 9 </td>
  <td> 2 - 2:15  </td>
  <td> Hour </td>
  <td> 25.0 </td>
</tr>

<tr>
  <td> 10 </td>
  <td> 2:15 - 2:30  </td>
  <td> Hour </td>
  <td> 27.5 </td>
</tr>

<tr>
  <td> 11 </td>
  <td> 2:30 - 2:45  </td>
  <td> Hour </td>
  <td> 30.0 </td>
</tr>

<tr>
  <td> 12 </td>
  <td> 2:45 - 3  </td>
  <td> Hour </td>
  <td> 32.5 </td>
</tr>

<tr>
  <td> 13 </td>
  <td> Over 3  </td>
  <td> Hour </td>
  <td> 50.0 </td>
</tr>

</table>
		</div>
	</div>
	<div id="footer-container" style="width:100%; height:50px; text-align:center;border-top: 1px gray solid;padding-top:10px;">
		<span>Copyright 2019 Midlands Taxis</span>
	</div>
</div>
</body>
</html>
