package com.gcit.lms;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.service.AdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AuthorController {
	
	@Autowired
	AdminService adminService;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome to home method", locale);
		return "welcome";
	}
	@RequestMapping(value = "/author", method = RequestMethod.GET)
	public String gottoAdmin(Locale locale, Model model) {
		return "author";
	}
	@RequestMapping(value = "/addauthor", method = RequestMethod.GET)
		 public ModelAndView showForm(Model model) {
		Author author = new Author();
		try {
			List<Book>books = adminService.readBooks();
				model.addAttribute("books",books);
				author.setBooks(books);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        return new ModelAndView("addauthor", "Author", author);
		    }
	@RequestMapping(value = "/addauthor1", method = RequestMethod.POST)
	public String addAuthor(@ModelAttribute("Author")Author author,BindingResult result, ModelMap model) throws SQLException {
		if (result.hasErrors()) {
			logger.info("error",result.getFieldError());
        }
		//adminService.saveAuthor(author);
		if(author.getBooks() != null) {
			logger.info("Entered");
		}
		else {
			logger.info("Still null");
		}
		
		model.clear();
		return "redirect:/author";
	}
	 
	@RequestMapping(value = "/viewauthors", method = RequestMethod.GET)
	public String gottoviewauthors(Locale locale, Model model,@RequestParam(value ="pageNo",required=false) Integer pageNo) {
		Integer totalCount =-1;
		if(pageNo == null) {
			pageNo=1;
		}
			try {
				totalCount = adminService.getAuthorsCount(null);
				int numOfPages = 0;
				if (totalCount % 4 > 0) {
					numOfPages = totalCount / 4 + 1;
				} else {
					numOfPages = totalCount / 4;
				}
				model.addAttribute("authors", adminService.readAuthors(null, pageNo));
				model.addAttribute("totalCount", totalCount);
				model.addAttribute("numOfPages", numOfPages);
				model.addAttribute("pageNo",pageNo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "viewauthors";
	}
	
	
}
