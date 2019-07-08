package com.cgi.studentservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @author Pushpanjali
 *
 */
@Controller
public class HomeController {
	/**
	 * This method is used to load initial page of our application.
	 * @return
	 */
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
}
