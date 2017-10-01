<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@include file="boot.htm" %>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@include file="boot.htm" %>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%BorrowerService borservice = new BorrowerService();	
%>

<div class="container">
		<form method="post" action="checkcard">
		${statusMessage}
		<br/>Enter your card number: <input type="number" name="cardNo"><br /><br /><br />
		 <br/>
	</form>
</div>