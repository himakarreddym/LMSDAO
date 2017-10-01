<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Book> books = service.readBooks();
%>
<div class="container">
	<h2>Add New Genre</h2>
	<form method="post" action="addGenre">
	${statusMessage}
		<br/>Enter Genre Name: <input type="text" name="genreName"><br /><br /><br />
		 <br/>
		
		<%-- Div class for select tag --%> 
		
		<div class="form-group">
		<label for="sel1">Select Books from list Below: </label>
		<select class="form-control" id="sel1" multiple="multiple" size="10" name="bookIds">
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