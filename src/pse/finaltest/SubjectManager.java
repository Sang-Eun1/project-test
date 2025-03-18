package pse.finaltest;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectManager {
	String deptName; // 학과명
	List<Subject> sublist = new ArrayList<>(); // 과목 저장하기 위한 리스트
	
	SubjectManager(String deptName){		
		this.deptName = deptName;
	}
	
	void addSubject(Subject sub) {
		if(!(sublist.contains(sub))){
			sublist.add(sub);
			System.out.println("과목 등록이 완료 되었습니다.");
		}
		else{
			System.out.println("이미 등록된 과목입니다.");
		}
	}
	
	Subject findSubject(String subID) {

		for (Subject subject : sublist) {
			if(subject.subID.equals(subID)){
				return subject;
			}
		}
		return null;
	}
	
	List<Subject> findStudent(String stdID) {

		List<Subject> findSublist = new ArrayList<>();
		for (Subject subject : sublist) {
			for(Student student : subject.stdset){
				if(student.stdID.equals(stdID)){
					findSublist.add(subject);
				}
			}
		}
		if(findSublist.isEmpty()){
			return null;
		}
		return findSublist;

	}
	
	@Override
	public String toString() {

		String str = "학과명 : "+this.deptName+"\n";	
		str += "개설 과목 현황\n";
		str += "=========================\n";
		for (Subject subject : sublist) {
			str += subject.toString();
		}

		return str;


	}

	public Subject getMaxSubject() {

		List<Subject> list = sublist.stream().sorted((o1, o2) -> o1.stdset.size() - o2.stdset.size())
						.toList();

		return list.get(sublist.size() - 1);






	}
}
