package br.com.brunolandia.sisvarejo.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController {

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView home()
	{
		return new ModelAndView( "modules/sisvarejo/ui/index.jsp" );
	}
	
}
