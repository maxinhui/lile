package top.builbu.website.sys.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Slf4j
@Controller
@RequestMapping("/sys")
public class SysController {

	
	@RequestMapping("/loginLoad")
	public String loginLoad(String name,String pwd){
		log.info("for:loginindex");
		
		return "/login/login";
	}
	
	@RequestMapping("/forLoad")
	public String forLoad(String html){
		log.info("for:"+html);
		return html;
	}
	
	

}
