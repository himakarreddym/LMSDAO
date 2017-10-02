<%@page import="com.gcit.lms.entity.Genre"%>
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
List<Genre> genres =service.readGenres();

%>
<div class="container" style="text-align:center;margin-top:45px;">
	<h3>Add New Book</h3>
	<form method="post" action="addBook">
	${statusMessage}
		<br/>Enter Book Title: <input type="text" name="bookName"><br /><br /><br />
		 <br/>
		
		<%-- Div class for select tag --%> 
		<div class="form-group" align="center" style="width:30%;float:left;padding-right:30px;">
		<label for="sel1">Select Publisher from list Below: </label>
		<select class="form-control" id="sel1" name="publisherId">
			<%for(Publisher p: publishers) {%>
			<option value=<%=p.getPublisherId()%>><%=p.getPublisherName() %></option>
			<%} %>
		</select>
		 </div>
		
		
		<div class="form-group" align="center" style="width:35%;float:left;padding-right:30px;" >
		<label for="sel1">Select Authors from list Below: </label>
		<select class="form-control" id="sel1" multiple="multiple" size="10" name="authorIds">
			<%for(Author a: authors) {%>
			<option value=<%=a.getAuthorId()%>><%=a.getAuthorName() %></option>
			<%} %>
		</select>
		 </div>
		 	<div class="form-group" align="center" style="width:35%;float:left;padding-right:10px;" >
		<label for="sel1">Select Genre from list Below: </label>
		<select class="form-control" id="sel1" multiple="multiple" size="10" name="genreIds">
			<%for(Genre g: genres) {%>
			<option value=<%=g.getGenreId()%>><%=g.getGenreName() %></option>
			<%} %>
		</select>
		 </div>
		 <div style="text-align:center">
		<button type="submit" class="btn btn-primary btn-md">Save Book</button>
		</div>
	</form>
</div>