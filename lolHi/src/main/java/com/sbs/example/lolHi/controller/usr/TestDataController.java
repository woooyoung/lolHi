package com.sbs.example.lolHi.controller.usr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.example.lolHi.dto.TestData;
import com.sbs.example.lolHi.service.TestDataService;

@Controller
public class TestDataController {
	@Autowired
	private TestDataService testdataService;

	@RequestMapping("usr/TestData/list")
	public String ShowTest(Model model) {
		List<TestData> testdatas = testdataService.getTestDatas();

		model.addAttribute("testdatas", testdatas);
		return null;

	}

	@RequestMapping("usr/TestData/insert")
	@ResponseBody
	public String InsertData() {
		return null;
	}
}
