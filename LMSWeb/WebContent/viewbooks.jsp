<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@include file="boot.htm" %>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%
	AdminService service = new AdminService();
	Integer totalCount = service.getBooksCount();
	int numOfPages = 0;
	if(totalCount%10 > 0){
		numOfPages = totalCount/10 +1;
	}else{
		numOfPages = totalCount/10;
	}
	List<Book> books = new ArrayList<>();
	if(request.getAttribute("books")!=null){
		books = (List<Book>)request.getAttribute("books");
	}else{
		books = service.readBooks(null, 1);
	}
%>

<div class="container" style="text-align:center;margin-top:45px;">
${statusMessage}
	<h3>Books List in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Books in LMS: <%=totalCount%> Books</h3>
	<nav aria-label="Page navigation example"  >
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="pageBooks?pageNo=<%=i%>"><%=i%></a></li>
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
			<th>Book Title</th>
			<th>Publisher Name :</th>
			<th>Book Genres </th>
			<th>Author Names</th>
			<th>Edit Book</th>
			<th>Delete Book</th>
		</tr>
		<%
			for (Book b : books) {
		%>
		<tr>
			<td><%=books.indexOf(b) + 1%></td>
			<td><%=b.getTitle()%></td>
				<td>
				 <%
				 			if(b.getPublisher() != null)
							out.println(b.getPublisher().getPublisherName() );
				%> 
			</td>
			
			  <td>
                <ul style="padding-right:50%;list-style-type: none;">
				 <% for (Genre g : b.getGenres()) {%>
						<li>	<%out.println(g.getGenreName());%></li>
					<%	} %> 
                </ul> 
			</td>
			
			<td>
                <ul style="padding-right:50%;list-style-type: none;">
				 <%for (Author a : b.getAuthors()) {%>
						<li>	<%out.println(a.getAuthorName());%></li>
					<%	} %> 
                        </ul> 
			</td>
			<td>
						<button type="button" class="btn btn-primary btn-sm"
					data-toggle="modal"
					data-remote="editbooks.jsp?bookId=<%=b.getBookId()%>"
					data-target="#myModel">Edit</button>
			</td>
			<td><button type="button"
					onclick="javascript:location.href='deleteBook?bookId=<%=b.getBookId()%>'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>
<!-- Model -->
<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Edit Author</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
			</div>
			<div class="modal-body">
				<p></p>
			</div>
		</div>
	</div>
</div>