package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class PublisherDAO extends BaseDAO {
	public PublisherDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void savePublisher(Publisher publisher) throws SQLException {
		save("INSERT INTO tbl_publisher (publisherName,publisherAddress,publisherPhone) VALUES (?,?,?)", new Object[] { publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone() });
	}
	
	public void saveBookPublisher(Publisher publisher) throws SQLException {
		for(Book b: publisher.getBooks()){
			save("INSERT INTO tbl_book VALUES (?, ?,?)", new Object[] { b.getBookId(), b.getTitle(),publisher.getPublisherId()});
		}
	}
	
	public Integer savePublisherWithID(Publisher publisher) throws SQLException {
		return saveWithID("INSERT INTO tbl_publisher (publisherName,publisherAddress,publisherPhone) VALUES (?,?,?)", new Object[] { publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone() });
	}

	public void updatePublisher(Publisher publisher) throws SQLException {
		save("UPDATE tbl_publisher SET publisherName = ?,publisherAddress=?,publisherPhone = ? WHERE publisherId = ?",
				new Object[] { publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone() ,publisher.getPublisherId() });
	}
	public Integer getPublishersCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_publisher", null);
	}

	public void deletePublisher(Publisher publisher) throws SQLException {
		save("DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[] { publisher.getPublisherId() });
	}
	
	public Publisher readPublisherByPK(Integer publisherId) throws SQLException {
		List<Publisher> publishers= readAll("SELECT * FROM tbl_publisher WHERE publisherId = ?", new Object[]{publisherId});
		if(publishers!=null){
			return publishers.get(0);
		}
		return null;
	}
	
	public List<Publisher> readPublishers(String PublisherName,Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if(PublisherName  !=null && !PublisherName.isEmpty()){
			PublisherName  = "%"+PublisherName+"%";
			return readAll("SELECT * FROM tbl_publisher WHERE publisherName like ?", new Object[]{PublisherName});
		}else{
			return readAll("SELECT * FROM tbl_publisher", null);
		}
		
	}

	public List<Publisher> readPublishers(String publisherName) throws SQLException {
		if(publisherName !=null && !publisherName.isEmpty()){
			publisherName = "%"+publisherName+"%";
			return readAll("SELECT * FROM tbl_publisher WHERE publisherName like ?", new Object[]{publisherName});
		}else{
			return readAll("SELECT * FROM tbl_publisher", null);
		}
		
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		while(rs.next()){
			Publisher a = new Publisher();
			a.setPublisherId(rs.getInt("publisherId"));
			a.setPublisherName(rs.getString("publisherName"));
			a.setPublisherAddress(rs.getString("publisherAddress"));
			a.setPublisherPhone(rs.getString("publisherPhone"));
			a.setBooks(bdao.readAllFirstLevel("SELECT * FROM tbl_book WHERE pubId = ?", new Object[]{a.getPublisherId()}));
			publishers.add(a);
		}
		
		return publishers;
	}
	
	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher a = new Publisher();
			a.setPublisherId(rs.getInt("publisherId"));
			a.setPublisherName(rs.getString("publisherName"));
			a.setPublisherAddress(rs.getString("publisherAddress"));
			a.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(a);
		}
		
		return publishers;
	}

}
