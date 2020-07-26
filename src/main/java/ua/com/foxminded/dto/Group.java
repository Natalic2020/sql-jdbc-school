package ua.com.foxminded.dto;

public class Group {

    static int count;
    int groupId;
    String groupName;
    int countStudents;
   
    public Group(String groupName) {
        this.count ++;
        this.groupId = count;
        this.groupName = groupName;
    }

    public Group(String groupName, int countStudents) {
        this.groupName = groupName;
        this.countStudents = countStudents;
    }
    
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
   
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return  groupName + ":" + countStudents;
    } 
}
