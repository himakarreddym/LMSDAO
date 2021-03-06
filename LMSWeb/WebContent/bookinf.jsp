<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@include file="boot.htm" %>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@include file="boot.htm" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%AdminService adminServive = new AdminService();
int bookId = Integer.parseInt(request.getParameter("bookId"));
int branchId = Integer.parseInt(request.getParameter("branchId"));
Book book = adminServive.readBookByPK(bookId);
%>
<div class="container" style="text-align: center;margin-top: 6%">
		${statusMessage}
			<h3>Book Information  </h3> <br>
		<div class="form-group" style="padding-left: 20%;">
	
        <table class="table table-striped" style="width: 80%;">
    		<tr>
    			<th> Book Title </th>
    			<td>
    			<% 	out.println(book.getTitle()); %>	 <br />
    			 </td>
    			</tr> 
    			<tr>
    			<th> Publishers name </th> 
    			 <td>
    			<% 	out.println(book.getPublisher().getPublisherName()); %>	 <br />
    			 </td>
    			</tr> 
    			<tr>
    			<th> Authors Names </th> 
    			<td>
    			<ul style="padding: 0;list-style-type: none;">
    			 <%
					for (Author a : book.getAuthors()) { %>
						<li> <%out.println(a.getAuthorName()); %></li>
					<% 	} %> 
  				
			</ul> 
				
			</td>
    			 </tr> 
    			<tr>
    			<th> Genres Names </th> 
    			<td>
				 <%
					for (Genre g : book.getGenres()) {
							out.println(g.getGenreName() + "|");
						}
				%> 
			</td>
    		</tr>		
    </table> 
         
    </div>
    <button type="button"
					onclick="javascript:location.href='librarian.jsp?bookId=<%=bookId%>&branchId=<%=branchId%>'"
					class="btn btn-primary btn-md"> Return to previous </button>
    
</div>