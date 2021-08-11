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
<title>Show Product</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/mystyle.css">

</head>
<body>
	<div class="container">
		<div class="header mb-5 mt-5">
			<h1 class="text-info">
				<c:out value="${product.name}"></c:out>
			</h1>
		</div>
		<div class="content">
			<div class="leftcontent">
				<h3 class="mb-3">Categories</h3>
				<ul>
					<c:forEach items="${product.categories}" var="cat">
						<li><c:out value="${cat.name}" /></li>
					</c:forEach>
				</ul>
			</div>
			<div class="rightcontent">
				<h5 >Add Category:</h5>
				<form class="ml-5 mt-4" action="/products/{id}/addcategory" method="post">
					<input type="hidden" value="${product.id}" name="product_id">
					<div class="form-group row">
						<select name="category_id">
							<c:forEach items="${prodCategories}" var="category">
								<option value="${category.id}"><c:out
										value="${category.name}" /></option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group row">
						<input class="mr-5 mt-3 btn btn-success" type="submit" value="add" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>