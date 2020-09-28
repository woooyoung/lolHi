package com.sbs.example.lolPrac.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.example.lolPrac.dto.Article;

@Mapper
public interface ArticleDao {

	List<Article> getArticles();

}
