package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.service.BorrowerService;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/checkbook","/checkcard","/checkInBook"})
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BorrowerService borService = new BorrowerService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = "/borrowermenu.jsp";
		switch (reqUrl) {
		
			
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
		String redirectUrl = "/borrowermenu.jsp";
		switch (reqUrl) {
		case "/checkcard":
			redirectUrl = checkCard(request, redirectUrl);
			break;
		case "/checkInBook":
			redirectUrl = checkInBook(request, redirectUrl);
			break;
		case "/checkbook":
			redirectUrl = checkoutBook(request, redirectUrl);
			break;
		default:
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
		
	}
	
	private String checkCard(HttpServletRequest request, String redirectUrl) {
		String message = null;
		Borrower bor;
		try {
			bor = borService.readBorrowerByPK(Integer.parseInt(request.getParameter("cardNo")));
			System.out.println("bor"+bor);
			if(bor != null) {
				message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Welcome Mr/Mrs :"+bor.getName() +"</strong></div>";
				request.setAttribute("cardNo", bor.getCardNo());
				if(request.getParameter("branchId") == null) {
				request.setAttribute("branchId", 1);
				}
				else {
					request.setAttribute("branchId",request.getParameter("branchId"));
				}
				redirectUrl = "/borrowermenu.jsp";
				
			}
			else{
				message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Please enter the valid card number and Try Again!!!!</div>";
				redirectUrl = "/borrower.jsp";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	private String checkoutBook(HttpServletRequest request, String redirectUrl) {
		BookLoans bookloans = new BookLoans();
		String message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Sucessfully Borrowered the book!!!Enjoy Reading!!!</div>";
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		if(bookId !=0 && branchId !=0 && cardNo !=0) {
			bookloans.setBookId(bookId);	
			bookloans.setBranchId(branchId);;	
			bookloans.setCardNo(cardNo);	
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime() + 7l*24l*60l*60l*1000l);
			bookloans.setDateOut(date);
			bookloans.setDueDate(date1);
			bookloans.setDateIn(null);
		BookCopies bc = new BookCopies();
		bc.setBookId(bookId);
		bc.setBranchId(branchId);
				try {
					List<BookCopies> bookcopies = borService.readBookCopies(bc);
					BookCopies bc1 = bookcopies.get(0);
					bc.setCopies(bc1.getCopies() -1);
					borService.saveBookLoans(bookloans);
					borService.saveBookCopies(bc);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Currently You cannot borrow the book!! Sorry for inconvinience</div>";
			redirectUrl = "/borrowermenu.jsp";
		}
		request.setAttribute("statusMessage", message);
		request.setAttribute("cardNo", cardNo);
		request.setAttribute("branchId", branchId);
		return redirectUrl;
	}
	
	private String checkInBook(HttpServletRequest request, String redirectUrl)  {
		BookLoans bookloans = new BookLoans();
		String message = null;
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		Timestamp dateOut;
		Date dueDate;
		if(bookId !=0 && branchId !=0 && cardNo !=0) {
		dateOut = Timestamp.valueOf((request.getParameter("dateOut")));
		dueDate = Timestamp.valueOf((request.getParameter("dueDate")));
			bookloans.setBookId(bookId);	
			bookloans.setBranchId(branchId);;	
			bookloans.setCardNo(cardNo);	
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			bookloans.setDateOut(dateOut);
			bookloans.setDueDate(dueDate);
			bookloans.setDateIn(date);
			BookCopies bc = new BookCopies();
			bc.setBookId(bookId);
			bc.setBranchId(branchId);
		try {
			borService.updateBookLoans(bookloans);
			List<BookCopies> bookcopies = borService.readBookCopies(bc);
			BookCopies bc1 = bookcopies.get(0);
			bc.setCopies(bc1.getCopies() + 1);
			message = "<div class=\"alert alert-success alert-dismissable custom-alert\" role=\"alert\"><strong>Success!!!</strong>Sucessfully Returned the book!!!Thank You !!!</div>";
			borService.saveBookCopies(bc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		}else{
			message = "<div class=\"alert alert-danger alert-dismissable custom-alert\" role=\"alert\"><strong>Warning!</strong>Currently You cannot borrow the book!! Sorry for inconvinience</div>";
			redirectUrl = "/borrowermenu.jsp";
		}
		request.setAttribute("statusMessage", message);
		request.setAttribute("cardNo", cardNo);
		request.setAttribute("branchId", branchId);
		return redirectUrl;
	}
}
