package com.example.sbs.lolHi.controller.usr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "Hi";
	}

	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return "bye";
	}

	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public String showMain3() {
		return "seeya";
	}

	@RequestMapping("/usr/home/plus")
	@ResponseBody
	public int showPlus(int a, int b) {
		return a + b;
	}

	@RequestMapping("/usr/home/minus")
	@ResponseBody
	public int showMinus(int a, int b) {
		return a - b;
	}

	@RequestMapping("/usr/home/multiple")
	@ResponseBody
	public int showMulti(int a, int b) {
		return a * b;
	}

	@RequestMapping("/usr/home/div")
	@ResponseBody
	public int showDiv(int a, int b) {
		return a / b;
	}

}
