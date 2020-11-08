package islamic.soft.saeedi.com.archjava2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repo
{
    private StudentDao mStudentDao;
    private LiveData<List<Student>> mAllStudent;
    public Repo(Application application)
    {
        AppDatabase db = AppDatabase.getDatabase(application);
        mStudentDao = db.studentDao();
        mAllStudent = mStudentDao.getAll("");
    }

    public LiveData<List<Student>> getAllStudent(String input)
    {
        mAllStudent = mStudentDao.getAll(input);
        return mAllStudent;
    }

    public void insert(Student student)
    {
        AppDatabase.databaseWriteExecutor.execute(() ->
        {
            mStudentDao.insert(student);
        });
    }
}
