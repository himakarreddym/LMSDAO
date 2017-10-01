<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@include file="boot.htm" %>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%AdminService service = new AdminService();
Borrower borrower = service.readBorrowerByPK(Integer.parseInt(request.getParameter("cardNo")));
%>

<div class="container">
    
	<form method="post" action="editBorrower">
		${statusMessage}
		<br/>Enter Borrower Name to Edit: <input type="text" name="borrowerName" value="<%=borrower.getName()%>"><br />
		<br/>Enter Borrower Address to Edit: <input type="text" name="borrowerAddress" value="<%=borrower.getAddress()%>"><br />
		<br/>Enter Borrower Phone to Edit: <input type="text" name="borrowerPhone" value="<%=borrower.getPhone()%>"><br />
		<input type="hidden" name="borrowerId" value="<%=borrower.getCardNo()%>"><br/>
        
		<button type="submit" class="btn btn-primary btn-sm">Update Borrower</button><br/><br/>
       
            
	</form>
    </div>
    
    