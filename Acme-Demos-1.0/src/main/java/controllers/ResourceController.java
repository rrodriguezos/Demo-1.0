package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.DemoService;
import services.DeveloperService;
import services.ResourceService;

@Controller
@RequestMapping("/resource")
public class ResourceController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private DemoService demoService;
	@Autowired
	private DeveloperService developerService;

	// Constructors -----------------------------------------------------------
	public ResourceController() {
		super();
	}

}
