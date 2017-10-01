<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@include file="boot.htm" %>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%LibrarianService libService = new LibrarianService();
BookCopies bookCopies = libService.readBookCopiesbyPK(Integer.parseInt(request.getParameter("bookId")),Integer.parseInt(request.getParameter("branchId")));
%>

<div class="container">
    
	<form method="post" action="editBookcopies">
		${statusMessage}
		<br/>Enter new number of copies : <input type="number" name="noOfCopies" value="<%=bookCopies.getCopies()%>"><br />
		<input type="hidden" name="bookId" value="<%=bookCopies.getBookId()%>"><br/>
		<input type="hidden" name="branchId" value="<%=bookCopies.getBranchId()%>"><br/>
        
		<button type="submit" class="btn btn-primary btn-sm">Update Book copies</button><br/><br/>
    
            
	</form>
    </div>
    
    