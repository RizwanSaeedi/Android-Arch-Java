package islamic.soft.saeedi.com.archjava2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private TextInputEditText mEtName;
    private TextInputEditText mEtMob;
    private TextInputEditText mEtAddress;

    private Button mBtnInsert;
    private StudentViewModel viewModel;
    private ListView mListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        initView();
        mBtnInsert = findViewById(R.id.btnInsert);
        viewModel.getAllStudents().observe(this, students -> {
            // Update the cached copy of the words in the adapter.
            loadListView(students);
        });
        attachEvents();
    }

    private void loadListView(List<Student> students)
    {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, students);
        mListView.setAdapter(adapter);
    }

    private void attachEvents()
    {
        mBtnInsert.setOnClickListener(view ->
        {
            String etName = mEtName.getText().toString().trim();
            String etMob = mEtMob.getText().toString().trim();
            String etAddress = mEtAddress.getText().toString().trim();

            Student student = new Student(etName, etMob, etAddress);
            viewModel.insert(student);
        });
    }

    private void initView()
    {
        mEtName = findViewById(R.id.etName);
        mEtMob = findViewById(R.id.etMob);
        mEtAddress = findViewById(R.id.etAddress);
        mListView = findViewById(R.id.listView);
    }
}
