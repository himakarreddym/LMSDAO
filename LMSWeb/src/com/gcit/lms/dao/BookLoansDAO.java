package com.gcit.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Borrower;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class BookLoansDAO extends BaseDAO {

	public BookLoansDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void saveBookLoans(BookLoans bookloans) throws SQLException {
		save("INSERT INTO tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate,dateIn) VALUES (?,?,?,?,?,?)", 
			new Object[] { bookloans.getBookId(),bookloans.getBranchId(),bookloans.getCardNo(),bookloans.getDateOut(),bookloans.getDueDate(),bookloans.getDateIn() });
	}
	
//	public void saveBookBookLoans(BookLoans bookloans) throws SQLException {
//		for(Book b: bookloans.getBooks()){
//			save("INSERT INTO tbl_book VALUES (?, ?,?)", new Object[] { b.getBookId(), b.getTitle(),bookloans.getCardNo()});
//		}
//	}
	
//	public Integer saveBookLoansWithID(BookLoans bookloans) throws SQLException {
//		return saveWithID("INSERT INTO tbl_book_loans (name,address,phone) VALUES (?,?,?)", new Object[] { bookloans.getName(),bookloans.getAddress(),bookloans.getPhone() });
//	}

	public void updateBookLoans(BookLoans bookloans) throws SQLException {
		save("UPDATE tbl_book_loans SET dueDate=?,dateIn = ? WHERE bookId = ? AND branchId = ? AND cardNo= ? And dateOut =? ",
			new Object[] { bookloans.getDueDate(),bookloans.getDateIn() ,bookloans.getBookId(),bookloans.getBranchId(),bookloans.getCardNo(),bookloans.getDateOut() });
	}

	public void deleteBookLoans(BookLoans bookloans) throws SQLException {
		save("DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo= ? And dateOut =?", 
				new Object[] { bookloans.getBookId(),bookloans.getBranchId(),bookloans.getCardNo(),bookloans.getDateOut() });
	}
	
	

	public List<BookLoans> readBookLoans(BookLoans bookloans) throws SQLException {
		if(bookloans.getBookId() != 0 && bookloans.getBranchId() != 0 && bookloans.getCardNo() != 0){
			return readAll("SELECT * FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo= ?", 
					new Object[]{bookloans.getBookId(),bookloans.getBranchId(),bookloans.getCardNo()});
		}else{
			return readAll("SELECT * FROM tbl_book_loans", null);
		}
		
	}

	public List<BookLoans> readBookLoansBycard (BookLoans bookloans) throws SQLException {
			return readAll("SELECT * FROM tbl_book_loans WHERE branchId = ? AND cardNo= ? AND dateIn is null", 
					new Object[]{bookloans.getBranchId(),bookloans.getCardNo()});
		
	}
	@Override
	public List<BookLoans> extractData(ResultSet rs) throws SQLException {
		List<BookLoans> bookloanss = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
		BorrowerDAO brdao = new BorrowerDAO(conn);
		while(rs.next()){
			BookLoans a = new BookLoans();
			a.setBookId(rs.getInt("bookId"));
			a.setBranchId(rs.getInt("branchId"));
			a.setCardNo(rs.getInt("cardNo"));
			a.setDateOut(rs.getTimestamp("dateOut"));
			a.setDueDate(rs.getTimestamp("dueDate"));
			a.setDateIn(rs.getTimestamp("dateIn"));
			List<Book> bk = (bdao.readAllFirstLevel("SELECT * FROM tbl_book WHERE bookId = ?", new Object[]{a.getBookId()}));
			a.setBook(bk.get(0));
			List<LibraryBranch> lb = (lbdao.readAllFirstLevel("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[]{a.getBranchId()}));
			a.setBranch(lb.get(0));
			List<Borrower> br = (brdao.readAllFirstLevel("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{a.getCardNo()}));
			a.setBorrower(br.get(0));
			bookloanss.add(a);
		}
		
		return bookloanss;
	}
	
	@Override
	public List<BookLoans> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<BookLoans> bookloanss = new ArrayList<>();
		while(rs.next()){
			BookLoans a = new BookLoans();
			a.setBookId(rs.getInt("bookId"));
			a.setBranchId(rs.getInt("branchId"));
			a.setCardNo(rs.getInt("cardNo"));
			a.setDateOut(rs.getTimestamp("dateOut"));
			a.setDueDate(rs.getTimestamp("dueDate"));
			a.setDateIn(rs.getTimestamp("dateIn"));
			bookloanss.add(a);
		}
		
		return bookloanss;
	}

}
