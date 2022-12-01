package C196.mainactivity.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentTitle;
    private Date assessmentDueDate;
    private Date assessmentGoalDate;
    private boolean assessmentObjective;

    public Assessment(int assessmentID, String assessmentTitle, Date assessmentDueDate, Date assessmentGoalDate, boolean assessmentObjective) {
        this.assessmentID = assessmentID;
        this.assessmentTitle = assessmentTitle;
        this.assessmentDueDate = assessmentDueDate;
        this.assessmentGoalDate = assessmentGoalDate;
        this.assessmentObjective = assessmentObjective;
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

    public Date getAssessmentDueDate() {
        return assessmentDueDate;
    }

    public void setAssessmentDueDate(Date assessmentDueDate) {
        this.assessmentDueDate = assessmentDueDate;
    }

    public Date getAssessmentGoalDate() {
        return assessmentGoalDate;
    }

    public void setAssessmentGoalDate(Date assessmentGoalDate) {
        this.assessmentGoalDate = assessmentGoalDate;
    }

    public boolean isAssessmentObjective() {
        return assessmentObjective;
    }

    public void setAssessmentObjective(boolean assessmentObjective) {
        this.assessmentObjective = assessmentObjective;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentID=" + assessmentID +
                ", assessmentTitle='" + assessmentTitle + '\'' +
                ", assessmentDueDate=" + assessmentDueDate +
                ", assessmentGoalDate=" + assessmentGoalDate +
                ", assessmentObjective=" + assessmentObjective +
                '}';
    }
}