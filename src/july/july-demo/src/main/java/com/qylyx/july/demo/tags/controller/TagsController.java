package com.qylyx.july.demo.tags.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags/")
public class TagsController {
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("===index===");
		return "tags/index";
	}
}
