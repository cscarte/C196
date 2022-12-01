package C196.mainactivity.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mentors")
public class Mentor {
    @PrimaryKey(autoGenerate = true)
    private int mentorID;
    private String mentorName;
    private String mentorPhoneNumber;
    private String mentorEmail;

    public Mentor(int mentorID, String mentorName, String mentorPhoneNumber, String mentorEmail) {
        this.mentorID = mentorID;
        this.mentorName = mentorName;
        this.mentorPhoneNumber = mentorPhoneNumber;
        this.mentorEmail = mentorEmail;
    }

    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorPhoneNumber() {
        return mentorPhoneNumber;
    }

    public void setMentorPhoneNumber(String mentorPhoneNumber) {
        this.mentorPhoneNumber = mentorPhoneNumber;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "mentorID=" + mentorID +
                ", mentorName='" + mentorName + '\'' +
                ", mentorPhoneNumber='" + mentorPhoneNumber + '\'' +
                ", mentorEmail='" + mentorEmail + '\'' +
                '}';
    }
}