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
    private String courseInstructorName;
    private String courseInstructorPhone;
    private String courseInstructorEmail;
    private Boolean courseStartDateAlert;
    private Boolean courseEndDateAlert;
    private int courseTermID;

    public String getCourseInstructorName() {
        return courseInstructorName;
    }

    public void setCourseInstructorName(String courseInstructorName) {
        this.courseInstructorName = courseInstructorName;
    }

    public String getCourseInstructorPhone() {
        return courseInstructorPhone;
    }

    public void setCourseInstructorPhone(String courseInstructorPhone) {
        this.courseInstructorPhone = courseInstructorPhone;
    }

    public String getCourseInstructorEmail() {
        return courseInstructorEmail;
    }

    public void setCourseInstructorEmail(String courseInstructorEmail) {
        this.courseInstructorEmail = courseInstructorEmail;
    }

    public Course(int courseID, String courseName, String courseStatus, String courseStartDate, String courseEndDate, String courseShareNotes, String courseInstructorName, String courseInstructorPhone, String courseInstructorEmail, int courseTermID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseStatus = courseStatus;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseShareNotes = courseShareNotes;
        this.courseInstructorName = courseInstructorName;
        this.courseInstructorPhone = courseInstructorPhone;
        this.courseInstructorEmail = courseInstructorEmail;
        this.courseTermID = courseTermID;
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

    public Boolean getCourseStartDateAlert() {
        return courseStartDateAlert;
    }

    public void setCourseStartDateAlert(Boolean courseStartDateAlert) {
        this.courseStartDateAlert = courseStartDateAlert;
    }

    public Boolean getCourseEndDateAlert() {
        return courseEndDateAlert;
    }

    public void setCourseEndDateAlert(Boolean courseEndDateAlert) {
        this.courseEndDateAlert = courseEndDateAlert;
    }

    public int getCourseTermID() {
        return courseTermID;
    }

    public void setCourseTermID(int courseTermID) {
        this.courseTermID = courseTermID;
    }

    @Override
    public String toString() {
        return courseName;
    }

    public Course() {
    }
}