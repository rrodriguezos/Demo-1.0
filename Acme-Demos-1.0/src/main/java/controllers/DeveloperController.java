/* UserController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.DemoService;
import services.DeveloperService;

@Controller
@RequestMapping("/developer")
public class DeveloperController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private DeveloperService developerService;
	@Autowired
	private DemoService demoService;

	// Constructors -----------------------------------------------------------

	public DeveloperController() {
		super();
	}

}