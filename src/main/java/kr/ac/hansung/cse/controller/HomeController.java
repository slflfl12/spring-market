package kr.ac.hansung.cse.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	//private static final Logger logger = LoggerFactory.getLogger("kr.ac.hansung.cse.controller.HomeController");
	//동일하다
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale) {
		// trace -> debug -> info -> warn -> error
		logger.trace("trace level: Welcome home! The client locale is {}", locale);
		logger.debug("trace level: Welcome home! The client locale is {}", locale);
		logger.info("trace level: Welcome home! The client locale is {}", locale);
		logger.warn("trace level: Welcome home! The client locale is {}", locale);
		logger.error("trace level: Welcome home! The client locale is {}", locale);
		
		String url = request.getRequestURL().toString();
		String clientIPaddress = request.getRemoteAddr();
		
		logger.info("Request URL: {}, Client IP: {},", url, clientIPaddress);
		//인자가 있는 logging 사용자의 url과 ipaddress 어떤지역에서 접속했는지 알아보고 싶을 때
		
		//아마 아래 4개가 실행됨
		return "home";
	}
	
}