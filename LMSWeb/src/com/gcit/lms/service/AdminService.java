package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;



	/**
	 * Author 
	 * 
	 */


	public class AdminService {
		
		public Utilities util = new Utilities();
		
		public void saveAuthor(Author author) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				AuthorDAO adao = new AuthorDAO(conn);
				if(author.getAuthorId()!=null){
					adao.updateAuthor(author);
					if(author.getBooks() != null && author.getBooks().size() > 0)
					adao.saveBookAuthor(author);
				}else{
					author.setAuthorId(adao.saveAuthorWithID(author));
					adao.saveBookAuthor(author);
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
		
		public void saveBook(Book book) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookDAO adao = new BookDAO(conn);
				if(book.getBookId()!=null){
					adao.updateBook(book);
					if(book.getAuthors() != null && book.getAuthors().size() > 0)
					adao.saveBookAuthor(book);
					if(book.getGenres() != null && book.getGenres().size() > 0)
					adao.saveBookGenre(book);
				}else{
					book.setBookId(adao.saveBookID(book));
					adao.saveBookAuthor(book);
					adao.saveBookGenre(book);
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
		
		public void upadatebookPublisher(Book book) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookDAO adao = new BookDAO(conn);
				if(book.getBookId()!=null){
					adao.updatebookPub(book);
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

		
		public List<Book> getBooks(String[] bookIds) throws SQLException
		{
					Connection conn = null;
			try {
				conn = util.getConnection();
				BookDAO bdao = new BookDAO(conn);
				List<Book> books = new ArrayList<>();
				for(String bookId: bookIds)
				{
					books.add(bdao.readBookByPK(Integer.parseInt(bookId)));
				}
				return books;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			return null;
		}
		public List<Author> getAuthors(String[] authorIds) throws SQLException
		{
					Connection conn = null;
			try {
				conn = util.getConnection();
				AuthorDAO adao = new AuthorDAO(conn);
				List<Author> authors = new ArrayList<>();
				for(String authorId: authorIds)
				{
					authors.add(adao.readAuthorByPK(Integer.parseInt(authorId)));
				}
				return authors;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			return null;
		}
		
		public List<Book> readBooks() throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookDAO bdao = new BookDAO(conn);
				return bdao.readAllBooks();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}

			return null;
		}
		
		public List<Author> readAuthors() throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				AuthorDAO adao = new AuthorDAO(conn);
				return adao.readAllAuthors();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}

			return null;
		}
		public List<BookLoans> readallBookLoans() throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookLoansDAO bldao = new BookLoansDAO(conn);
				return bldao.readAllbookLoans();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}

			return null;
		}
		
		public List<Publisher> readPublishers() throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				PublisherDAO pdao = new PublisherDAO(conn);
				return pdao.readPublishers(null);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}

			return null;
		}
		public List<Genre> getGenres(String[] genreIds) throws SQLException
		{
					Connection conn = null;
			try {
				conn = util.getConnection();
				GenreDAO gdao = new GenreDAO(conn);
				List<Genre> genres = new ArrayList<>();
				for(String genreId: genreIds)
				{
					genres.add(gdao.readGenreByPK(Integer.parseInt(genreId)));
				}
				return genres;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			return null;
		}
		public List<Genre> readGenres() throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				GenreDAO pdao = new GenreDAO(conn);
				return pdao.readGenres(null);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}

			return null;
		}
		
		
		public void deleteAuthor(Author author) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				AuthorDAO adao = new AuthorDAO(conn);
				adao.deleteAuthor(author);
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
		
		public void deleteBook(Book book) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookDAO bdao = new BookDAO(conn);
				bdao.deleteBook(book);
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
		
		public List<BookLoans> readBookLoans1() throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookLoansDAO bcdao = new BookLoansDAO(conn);
				return bcdao.readBookLoans1();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}

			return null;
		}
		
		public void updatedueDate(BookLoans bookloans) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookLoansDAO bcdao = new BookLoansDAO(conn);
					bcdao.updatedueDate(bookloans);
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
		
		public void deleteBookAuthor(Author author,int bookId) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				AuthorDAO adao = new AuthorDAO(conn);
				adao.deleteBookAuthor(author,bookId);
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
		
		public List<Author> readAuthors(String searchString, Integer pageNo) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				AuthorDAO adao = new AuthorDAO(conn);
				return adao.readAuthors(searchString, pageNo);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			
			return null;
		}
		public List<BookLoans> readBookLoansPage(Integer pageNo) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookLoansDAO bldao = new BookLoansDAO(conn);
				return bldao.readBookLoansPage(pageNo);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			
			return null;
		}
		
		public List<Book> readBooks(String searchString, Integer pageNo) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookDAO bdao = new BookDAO(conn);
				return bdao.readBooksDAO(searchString, pageNo);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			
			return null;
		}
		
		public Author readAuthorByPK(Integer authorId) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				AuthorDAO adao = new AuthorDAO(conn);
				return adao.readAuthorByPK(authorId);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			
			return null;
		}
		
		public Book readBookByPK(Integer bookId) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookDAO bdao = new BookDAO(conn);
				return bdao.readBookByPK(bookId);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			
			return null;
		}
		
		public Publisher readPublisherByPK(Integer pubId) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				PublisherDAO pdao = new PublisherDAO(conn);
				return pdao.readPublisherByPK(pubId);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			
			return null;
		}
		
		public Integer getAuthorsCount() throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				AuthorDAO adao = new AuthorDAO(conn);
				return adao.getAuthorsCount();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			
			return null;
		}
		
		public Integer getbookLoansCount() throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookLoansDAO bldao = new BookLoansDAO(conn);
				return bldao.getbookLoansCount();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			
			return null;
		}
		
		public Integer getBooksCount() throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				BookDAO bdao = new BookDAO(conn);
				return bdao.getBooksCount();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					conn.close();
				}
			}
			
			return null;
		}
		public Borrower readBorrowerByPK(Integer cardNo) throws SQLException{
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
		
		public LibraryBranch readBranchByPK(Integer branchId) throws SQLException{
			Connection conn = null;
			try {
				conn = util.getConnection();
				LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
				return lbdao.readbranchByPK(branchId);
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
	 * Publisher
	 * 
	 */
	public void savePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			if(publisher.getPublisherId()!=null){
				pdao.updatePublisher(publisher);
			}else{
				pdao.savePublisher(publisher);
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
	public void deletePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.deletePublisher(publisher);
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

	public List<Publisher> readPublishers(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublishers(searchString);
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
	 * Library_Branches 
	 * 
	 */
	public int saveLibraryBranch(LibraryBranch branch) throws SQLException{
		Connection conn = null;
		int branchId = -1;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			if(branch.getBranchId()!=null){
				lbdao.updateLibraryBranches(branch);
			}else{
				branchId =lbdao.saveLibraryBranchesWithID(branch);
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
		return branchId;
	}
	public void savebookCopies(BookCopies bookCopies) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			bcdao.saveBookCopies(bookCopies);
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
	public void deleteLibraryBranch(LibraryBranch borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			lbdao.deleteLibraryBranches(borrower);
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
	 * Borrower
	 * 
	 */
	public void saveBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO brdao = new BorrowerDAO(conn);
			if(borrower.getCardNo()!=null){
				brdao.updateBorrower(borrower);
			}else{
				brdao.saveBorrower(borrower);
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
	public void deleteBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO brdao = new BorrowerDAO(conn);
			brdao.deleteBorrower(borrower);
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

	public List<Borrower> readBorrower(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO brdao = new BorrowerDAO(conn);
			return brdao.readBorrowers(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

		return null;
	}
	
	public List<Borrower> readBorrowers(String searchString,Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO brdao = new BorrowerDAO(conn);
			return brdao.readBorrowers1(searchString,pageNo);
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
	 * Genre
	 * 
	 */
	public void saveGenre(Genre genre) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			if(genre.getGenreId()!=null){
				gdao.updateGenre(genre);
				if(genre.getBooks() != null && genre.getBooks().size() > 0)
				gdao.saveBookGenre(genre);
			}else{
				genre.setGenreId(gdao.saveGenreWithID(genre));
				gdao.saveBookGenre(genre);
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
	
	
	
	public void deleteGenre(Genre genre) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.deleteGenre(genre);
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

	public List<Genre> readGenre(String searchString,Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readGenres(searchString,pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

		return null;
	}
	public List<Publisher> readPublisher(String searchString,Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublishers(searchString,pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

		return null;
	}
	public List<LibraryBranch> readBranch(String searchString,Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			return lbdao.readBranch(searchString,pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

		return null;
	}
	public Integer getGenresCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.getGenresCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public Integer getPublishersCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.getPublishersCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Integer getbarnchesCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			return lbdao.getbarnchesCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Integer getBorrowersCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO brdao = new BorrowerDAO(conn);
			return brdao.getBorrowersCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Genre readGenreByPK(Integer genreId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readGenreByPK(genreId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public void deleteBookGenre(Genre genre,int bookId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO adao = new GenreDAO(conn);
			adao.deleteBookGenre(genre,bookId);
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
