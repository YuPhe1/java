package ex10;

public class StudentVO {
	private String scode;
	private String sname;
	private String dept;
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
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "StudentVO [scode=" + scode + ", sname=" + sname + ", dept=" + dept + "]";
	}
}
