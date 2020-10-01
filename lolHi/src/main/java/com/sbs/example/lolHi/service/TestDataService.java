package com.sbs.example.lolHi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.TestDataDao;
import com.sbs.example.lolHi.dto.TestData;

@Service
public class TestDataService {
	@Autowired
	private TestDataDao testdataDao;
	
	public List<TestData> getTestDatas(){
		return testdataDao.getTestDatas();
	}
}
