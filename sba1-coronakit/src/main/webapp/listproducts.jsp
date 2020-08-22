<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<form action="list" method="post"></form>
	<c:choose>
		<c:when test="${products == null || products.isEmpty() }">
			<p>No Products Available</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Id</th>
					<th>ProductName</th>
					<th>Cost</th>
					<th>ProductDescription</th>
				</tr>
				<c:forEach items="${products }" var="product">
					<tr>
						<td>${product.Id }</td>
						<td>${product.productName }</td>
						<td>${product.cost }</td>
						<td>${product.productDescription }</td>
						<td>
							<a href="deleteProduct?id=${loan.Id }">DELETE</a>
							<span>|</span>
							<a href="editProduct?id=${loan.Id }">EDIT</a>
						</td>
					</tr> 
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>