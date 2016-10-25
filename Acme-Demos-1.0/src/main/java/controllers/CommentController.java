package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	// Constructor -----------------------------------------------------
	public CommentController() {
		super();
	}

	// Services --------------------------------------------------------
	@Autowired
	private CommentService commentService;

}
