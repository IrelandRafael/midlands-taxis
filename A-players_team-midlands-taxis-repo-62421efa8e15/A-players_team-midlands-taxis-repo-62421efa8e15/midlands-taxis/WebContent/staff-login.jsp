<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
	<title>Login staff</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body style="width:100%; height:100%">

<div style="width:70%; margin: 0 auto">
	<div class="header-container">
		<img src="${pageContext.request.contextPath}/imgs/taxi-logo.jpg" height="40">
		<span class="header-title" style="margin-left:15px;">Midlands</span>
		<span class="header-title" style="color: orange">Taxis</span>
	</div>
	<div class="main-container">
		<div class="edit-form">	
			<div class="label">Staff login</div>
			<div class="invalid-msg">
				<c:out value="${invalidInputMsg}"/>
			</div>
			<form action="${pageContext.request.contextPath}/staff-login" method="POST">
				<div>
					<span class="label">Username</span>
		         	<input type="text" name="user_name" style="width:180px; height:25px;">
	         	</div>
	         	<div>
		         	<span class="label">Password</span>
		         	<input type="password" name="password" style="width:180px; height:25px;">
	         	</div>
				<div style="padding-left:170px;">
					<input type="submit" value="Login" style="width:120px; height:30px"/>
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