package ex10;

public class CourseVO {
	private String ccode;
	private String cname;
	
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
	@Override
	public String toString() {
		return "CoursVO [ccode=" + ccode + ", cname=" + cname + "]";
	}
}
