<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@include file="boot.htm" %>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%LibrarianService libService = new LibrarianService();
LibraryBranch branch = libService.readbranchbyPK(Integer.parseInt(request.getParameter("branchId")));
%>

<div class="container">
    
	<form method="post" action="editBranchInf">
		${statusMessage}
		<br/>Enter new Branch Information below : <br /> <br /> 
		<label>Branch name :   </label>   <input type="text" name="branchName" value="<%=branch.getBranchName()%>"><br /> <br /> <br />
		<label> Branch Address : </label>  <input type="text" name="branchAddress" value="<%=branch.getBranchAddress()%>"><br />
		
		
		
		<input type="hidden" name="branchId" value="<%=branch.getBranchId()%>"><br/>
        
		<button type="submit" class="btn btn-primary btn-sm">Update Book copies</button><br/><br/>
    
            
	</form>
    </div>
    
    