<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>

<div class="container">
	<h2>Add New Borrower</h2>
	<form method="post" action="addBorrower">
	${statusMessage}
		<br/>Enter Borrower Name: <input type="text" name="borrowerName"><br />
		<br/>Enter Borrower Address: <input type="text" name="borrowerAddress"><br />
		<br/>Enter Borrower Phone: <input type="text" name="borrowerPhone"><br />
		 <br/>
		<button type="submit" class="btn btn-primary btn-md">Save Borrower</button>
	</form>
</div>