package com.dev.core.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dev.core.controller.AbstractResource;
import com.dev.core.utils.JwtUtils;
import com.google.gson.JsonObject;

@RestController
public class LoginController extends AbstractResource {

	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping(value = { "/" })
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

	@GetMapping(value = { "/home" })
	@PreAuthorize("@authorizationService.hasRole('ROLE_ADMIN')")
	public ModelAndView homePage() {
		JsonObject token = jwtUtils.generateJwtToken(getAuthentication());
		ModelAndView model = new ModelAndView();
		model.addObject("token", token);
		model.setViewName("home");
		return model;
	}
	
	@GetMapping(value = { "/get-token" })
	public JsonObject getToken() {
		return jwtUtils.generateJwtToken(getAuthentication());
	}

	@GetMapping(value = "/login")
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		request.getSession().invalidate();

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out successfully.");
		}

		model.setViewName("index");
		return model;
	}

	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}

}
