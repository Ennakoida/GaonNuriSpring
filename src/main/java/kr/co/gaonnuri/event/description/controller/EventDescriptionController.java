package kr.co.gaonnuri.event.description.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventDescriptionController {

	@RequestMapping(value="/event/cdgDescription.do", method=RequestMethod.GET)
	public String showCdgDescription() {
		return "event/description/cdg";
	}
	
	@RequestMapping(value="/event/cggDescription.do", method=RequestMethod.GET)
	public String showCggDescription() {
		return "event/description/cgg";
	}
	
	@RequestMapping(value="/event/dsgDescription.do", method=RequestMethod.GET)
	public String showDsgDescription() {
		return "event/description/dsg";
	}
	
	@RequestMapping(value="/event/gbgDescription.do", method=RequestMethod.GET)
	public String showGbgDescription() {
		return "event/description/gbg";
	}
	
	@RequestMapping(value="/event/jmDescription.do", method=RequestMethod.GET)
	public String showJmDescription() {
		return "event/description/jm";
	}
}
