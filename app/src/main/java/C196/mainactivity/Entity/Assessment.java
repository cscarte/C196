package C196.mainactivity.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentTitle;
    private String assessmentDueDate;
    private String assessmentGoalDate;
    private boolean assessmentObjective;
    private int courseID;

    public Assessment(int assessmentID, String assessmentTitle, String assessmentDueDate, String assessmentGoalDate, boolean assessmentObjective, int courseID) {
        this.assessmentID = assessmentID;
        this.assessmentTitle = assessmentTitle;
        this.assessmentDueDate = assessmentDueDate;
        this.assessmentGoalDate = assessmentGoalDate;
        this.assessmentObjective = assessmentObjective;
        this.courseID = courseID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getAssessmentDueDate() {
        return assessmentDueDate;
    }

    public void setAssessmentDueDate(String assessmentDueDate) {
        this.assessmentDueDate = assessmentDueDate;
    }

    public String getAssessmentGoalDate() {
        return assessmentGoalDate;
    }

    public void setAssessmentGoalDate(String assessmentGoalDate) {
        this.assessmentGoalDate = assessmentGoalDate;
    }

    public boolean isAssessmentObjective() {
        return assessmentObjective;
    }

    public void setAssessmentObjective(boolean assessmentObjective) {
        this.assessmentObjective = assessmentObjective;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentID=" + assessmentID +
                ", assessmentTitle='" + assessmentTitle + '\'' +
                ", assessmentDueDate=" + assessmentDueDate +
                ", assessmentGoalDate=" + assessmentGoalDate +
                ", assessmentObjective=" + assessmentObjective +
                ", courseID=" + courseID +
                '}';
    }

    public Assessment() {
    }
}