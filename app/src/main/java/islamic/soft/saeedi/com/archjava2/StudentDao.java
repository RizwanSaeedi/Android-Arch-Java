package islamic.soft.saeedi.com.archjava2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao
{
    @Query("SELECT * FROM student WHERE name LIKE '%' || :input  || '%' ORDER BY ID ASC")
    LiveData<List<Student>> getAll(String input);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Student word);

    @Query("DELETE FROM student")
    void deleteAll();
//    // allowing the insert of the same word multiple times by passing a
//    // conflict resolution strategy
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insert(Student student);
//
//    @Query("DELETE FROM student")
//    void deleteAll();
//
//    @Query("SELECT * from student ORDER BY ID ASC")
//    List<Student> getAll();
}
