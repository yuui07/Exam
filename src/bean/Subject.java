package bean;

import java.io.Serializable;

public class Subject extends User implements Serializable {

    // 科目コード
    private String cd;
    // 科目名
    private String name;
    // 科目が属する学校
    private School school;



    // 科目コードのゲッターとセッター
    public String getCd() {
        return cd;
    }
    public void setCd(String cd) {
        this.cd = cd;
    }



    // 科目名のゲッターとセッター
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    // 科目が属する学校のゲッターとセッター
    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }
}
