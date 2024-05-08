package bean;

public class Student {

    // 学生番号
    private String no;
    // 学生名
    private String name;
    // 入学年度
    private int entYear;
    // クラス番号
    private String classNum;
    // 出席状況
    private boolean isAttend;
    // 所属する学校
    private School school;



    // 学生番号のゲッターとセッター
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }



    // 学生名のゲッターとセッター
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    // 入学年度のゲッターとセッター
    public int getEntYear() {
        return entYear;
    }
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }



    // クラス番号のゲッターとセッター
    public String getClassNum() {
        return classNum ;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }



    // 出席状況のゲッターとセッター
    public boolean isAttend() {
        return isAttend;
    }
    public void setAttend(boolean isAttend) {
        this.isAttend = isAttend;
    }



    // 所属する学校のゲッターとセッター
    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }
}
