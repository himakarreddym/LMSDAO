<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@include file="boot.htm" %>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@include file="boot.htm" %>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%BorrowerService borService = new BorrowerService();
List<LibraryBranch> branches = borService.readLibraryBranch(null);
SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY, HH:mm");
%> 
<div class="container">
	<div style="padding:20px;">
	<div class="form-group" align="center">
	${statusMessage}
		<br /> <br /> <br /> <br /> <br />
		<form method="post" action="checkcard">
		<input type="hidden" value="<%=Integer.parseInt(request.getAttribute("cardNo").toString())%>" name="cardNo">
		<h3> <label for="sel1" > Select Branches from list Below  </label> </h3>
		<select class="form-control" id="sel1" name="branchId" onchange="javascript:this.form.submit()">
			<%for(LibraryBranch branch: branches) {%>
			<option value=<%=branch.getBranchId()%>
			 <% if(Integer.parseInt(request.getAttribute("branchId").toString()) == branch.getBranchId()) out.print("selected");%> 
			
			><%=branch.getBranchName() %></option>
			<%} %>
		</select>
		</form>
		</div>
		</div>
	
 	 
	 <div class="form-group" align="center" style="width:44%;float:left;padding-right:10px;">
		<br /> <br />
		<h4> <label for="sel1" >  Books available in this branch  </label> </h4> 
		
		<table class="table table-striped" >
			<tr>
			<th>Book Name</th>
			<th>Current copies</th>
			<th>Edit</th>
			</tr>
		<%BookCopies bookCopies = new BookCopies();
		bookCopies.setBookId(0);
		bookCopies.setBranchId(Integer.parseInt(request.getAttribute("branchId").toString())); %>
		 <% List<BookCopies> bookcopies = borService.readBookCopiesbyCond(bookCopies); %>
		 <%for(BookCopies bc : bookcopies ){ %>
		 <tr>
    			<td>
    			<% 	out.println(bc.getBook().getTitle()); %>	 <br />
    			 </td>
    			 <td>
    			<% 	out.println(bc.getCopies()); %>	 <br />
    			 </td>
    			<td>
    			<form method="post" action="checkbook">	
    			 <input type="hidden" name="bookId" value="<%=bc.getBookId()%>">
    			 <input type="hidden" name="branchId" value=<%=Integer.parseInt(request.getAttribute("branchId").toString()) %>><br/>
    			 <input type="hidden" name="cardNo" value="<%=Integer.parseInt(request.getAttribute("cardNo").toString())%>">
    			 <button type="submit" class="btn btn-primary btn-sm">CheckOut</button>
    			 </form>
			</td>	
    		</tr>
				
		<%	}%>
			</table>	
	</div>



	<div class="form-group" align="center" style="width:56%;float:left;">
		<br /> <br />
		<h4> <label for="sel1" >  Books you borrowed in this branch  </label> </h4> 
		<table class="table table-striped" >
			<tr>
			<th>Book Name</th>
			<th>Date out</th>
			<th>Due date</th>
			<th>Edit</th>
			</tr>
		<%BookLoans bookLoans = new BookLoans();
		bookLoans.setBranchId(Integer.parseInt(request.getAttribute("branchId").toString()));
		bookLoans.setCardNo(Integer.parseInt(request.getAttribute("cardNo").toString()));
		%>
	 <% List<BookLoans> borrowedbooks= borService.readBookLoansBycard(bookLoans); %> 
		 <%for(BookLoans bl : borrowedbooks ){ %>

		 <tr>
    			<td>
    			<% 	out.println(bl.getBook().getTitle()); %>	 <br />
    			 </td>
    			 <td>
    			<% 	out.println(dateFormat.format(bl.getDateOut())); %>	 <br />
    		
    			 </td>
    			 	 <td>
    			<% 	out.println(dateFormat.format(bl.getDueDate())); %>	 <br />
    		
    			 </td>
 
    			<td>
    			<form method="post" action="checkInBook">	
    			 <input type="hidden" name="bookId" value="<%=bl.getBookId()%>">
    			 <input type="hidden" name="branchId" value=<%=Integer.parseInt(request.getAttribute("branchId").toString()) %>><br/>
    			 <input type="hidden" name="cardNo" value="<%=Integer.parseInt(request.getAttribute("cardNo").toString())%>">
    			 <input type="hidden" name="dateOut" value="<%=bl.getDateOut()%>">
    			   <input type="hidden" name="dueDate" value="<%=bl.getDueDate()%>">
    			 <button type="submit" class="btn btn-primary btn-sm">CheckIn</button>
    			 </form>
			</td>

    		</tr>
				
		<%	}%>
			</table>
	
	</div>
	</div>
	</div>