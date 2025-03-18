package pse.finaltest;

import java.util.HashSet;
import java.util.Objects;

public class Subject {
	String subID; //과목 번호
	String subName; //과목 이름
	HashSet<Student> stdset = new HashSet<>(); // 수강생 저장구조
	
	public Subject(String subID) {
		this(subID, null);		
	}

	public Subject(String subID, String subName) {
		super();
		this.subID = subID;
		this.subName = subName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Subject subject = (Subject) o;
		return Objects.equals(subID, subject.subID);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(subID);
	}

	public void register(Student std) {
		if(!(stdset.contains(std))){
			stdset.add(std);
			System.out.println("수강 등록이 잘 되었습니다.");
		}
		else{
			System.out.println("이미 수강 등록되어 있습니다.");
		}
	}
	
	@Override
	public String toString() {

		String str = "과목번호 : "+this.subID+"\n";
		str += "과목이름 : "+this.subName+"\n";
		str += "등록 학생 명단\n";
		str += "------------------------\n";



		if(stdset.isEmpty()){
			str += "등록한 학생이 존재하지 않습니다." + "\n\n";
		}
		else{
			for (Student student : stdset) {
				str += student.toString();
			}
		}

		return str;
	}
	
	
}
