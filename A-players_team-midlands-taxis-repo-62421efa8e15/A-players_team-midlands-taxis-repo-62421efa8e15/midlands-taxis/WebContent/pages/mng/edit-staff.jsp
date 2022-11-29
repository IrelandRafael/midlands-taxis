<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
	<title>Edit staff</title>
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
		
			<div class="container-title">Add/Edit staff</div>
			
			<div class="invalid-msg">
				<c:out value="${invalidInputMsg}"/>
			</div>
		
			<form action="${pageContext.request.contextPath}/${staffMember.staffId > 0 ? 'edit-staff' : 'create-staff'}" method="POST">
				<input type="hidden" name="staff_id" value="${staffMember.staffId}">
				<div>
					<span class="label">First name</span>
		         	<input class="form-input" type="text" name="first_name" value="${staffMember.firstName}">
	         	</div>
	         	<div>
					<span class="label">Last name</span>
		         	<input class="form-input" type="text" name="last_name" value="${staffMember.lastName}">
	         	</div>
	         	<div>
					<span class="label">Username</span>
		         	<input class="form-input" type="text" name="user_name" value="${staffMember.username}">
	         	</div>
	         	<div>
					<span class="label">Email</span>
		         	<input class="form-input" type="text" name="email" value="${staffMember.email}">
	         	</div>
	         	<div>
		         	<span class="label">Category</span>
		         	<select name="category">
		         		<option value="">Select</option>
					  	<option value="MANAGEMENT" ${staffMember.checkCategory("MANAGEMENT") ? 'selected="selected"' : ''}>Management</option>
					  	<option value="FRONT_DESK" ${staffMember.checkCategory("FRONT_DESK") ? 'selected="selected"' : ''}>Front desk</option>
					  	<option value="DRIVER" ${staffMember.checkCategory("DRIVER") ? 'selected="selected"' : ''}>Driver</option>
					</select>
	         	</div>
	         	
	         	<div ${staffMember.staffId > 0 ? 'hidden' : ''}>
		         	<span class="label">Password</span>
		         	<input class="form-input" type="password" name="password">
	         	</div>
	         	
	         	<div ${staffMember.staffId > 0 ? 'hidden' : ''}>
		         	<span class="label">Confirm password</span>
		         	<input class="form-input" type="password" name="confirm_password">
	         	</div>
				
				<div style="padding-left:170px;">
					<input type="submit" value="${staffMember.staffId > 0 ? 'Update' : 'Create'}"/>
					<a href="${pageContext.request.contextPath}/manage-staff">Cancel</a>
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