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

<div class="container" style="margin-top: 45px;">
    
    <h3 style="padding-left: 35%;"> Enter the new details of the borrower</h3> <br>
	<form method="post" action="editBorrower" style="text-align:center;padding-left: 20%; ">
		${statusMessage}
		
		<table class="table table-striped" style="width: 80%">
		<tr>
			<td>
				Enter Borrower Name to Edit 
				</td>
			<td>
			<input type="text" name="borrowerName" value="<%=borrower.getName()%>" style="width: 180px;">
			</td>
		</tr>
		<tr>	
			<td>
			Enter Borrower Address to Edit:
			</td>
			<td>
			<input type="text" name="borrowerAddress" value="<%=borrower.getAddress()%>">
			</td>
		</tr>
		<tr>
			<td>
			Enter Borrower Phone to Edit:
			</td>
			<td>
			 <input type="text" name="borrowerPhone" value="<%=borrower.getPhone()%>">
			</td>
		</tr>
		<tr>
			<td>
			<input type="hidden" name="borrowerId" value="<%=borrower.getCardNo()%>">
			</td>
		 </tr>
		
       </table> 
       <button type="submit" class="btn btn-primary btn-md" style="margin-right: 20% ">Update Borrower</button>
            
	</form>
    </div>
    
    