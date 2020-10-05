package com.sbs.example.lolHi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.TestDataDao;
import com.sbs.example.lolHi.dto.TestData;


@Service
public class TestDataService {
	
	private TestDataDao testdataDao;

	public List<TestData> getTestDatas() {
		return testdataDao.getTestDatas();
	}

	public int InsertData(TestData testdata) {
		System.out.println("hihihihihihi1111111111");
		return testdataDao.insertData(testdata);
	}
}
