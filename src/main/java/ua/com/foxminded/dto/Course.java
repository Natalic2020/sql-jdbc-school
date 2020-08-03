package ua.com.foxminded.dto;

public class Course {

    static int count;
    int courseId;
    String courseName;
    String courseDescription;
  
    public Course( String courseName) {
        this.count ++;
        this.courseId = count;
        this.courseName = courseName;
    }
    
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseDescription() {
        return courseDescription;
    }
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    } 
}
