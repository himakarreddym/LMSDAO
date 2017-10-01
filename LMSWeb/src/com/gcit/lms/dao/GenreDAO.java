package com.gcit.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GenreDAO extends BaseDAO {

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void saveGenre(Genre Genre) throws SQLException {
		save("INSERT INTO tbl_genre (`genre_name`) VALUES (?)", new Object[] { Genre.getGenreName() });
	}
	
	public void saveBookGenre(Genre Genre) throws SQLException {
		for(Book b: Genre.getBooks()){
			save("INSERT INTO tbl_book_genres VALUES (?, ?)", new Object[] { Genre.getGenreId(),b.getBookId()});
		}
	}
	
	public Integer saveGenreWithID(Genre Genre) throws SQLException {
		return saveWithID("INSERT INTO tbl_genre (`genre_name`) VALUES (?)", new Object[] { Genre.getGenreName() });
	}
	public void deleteBookGenre(Genre Genre,int bookId) throws SQLException {
		save("DELETE FROM tbl_book_genres WHERE genre_id = ? AND bookId = ?", new Object[] { Genre.getGenreId(),bookId });
	}
	public void updateGenre(Genre Genre) throws SQLException {
		save("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?",
				new Object[] { Genre.getGenreName(), Genre.getGenreId() });
	}
	public Integer getGenresCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_genre", null);
	}
	public Genre readGenreByPK(Integer genreId) throws SQLException {
		List<Genre> genres = readAll("SELECT * FROM tbl_genre WHERE genre_id = ?", new Object[]{genreId});
		if(genres!=null){
			return genres.get(0);
		}
		return null;
	}
	public void deleteGenre(Genre Genre) throws SQLException {
		save("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[] { Genre.getGenreId() });
	}
	
	
	public List<Genre> readGenres(String GenreName,Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if(GenreName  !=null && !GenreName.isEmpty()){
			GenreName  = "%"+GenreName+"%";
			return readAll("SELECT * FROM tbl_genre WHERE genre_name like ?", new Object[]{GenreName});
		}else{
			return readAll("SELECT * FROM tbl_genre", null);
		}
		
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> Genres = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		while(rs.next()){
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genre_id"));
			a.setGenreName(rs.getString("genre_name"));
			a.setBooks(bdao.readAllFirstLevel("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_genres WHERE genre_id = ?)", new Object[]{a.getGenreId()}));
			Genres.add(a);
		}
		
		return Genres;
	}
	
	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Genre> Genres = new ArrayList<>();
		while(rs.next()){
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genre_id"));
			a.setGenreName(rs.getString("genre_name"));
			Genres.add(a);
		}
		
		return Genres;
	}


}
