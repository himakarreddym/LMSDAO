package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BookDAO extends BaseDAO {

	public BookDAO(Connection conn) {
		super(conn);
	}

	public void saveBook(Book book) throws SQLException {
		save("INSERT INTO tbl_book (title,pubId) VALUES (?,?)", new Object[] { book.getTitle(),book.getPublisher().getPublisherId() });
	}
	
	public void saveBookAuthor(Book book) throws SQLException {
		for(Author a: book.getAuthors()){
			save("INSERT INTO tbl_book_authors VALUES (?, ?)", new Object[] { book.getBookId(), a.getAuthorId()});
		}
	}
	public void saveBookGenre(Book book) throws SQLException {
		for(Genre g: book.getGenres()){
			save("INSERT INTO tbl_book_genres VALUES (?, ?)", new Object[] {g.getGenreId() , book.getBookId()});
		}
	}
	
	public Integer saveBookID(Book book) throws SQLException {
		return saveWithID("INSERT INTO tbl_book (title,pubId) VALUES (?,?)", new Object[] { book.getTitle(),book.getPublisher().getPublisherId() });
	}

	public void updateBook(Book book) throws SQLException {
		save("UPDATE tbl_book SET title = ? , pubId = ? WHERE bookId = ?", new Object[] { book.getTitle(),book.getPublisher().getPublisherId() ,book.getBookId() });
	}
	public void updatebookPub(Book book) throws SQLException {
		save("UPDATE tbl_book SET pubId = ? WHERE bookId = ?", new Object[] { book.getPublisher().getPublisherId() ,book.getBookId() });
	}

	public void deleteBook(Book book) throws SQLException {
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[] { book.getBookId() });
	}
	public Integer getBooksCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_book", null);
	}

	public List<Book> readAllBooks() throws SQLException {
		return readAll("SELECT * FROM tbl_book", null);
	}
	
	public List<Book> readBooksByTitle(String bookTitle) throws SQLException {
		bookTitle = "%"+bookTitle+"%";
		return readAll("SELECT * FROM tbl_book WHERE title like ?", new Object[]{bookTitle});
		
	}
	public Book readBookByPK(int bookId) throws SQLException{
		List<Book> books = readAll("SELECT * FROM tbl_book WHERE bookId = ?", new Object[]{bookId});
		if(books!=null){
			return books.get(0);
		}
		return null;
	}
	
	public List<Book> readBooksDAO(String bookTitle, Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if(bookTitle !=null && !bookTitle.isEmpty()){
			bookTitle = "%"+bookTitle+"%";
			return readAll("SELECT * FROM tbl_book WHERE title like ?", new Object[]{bookTitle});
		}else{
			return readAll("SELECT * FROM tbl_book", null);
		}
		
	}

	@Override
	public List extractData(ResultSet rs) throws SQLException {
		AuthorDAO adao = new AuthorDAO(conn);
		GenreDAO gdao = new GenreDAO(conn);
		PublisherDAO pdao = new PublisherDAO(conn);
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setAuthors(adao.readAllFirstLevel("SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)", new Object[]{b.getBookId()}));
			b.setGenres(gdao.readAllFirstLevel("SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId = ?)", new Object[]{b.getBookId()}));
			List<Publisher> pd = (pdao.readAllFirstLevel("SELECT * FROM tbl_publisher WHERE publisherId = (SELECT pubId FROM tbl_book WHERE bookId = ? LIMIT 1) ", new Object[]{b.getBookId()}));
			b.setPublisher(pd.size()==1 ? pd.get(0):null);
			books.add(b);
		}
		return books;
	}
	
	@Override
	public List extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			books.add(b);
		}
		return books;
	}
}
