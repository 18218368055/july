package com.qylyx.july.demo.tags.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qylyx.july.demo.common.data.DataFactory;

@Controller
@RequestMapping("/tags/")
public class TagsController {
	
	@RequestMapping("/index")
	public String index(Model model) {
		System.out.println("=== july tags index ===");
		model.addAttribute("user", DataFactory.getUser());
		model.addAttribute("userList", DataFactory.getUserList());
		model.addAttribute("userArr", DataFactory.getUserList().toArray());
		return "tags/index";
	}
}
