<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Book> books = service.readBooks();
%>
<div class="container" style="text-align: center;margin-top: 45px;">
	<h3>Add New Genre</h3> <br>
	<form method="post" action="addGenre">
	${statusMessage}
		<br/>Enter Genre Name: <input type="text" name="genreName"><br /><br />
		 <br/>
		
		<%-- Div class for select tag --%> 
		<label for="sel1">Select Books from list Below: </label>
		<div class="form-group" style="padding-left: 22%;">
		
		<select class="form-group" id="sel1" multiple="multiple" size="10" name="bookIds" style="width:80%;">
			<option value="">Select Book to associate</option>
			<%for(Book b: books) {%>
			<option value=<%=b.getBookId()%>><%=b.getTitle() %></option>
			<%} %>
		</select>
		 </div>
		 
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Genre</button>
	</form>
</div>