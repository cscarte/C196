package C196.mainactivity.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import C196.mainactivity.DAO.AssessmentDAO;
import C196.mainactivity.DAO.CourseDAO;
import C196.mainactivity.DAO.MentorDAO;
import C196.mainactivity.DAO.TermDAO;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.Entity.Course;
import C196.mainactivity.Entity.Mentor;
import C196.mainactivity.Entity.Term;

@Database(entities = {Assessment.class, Course.class, Mentor.class, Term.class}, version = 3, exportSchema = false)
public abstract class ClassSchedulerDatabase extends RoomDatabase {

    public abstract AssessmentDAO assessmentDAO();

    public abstract CourseDAO courseDAO();

    public abstract MentorDAO mentorDAO();

    public abstract TermDAO termDAO();

    private static volatile ClassSchedulerDatabase INSTANCE;

    static ClassSchedulerDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
        synchronized (ClassSchedulerDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ClassSchedulerDatabase.class, "ClassSchedulerDatabase.db")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
    }
        return INSTANCE;
    }
}
