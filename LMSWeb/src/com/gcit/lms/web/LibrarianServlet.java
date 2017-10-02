package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.service.LibrarianService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/editBookcopies","/editBranchInf"})
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LibrarianService libService = new LibrarianService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = "/librarian.jsp";
		switch (reqUrl) {
		case "/editBookcopies":
			redirectUrl = updateCopies(request, redirectUrl);
			break;
		case "/editBranchInf":
			redirectUrl = updateBranch(request, redirectUrl);
			break;
		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
		
	}
	
	private String updateBranch(HttpServletRequest request, String redirectUrl) {
		LibraryBranch branch = new LibraryBranch();
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Branch Information Updated sucessfully</div>";
		branch.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		
		
		if(! (request.getParameter("branchName")).equalsIgnoreCase("N/A") ) {
			
			
		branch.setBranchName(request.getParameter("branchName"));
		branch.setBranchAddress(request.getParameter("branchAddress"));
			try {
			libService.saveLibraryBranch(branch);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Book Copies should be atleast 1</div>";
			redirectUrl = "/editbookcopies.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	
	private String updateCopies(HttpServletRequest request, String redirectUrl) {
		BookCopies bookCopies = new BookCopies();
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Book Copies Updated sucessfully</div>";
		bookCopies.setBookId(Integer.parseInt(request.getParameter("bookId")));
		bookCopies.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		bookCopies.setCopies(Integer.parseInt(request.getParameter("noOfCopies")));
		if ( bookCopies.getCopies() != 0 ) {	
			try {
			libService.saveBookCopies(bookCopies);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Book Copies should be atleast 1</div>";
			redirectUrl = "/editbookcopies.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
}
