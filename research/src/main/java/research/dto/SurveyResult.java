package research.dto;

import java.util.Date;

public class SurveyResult {
	private int surrSeq;
	private int surSeq;
	private int surqSeq;
	private int chooseNum;
	private String description;
	private String writer;
	private Date regDate;
	private Date udtDate;
	@Override
	public String toString() {
		return "SurveyResult [surrSeq=" + surrSeq + ", surSeq=" + surSeq + ", surqSeq=" + surqSeq + ", chooseNum="
				+ chooseNum + ", description=" + description + ", writer=" + writer + ", regDate=" + regDate
				+ ", udtDate=" + udtDate + "]";
	}
	public int getSurrSeq() {
		return surrSeq;
	}
	public void setSurrSeq(int surrSeq) {
		this.surrSeq = surrSeq;
	}
	public int getSurSeq() {
		return surSeq;
	}
	public void setSurSeq(int surSeq) {
		this.surSeq = surSeq;
	}
	public int getSurqSeq() {
		return surqSeq;
	}
	public void setSurqSeq(int surqSeq) {
		this.surqSeq = surqSeq;
	}
	public int getChooseNum() {
		return chooseNum;
	}
	public void setChooseNum(int chooseNum) {
		this.chooseNum = chooseNum;
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
