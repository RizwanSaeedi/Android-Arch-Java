package islamic.soft.saeedi.com.archjava2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel
{
    private LiveData<String> searchInput;
    private Repo mRepository;
    private final LiveData<List<Student>> mAllStudents;

    public StudentViewModel(Application application)
    {
        super(application);
        mRepository = new Repo(application);
        mAllStudents = mRepository.getAllStudent("");
    }

    LiveData<List<Student>> getAllStudents(String input)
    {
        return mRepository.getAllStudent(input);
    }

    public void insert(Student student)
    {
        mRepository.insert(student);
    }

    public void deleteByID(int id)
    {
        mRepository.deleteByID(id);
    }
}
