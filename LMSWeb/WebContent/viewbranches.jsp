<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@include file="boot.htm" %>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%
	AdminService service = new AdminService();
	Integer totalCount = service.getbarnchesCount();
	int numOfPages = 0;
	if(totalCount%10 > 0){
		numOfPages = totalCount/10 +1;
	}else{
		numOfPages = totalCount/10;
	}
	List<LibraryBranch> branches = new ArrayList<>();
	if(request.getAttribute("branches")!=null){
		branches = (List<LibraryBranch>)request.getAttribute("branches");
	}else{
		branches = service.readBranch(null, 1);
	}
%>

<div class="container" style="text-align:center;margin-top:35px;" >
${statusMessage}
	<h4>List of LibraryBranchs in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total LibraryBranchs in LMS: <%=totalCount%> LibraryBranchs</h4>
	<nav aria-label="Page navigation example" style="text-align: center">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="pageLibraryBranchs?pageNo=<%=i%>"><%=i%></a></li>
			<%} %>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav>
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>LibraryBranch Name</th>
			<th>LibraryBranch Address</th>
			<th>Edit LibraryBranch</th>
			<th>Delete LibraryBranch</th>
		</tr>
		<%
			for (LibraryBranch a : branches) {
		%>
		<tr>
 		<td><%=branches.indexOf(a) + 1%></td>
			<td><%=a.getBranchName()%></td>
			<td><%=a.getBranchAddress()%></td>
			<td><button type="button"
					onclick="javascript:location.href='librarian.jsp?branchId=<%=a.getBranchId()%>'"
					class="btn btn-primary btn-sm">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteLibraryBranch?branchId=<%=a.getBranchId()%>'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr> 
		<%
			}
		%>
	</table>
</div>