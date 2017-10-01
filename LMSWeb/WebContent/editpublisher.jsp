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

<div class="container">
    
	<form method="post" action="editPublisher">
		${statusMessage}
		<br/>Enter Publisher Name to Edit: <input type="text" name="publisherName" value="<%=publisher.getPublisherName()%>"><br />
		<br/>Enter Publisher Address to Edit: <input type="text" name="publisherAddress" value="<%=publisher.getPublisherAddress()%>"><br />
		<br/>Enter Publisher Phone to Edit: <input type="text" name="publisherPhone" value="<%=publisher.getPublisherPhone()%>"><br />
		<input type="hidden" name="publisherId" value="<%=publisher.getPublisherId()%>"><br/>
        
		<button type="submit" class="btn btn-primary btn-sm">Update Publisher</button><br/><br/>
       
            
	</form>
    </div>
    
    