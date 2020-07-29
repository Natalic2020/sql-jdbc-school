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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + countStudents;
        result = prime * result + groupId;
        result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        if (countStudents != other.countStudents)
            return false;
        if (groupId != other.groupId)
            return false;
        if (groupName == null) {
            if (other.groupName != null)
                return false;
        } else if (!groupName.equals(other.groupName))
            return false;
        return true;
    } 
}
