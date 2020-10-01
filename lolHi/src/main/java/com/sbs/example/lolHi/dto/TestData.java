package com.sbs.example.lolHi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestData {
	private int idx;
	private String title;
	private String description;
	private String regDate;
	private String lastDate;
}
