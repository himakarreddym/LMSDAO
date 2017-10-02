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
<%
	if (request.getAttribute("statusMessage") != null) {
		out.println(request.getAttribute("statusMessage"));
	}
%>
<div class="container">
	<h1>List of Books in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Books in LMS: <%=totalCount%> Books</h1>
	<nav aria-label="Page navigation example">
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
				 <%
					for (Genre g : b.getGenres()) {
							out.println(g.getGenreName() + "|");
						}
				%> 
			</td>
			
			<td>
				 <%
					for (Author a : b.getAuthors()) {
							out.println(a.getAuthorName() + "|");
						}
				%> 
			</td>
			<td><button type="button"
					onclick="javascript:location.href='editbooks.jsp?bookId=<%=b.getBookId()%>'"
					class="btn btn-primary btn-sm">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteBook?bookId=<%=b.getBookId()%>'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>