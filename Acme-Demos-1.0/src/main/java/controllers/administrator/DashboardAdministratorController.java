package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.DemoService;
import services.DeveloperService;
import controllers.AbstractController;
import domain.Demo;
import domain.Developer;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services -------------------------------------------
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private DemoService demoService;
	
	@Autowired
	private DeveloperService developerService;

	
	// Constructor ----------------------------------------
	public DashboardAdministratorController() {
		super();
	}

	// Dashboard ------------------------------------------
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		Double avgNumberCommentsPerDemo;
		Collection<Demo> demos25PercentageMoreCommentsThanAvg;
		Collection<Developer> developersMoreCommentsThanAvg;
		
		
		avgNumberCommentsPerDemo = commentService.averageCommentsPerDemoByDeveloperId();
		
		demos25PercentageMoreCommentsThanAvg = demoService.demos25PercentageMoreCommentsThanAvg();
		
		developersMoreCommentsThanAvg = developerService.developersMoreCommentsThanAvg();

		result = new ModelAndView("administrator/dashboard");
		result.addObject("avgNumberCommentsPerDemo", avgNumberCommentsPerDemo);
		result.addObject("demos25PercentageMoreCommentsThanAvg", demos25PercentageMoreCommentsThanAvg);
		result.addObject("developersMoreCommentsThanAvg", developersMoreCommentsThanAvg);
		result.addObject("requestURI", "/dashboard/administrator/dashboard.do");

		return result;
	}

}
