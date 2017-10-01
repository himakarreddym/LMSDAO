<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
%>
<div class="container">
	<h2>Add New Publisher</h2>
	<form method="post" action="addbranch">
	${statusMessage}
		<br/>Enter Library branch Name: <input type="text" name="branchName"><br />
		<br/>Enter Library branch Address: <input type="text" name="branchAddress"><br />
		 <br/>
		<button type="submit" class="btn btn-primary btn-md">Save Branch</button>
	</form>
</div>