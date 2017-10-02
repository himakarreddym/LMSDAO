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
	Integer totalCount = service.getPublishersCount();
	int numOfPages = 0;
	if(totalCount%10 > 0){
		numOfPages = totalCount/10 +1;
	}else{
		numOfPages = totalCount/10;
	}
	List<Publisher> publishers = new ArrayList<>();
	if(request.getAttribute("publishers")!=null){
		publishers = (List<Publisher>)request.getAttribute("publishers");
	}else{
		publishers = service.readPublisher(null, 1);
	}
%>

<div class="container" style="text-align:center;margin-top:45px;">
${statusMessage}
	<h3>List of Publishers in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Publishers in LMS: <%=totalCount%> Publishers</h3>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="pagePublishers?pageNo=<%=i%>"><%=i%></a></li>
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
			<th>Publisher Name</th>
			<th>Publisher Address</th>
			<th>Publisher Phone</th>
			<th>Books</th>
			<th>Edit Publisher</th>
			<th>Delete Publisher</th>
		</tr>
		<%
			for (Publisher a : publishers) {
		%>
		<tr>
			<td><%=publishers.indexOf(a) + 1%></td>
			<td><%=a.getPublisherName()%></td>
			<td><%=a.getPublisherAddress()%></td>
			<td><%=a.getPublisherPhone()%></td>
			<td>
				<%
					for (Book b : a.getBooks()) {
							out.println(b.getTitle() + "|");
						}
				%>
			</td>
			<td><button type="button"
					onclick="javascript:location.href='editpublisher.jsp?publisherId=<%=a.getPublisherId()%>'"
					class="btn btn-primary btn-sm">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deletePublisher?publisherId=<%=a.getPublisherId()%>'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>