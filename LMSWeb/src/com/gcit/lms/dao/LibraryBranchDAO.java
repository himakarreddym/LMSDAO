package com.gcit.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class LibraryBranchDAO extends BaseDAO {

	public LibraryBranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void saveLibraryBranches(LibraryBranch braches) throws SQLException {
		save("INSERT INTO tbl_library_branch (branchName,branchAddress) VALUES (?,?)", new Object[] { braches.getBranchName(),braches.getBranchAddress() });
	}
	
//	public void saveBookLibraryBranches(LibraryBranches braches) throws SQLException {
//		for(Book b: braches.getBooks()){
//			save("INSERT INTO tbl_book VALUES (?, ?,?)", new Object[] { b.getBookId(), b.getTitle(),braches.getBranchId()});
//		}
//	}
	
	public Integer saveLibraryBranchesWithID(LibraryBranch braches) throws SQLException {
		return saveWithID("INSERT INTO tbl_library_branch (branchName,branchAddress) VALUES (?,?)", new Object[] { braches.getBranchName(),braches.getBranchAddress() });
	}

	public void updateLibraryBranches(LibraryBranch braches) throws SQLException {
		save("UPDATE tbl_library_branch SET branchName = ?,branchAddress=? WHERE branchId = ?",
				new Object[] { braches.getBranchName(),braches.getBranchAddress() ,braches.getBranchId() });
	}

	public void deleteLibraryBranches(LibraryBranch braches) throws SQLException {
		save("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[] { braches.getBranchId() });
	}
	
	

	public List<LibraryBranch> readBranchs(String branchName) throws SQLException {
		if(branchName !=null && !branchName.isEmpty()){
			branchName = "%"+branchName+"%";
			return readAll("SELECT * FROM tbl_library_branch WHERE branchName like ?", new Object[]{branchName});
		}else{
			return readAll("SELECT * FROM tbl_library_branch", null);
		}
		
	}
	public List<LibraryBranch> readBranch(String branchName,Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if(branchName  !=null && !branchName.isEmpty()){
			branchName  = "%"+branchName+"%";
			return readAll("SELECT * FROM tbl_library_branch WHERE branchName like ?", new Object[]{branchName});
		}else{
			return readAll("SELECT * FROM tbl_library_branch", null);
		}
		
	}
	
	public LibraryBranch readbranchByPK(Integer branchId) throws SQLException {
		List<LibraryBranch> branches = readAll("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[]{branchId});
		if(branches!=null){
			return branches.get(0);
		}
		return null;
	}
	
	public Integer getbarnchesCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_library_branch", null);
	}

	@Override
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> brachess = new ArrayList<>();
		BookCopiesDAO bcdao = new BookCopiesDAO(conn);
		while(rs.next()){
			LibraryBranch a = new LibraryBranch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
			a.setBookcopies(bcdao.readAllFirstLevel("SELECT * FROM tbl_book_copies WHERE branchId = ?", new Object[]{a.getBranchId()}));
			brachess.add(a);
		}
		
		return brachess;
	}
	
	@Override
	public List<LibraryBranch> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<LibraryBranch> brachess = new ArrayList<>();
		while(rs.next()){
			LibraryBranch a = new LibraryBranch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
			brachess.add(a);
		}
		
		return brachess;
	}

}
