package com.yolo.model.domain;

import java.io.Serializable;
public class NoticeBoardFile implements Serializable {
	private int upno;
	private String rfileName;
	private String sfileName;
	private int bno;
	public NoticeBoardFile(){}
	public NoticeBoardFile(int no, String rfileName, String sfileName, int bno) {
		super();
		this.upno = no;
		this.rfileName = rfileName;
		this.sfileName = sfileName;
		this.bno = bno;
	}
	public NoticeBoardFile(String rfileName, String sfileName) {
		super();
		this.rfileName = rfileName;
		this.sfileName = sfileName;
	}
	@Override
	public String toString() {
		return  new StringBuilder().append("NoticeBoardFile [upno=")
				.append(upno).append(", rfileName=")
				.append(rfileName).append(", fileName=")
				.append(sfileName).append(", bno=")
				.append(bno).append("]").toString();
	}
	public int getUpno() {
		return upno;
	}
	public void setUpno(int upno) {
		this.upno = upno;
	}

	public String getRfileName() {
		return rfileName;
	}
	public void setRfileName(String rfileName) {
		this.rfileName = rfileName;
	}
	public String getSfileName() {
		return sfileName;
	}
	public void setFileName(String sfileName) {
		this.sfileName = sfileName;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	
}








