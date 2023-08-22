<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body onload='document.loginForm.username.focus();'>
	<h1>Spring Security - Login Form</h1>

	<c:if test="${not empty error}">
		<div style="color: red; font-weight: bold; margin: 30px 0px;">${error}</div>
	</c:if>
	<c:if test="${not empty message}">
		<div style="color: green; font-weight: bold; margin: 30px 0px;">${message}</div>
	</c:if>

	<form name='login' action="login" method='POST'>
		<table>
			<tr>
				<td>UserName:</td>
				<td><input type='text' name='username' value='admin@demo.in'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' value="d#demo9" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		</table>
	</form>

	<form>
		<a href="oauth-login/google/initiate" class="social_box_google"> <span
			class="icon"><i class="fa fa-google-plus"></i></span> <span
			class="icon_title">Login via Google</span>
		</a>
	</form>
</body>

<style>
.social_box_google {
	background-image: ../images/google.png
}
</style>