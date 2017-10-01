<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@include file="boot.htm" %>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%AdminService service = new AdminService();
Book book = service.readBookByPK(Integer.parseInt(request.getParameter("bookId")));
List<Author> authors = service.readAuthors();
List<Author> authorBooks = book.getAuthors();
%>

<div class="container">
    
	<form method="post" action="editBook">
		${statusMessage}
	<br/>Enter new Book Title : <input type="text" name="bookName" value="<%=book.getTitle()%>"><br />
		<input type="hidden" name="bookId" value="<%=book.getBookId()%>"><br/>
		
		
        	<div class="form-group">
		<label for="sel1">Select Books from list Below: </label>
		<select class="form-control" id="sel1" multiple="multiple" size="10" name="authorIds">
			<%for(Author a: authors) {
			if(! authorBooks.contains(a)) {
			%>
			<option value=<%=a.getAuthorId()%>><%=a.getAuthorName() %></option>
			<% }} %>
		</select>
		 </div><br/><br />
		<button type="submit" class="btn btn-primary btn-sm">Update Author</button><br/><br/>
        <table class="table table-striped">
    		<tr>
    			<th> Books Written by the Author </th>
    			<th> Delete </th>
    		</tr>		
    		<% for (Author b : book.getAuthors()) { %>
    		<tr>
    			<td>
    			
    			<% 	out.println(b.getAuthorName()); %>	 <br />
    			 </td>
    			 <td><button type="button"
					onclick="javascript:location.href='deleteBookAuthor?authorId=<%=b.getAuthorId()%>&bookId=<%=book.getBookId() %>'"
					class="btn btn-danger btn-sm">Delete</button></td>
    		</tr>
				
		<%	}%>
         </table>
         
         
		 
		<br/>
            
	</form>
    </div>
    