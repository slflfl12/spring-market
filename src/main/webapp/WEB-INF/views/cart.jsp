<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/controller.js" /> "></script>

<div class="container-wrapper">
	<div class="container">
		<div class="jumbotron"> <!-- 전광판 같은 부분 -->
			<div class="container">
				<h2>Cart</h2>

				<p>All the selected products in your shopping cart</p>
			</div>
		</div>
		
            
		<section class="container" ng-app="cartApp"> <!-- 여기부터 angular -->
			<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')"> <!-- 컨트롤러로 cartCtrl을 사용하고 ng-init으로 initCartId를 호출(cartId는 Model에서 가져온 값) -->
				<a class="btn btn-warning pull-left" ng-click="clearCart()">
						<i class="fas fa-trash"></i> Clear Cart
				</a> 			

				<br/>
				
				<table  class="table table-hover">
					<tr>
						<th>Product</th>
						<th>Unit Price</th>
						<th>Quantity</th>
						<th>Total Price</th>
						<th>Action</th>
					</tr>
					
					<tr ng-repeat="item in cart.cartItems">
						<td>{{item.product.name}}</td>
						<td>{{item.product.price}}</td>
						<td>{{item.quantity}}</td>
						<td>{{item.totalPrice}}</td>
						<td><a  class="btn btn-danger" ng-click="removeFromCart(item.product.id)"> 
							<i class="fas fa-minus"></i>remove </a></td>
					</tr>
					
					<tr>
						<td></td>
						<td></td>
						<td>Grand Total</td>
						<td>{{calGrandTotal()}}</td>
						<td></td>
					</tr>
				</table>

				<a class="btn btn-info" href="<c:url value="/products" />" class="btn btn-default">Continue	Shopping</a>
			</div>
		</section>

	</div>
</div>