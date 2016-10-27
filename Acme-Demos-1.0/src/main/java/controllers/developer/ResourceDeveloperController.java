package controllers.developer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.ResourceService;
import services.DemoService;

import controllers.AbstractController;
import domain.Resource;
import domain.Demo;

@Controller
@RequestMapping("/resource/developer")
public class ResourceDeveloperController extends AbstractController {

	// Supporting services -------------------------------

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private DemoService demoService;

	// Constructors
	// ----------------------------------------------------------------

	public ResourceDeveloperController() {
		super();
	}

	// Create-----------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int demoId) {
		ModelAndView result;
		Resource resource;
		Demo demo;

		resource = resourceService.create();
		demo = demoService.findOne(demoId);
		resource.setDemo(demo);

		result = createEditModelAndView(resource);

		return result;
	}

	// Save-------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Resource resource, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(resource);
		} else {
			try {
				resourceService.save(resource);
				result = new ModelAndView("redirect:/resource/list.do?demoId="
						+ resource.getDemo().getId());
				redir.addFlashAttribute("message2", "resource.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(resource);

				result.addObject("message2", "resource.commit.error");
			}
		}

		return result;
	}

	// Delete-----------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView save(@RequestParam int resourceId) {
		ModelAndView result;

		Resource resource = resourceService.findOne(resourceId);

		resourceService.delete(resource);

		result = new ModelAndView("redirect:/demo/developer/mylist.do");
		result.addObject("requestUri", "/demo/developer/mylist.do");

		return result;
	}

	// Ancillary methods--------------------------------------------------------

	protected ModelAndView createEditModelAndView(Resource resource) {
		ModelAndView result;

		result = createEditModelAndView(resource, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Resource resource,
			String message) {
		ModelAndView result;

		result = new ModelAndView("resource/create");

		result.addObject("resource", resource);
		result.addObject("message2", message);

		return result;
	}

}
