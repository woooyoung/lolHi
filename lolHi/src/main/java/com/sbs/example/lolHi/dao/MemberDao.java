package com.sbs.example.lolHi.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.example.lolHi.dto.Member;

@Mapper
public interface MemberDao {
	void join(Map<String, Object> param);

	Member getMemberByLoginId(@Param("loginId") String loginId);

	Member getMemberById(@Param("id") int id);

	void modify(Map<String, Object> param);

	Member getMemberByNameAndEmail(@Param("name") String name, @Param("email") String email);
}
