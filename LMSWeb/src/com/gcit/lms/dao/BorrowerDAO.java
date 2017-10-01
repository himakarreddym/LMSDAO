package com.gcit.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Publisher;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BorrowerDAO extends BaseDAO {

	
	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void saveBorrower(Borrower borrower) throws SQLException {
		save("INSERT INTO tbl_borrower (name,address,phone) VALUES (?,?,?)", new Object[] { borrower.getName(),borrower.getAddress(),borrower.getPhone() });
	}
	
//	public void saveBookBorrower(Borrower borrower) throws SQLException {
//		for(Book b: borrower.getBooks()){
//			save("INSERT INTO tbl_book VALUES (?, ?,?)", new Object[] { b.getBookId(), b.getTitle(),borrower.getCardNo()});
//		}
//	}
	
	public Integer saveBorrowerWithID(Borrower borrower) throws SQLException {
		return saveWithID("INSERT INTO tbl_borrower (name,address,phone) VALUES (?,?,?)", new Object[] { borrower.getName(),borrower.getAddress(),borrower.getPhone() });
	}

	public void updateBorrower(Borrower borrower) throws SQLException {
		save("UPDATE tbl_borrower SET name = ?,address=?,phone = ? WHERE cardNo = ?",
				new Object[] { borrower.getName(),borrower.getAddress(),borrower.getPhone() ,borrower.getCardNo() });
	}

	public void deleteBorrower(Borrower borrower) throws SQLException {
		save("DELETE FROM tbl_borrower WHERE cardNo = ?", new Object[] { borrower.getCardNo() });
	}

	public Integer getBorrowersCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_borrower", null);
	}

	public List<Borrower> readBorrowers(String name) throws SQLException {
		if(name !=null && !name.isEmpty()){
			name = "%"+name+"%";
			return readAll("SELECT * FROM tbl_borrower WHERE name like ?", new Object[]{name});
		}else{
			return readAll("SELECT * FROM tbl_borrower", null);
		}
		
	}
	public List<Borrower> readBorrowers1(String name,int pageNo) throws SQLException {
		setPageNo(pageNo);
		if(name !=null && !name.isEmpty()){
			name = "%"+name+"%";
			return readAll("SELECT * FROM tbl_borrower WHERE name like ?", new Object[]{name});
		}else{
			return readAll("SELECT * FROM tbl_borrower", null);
		}
		
	}
	public Borrower readBorrowerByPK(Integer cardNo) throws SQLException {
		List<Borrower> borrowers = readAll("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{cardNo});
		if(borrowers.size() > 0){
			return borrowers.get(0);
		}
		return null;
	}
	public List<Borrower> readBorrowerBybranch(Integer branchId,Integer cardNo) throws SQLException {
			return (readAll("SELECT * FROM tbl_borrower WHERE cardNo = ? AND branchId = ?", new Object[]{cardNo,branchId}));
	
	}
	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		BookLoansDAO bldao = new BookLoansDAO(conn);
		while(rs.next()){
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));
			a.setAddress(rs.getString("address"));
			a.setPhone(rs.getString("phone"));
			a.setBookloans(bldao.readAllFirstLevel("SELECT * FROM tbl_book_loans WHERE cardNo = ?", new Object[]{a.getCardNo()}));
			borrowers.add(a);
		}
		
		return borrowers;
	}
	
	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while(rs.next()){
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));
			a.setAddress(rs.getString("address"));
			a.setPhone(rs.getString("phone"));
			borrowers.add(a);
		}
		
		return borrowers;
	}
}
