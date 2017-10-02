<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Book> books = service.readBooks();
%>
<div class="container" style="text-align:center; margin-top:45px;">
	<h3>Add New Author</h3>	<br>
	<form method="post" action="addAuthor">
		${statusMessage}
		
		
		<br />Enter Author Name: <input type="text" name="authorName"><br />
		<br /> 

	
		<label for="sel1">Select Books from list Below: </label>
		<div class="form-group" style="width:90%;display:inline-block;padding-left: 20%">
			 <select
				class="form-group" id="sel1" multiple="multiple" size="10" style="width:70%;"
				name="bookIds">
				<%
					for (Book b : books) {
				%>
				<option value=<%=b.getBookId()%>><%=b.getTitle()%></option>
				<%
					}
				%>
			</select>
		</div>

		<br />
		<button type="submit" class="btn btn-primary btn-md">Save
			Author</button>
	</form>
</div>