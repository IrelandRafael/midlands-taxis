<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Manage bookings</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body style="width:100%; height:100%">

<div style="width:70%; margin: 0 auto">
	<div class="header-container">
		<img src="${pageContext.request.contextPath}/imgs/taxi-logo.jpg" height="40">
		<span class="header-title" style="margin-left:15px;">Midlands</span>
		<span class="header-title" style="color: orange">Taxis</span>
		<div class="logout">
			<a href="${pageContext.request.contextPath}/staff-login">Logout</a>
		</div>
	</div>
	<div class="main-container">
		<div class="page-container">
		
			<div class="container-title">Bookings list</div>
		
			<table class="list">
				<thead>
					<tr>
						<th>Booking no.</th>
						<th>Status</th>
						<th>Pickup location</th>
						<th>Pickup time</th>
						<th>Drop location</th>
						<th>Driver</th>
						<th class="actions">Actions</th>
					</tr>
				</thead>
	   			<c:forEach var="booking" items="${bookings}" >
	   				<tr>
	   					<td>${booking.bookingNo}</td>
	   					<td>${booking.status}</td>
	   					<td>${booking.pickupLocation}</td>
	   					<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${booking.pickupTime}"/></td>
	   					<td>${booking.dropLocation}</td>
	   					<td>${booking.driverName}</td>
	   					<td>
	   						<a href="#">View</a> 
	   					</td>
	   				</tr>
				</c:forEach>
			</table>
			<div class="button-panel">
				<button onclick="window.location.href='${pageContext.request.contextPath}/add-booking'">Add new booking</button>
			</div>
		</div>					
	</div>
	<div id="footer-container" style="width:100%; height:50px; text-align:center;border-top: 1px gray solid;padding-top:10px;">
		<span>Copyright 2019 Midlands Taxis</span>
	</div>
</div>
</body>
</html>