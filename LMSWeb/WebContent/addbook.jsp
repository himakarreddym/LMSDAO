<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Author> authors = service.readAuthors();
List<Publisher> publishers =service.readPublishers();

%>
<div class="container">
	<h2>Add New Book</h2>
	<form method="post" action="addBook">
	${statusMessage}
		<br/>Enter Book Title: <input type="text" name="bookName"><br /><br /><br />
		 <br/>
		
		<%-- Div class for select tag --%> 
		<div class="form-group" align="center" style="width:25%;float:left;padding-right:30px;">
		<label for="sel1">Select Publisher from list Below: </label>
		<select class="form-control" id="sel1" name="publisherId">
			<%for(Publisher p: publishers) {%>
			<option value=<%=p.getPublisherId()%>><%=p.getPublisherName() %></option>
			<%} %>
		</select>
		 </div>
		
		
		<div class="form-group" align="center" style="width:25%;float:left;padding-right:10px;" >
		<label for="sel1">Select Books from list Below: </label>
		<select class="form-control" id="sel1" multiple="multiple" size="10" name="authorIds">
			<%for(Author a: authors) {%>
			<option value=<%=a.getAuthorId()%>><%=a.getAuthorName() %></option>
			<%} %>
		</select>
		 </div>
		 
		<button type="submit" class="btn btn-primary btn-md">Save Author</button>
	</form>
</div>