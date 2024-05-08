package bean;

import java.io.Serializable;

public class Test extends User implements Serializable {

    // 受験した学生情報
    private Student student;
    // クラス番号
    private String classNum;
    // 受験した科目情報
    private Subject subject;
    // 受験した学校情報
    private School school;
    // テストの番号
    private int no;
    // テストの得点
    private int point;



    // 受験した学生情報のゲッターとセッター
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }



    // クラス番号のゲッターとセッター
    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }



    // 受験した科目情報のゲッターとセッター
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }



    // 受験した学校情報のゲッターとセッター
    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }



    // テストの番号のゲッターとセッター
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }



    // テストの得点のゲッターとセッター
    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
}
