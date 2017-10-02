<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Book> books = service.readBooks();
%>
<div class="container" style="text-align: center">
	<br><br><h2>Add New Library Branch</h2> <br><br>
	<form method="post" action="addbranch">
	${statusMessage}
	<div style="text-align: center" >
	<table class="table table-striped" style="text-align: center">
		<tr>
		<td>
		<label> Enter Library branch Name: </label>
		</td>
		<td>
		<input type="text" name="branchName">
		</td>
		</tr>
		<tr>
		<td>
		<label> Enter Library branch Address: </label>
		</td>
		<td>
		<input type="text" name="branchAddress">
		</td>
		</tr>
		 
	</table>
	<button type="submit" class="btn btn-primary btn-md" >Save Branch</button>
	</div>
	</form>
</div>