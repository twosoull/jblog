package com.javaex.vo;

public class PostVo {
	private int postNo;
	private int cateNo;
	private String postTitle;
	private String postContent;
	private String regDate;
	
	//count
	private String postCnt;
	
	//users
	private String userName;
	
	//category
	private String id;
	
	public PostVo() {
	
	}

	public PostVo(int postNo, int cateNo, String postTitle, String postContent, String regDate) {
		
		this.postNo = postNo;
		this.cateNo = cateNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.regDate = regDate;
	}
	
	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostCnt() {
		return postCnt;
	}

	public void setPostCnt(String postCnt) {
		this.postCnt = postCnt;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", cateNo=" + cateNo + ", postTitle=" + postTitle + ", postContent=" + postContent + ", regDate=" + regDate + "]";
	}
	
	
	
}
