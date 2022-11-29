<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
	<title>Add new booking</title>
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
		
		<div class="edit-form">
		
			<div class="container-title">Add new booking</div>
			
			<div class="invalid-msg">
				<c:out value="${invalidInputMsg}"/>
			</div>
		
			<form action="${pageContext.request.contextPath}/add-booking" method="POST">
				<div>
					<span class="label">Pickup location *</span>
		         	<input class="form-input" type="text" name="pickup_location" value="${booking.pickupLocation}">
	         	</div>
	         	
	         	<div>
					<span class="label">Pickup time</span>
		         	<input class="form-input" type="text" name="pickup_time" value="${booking.pickupTime}" placeholder="e.g. 01/12/2019 07:00">
	         	</div>
	         	
	         	<div>
					<span class="label">Drop location</span>
		         	<input class="form-input" type="text" name="drop_location" value="${booking.dropLocation}">
	         	</div>
	         	
	         	<div>
		         	<span class="label">Assign a driver *</span>
		         	<select name="driver">
		         		<option value="">Select</option>
		         		<c:forEach var="driver" items="${availableDrivers}" >
							<option value="${driver.driverId}" ${booking.driverId == driver.driverId ? 'selected="selected"' : ''}>${driver.fullName}</option>	   				
						</c:forEach>
					</select>
	         	</div>
				
				<div style="padding-left:170px;">
					<input type="submit" value="Save"/>
					<a href="${pageContext.request.contextPath}/bookings">Cancel</a>
				</div>	         
	      	</form>
			
		</div>			
	</div>
	<div id="footer-container" style="width:100%; height:50px; text-align:center;border-top: 1px gray solid;padding-top:10px;">
		<span>Copyright 2019 Midlands Taxis</span>
	</div>
</div>
</body>
</html>