package com.yolo.model.domain;

import java.io.Serializable;

public class ClassInfo implements Serializable{
	private String ccode;		// 과목코드
	private String ctitle;		// 과목
	private int chour;			// 수업시간
	private int cscore;			// 학점(ex. 1주 or 2주 과정)
	public ClassInfo(){}
	public ClassInfo(String ccode, String ctitle, int chour, int cscore) {
		super();
		this.ccode = ccode;
		this.ctitle = ctitle;
		this.chour = chour;
		this.cscore = cscore;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	public int getChour() {
		return chour;
	}
	public void setChour(int chour) {
		this.chour = chour;
	}
	public int getCscore() {
		return cscore;
	}
	public void setCscore(int cscore) {
		this.cscore = cscore;
	}
	
	@Override
	public String toString() {
		return "ClassInfo [ccode=" + ccode + ", ctitle=" + ctitle + ", chour="
				+ chour + ", cscore=" + cscore + "]";
	}
}