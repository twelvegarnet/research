package research.dto;

public class Research {
	private String seq;
	private String title;
	private String startDate;
	private String endDate;
	private String rcontents;
	private String hit;
	private String regName;
	private String regDate;
	private String udtName;
	private String udtDate;
	@Override
	public String toString() {
		return "Research [seq=" + seq + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", rcontents=" + rcontents + ", hit=" + hit + ", regName=" + regName + ", regDate=" + regDate
				+ ", udtName=" + udtName + ", udtDate=" + udtDate + "]";
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRcontents() {
		return rcontents;
	}
	public void setRcontents(String rcontents) {
		this.rcontents = rcontents;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getUdtName() {
		return udtName;
	}
	public void setUdtName(String udtName) {
		this.udtName = udtName;
	}
	public String getUdtDate() {
		return udtDate;
	}
	public void setUdtDate(String udtDate) {
		this.udtDate = udtDate;
	}
	
}
