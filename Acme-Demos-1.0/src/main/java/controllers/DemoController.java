package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CommentService;
import services.DemoService;
import services.DeveloperService;

@Controller
@RequestMapping("/demo")
public class DemoController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private DemoService demoService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private DeveloperService developerService;

	// Constructors -----------------------------------------------------------
	public DemoController() {
		super();
	}
}
