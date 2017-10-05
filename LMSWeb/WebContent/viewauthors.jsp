<%@include file="boot.htm"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>

<%
	AdminService service = new AdminService();
	Integer totalCount =-1;
	if(request.getParameter("searchAuthor") != null && ! request.getParameter("searchAuthor").isEmpty()){
		 totalCount = service.getAuthorsCount(request.getParameter("searchAuthor"));
	}
	else{
		 totalCount = service.getAuthorsCount(null);
	}
	int numOfPages = 0;
	if(totalCount%4 > 0){
		numOfPages = totalCount/4 +1;
	}else{
		numOfPages = totalCount/4;
	}
	List<Author> authors = new ArrayList<>();
	if(request.getAttribute("authors")!=null){
		authors = (List<Author>)request.getAttribute("authors");
	}else{
		authors = service.readAuthors(null, 1);
	}
%>
${statusMessage}



<script>
	function searchAuthors(pageNo) {
		$.ajax({
			method : "POST",
			url : "searchAuthor",
			data : {
				searchAuthor : $('#searchAuthor').val(),
				pageNo: pageNo
			}
		}).done(function(data) {
			  $('#authorInfo').html(data);
		});
	}
</script>


 <div class="jumbotron jumbotron-billboard">
  <div class="img"></div>
    <div class="container">
        <div>
 
 <button class="btn btn-outline-danger my-2 my-sm-0" type="button" id="searchAuthor1" onclick="searchAuthors(1);" style="float:right;">Search</button>       
<div style="float: right;">
	<input class="form-control mr-sm-2" type="text"
		placeholder="Search Author" aria-label="Search" id="searchAuthor" name="searchAuthor">
	<input type="hidden" value="1" name="pageNo" id="pageNo"></input>

</div>	

<br/>

<div id="tabletest"></div>
<div class="container" id="authorInfo" style="text-align: center; ">
	<h4 style="padding-left: 20%">
		 Total Authors in LMS:
		<%=totalCount%>
		<%int pageNo = 1;
		if(request.getParameter("pageNo") !=null){
			pageNo= Integer.parseInt(request.getParameter("pageNo")); } %>
		Authors
	</h4>
	<br>
	<nav aria-label="Page navigation example">
		<ul class="pagination" style="padding-left: 38%">
			<li class="page-item"
			<%
					if(pageNo == 1)
					{%>
						style="display:none"
					<%}
				%>
			><a class="page-link" href="pageAuthors?pageNo=<%=(pageNo-1) %>"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<%
			if(request.getParameter("searchAuthor") != null && ! request.getParameter("searchAuthor").isEmpty()){ %>
			<li class="page-item"><a class="page-link"
				href="searchAuthor?pageNo=<%=i%>&searchAuthor=<%=request.getParameter("searchAuthor")%>"><%=i%></a></li>
			<%} else { %>
			<li class="page-item"><a class="page-link"
				href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
			<%} %>

			<%} %>
			<li class="page-item" 
			<%
					if(pageNo == numOfPages)
					{%>
						style="display:none"
					<%}
				%>
			
			><a class="page-link" href="pageAuthors?pageNo=<%=(pageNo+1) %>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav>
	 <table class="table table-striped" id="authorTable">
		<tr>
			<th>#</th>
			<th>Author Name</th>
			<th>Books Written</th>
			<th>Edit Author</th>
			<th>Delete Author</th>
		</tr>
		<%
			for (Author a : authors) {
		%>
		<tr>
			<td><%=authors.indexOf(a) + 1%></td>
			<td><%=a.getAuthorName()%></td>
			<td>
				<ul style="padding-right: 50%; list-style-type: none;">
					<%
					for (Book b : a.getBooks()) {  %>
					<li>
						<%out.println(b.getTitle()); %>
					</li>
					<% 	} %>
				</ul>
			</td>
			<td>
				<button type="button" class="btn btn-primary btn-sm"
					data-toggle="modal"
					data-remote="editauthor.jsp?authorId=<%=a.getAuthorId()%>"
					data-target="#myModel">Edit</button>
			<td>

				<button type="button"
					onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'"
					class="btn btn-danger btn-sm">Delete</button>
			</td>
		</tr>
		<%
			}
		%>
	</table> 
</div>

        </div>
    </div>
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