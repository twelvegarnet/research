package research.dto;

import java.util.Date;

public class SurveyResult {
	private String surrSeq;
	private String surSeq;
	private String surqSeq;
	private String surNum;
	private String description;
	private String writer;
	private Date regDate;
	private Date udtDate;
	@Override
	public String toString() {
		return "SurveyResult [surrSeq=" + surrSeq + ", surSeq=" + surSeq + ", surqSeq=" + surqSeq + ", surNum=" + surNum
				+ ", description=" + description + ", writer=" + writer + ", regDate=" + regDate + ", udtDate="
				+ udtDate + "]";
	}
	public String getSurrSeq() {
		return surrSeq;
	}
	public void setSurrSeq(String surrSeq) {
		this.surrSeq = surrSeq;
	}
	public String getSurSeq() {
		return surSeq;
	}
	public void setSurSeq(String surSeq) {
		this.surSeq = surSeq;
	}
	public String getSurqSeq() {
		return surqSeq;
	}
	public void setSurqSeq(String surqSeq) {
		this.surqSeq = surqSeq;
	}
	public String getSurNum() {
		return surNum;
	}
	public void setSurNum(String surNum) {
		this.surNum = surNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUdtDate() {
		return udtDate;
	}
	public void setUdtDate(Date udtDate) {
		this.udtDate = udtDate;
	}
	
}
