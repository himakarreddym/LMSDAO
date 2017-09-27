package com.gcit.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.entity.LibraryBranches;
import com.gcit.lms.entity.BookCopies;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class LibraryBranchDAO extends BaseDAO {

	public LibraryBranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void saveBranches(LibraryBranches braches) throws SQLException {
		save("INSERT INTO tbl_library_branch (branchName,branchAddress) VALUES (?,?)", new Object[] { braches.getBranchName(),braches.getBranchAddress() });
	}
	
//	public void saveBookLibraryBranches(LibraryBranches braches) throws SQLException {
//		for(Book b: braches.getBooks()){
//			save("INSERT INTO tbl_book VALUES (?, ?,?)", new Object[] { b.getBookId(), b.getTitle(),braches.getBranchId()});
//		}
//	}
	
	public Integer saveBranchesWithID(LibraryBranches braches) throws SQLException {
		return saveWithID("INSERT INTO tbl_library_branch (branchName,branchAddress) VALUES (?,?)", new Object[] { braches.getBranchName(),braches.getBranchAddress() });
	}

	public void updateLibraryBranches(LibraryBranches braches) throws SQLException {
		save("UPDATE tbl_library_branch SET branchName = ?,branchAddress=? WHERE branchId = ?",
				new Object[] { braches.getBranchName(),braches.getBranchAddress() ,braches.getBranchId() });
	}

	public void deleteLibraryBranches(LibraryBranches braches) throws SQLException {
		save("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[] { braches.getBranchId() });
	}
	
	

	public List<LibraryBranches> readBranchs(String branchName) throws SQLException {
		if(branchName !=null && !branchName.isEmpty()){
			branchName = "%"+branchName+"%";
			return readAll("SELECT * FROM tbl_library_branch WHERE branchName like ?", new Object[]{branchName});
		}else{
			return readAll("SELECT * FROM tbl_library_branch", null);
		}
		
	}

	@Override
	public List<LibraryBranches> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranches> brachess = new ArrayList<>();
		BookCopiesDAO bcdao = new BookCopiesDAO(conn);
		while(rs.next()){
			LibraryBranches a = new LibraryBranches();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBookcopies(bcdao.readAllFirstLevel("SELECT * FROM tbl_book_copies WHERE branchId = ?", new Object[]{a.getBranchId()}));
			brachess.add(a);
		}
		
		return brachess;
	}
	
	@Override
	public List<LibraryBranches> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<LibraryBranches> brachess = new ArrayList<>();
		while(rs.next()){
			LibraryBranches a = new LibraryBranches();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
			brachess.add(a);
		}
		
		return brachess;
	}

}
