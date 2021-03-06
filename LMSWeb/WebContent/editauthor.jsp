<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@include file="ref.html"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%AdminService service = new AdminService();
Author author = service.readAuthorByPK(Integer.parseInt(request.getParameter("authorId")));
List<Book> books = service.readBooks();
List<Book> authorBooks = author.getBooks();
%>
<div class="container" style="text-align:center;">
    
	<form method="post" action="editAuthor">
		${statusMessage}
		<br/>Enter Author Name to Edit: <input type="text" name="authorName" value="<%=author.getAuthorName()%>"><br />
		<input type="hidden" name="authorId" value="<%=author.getAuthorId()%>"><br/>
		
		
		<label for="sel1">Select Books from list Below: </label>
        	<div class="form-group" style="display:inline-block;padding-left: 5%">
		<select class="form-group" id="sel1" multiple="multiple" size="10" name="bookIds" >
			<%for(Book b: books) {
			if(! authorBooks.contains(b)) {
			%>
			<option value=<%=b.getBookId()%>><%=b.getTitle() %></option>
			<% }} %>
		</select>
		 </div><br/><br />
		<button type="submit" class="btn btn-primary btn-sm">Update Author</button><br/><br/>
        <table class="table table-striped">
    		<tr>
    			<th> Books Written by the Author </th>
    			<th> Delete </th>
    		</tr>		
    		<% for (Book b : author.getBooks()) { %>
    		<tr>
    			<td>
    			
    			<% 	out.println(b.getTitle()); %>	 <br />
    			 </td>
    			 <td>
    			 	   <button type="button" class="btn btn-primary btn-sm"
					data-toggle="modal"
					data-remote="deleteAuthorBook?authorId=<%=author.getAuthorId()%>&bookId=<%=b.getBookId() %>"
					data-target="#myModel">Delete
					</button>
			</td>
    		</tr>
				
		<%	}%>
         </table>
         
      
		 
		<br/>
            
	</form>
    </div>
    