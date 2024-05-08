package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject extends User implements Serializable {

    // 入学年度
    private int entYear;
    // 学生番号
    private String studentNo;
    // 学生名
    private String studentName;
    // クラス番号
    private String classNum;
    // 科目ごとの得点（科目番号と得点のマップ）
    private Map<Integer, Integer> points;



    // 入学年度のゲッターとセッター
    public int getEntYear() {
        return entYear;
    }
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }



    // 学生番号のゲッターとセッター
    public String getStudentNo() {
        return studentNo;
    }
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }



    // 学生名のゲッターとセッター
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }



    // クラス番号のゲッターとセッター
    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }



    // 科目ごとの得点のマップのゲッターとセッター
    public Map<Integer, Integer> getPoints() {
        return points;
    }
    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }
}
