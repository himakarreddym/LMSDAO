<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@include file="boot.htm" %>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%LibrarianService libService = new LibrarianService();
List<LibraryBranch> branches = libService.readLibraryBranch(null);	
%>

<div class="container">

	<div class="form-group" align="center">
	${statusMessage}
		<br /> <br /> <br /> <br /> <br />
		<h2> <label for="sel1" > Select Branches from list Below:  </label> </h2>
		<select class="form-group" id="sel1" name="branchId" onchange="javascript:location.href='librarian.jsp?branchId='+this.value">
			<%for(LibraryBranch branch: branches) {%>
			<option value=<%=branch.getBranchId()%>
			<% if(Integer.parseInt(request.getParameter("branchId")) == branch.getBranchId()) out.print("selected");%>
			
			><%=branch.getBranchName() %></option>
			<%} %>
		</select>
		<br /><br />
		
	 <button type="button"
					onclick="javascript:location.href='updatebranch.jsp?branchId=<%=Integer.parseInt(request.getParameter("branchId")) %>'"
					class="btn btn-info btn-sm">Update Information</button>
		 </div>
		 

		 <div class="form-group" align="center" >
		<br /> <br />
		<h2> <label for="sel1" >  Select Book and copies from list Below: </label> </h2> 
		<table class="table table-striped" >
			<tr>
			<th>Book Name</th>
			<th>Current copies</th>
			<th>Edit</th>
			</tr>
		<%BookCopies bookCopies = new BookCopies();
		int branchId =Integer.parseInt(request.getParameter("branchId"));
		bookCopies.setBookId(0);
		bookCopies.setBranchId(branchId);%>
		 <% List<BookCopies> bookcopies = libService.readBookCopies(bookCopies); %>
		 <%for(BookCopies bc : bookcopies ){ %>
		 <tr>
    			<td>
    			<a href="bookinf.jsp?bookId=<%=bc.getBookId()%>&branchId=<%=branchId%>"><% 	out.println(bc.getBook().getTitle()); %>	 <br /></a>
    			 </td>
    			 <td>
    			<% 	out.println(bc.getCopies()); %>	 <br />
    			 </td>
    			 
    			 <td><button type="button"
					onclick="javascript:location.href='editbookcopies.jsp?bookId=<%=bc.getBookId()%>&branchId=<%=branchId%>'"
					class="btn btn-primary btn-sm"> Update copies </button></td>

    		</tr>
				
			<%	}%> 
			</table>

	</div>
<!-- 	</form> -->
</div>