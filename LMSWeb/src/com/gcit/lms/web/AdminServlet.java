package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;
import com.mysql.fabric.Response;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/addAuthor", "/deleteAuthor", "/editAuthor", "/pageAuthors","/deleteAuthorBook",
	"/addGenre","/pageGenres","/deleteGenre","/deleteGenreBook","/editGenre",
	"/addPublisher","/deletePublisher","/pagePublishers","/editPublisher",
	"/addBorrower","/editBorrower","/deleteBorrower","/pageBorrowers",
	"/addbranch","/pageLibraryBranchs","/editLibraryBranch","/deleteLibraryBranch",
	"/addBook","/pageBooks","/deleteBook","/editBook","/deleteBookAuthor","/deleteGenreBook1",
	"/deletebookPublisher","/editdueDate","/pageBookLoans","/addbranchBook","/override",
	"/searchAuthor"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = null;
		Boolean isAjax = Boolean.FALSE;
		switch (reqUrl) {
		case "/deleteAuthor":
			redirectUrl = deleteAuthor(request, response, "/viewauthors.jsp");
			break;
		case "/pageAuthors":
			redirectUrl = pageAuthors(request, response, "/viewauthors.jsp");
			break;
		case "/deleteAuthorBook":
			redirectUrl = deleteAuthorBook(request, response, "/viewauthors.jsp");
			break;
		case "/deleteGenreBook":
			redirectUrl=deleteGenreBook(request, response, "/viewgenres.jsp");
			break;
		case "/pageGenres":
			redirectUrl=pageGenres(request, response, "/viewgenres.jsp");
			break;
		case "/deleteGenre":
			redirectUrl= deleteGenre(request, response, "/viewgenres.jsp");
			break;
		case "/deletePublisher":
			redirectUrl= deletePublisher(request, response, "/viewpublishers.jsp");
			break;
		case "/pagePublishers":
			redirectUrl=pagePublishers(request, response, "/viewpublishers.jsp");
			break;
		case "/deleteBorrower":
			redirectUrl= deleteBorrower(request, response, "/viewborrowers.jsp");
			break;
		case "/pageBorrowers":
			redirectUrl=pageBorrowers(request, response, "/viewborrowers.jsp");
			break;
		case "/pageLibraryBranchs":
			redirectUrl=pageLibraryBranchs(request,response,"/viewbranches.jsp");
			break;
		case "/deleteLibraryBranch":
			redirectUrl= deleteLibraryBranch(request, response, "/viewbranches.jsp");
			break;
		case "/pageBooks":
			redirectUrl=pageBooks(request,response,"/viewbooks.jsp");
			break;
		case "/deleteBook":
			redirectUrl=deleteBook(request,response,"/viewbooks.jsp");
			break;
		case "/deleteBookAuthor":
			redirectUrl=deleteBookAuthor(request,response,"/viewbooks.jsp");
			break;
		case "/deleteGenreBook1":
			redirectUrl=deleteGenreBook1(request, response, "/viewbooks.jsp");
			break;
		case "/deletebookPublisher":
			try {
				redirectUrl=deletebookPublisher(request, response, "/viewbooks.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/pageBookLoans":
			redirectUrl=pageBookLoans(request,response,"/OverrideDuedate.jsp");
			break;
		case "/searchAuthor":
			try {
				searchAuthor(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isAjax = Boolean.TRUE;
			break;
		
			
		default:
			break;
		}
		if(! isAjax) {
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
	}}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = null;
		Boolean isAjax = Boolean.FALSE;
		switch (reqUrl) {
		case "/addAuthor":
			redirectUrl = addAuthor(request, "/viewauthors.jsp", false);
			break;
		case "/editAuthor":
			redirectUrl = addAuthor(request, "/viewauthors.jsp", true);
			break;
		case "/addGenre":
			redirectUrl=addGenre(request, "/viewgenres.jsp", false);
			break;
		case "/editGenre":
			redirectUrl=addGenre(request, "/viewgenres.jsp", true);
			break;
		case "/addPublisher":
			redirectUrl=addPublisher(request, "/viewpublishers.jsp", false);
			break;
		case "/editPublisher":
			redirectUrl=addPublisher(request, "/viewpublishers.jsp", true);
			break;
		case "/addBorrower":
			redirectUrl=addBorrower(request, "/viewborrowers.jsp", false);
			break;
		case "/editBorrower":
			redirectUrl=addBorrower(request, "/viewborrowers.jsp", true);
			break;
		case "/addbranch":
			redirectUrl=addbranch(request, "/bookbranch.jsp", false);
			break;
		case "/editLibraryBranch":
			redirectUrl=addbranch(request, "/viewbranches.jsp", true);
			break;
		case "/addBook":
			redirectUrl = addBook(request, "/viewbooks.jsp", false);
			break;
		case "/editBook":
			redirectUrl = addBook(request, "/viewbooks.jsp", true);
			break;
		case "/editdueDate":
			try {
				redirectUrl = editdueDate(request,"/OverrideDuedate.jsp");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/addbranchBook":
			redirectUrl = addbranchBook(request, "/bookbranch.jsp");
			break;
		case "/override":
			try {
				redirectUrl=overridedate(request,"/overidedate.jsp");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/searchAuthor":
			try {
				searchAuthor(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isAjax = Boolean.TRUE;
			break;
		
			
		default:
			break;
		}
		if(! isAjax) {
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
	private String addbranchBook(HttpServletRequest request, String redirectUrl) {
		BookCopies bookCopies = new BookCopies();
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>No of copies Added sucesfully for the book</div>";
		
		if(request.getParameter("noofCopies") != null && request.getParameter("branchId") != null && request.getParameter("bookId") != null) {
		int noofCopies = Integer.parseInt(request.getParameter("noofCopies"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		try {
			bookCopies.setBookId(bookId);
			bookCopies.setBranchId(branchId);
			bookCopies.setCopies(noofCopies);
		adminService.savebookCopies(bookCopies);
		request.setAttribute("branchId", branchId);
//		request.setAttribute("bookId", bookId);
		} catch (SQLException e) {
		e.printStackTrace();
		}}else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\">Book Copies cannot be Empty</div>";
		}
		request.setAttribute("statusMessage", message);
		 return redirectUrl;
		}	
	
	private String overridedate(HttpServletRequest request, String redirectUrl) throws ParseException {
		request.setAttribute("bookId",Integer.parseInt(request.getParameter("bookId")));
		request.setAttribute("branchId",Integer.parseInt(request.getParameter("branchId")));
		request.setAttribute("CardNo",Integer.parseInt(request.getParameter("CardNo")));
		request.setAttribute("dateOut",Timestamp.valueOf(request.getParameter("dateOut")));
		request.setAttribute("dueDate",Timestamp.valueOf(request.getParameter("dueDate")));
		return redirectUrl;
	}
	
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
	private String editdueDate(HttpServletRequest request, String redirectUrl) throws ParseException {
		BookLoans bookloans = new BookLoans();
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Successfully overrided the Due date</div>";
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int cardNo = Integer.parseInt(request.getParameter("CardNo"));
		Timestamp dateOut = Timestamp.valueOf((request.getParameter("dateOut")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 =sdf.parse(request.getParameter("newdueDate"));
			Timestamp newdueDate = (Timestamp) new java.sql.Timestamp(date1.getTime());
			if(newdueDate.after(new Date())) {
			if(bookId !=0 && branchId !=0 && cardNo !=0) {
				bookloans.setBookId(bookId);	
				bookloans.setBranchId(branchId);;	
				bookloans.setCardNo(cardNo);	
				bookloans.setDateOut(dateOut);
				bookloans.setDueDate(newdueDate);
					try {
						adminService.updatedueDate(bookloans);;
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
				else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\">Currently You cannot override the book the book!! Sorry for inconvinience</div>";
			redirectUrl = "/OverrideDuedate.jsp";
				}
			}
			else {
				request.setAttribute("bookId", bookId);
				request.setAttribute("branchId", bookId);
				request.setAttribute("CardNo", bookId);
				request.setAttribute("dateOut", bookId);
				request.setAttribute("bookId", bookId);
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!!!</strong>You cannot select the date not earlier than today!!</div>";
				redirectUrl = "/overidedate.jsp";
				}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	
	private String pageBookLoans(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("bookloans", adminService.readBookLoansPage(pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
	private String addBook(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Book book = new Book();
		Publisher pub = new Publisher();
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Book added Sucessfully</div>";
		
		if (request.getParameter("bookName") != null && !request.getParameter("bookName").isEmpty()) {
			if(request.getParameter("bookName").length() > 45){
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\">Book Name cannot be more than 45 chars</div>";
				redirectUrl = "/addbook.jsp";
			}else{
				book.setTitle(request.getParameter("bookName"));
				String[] authorIds = request.getParameterValues("authorIds");
				String[] genreIds = request.getParameterValues("genreIds");
				//Checking for publisher
				int pubId = 0;
				if(request.getParameter("publisherId") != null) {
					pubId = Integer.parseInt(request.getParameter("publisherId"));
				}
					pub.setPublisherId(pubId);
					book.setPublisher(pub);
					//AuthorIds
				try {
					if(authorIds !=null) {
						book.setAuthors(adminService.getAuthors(authorIds));
					}
					if(genreIds != null) {
						book.setGenres(adminService.getGenres(genreIds));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(editMode){
					book.setBookId(Integer.parseInt(request.getParameter("bookId")));
					
				}
				try {
					adminService.saveBook(book);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Book Name cannot be Empty</div>";
			redirectUrl = "/addbook.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	private String pageBooks(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("books", adminService.readBooks(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	
	private String deleteBook(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Book deleted Sucessfully</div>";
		if(request.getParameter("bookId")!=null){
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new Book();
			book.setBookId(bookId);
			try {
				adminService.deleteBook(book);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Book deletion failed. Contact Admin</div>";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	private String deleteBookAuthor(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Author deleted Sucessfully for this Book</div>";
		if(request.getParameter("authorId")!=null && request.getParameter("bookId")!=null){
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			Author author = new Author();
			author.setAuthorId(authorId);
			try {
				adminService.deleteBookAuthor(author,bookId);
				request.setAttribute("bookId",bookId);
				redirectUrl = "/editbooks.jsp";
				
			} catch (SQLException e) {
				e.printStackTrace();
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Author deleted failed with the book. Contact Admin</div>";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	
	private String deletebookPublisher(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException, SQLException {
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Publisher deleted Sucessfully for this Book</div>";
		if(request.getParameter("bookId")!=null){
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new Book();
			book.setBookId(bookId);
			Publisher pub = new Publisher();
			pub.setPublisherId(null);
			book.setPublisher(pub);
			try {
				adminService.upadatebookPublisher(book);
				request.setAttribute("bookId",bookId);
				redirectUrl = "/editbooks.jsp";
				
			} catch (SQLException e) {
				e.printStackTrace();
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Author deleted failed. Contact Admin</div>";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
	private String addbranch(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		LibraryBranch branch = new LibraryBranch();
		String message = null;
		
		if (request.getParameter("branchName") != null && !request.getParameter("branchName").isEmpty()) {
		if(request.getParameter("branchName").length() > 45){
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Branch Name cannot be more than 45 chars</div>";
		redirectUrl = "/addbranch.jsp";
		}else{
		branch.setBranchName(request.getParameter("branchName"));
		branch.setBranchAddress(request.getParameter("branchAddress"));
	
		if(editMode){
		branch.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		}
		try {
		int branchid1 = adminService.saveLibraryBranch(branch);
		message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>LibraryBranch Added/Updated Sucessfully</div>";
		request.setAttribute("branchId", branchid1);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
		}else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>LibraryBranch Name cannot be Empty</div>";
		redirectUrl = "/addbranch.jsp";
		}
		request.setAttribute("statusMessage", message);
		 return redirectUrl;
		}	
	private String pageLibraryBranchs(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("branches", adminService.readBranch(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	private String deleteLibraryBranch(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Branch deleted Sucessfully</div>";
		if(request.getParameter("branchId")!=null){
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			LibraryBranch lb = new LibraryBranch();
			lb.setBranchId(branchId);
			try {
				adminService.deleteLibraryBranch(lb);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Branch delete failed. Contact Admin</div>";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
	private String addBorrower(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Borrower borrower = new Borrower();
		String message = null;
		
		if (request.getParameter("borrowerName") != null && !request.getParameter("borrowerName").isEmpty()) {
		if(request.getParameter("borrowerName").length() > 45){
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Borrower Name cannot be more than 45 chars</div>";
		redirectUrl = "/addborrower.jsp";
		}else{
		borrower.setName(request.getParameter("borrowerName"));
		borrower.setAddress(request.getParameter("borrowerAddress"));
		borrower.setPhone(request.getParameter("borrowerPhone"));
	
		if(editMode){
		borrower.setCardNo(Integer.parseInt(request.getParameter("borrowerId")));
		}
		try {
		adminService.saveBorrower(borrower);
		message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Borrower added Sucessfully</div>";
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
		}else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Borrower Name cannot be Empty</div>";
		redirectUrl = "/addborrower.jsp";
		}
		request.setAttribute("statusMessage", message);
		 return redirectUrl;
		}	
	
	private String deleteBorrower(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Borrower deleted Sucessfully</div>";
		if(request.getParameter("cardNo")!=null){
			Integer borrowerId = Integer.parseInt(request.getParameter("cardNo"));
			Borrower bor = new Borrower();
			bor.setCardNo(borrowerId);
			try {
				adminService.deleteBorrower(bor);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Borrower deleted failed. Contact Admin</div>";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	private String pageBorrowers(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("borrowers", adminService.readBorrowers(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
	private String addPublisher(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Publisher publisher = new Publisher();
		String message = null;
		
		if (request.getParameter("publisherName") != null && !request.getParameter("publisherName").isEmpty()) {
		if(request.getParameter("publisherName").length() > 45){
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Publisher Name cannot be more than 45 chars</div>";
		redirectUrl = "/addpublisher.jsp";
		}else{
		publisher.setPublisherName(request.getParameter("publisherName"));
		publisher.setPublisherAddress(request.getParameter("publisherAddress"));
		publisher.setPublisherPhone(request.getParameter("publisherPhone"));
	
		if(editMode){
		publisher.setPublisherId(Integer.parseInt(request.getParameter("publisherId")));
		}
		try {
		adminService.savePublisher(publisher);
		message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Publisher added Sucessfully</div>";
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
		}else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Publisher Name cannot be Empty</div>";
		redirectUrl = "/addpublisher.jsp";
		}
		request.setAttribute("statusMessage", message);
		 return redirectUrl;
		}	
	
	private String pagePublishers(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("publishers", adminService.readPublisher(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	
	private String deletePublisher(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Publisher deleted Sucessfully</div>";
		if(request.getParameter("publisherId")!=null){
			Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
			Publisher publisher = new Publisher();
			publisher.setPublisherId(publisherId);
			try {
				adminService.deletePublisher(publisher);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Publisher deleted failed. Contact Admin</div>";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
	
		private String addGenre(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Genre genre = new Genre();
		String message = null;
		
		if (request.getParameter("genreName") != null && !request.getParameter("genreName").isEmpty()) {
		if(request.getParameter("genreName").length() > 45){
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Genre Name cannot be more than 45 chars</div>";
		redirectUrl = "/addgenre.jsp";
		}else{
		genre.setGenreName(request.getParameter("genreName"));
		String[] bookIds = request.getParameterValues("bookIds");
		try {
		if(bookIds !=null)
		genre.setBooks(adminService.getBooks(bookIds));
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		if(editMode){
		genre.setGenreId(Integer.parseInt(request.getParameter("genreId")));
		}
		try {
		adminService.saveGenre(genre);
		message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Genre added Sucessfully</div>";
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
		}else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Genre Name cannot be Empty</div>";
		redirectUrl = "/addgenre.jsp";
		}
		request.setAttribute("statusMessage", message);
		 return redirectUrl;
		}
		
		private String deleteGenreBook(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
				throws ServletException, IOException {
			String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Book deleted Sucessfully for this Genre</div>";
			if(request.getParameter("genreId")!=null && request.getParameter("bookId")!=null){
				Integer genreId = Integer.parseInt(request.getParameter("genreId"));
				Integer bookId = Integer.parseInt(request.getParameter("bookId"));
				Genre genre = new Genre();
				genre.setGenreId(genreId);
				try {
					adminService.deleteBookGenre(genre,bookId);
					request.setAttribute("genreId",genreId);
					redirectUrl = "/editgenre.jsp";
					
				} catch (SQLException e) {
					e.printStackTrace();
					message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Genre deleted failed. Contact Admin</div>";
				}
				request.setAttribute("statusMessage", message);
				return redirectUrl;
			}
			
			return null;
		}
		private String deleteGenreBook1(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
				throws ServletException, IOException {
			String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Book deleted Sucessfully for this Genre</div>";
			if(request.getParameter("genreId")!=null && request.getParameter("bookId")!=null){
				Integer genreId = Integer.parseInt(request.getParameter("genreId"));
				Integer bookId = Integer.parseInt(request.getParameter("bookId"));
				Genre genre = new Genre();
				genre.setGenreId(genreId);
				try {
					adminService.deleteBookGenre(genre,bookId);
					request.setAttribute("bookId",bookId);
					redirectUrl = "/editbooks.jsp";
					
				} catch (SQLException e) {
					e.printStackTrace();
					message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Genre deleted failed. Contact Admin</div>";
				}
				request.setAttribute("statusMessage", message);
				return redirectUrl;
			}
			
			return null;
		}

		private String pageGenres(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
				throws ServletException, IOException {
			if(request.getParameter("pageNo")!=null){
				Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
				try {
					request.setAttribute("genres", adminService.readGenre(null, pageNo));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return redirectUrl;
			}
			return null;
		}
		
		private String deleteGenre(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
				throws ServletException, IOException {
			String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Genre deleted Sucessfully</div>";
			if(request.getParameter("genreId")!=null){
				Integer genreId = Integer.parseInt(request.getParameter("genreId"));
				Genre genre = new Genre();
				genre.setGenreId(genreId);
				try {
					adminService.deleteGenre(genre);
				} catch (SQLException e) {
					e.printStackTrace();
					message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Genre deleted failed. Contact Admin</div>";
				}
				request.setAttribute("statusMessage", message);
				return redirectUrl;
			}
			
			return null;
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
	private String deleteAuthorBook(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Book deleted Sucessfully for this Author</div>";
		if(request.getParameter("authorId")!=null && request.getParameter("bookId")!=null){
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			Author author = new Author();
			author.setAuthorId(authorId);
			try {
				adminService.deleteBookAuthor(author,bookId);
				request.setAttribute("authorId",authorId);
				redirectUrl = "/editauthor.jsp";
				
			} catch (SQLException e) {
				e.printStackTrace();
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Author deleted failed. Contact Admin</div>";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}

	private String addAuthor(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Author author = new Author();
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Author added Sucessfully</div>";
		
		if (request.getParameter("authorName") != null && !request.getParameter("authorName").isEmpty()) {
			if(request.getParameter("authorName").length() > 45){
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Author Name cannot be more than 45 chars</div>";
				if(editMode) {
					
					request.setAttribute("authorId", Integer.parseInt(request.getParameter("authorId")));
			
					redirectUrl = "/editauthor.jsp";
				}
				else {
				redirectUrl = "/addauthor.jsp";
				}
			}else{
				author.setAuthorName(request.getParameter("authorName"));
				String[] bookIds = request.getParameterValues("bookIds");
				try {
					if(bookIds !=null)
						author.setBooks(adminService.getBooks(bookIds));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(editMode){
					author.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
				}
				try {
					request.setAttribute("pageNo", 1);
					adminService.saveAuthor(author);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\">Author Name cannot be empty.</div>";
			if(editMode) {
				redirectUrl = "/editauthor.jsp";
			}
			else {
			redirectUrl = "/addauthor.jsp";
			}
		}
		request.setAttribute("pageNo", 1);
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	private String deleteAuthor(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Author deleted Sucessfully</div>";
		if(request.getParameter("authorId")!=null){
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));
			Author author = new Author();
			author.setAuthorId(authorId);
			try {
				adminService.deleteAuthor(author);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Author deleted failed. Contact Admin</div>";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	
	private String pageAuthors(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("authors", adminService.readAuthors(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		
		return null;
	}
	
	
	
			
	private void searchAuthor(HttpServletRequest request,  HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		if(request.getParameter("pageNo")!=null && request.getParameter("searchAuthor")!= null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));			
			StringBuffer str =new StringBuffer();
			if(request.getParameter("pageNo")!=null){
				Integer totalCount =-1;
				if(request.getParameter("searchAuthor") != null && ! request.getParameter("searchAuthor").isEmpty()){
					 totalCount = adminService.getAuthorsCount(request.getParameter("searchAuthor"));
				}
				else{
					 totalCount = adminService.getAuthorsCount(null);
				}
				int numOfPages = 0;
				if(totalCount%4 > 0){
					numOfPages = totalCount/4 +1;
				}else{
					numOfPages = totalCount/4;
				}
				
				
				
				str.append("<h4 style=\"padding-left: 20%\">Authors List in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Authors in LMS:"+totalCount+"Authors</h4><br>");
				str.append("<nav aria-label=\"Page navigation example\"><ul class=\"pagination\" style=\"padding-left: 26%\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\"aria-label=\"Previous\" onclick='searchAuthors("+(pageNo-1)+"); return false;'");
				if(pageNo == 1)
					str.append(" style='display:none;' ");
				str.append("> <span aria-hidden=\"true\">&laquo;</span> <spanclass=\"sr-only\">Previous</span></a></li>");
				for(int i=1; i<=numOfPages; i++){
					if(request.getParameter("searchAuthor") != null && ! request.getParameter("searchAuthor").isEmpty()){
						//str.append("<li class=\"page-item\"><a class=\"page-link\" href=\"searchAuthor?pageNo="+i+"&searchAuthor="+request.getParameter("searchAuthor")+"\">"+i+"</a></li>");

						str.append("<li class=\"page-item\" ><a class=\"page-link\" href=\"#\" onclick='searchAuthors("+i+"); return false;'>"+i+"</a></li>");	
					}
					else {
						str.append("<li class=\"page-item\" ><a class=\"page-link\" href=\"#\" onclick='searchAuthors("+i+"); return false;'>"+i+"</a></li>");
					}
				
				}
				str.append("<li class=\"page-item\"><a class=\"page-link\" href=\"#\"aria-label=\"Next\" onclick='searchAuthors("+(pageNo+1)+"); return false;'");
				if(pageNo == numOfPages)
					str.append(" style='display:none;' ");
				str.append("> <span aria-hidden=\"true\">&raquo;</span> <spanclass=\"sr-only\">Next</span></a></li></ul>");
				
			str.append("<table class='table table-striped' id='authorTable'><tr><th>#</th><th>AuthorName</th><th>Books Written</th><th>Edit Author</th><th>Delete Author</th></tr>");
			try {
				List<Author> authors = adminService.readAuthors(request.getParameter("searchAuthor"), pageNo);
				for (Author a : authors) {
					Integer index = (authors.indexOf(a) + 1);
					str.append("<tr><td>"+index+"</td><td>"+a.getAuthorName()+"</td><td><ul style='padding-right: 50%; list-style-type: none;'>");
					for (Book b : a.getBooks()) {
						str.append("<li>"+b.getTitle()+"</li>");
					}
					str.append("</ul></td>");
					str.append("<td><button type='button' class='btn btn-primary btn-sm'data-toggle='modal'data-remote='editauthor.jsp?authorId="+a.getAuthorId()+"'data-target='#myModel'>Edit</button><td>");
					str.append("<button type='button'onclick='javascript:location.href='deleteAuthor?authorId="+a.getAuthorId()+"' class='btn btn-danger btn-sm'>Delete</button></td>");
					str.append("</tr>");
				}
				str.append("</table>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.getWriter().write(str.toString());
		}
		
	}
		}
	}
