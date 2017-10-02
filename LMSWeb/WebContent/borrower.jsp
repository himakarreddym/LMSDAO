<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@include file="boot.htm" %>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%BorrowerService borservice = new BorrowerService();	
%>

<div class="container"style="text-align:center;margin-top:10%;padding-right: 40px;">
		${statusMessage}
		<form method="post" action="checkcard">
		<br/>Enter your card number: <input type="number" name="cardNo"><br /><br /><br />
		 <br/>
		 <button type="submit"class="btn btn-primary btn-md">Submit</button>
	</form>
</div>