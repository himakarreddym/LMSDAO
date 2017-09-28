package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibraryBranch;

public class LibrarianService {

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
	 * Update library branches
	 * 
	 */
	
	public void saveLibraryBranch(LibraryBranch borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			if(borrower.getBranchId()!=null){
				lbdao.updateLibraryBranches(borrower);
			}else{
				lbdao.saveLibraryBranches(borrower);
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
	 * Read book copies
	 * 
	 */
	
	public List<BookCopies> readBookCopies(int bookId, int branchId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			return bcdao.readBookCopies(bookId, branchId);
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
	
	
}
