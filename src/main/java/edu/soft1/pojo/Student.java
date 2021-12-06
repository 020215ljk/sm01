package edu.soft1.pojo;

public class Student {
    private String name;
    private String sex;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }


    public static void main(String[] args) {
        Student student = new Student();
        student.setName("peter");
        student.setSex("男");
        System.out.println("name="+student.getName());
        System.out.println("sex="+student.getSex());
    }

}
