package ex10;

import java.util.Date;

public class EnrolVO {
	private String scode;
	private String sname;
	private String ccode;
	private String cname;
	private int grade;
	private Date edate;
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	@Override
	public String toString() {
		return "EnrolVO [scode=" + scode + ", sname=" + sname + ", ccode=" + ccode + ", cname=" + cname + ", grade="
				+ grade + ", edate=" + edate + "]";
	}
}
