package com.zw.admin.server.dto;

import java.util.Date;

import com.zw.admin.server.model.Notice;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeReadVO extends Notice {

	private static final long serialVersionUID = -3842182350180882396L;

	private Long userId;
	private Date readTime;
	private Boolean isRead;
}
