<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
%>
<div class="container"style="margin-top: 45px;">
	<h3 style="padding-left: 40%;">Add New Publisher</h3> <br>
	<form method="post" action="addPublisher"style="text-align:center;padding-left: 15%;">
	${statusMessage}
		<table class="table table-striped" style="width: 80%">
		<tr>
			<td>
				Enter Publisher Name
				</td>
			<td>
			<input type="text" name="publisherName">
			</td>
		</tr>
		<tr>	
			<td>
			Enter Publisher Address
			</td>
			<td>
			<input type="text" name="publisherAddress">
			</td>
		</tr>
		<tr>
			<td>
			Enter Publisher Phone
			</td>
			<td>
			 <input type="text" name="publisherPhone">
			</td>
		</tr>
		
		
       </table> 
		<button type="submit" class="btn btn-primary btn-md"style="margin-right: 15%">Save Publisher</button>
	</form>
</div>