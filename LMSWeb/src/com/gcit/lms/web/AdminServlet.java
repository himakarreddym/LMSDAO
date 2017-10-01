package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/addAuthor", "/deleteAuthor", "/editAuthor", "/pageAuthors","/deleteAuthorBook",
	"/addGenre","/pageGenres","/deleteGenre","/deleteGenreBook","/editGenre",
	"/addPublisher","/deletePublisher","/pagePublishers","/editPublisher",
	"/addBorrower","/editBorrower","/deleteBorrower","/pageBorrowers",
	"/addbranch","/pageLibraryBranchs","/editLibraryBranch","/deleteLibraryBranch",
	"/addBook","/pageBooks","/deleteBook","/editBook","/deleteBookAuthor"})
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
			
		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = null;
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
			redirectUrl=addbranch(request, "/viewbranches.jsp", false);
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
		default:
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
	private String addBook(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Book book = new Book();
		String message = "Book added Sucessfully";
		
		if (request.getParameter("bookName") != null && !request.getParameter("bookName").isEmpty()) {
			if(request.getParameter("bookName").length() > 45){
				message = "Book Name cannot be more than 45 chars";
				redirectUrl = "/addbook.jsp";
			}else{
				book.setTitle(request.getParameter("bookName"));
				String[] authorIds = request.getParameterValues("authorIds");
				try {
					if(authorIds !=null)
						book.setAuthors(adminService.getAuthors(authorIds));
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
			message = "Book Name cannot be Empty";
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
		String message = "Book deleted Sucessfully";
		if(request.getParameter("bookId")!=null){
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new Book();
			book.setBookId(bookId);
			try {
				adminService.deleteBook(book);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Book deletion failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	private String deleteBookAuthor(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Author deleted Sucessfully for this Book";
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
				message = "Author deleted failed with the book. Contact Admin";
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
		String message = "Entered Add Branch";
		
		if (request.getParameter("branchName") != null && !request.getParameter("branchName").isEmpty()) {
		if(request.getParameter("branchName").length() > 45){
		message = "Branch Name cannot be more than 45 chars";
		redirectUrl = "/addbranch.jsp";
		}else{
		branch.setBranchName(request.getParameter("branchName"));
		branch.setBranchAddress(request.getParameter("branchAddress"));
	
		if(editMode){
		branch.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		}
		try {
		adminService.saveLibraryBranch(branch);
		message  = "LibraryBranch Added/Updated Sucessfully";
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
		}else{
		message = "LibraryBranch Name cannot be Empty";
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
		String message = "Branch deleted Sucessfully";
		if(request.getParameter("branchId")!=null){
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			LibraryBranch lb = new LibraryBranch();
			lb.setBranchId(branchId);
			try {
				adminService.deleteLibraryBranch(lb);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Branch delete failed. Contact Admin";
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
		String message = "Entered Add Borrower";
		
		if (request.getParameter("borrowerName") != null && !request.getParameter("borrowerName").isEmpty()) {
		if(request.getParameter("borrowerName").length() > 45){
		message = "Borrower Name cannot be more than 45 chars";
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
		message  = "Borrower added Sucessfully";
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
		}else{
		message = "Borrower Name cannot be Empty";
		redirectUrl = "/addborrower.jsp";
		}
		request.setAttribute("statusMessage", message);
		 return redirectUrl;
		}	
	
	private String deleteBorrower(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Borrower deleted Sucessfully";
		if(request.getParameter("cardNo")!=null){
			Integer borrowerId = Integer.parseInt(request.getParameter("cardNo"));
			Borrower bor = new Borrower();
			bor.setCardNo(borrowerId);
			try {
				adminService.deleteBorrower(bor);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Borrower deleted failed. Contact Admin";
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
		String message = "Entered Add Publisher";
		
		if (request.getParameter("publisherName") != null && !request.getParameter("publisherName").isEmpty()) {
		if(request.getParameter("publisherName").length() > 45){
		message = "Publisher Name cannot be more than 45 chars";
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
		message  = "Publisher added Sucessfully";
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
		}else{
		message = "Publisher Name cannot be Empty";
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
		String message = "Publisher deleted Sucessfully";
		if(request.getParameter("publisherId")!=null){
			Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
			Publisher publisher = new Publisher();
			publisher.setPublisherId(publisherId);
			try {
				adminService.deletePublisher(publisher);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Publisher deleted failed. Contact Admin";
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
		String message = "Entered Add genre";
		
		if (request.getParameter("genreName") != null && !request.getParameter("genreName").isEmpty()) {
		if(request.getParameter("genreName").length() > 45){
		message = "Genre Name cannot be more than 45 chars";
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
		message  = "Genre added Sucessfully";
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
		}else{
		message = "Genre Name cannot be Empty";
		redirectUrl = "/addgenre.jsp";
		}
		request.setAttribute("statusMessage", message);
		 return redirectUrl;
		}
		
		private String deleteGenreBook(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
				throws ServletException, IOException {
			String message = "Book deleted Sucessfully for this Genre";
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
					message = "Genre deleted failed. Contact Admin";
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
			String message = "Genre deleted Sucessfully";
			if(request.getParameter("genreId")!=null){
				Integer genreId = Integer.parseInt(request.getParameter("genreId"));
				Genre genre = new Genre();
				genre.setGenreId(genreId);
				try {
					adminService.deleteGenre(genre);
				} catch (SQLException e) {
					e.printStackTrace();
					message = "Genre deleted failed. Contact Admin";
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
		String message = "Book deleted Sucessfully for this Author";
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
				message = "Author deleted failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}

	private String addAuthor(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Author author = new Author();
		String message = "Author added Sucessfully";
		
		if (request.getParameter("authorName") != null && !request.getParameter("authorName").isEmpty()) {
			if(request.getParameter("authorName").length() > 45){
				message = "Author Name cannot be more than 45 chars";
				redirectUrl = "/addauthor.jsp";
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
					adminService.saveAuthor(author);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{
			message = "Author Name cannot be Empty";
			redirectUrl = "/addauthor.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	private String deleteAuthor(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Author deleted Sucessfully";
		if(request.getParameter("authorId")!=null){
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));
			Author author = new Author();
			author.setAuthorId(authorId);
			try {
				adminService.deleteAuthor(author);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Author deleted failed. Contact Admin";
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
	
	
	
}