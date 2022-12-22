package C196.mainactivity.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import C196.mainactivity.Entity.Assessment;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM assessments ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessments();

    @Query("SELECT * FROM assessments WHERE courseID = :courseID")
    LiveData<List<Assessment>> getAssessmentByCourseID(int courseID);

    @Query("SELECT * FROM assessments ORDER BY assessmentDueDate")
    LiveData<List<Assessment>> getAllAssessmentsOrderByDueDate();

    @Query("DELETE FROM assessments")
    int deleteAllAssessments();

    @Query("SELECT COUNT(*) FROM assessments")
    int getCountOfAssessments();

    @Query("SELECT COUNT(*) FROM assessments WHERE courseID = :courseID")
    int getAssessmentCountByCourseID(int courseID);

    @Query("SELECT COUNT(*) FROM assessments WHERE courseID IS NOT NULL")
    int getAssessmentCountByNonNullCourses();
}
