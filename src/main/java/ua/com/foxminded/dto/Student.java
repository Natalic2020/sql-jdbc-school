package ua.com.foxminded.dto;

public class Student {

    static int count; 
    int studentId;
    int groupId;
    String firstName;
    String lastName;
    
    public Student(String firstName, String lastName) {
        this.count ++;
        this.studentId = count;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return  firstName + " :  " + lastName ;
    }   
}
