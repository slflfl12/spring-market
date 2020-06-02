<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div class="container-wrapper">
	<div class="container">
		<h1>Product Detail</h1>
		<p class="lead">Here is the detail information of the product!</p>

		<div class="row">
			<div class="col-md-6">
				<img src="<c:url value="/resources/images/${product.imageFilename}" />" alt="image"
					style="width: 80%" />

			</div>

			<div class="col-md-6">
				<h3>${product.name}</h3>
				<p>
					<strong> Description: </strong> ${product.description}
				</p>
				<p>
					<strong> Manufacturer: </strong> ${product.manufacturer}
				</p>
				<p>
					<strong> Category: </strong> ${product.category}
				</p>
				<p>
					<strong> 가격: </strong> ${product.price}만원
				</p>
				<br>
			</div>
		</div>
	</div>
</div>