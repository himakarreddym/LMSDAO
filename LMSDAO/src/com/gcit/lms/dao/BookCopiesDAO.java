package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibraryBranch;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class BookCopiesDAO extends BaseDAO {

	public BookCopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void saveBookCopies(BookCopies bookcopies) throws SQLException {
		save("INSERT INTO tbl_book_copies (bookId,branchId,noOfCopies) VALUES (?,?,?)", new Object[] { bookcopies.getBookId(),bookcopies.getBranchId(),bookcopies.getCopies() });
	}
	
//	public void saveBookBookCopies(BookCopies bookcopies) throws SQLException {
//		for(Book b: bookcopies.getBooks()){
//			save("INSERT INTO tbl_book VALUES (?, ?,?)", new Object[] { b.getBookId(), b.getTitle(),bookcopies.getPublisherId()});
//		}
//	}
	
	public Integer saveBookCopiesWithID(BookCopies bookcopies) throws SQLException {
		return saveWithID("INSERT INTO tbl_book_copies (bookId,branchId,noOfCopies) VALUES (?,?,?)", new Object[] { bookcopies.getBookId(),bookcopies.getBranchId(),bookcopies.getCopies() });
	}

	public void updateBookCopies(BookCopies bookcopies) throws SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?",
				new Object[] { bookcopies.getCopies(),bookcopies.getBookId(),bookcopies.getBranchId() });
	}

	public void deleteBookCopies(BookCopies bookcopies) throws SQLException {
		save("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[] { bookcopies.getBookId(),bookcopies.getBranchId() });
	}
	
	

	public List<BookCopies> readBookCopies(int bookId, int branchId) throws SQLException {
		if(bookId !=0 && branchId !=0 ){
			return readAll("SELECT * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[]{bookId,branchId});
		}else{
			return readAll("SELECT * FROM tbl_book_copies", null);
		}
		
	}

	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException {
		List<BookCopies> bookcopiess = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
		while(rs.next()){
			BookCopies a = new BookCopies();
			a.setBookId(rs.getInt("bookId"));
			a.setBranchId(rs.getInt("branchId"));
			a.setCopies(rs.getInt("noOfCopies"));
			List<Book> bk = (bdao.readAllFirstLevel("SELECT * FROM tbl_book WHERE bookId = ?", new Object[]{a.getBookId()}));
			a.setBook(bk.get(0));
			List<LibraryBranch> lb = (lbdao.readAllFirstLevel("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[]{a.getBranchId()}));
			a.setBranch(lb.get(0));
			bookcopiess.add(a);
		}
		
		return bookcopiess;
	}
	
	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<BookCopies> bookcopiess = new ArrayList<>();
		while(rs.next()){
			BookCopies a = new BookCopies();
			a.setBookId(rs.getInt("bookId"));
			a.setBranchId(rs.getInt("branchId"));
			a.setCopies(rs.getInt("noOfCopies"));
			bookcopiess.add(a);
		}
		
		return bookcopiess;
	}

}
