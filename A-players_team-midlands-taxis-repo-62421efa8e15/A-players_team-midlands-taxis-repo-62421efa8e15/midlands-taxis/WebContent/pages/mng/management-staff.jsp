<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
	<title>Manage staff</title>
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
			<div class="selected">
				<a href="${pageContext.request.contextPath}/manage-staff">Management Staff</a>
			</div>
			<div>
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
		
			<div class="container-title">Staff list</div>
		
			<table class="list">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Username</th>
						<th>Email</th>
						<th>Category</th>
						<th class="actions">Actions</th>
					</tr>
				</thead>
	   			<c:forEach var="staffMember" items="${staffMembers}" >
	   				<tr>
	   					<td>${staffMember.firstName}</td>
	   					<td>${staffMember.lastName}</td>
	   					<td>${staffMember.username}</td>
	   					<td>${staffMember.email}</td>
	   					<td>${staffMember.userCategory}</td>
	   					<td>
	   						<a href="${pageContext.request.contextPath}/edit-staff?staffId=${staffMember.staffId}">Edit</a> | 
	   						<a href="${pageContext.request.contextPath}/create-staff?staffId=${staffMember.staffId}" onclick="return confirm('Are you sure you want to continue')" >Delete</a>
	   					</td>
	   				</tr>
				</c:forEach>
			</table>
			<div class="button-panel">
				<button onclick="window.location.href='${pageContext.request.contextPath}/pages/mng/edit-staff.jsp'">Add new staff</button>
			</div>
		</div>			
	</div>
	<div id="footer-container" style="width:100%; height:50px; text-align:center;border-top: 1px gray solid;padding-top:10px;">
		<span>Copyright 2019 Midlands Taxis</span>
	</div>
</div>
</body>
</html>