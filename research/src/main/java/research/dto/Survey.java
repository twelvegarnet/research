package research.dto;

import java.util.Date;

public class Survey {
	private String surSeq;
	private String surTitle;
	private String surCnt;
	private Date surStartDate;
	private Date surEndDate;
	private String hit;
	private String regName;
	private Date regDate;
	private String udtName;
	private Date udtDate;
	@Override
	public String toString() {
		return "Survey [surSeq=" + surSeq + ", surTitle=" + surTitle + ", surCnt=" + surCnt + ", surStartDate="
				+ surStartDate + ", surEndDate=" + surEndDate + ", hit=" + hit + ", regName=" + regName + ", regDate="
				+ regDate + ", udtName=" + udtName + ", udtDate=" + udtDate + "]";
	}
	public String getSurSeq() {
		return surSeq;
	}
	public void setSurSeq(String surSeq) {
		this.surSeq = surSeq;
	}
	public String getSurTitle() {
		return surTitle;
	}
	public void setSurTitle(String surTitle) {
		this.surTitle = surTitle;
	}
	public String getSurCnt() {
		return surCnt;
	}
	public void setSurCnt(String surCnt) {
		this.surCnt = surCnt;
	}
	public Date getSurStartDate() {
		return surStartDate;
	}
	public void setSurStartDate(Date surStartDate) {
		this.surStartDate = surStartDate;
	}
	public Date getSurEndDate() {
		return surEndDate;
	}
	public void setSurEndDate(Date surEndDate) {
		this.surEndDate = surEndDate;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
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
