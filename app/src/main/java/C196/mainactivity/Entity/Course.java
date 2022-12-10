package C196.mainactivity.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseName;
    private String courseStatus;
    private String courseStartDate;
    private String courseEndDate;
    private String courseShareNotes;

    public Course(int courseID, String courseName, String courseStatus, String courseStartDate, String courseEndDate, String courseShareNotes) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseStatus = courseStatus;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseShareNotes = courseShareNotes;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getCourseShareNotes() {
        return courseShareNotes;
    }

    public void setCourseShareNotes(String courseShareNotes) {
        this.courseShareNotes = courseShareNotes;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", courseStartDate=" + courseStartDate +
                ", courseEndDate=" + courseEndDate +
                ", courseShareNotes='" + courseShareNotes + '\'' +
                '}';
    }

    public Course() {
    }
}