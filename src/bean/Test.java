package bean;

import java.io.Serializable;

public class Test extends User implements Serializable  {

	private Student student;
	private String classNum;
	private Subject subject;
	private School school;
	private int no;
	private int point;



	public Student getstudent(){
		return student;
	}
	public void setStudent(Student student){
		this.student = student;
	}



	public String getclassNum(){
		return classNum;
	}
	public void setClassNum(String classNum){
		this.classNum = classNum;
	}



	public Subject getsubject(){
		return subject;
	}
	public void setSubject(Subject subject){
		this.subject = subject;
	}



	public School getSchool(){
		return school;
	}
	public void setSchool(School school){
		this.school = school;
	}



	public int getno(){
		return no;
	}
	public void setNo(int no){
		this.no = no;
	}



	public int getpoint(){
		return point;
	}
	public void setPoint(int point){
		this.point = point;
	}
}