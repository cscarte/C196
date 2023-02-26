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
    private boolean assessmentGoalDateAlert;
    private boolean assessmentObjective;
    private int assessmentCourseID;

    public Assessment(int assessmentID, String assessmentTitle, String assessmentDueDate, String assessmentGoalDate, boolean assessmentGoalDateAlert, boolean assessmentObjective, int assessmentCourseID) {
        this.assessmentID = assessmentID;
        this.assessmentTitle = assessmentTitle;
        this.assessmentDueDate = assessmentDueDate;
        this.assessmentGoalDate = assessmentGoalDate;
        this.assessmentGoalDateAlert = assessmentGoalDateAlert;
        this.assessmentObjective = assessmentObjective;
        this.assessmentCourseID = assessmentCourseID;
    }

    public boolean isAssessmentGoalDateAlert() {
        return assessmentGoalDateAlert;
    }

    public void setAssessmentGoalDateAlert(boolean assessmentGoalDateAlert) {
        this.assessmentGoalDateAlert = assessmentGoalDateAlert;
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

    public int getAssessmentCourseID() {
        return assessmentCourseID;
    }

    public void setAssessmentCourseID(int assessmentCourseID) {
        this.assessmentCourseID = assessmentCourseID;
    }

    @Override
    public String toString() {
        return assessmentTitle;
    }

    public Assessment() {
    }
}