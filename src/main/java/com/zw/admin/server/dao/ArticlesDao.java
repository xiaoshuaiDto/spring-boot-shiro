package com.zw.admin.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zw.admin.server.model.Articles;

@Mapper
public interface ArticlesDao {

	@Select("select * from articles t where t.id = #{id}")
	Articles getById(Long id);

	@Delete("delete from articles where id = #{id}")
	int delete(Long id);

	@Update("update articles t set title = #{title}, content = #{content}, status = #{status}, updateTime = #{updateTime} where t.id = #{id}")
	int update(Articles articles);

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into articles(title, content, status, createTime, updateTime) values(#{title}, #{content}, #{status}, #{createTime}, #{updateTime})")
	int save(Articles articles);

	int count(@Param("params") Map<String, Object> params);

	List<Articles> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
			@Param("limit") Integer limit);
}
