<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>

</head>
<body>
	homePage

	<form name='login' action="logout" method='POST'>
		<table>
			<tr>
				<td>${token}</td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="Logout" /></td>
			</tr>
		</table>
	</form>
</body>
</html>