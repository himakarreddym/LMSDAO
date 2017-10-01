<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@include file="boot.htm" %>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%AdminService service = new AdminService();
 LibraryBranch branch = service.readBranchByPK(Integer.parseInt(request.getParameter("branchId"))); 
%>

 <div class="container">
    
	<form method="post" action="editLibraryBranch">
		${statusMessage}
		<br/>Enter LibraryBranch Name to Edit: <input type="text" name="branchName" value="<%=branch.getBranchName()%>"><br />
		<br/>Enter LibraryBranch Address to Edit: <input type="text" name="branchAddress" value="<%=branch.getBranchAddress()%>"><br />
		
		<input type="hidden" name="branchId" value="<%=branch.getBranchId()%>"><br/>
        
		<button type="submit" class="btn btn-primary btn-sm">Update LibraryBranch</button><br/><br/>
       
            
	</form>
    </div> 
    