package com.zw.admin.server.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zw.admin.server.annotation.LogAnnotation;
import com.zw.admin.server.dao.ArticlesDao;
import com.zw.admin.server.model.Articles;
import com.zw.admin.server.page.table.PageTableRequest;
import com.zw.admin.server.page.table.PageTableHandler;
import com.zw.admin.server.page.table.PageTableResponse;
import com.zw.admin.server.page.table.PageTableHandler.CountHandler;
import com.zw.admin.server.page.table.PageTableHandler.ListHandler;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

	@Autowired
	private ArticlesDao articlesDao;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存文章")
	@RequiresPermissions("articles:add")
	public Articles saveArticles(@RequestBody Articles articles) {
		articlesDao.save(articles);

		return articles;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取文章")
	@RequiresPermissions("articles:query")
	public Articles get(@PathVariable Long id) {
		return articlesDao.getById(id);
	}

	@LogAnnotation
	@PutMapping
	@ApiOperation(value = "修改文章")
	@RequiresPermissions("articles:add")
	public Articles updateArticles(@RequestBody Articles articles) {
		articlesDao.update(articles);

		return articles;
	}

	@GetMapping
	@ApiOperation(value = "文章列表")
	@RequiresPermissions("articles:query")
	public PageTableResponse<Articles> listArticles(PageTableRequest request) {
		return PageTableHandler.<Articles> builder().countHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return articlesDao.count(request.getParams());
			}
		}).listHandler(new ListHandler<Articles>() {

			@Override
			public List<Articles> list(PageTableRequest request) {
				return articlesDao.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).build().handle(request);
	}

	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除文章")
	@RequiresPermissions(value = { "articles:del" })
	public void delete(@PathVariable Long id) {
		articlesDao.delete(id);
	}
}
