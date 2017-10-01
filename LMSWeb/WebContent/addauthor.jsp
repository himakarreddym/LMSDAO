<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Book> books = service.readBooks();
%>
<div class="container">
	<h2>Add New Author</h2>
	<form method="post" action="addAuthor">
	${statusMessage}
		<br/>Enter Author Name: <input type="text" name="authorName"><br /><br /><br />
		 <br/>
		
		<%-- Div class for select tag --%> 
		
		<div class="form-group">
		<label for="sel1">Select Books from list Below: </label>
		<select class="form-control" id="sel1" multiple="multiple" size="10" name="bookIds">
			<%for(Book b: books) {%>
			<option value=<%=b.getBookId()%>><%=b.getTitle() %></option>
			<%} %>
		</select>
		 </div>
		 
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Author</button>
	</form>
</div>