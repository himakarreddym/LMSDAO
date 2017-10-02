<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>

<div class="container"style="margin-top: 45px;">
<h3 style="padding-left: 40%;">Add New Borrower</h3> <br>
	<form method="post" action="addBorrower"style="text-align:center;padding-left: 15%;">	
		${statusMessage}
		<table class="table table-striped" style="width: 80%">
		<tr>
			<td>
				Enter Borrower Name
				</td>
			<td>
			<input type="text" name="borrowerName">
			</td>
		</tr>
		<tr>	
			<td>
			Enter Borrower Address
			</td>
			<td>
			<input type="text" name="borrowerAddress">
			</td>
		</tr>
		<tr>
			<td>
			Enter Borrower Phone number
			</td>
			<td>
			 <input type="text" name="borrowerPhone">
			</td>
		</tr>
		
		
       </table> 
		<button type="submit" class="btn btn-primary btn-md"style="margin-right: 15%">Save Borrower</button>
	</form>
</div>