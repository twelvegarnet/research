package research.dto;

import java.util.Date;

public class SurveyContent {
	private int surqSeq;		// 문제번호
	private int surSeq;			// 설문번호
	private String surqTitle;	// 문제
	private String surqTitle1;	// 문항제목1
	private String surqTitle2;	// 문항제목2
	private String surqTitle3;	// 문항제목3
	private String surqTitle4;	// 문항제목4
	private String surqTitle5;	// 문항제목5
	private String regName;		// 등록자
	private Date regDate;		// 등록일
	private String udtName;		// 수정자
	private Date udtDate;		// 수정일
	@Override
	public String toString() {
		return "SurveyContent [surqSeq=" + surqSeq + ", surSeq=" + surSeq + ", surqTitle=" + surqTitle + ", surqTitle1="
				+ surqTitle1 + ", surqTitle2=" + surqTitle2 + ", surqTitle3=" + surqTitle3 + ", surqTitle4="
				+ surqTitle4 + ", surqTitle5=" + surqTitle5 + ", regName=" + regName + ", regDate=" + regDate
				+ ", udtName=" + udtName + ", udtDate=" + udtDate + "]";
	}
	public int getSurqSeq() {
		return surqSeq;
	}
	public void setSurqSeq(int surqSeq) {
		this.surqSeq = surqSeq;
	}
	public int getSurSeq() {
		return surSeq;
	}
	public void setSurSeq(int surSeq) {
		this.surSeq = surSeq;
	}
	public String getSurqTitle() {
		return surqTitle;
	}
	public void setSurqTitle(String surqTitle) {
		this.surqTitle = surqTitle;
	}
	public String getSurqTitle1() {
		return surqTitle1;
	}
	public void setSurqTitle1(String surqTitle1) {
		this.surqTitle1 = surqTitle1;
	}
	public String getSurqTitle2() {
		return surqTitle2;
	}
	public void setSurqTitle2(String surqTitle2) {
		this.surqTitle2 = surqTitle2;
	}
	public String getSurqTitle3() {
		return surqTitle3;
	}
	public void setSurqTitle3(String surqTitle3) {
		this.surqTitle3 = surqTitle3;
	}
	public String getSurqTitle4() {
		return surqTitle4;
	}
	public void setSurqTitle4(String surqTitle4) {
		this.surqTitle4 = surqTitle4;
	}
	public String getSurqTitle5() {
		return surqTitle5;
	}
	public void setSurqTitle5(String surqTitle5) {
		this.surqTitle5 = surqTitle5;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getUdtName() {
		return udtName;
	}
	public void setUdtName(String udtName) {
		this.udtName = udtName;
	}
	public Date getUdtDate() {
		return udtDate;
	}
	public void setUdtDate(Date udtDate) {
		this.udtDate = udtDate;
	}
	
	
	
}
