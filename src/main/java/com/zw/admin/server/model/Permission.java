package com.zw.admin.server.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Permission extends BaseEntity<Long> {

	private static final long serialVersionUID = 6180869216498363919L;

	private Long parentId;
	private String name;
	private String css;
	private String href;
	private Integer type;
	private String permission;
	private Integer sort;
}
