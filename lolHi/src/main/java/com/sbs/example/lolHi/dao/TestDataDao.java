package com.sbs.example.lolHi.dao;

import java.util.List;

import com.sbs.example.lolHi.dto.TestData;

public interface TestDataDao {

	List<TestData> getTestDatas();

	int insertData(TestData testdata);

}
