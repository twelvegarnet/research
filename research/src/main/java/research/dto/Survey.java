package research.dto;

import java.util.Date;

public class Survey {
	private int surSeq;				// 설문번호
	private String surTitle;		// 제목
	private int surCnt;				// 문제수
	private Date surStartDate;		// 설문시작일자
	private Date surEndDate;		// 설문종료일자
	private int hit;				// 조회수
	private String regName;			// 등록자
	private Date regDate;			// 등록일
	private String udtName;			// 수정자
	private Date udtDate;			// 수정일
	private int rnum;
	private int rownum;
	private String writer;
	private int didSur;
	
	@Override
	public String toString() {
		return "Survey [surSeq=" + surSeq + ", surTitle=" + surTitle + ", surCnt=" + surCnt + ", surStartDate="
				+ surStartDate + ", surEndDate=" + surEndDate + ", hit=" + hit + ", regName=" + regName + ", regDate="
				+ regDate + ", udtName=" + udtName + ", udtDate=" + udtDate + ", rnum=" + rnum + ", rownum=" + rownum
				+ ", writer=" + writer + ", didSur=" + didSur + "]";
	}
	public int getSurSeq() {
		return surSeq;
	}
	public void setSurSeq(int surSeq) {
		this.surSeq = surSeq;
	}
	public String getSurTitle() {
		return surTitle;
	}
	public void setSurTitle(String surTitle) {
		this.surTitle = surTitle;
	}
	public int getSurCnt() {
		return surCnt;
	}
	public void setSurCnt(int surCnt) {
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
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
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getDidSur() {
		return didSur;
	}
	public void setDidSur(int didSur) {
		this.didSur = didSur;
	}
	
	
	
	
	
	
}
