package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;

public class BorrowerService {

	public Utilities util = new Utilities();
	
	/**
	 * Read library branches
	 * 
	 */
	
	public List<LibraryBranch> readLibraryBranch(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			return lbdao.readBranchs(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

		return null;
	}
	
	/**
	 * Read book loans
	 * 
	 */
	
	public List<BookLoans> readBookLoans(BookLoans bookloans) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bcdao = new BookLoansDAO(conn);
			return bcdao.readBookLoans(bookloans);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

		return null;
	}
	/**
	 * Read book loans from card and branchId
	 * 
	 */
	
	public List<BookLoans> readBookLoansBycard(BookLoans bookloans) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bcdao = new BookLoansDAO(conn);
			return bcdao.readBookLoansBycard(bookloans);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

		return null;
	}
	/**
	 * Update book loans
	 * 
	 */
	
	public void saveBookLoans(BookLoans bookloans) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bcdao = new BookLoansDAO(conn);
				bcdao.saveBookLoans(bookloans);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void updateBookLoans(BookLoans bookloans) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bcdao = new BookLoansDAO(conn);
				bcdao.updateBookLoans(bookloans);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	
	/**
	 * Read book copies
	 * 
	 */
	
	public List<BookCopies> readBookCopies(BookCopies bookcopies) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			return bcdao.readBookCopies(bookcopies);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

		return null;
	}
	
	public List<BookCopies> readBookCopiesbyCond(BookCopies bookcopies) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			return bcdao.readBookCopiesbyCond(bookcopies);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

		return null;
	}
	/**
	 * Update book copies
	 * 
	 */
	
	public void saveBookCopies(BookCopies bookcopies) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			if(bookcopies.getBranchId()!=null && bookcopies.getBookId() != null){
				bcdao.updateBookCopies(bookcopies);
			}else{
				bcdao.saveBookCopies(bookcopies);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	/**
	 * Read card number
	 * 
	 */
	public Borrower readBorrowerByPK(int cardNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO brdao = new BorrowerDAO(conn);
			return brdao.readBorrowerByPK(cardNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

		return null;
	}
	
	
}
