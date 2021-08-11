<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Category</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<a class="btn btn-warning mt-3 float-right" href="/products/new">Create
			new Product?</a>
		<h1 class="text-info">New Category</h1>
		<br>
		<form:form action="/categories/new" method="post"
			modelAttribute="category">
			<div class="form-group row">
				<form:label path="name" class="col-sm-2 col-form-label">Name:</form:label>
				<div class="col-sm-6">
					<form:input path="name" class=" form-control" />
					<br> <small class="text-danger"><form:errors
							path="name" /> </small>
				</div>
			</div>
			<div class="form-group row">
				<input class="mr-5 mt-3 btn btn-success" type="submit"
					value="Create" />
			</div>
		</form:form>
	</div>
</body>
</html>