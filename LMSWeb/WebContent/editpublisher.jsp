<%@page import="com.gcit.lms.entity.Publisher"%>
<%@include file="boot.htm" %>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%AdminService service = new AdminService();
Publisher publisher = service.readPublisherByPK(Integer.parseInt(request.getParameter("publisherId")));
%>

<div class="container" style="margin-top: 45px;">
     <h3 style="padding-left: 35%;"> Enter the new details of the Publisher</h3> <br>
	<form method="post" action="editPublisher"style="text-align:center;padding-left: 20%; ">
		${statusMessage}
		<table class="table table-striped" style="width: 80%">
		<tr>
			<td>
				Enter Publisher Name to Edit
				</td>
			<td>
			<input type="text" name="publisherName" value="<%=publisher.getPublisherName()%>"style="width: 180px;">
			</td>
		</tr>
		<tr>	
			<td>
			Enter Publisher Address to Edit
			</td>
			<td>
			<input type="text" name="publisherAddress" value="<%=publisher.getPublisherAddress()%>">
			</td>
		</tr>
		<tr>
			<td>
			Enter Publisher Phone to Edit
			</td>
			<td>
			 <input type="text" name="publisherPhone" value="<%=publisher.getPublisherPhone()%>">
			</td>
		</tr>
		
        <tr>
			<td>
			<input type="hidden" name="publisherId" value="<%=publisher.getPublisherId()%>">
			</td>
		 </tr>
		
       </table> 
        
		<button type="submit" class="btn btn-primary btn-md"style="margin-right: 20% ">Update Publisher</button><br/><br/>
       
            
	</form>
    </div>
    
    