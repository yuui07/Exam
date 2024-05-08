package bean;

import java.io.Serializable;

public class TestListStudent extends User implements Serializable {

    // 科目名
    private String subjectName;
    // 科目コード
    private String subjectCd;
    // 受験番号
    private int num;
    // 得点
    private int point;



    // 科目名のゲッターとセッター
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }



    // 科目コードのゲッターとセッター
    public String getSubjectCd() {
        return subjectCd;
    }
    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }



    // 受験番号のゲッターとセッター
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }



    // 得点のゲッターとセッター
    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
}