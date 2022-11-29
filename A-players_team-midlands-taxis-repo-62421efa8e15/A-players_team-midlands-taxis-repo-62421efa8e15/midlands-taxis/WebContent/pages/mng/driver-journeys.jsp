<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Driver journeys</title>
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
		
		<div class="staff-mng-menu">
			<div>
				<a href="${pageContext.request.contextPath}/manage-staff">Management Staff</a>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/pages/mng/manage-fares.jsp">Manage Fares</a>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/taxi-bookings">View taxi bookings</a>
			</div>
			<div class="selected">
				<a href="${pageContext.request.contextPath}/driver-journeys">View driver journeys</a>
			</div>					
		</div>
	
		<div class="page-container">
			<div class="container-title">Staff list</div>
		
			<table class="list">
				<thead>
					<tr>
						<th>Driver first name</th>
						<th>Driver last name</th>
						<th>Booking no.</th>
						<th>Journey status</th>
						<th>Journey start</th>
						<th>Journey end</th>
					</tr>
				</thead>
	   			<c:forEach var="journey" items="${driverJourneys}" >
	   				<tr>
	   					<td>${journey.driverFirstName}</td>
	   					<td>${journey.driverLastName}</td>
	   					<td>${journey.bookingNo}</td>
	   					<td>${journey.journeyStatus}</td>
	   					<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${journey.journeyStartedTime}"/></td>
	   					<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${journey.journeyCompletedTime}"/></td>
	   				</tr>
				</c:forEach>
			</table>
		</div>		
	</div>
	<div id="footer-container" style="width:100%; height:50px; text-align:center;border-top: 1px gray solid;padding-top:10px;">
		<span>Copyright 2019 Midlands Taxis</span>
	</div>
</div>
</body>
</html>