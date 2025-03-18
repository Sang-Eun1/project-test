package pse.finaltest;

import java.util.Objects;

public class Student {
	String stdID; //학번
	String stdName; //이름	
	
	public Student(String stdID, String stdName) {
		super();
		this.stdID = stdID;
		this.stdName = stdName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return Objects.equals(stdID, student.stdID);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(stdID);
	}

	// equals 랑 hashcode 오버라이딩 해주면 비교 됨

	@Override
	public String toString() {


		String str = "학번 : "+this.stdID+" / ";
		str += "이름 : "+this.stdName+"\n";		
		str += "------------------------\n";
		return str;
	}

}
