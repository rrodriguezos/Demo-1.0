package controllers.investor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.InstalmentService;
import controllers.AbstractController;
import domain.Instalment;

@Controller
@RequestMapping("/instalment/investor")
public class InstalmentInvestorController extends AbstractController {

	// Supporting services --------------------------------
	@Autowired
	private InstalmentService instalmentService;

	
	// Constructors ---------------------------------------
	public InstalmentInvestorController() {
		super();
	}
	
	// Create ---------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer investmentId) {
		ModelAndView result;
		Instalment instalment;

		instalment = instalmentService.create(investmentId);

		result = createEditModelAndView(instalment);

		return result;
	}

	// Save -----------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Instalment instalment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(instalment);
		} else {
			try {
				instalmentService.save(instalment);
				
				result = new ModelAndView("redirect:/instalment/investor/list.do?investmentId=" + instalment.getInvestment().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(instalment, "instalment.commit.error");
			}
		}

		return result;
	}
	
	// Ancillary methods ----------------------------------
	protected ModelAndView createEditModelAndView(Instalment instalment) {
		ModelAndView result;

		result = createEditModelAndView(instalment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Instalment instalment, String message) {
		ModelAndView result;

		result = new ModelAndView("instalment/edit");
		result.addObject("instalment", instalment);
		result.addObject("message", message);

		return result;
	}

}
