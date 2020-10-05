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
	public String InsertData() {
		TestData testdata = new TestData();
		testdata.setDescription("des");
		testdata.setLastDate("2000-09-09");
		testdata.setRegDate("2000-09-09");
		testdata.setTitle("11!1");
		testdata.setIdx(1);
		System.out.println(testdata.toString()+"******************");
		testdataService.InsertData(testdata);
		System.out.println("33333333333333333333");
		return null;
	}
}
