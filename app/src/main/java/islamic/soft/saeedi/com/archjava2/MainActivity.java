package islamic.soft.saeedi.com.archjava2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private TextInputEditText mEtName;
//    private TextInputEditText mEtMob;
//    private TextInputEditText mEtAddress;

    private StudentViewModel viewModel;
    private ListView mListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        initView();

        viewModel.getAllStudents("").observe(this, students ->
        {
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
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            View dView = getLayoutInflater().inflate(R.layout.save_student_dialog, null);
            EditText etName = dView.findViewById(R.id.etName);
            EditText etMob = dView.findViewById(R.id.etMob);
            EditText etAddress = dView.findViewById(R.id.etAddress);
            alert.setView(dView);

            alert.setPositiveButton("ADD", (dialogInterface, i) ->
            {
                Student student = new Student(etName.getText().toString().trim(), etMob.getText().toString().trim(), etAddress.getText().toString().trim());
                viewModel.insert(student);
                Snackbar.make(view, "Added", Snackbar.LENGTH_SHORT);

                etName.setText("");
                etMob.setText("");
                etAddress.setText("");
            });

            alert.setNeutralButton("CANCEL", null);
            alert.setCancelable(false);
            alert.create();
            alert.show();
        });

        mListView.setOnItemClickListener((adapterView, view, p, l) ->
        {
            Student selectedStudent = (Student) adapterView.getItemAtPosition(p);

            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            View dView = getLayoutInflater().inflate(R.layout.save_student_dialog, null);
            EditText etName = dView.findViewById(R.id.etName);
            EditText etMob = dView.findViewById(R.id.etMob);
            EditText etAddress = dView.findViewById(R.id.etAddress);

            etName.setText(selectedStudent.getName());
            etMob.setText(selectedStudent.getMob());
            etAddress.setText(selectedStudent.getAddress());

            alert.setView(dView);

            alert.setPositiveButton("UPDATE", (dialogInterface, i) ->
            {
                Student student = new Student(etName.getText().toString().trim(), etMob.getText().toString().trim(), etAddress.getText().toString().trim());
                student.setID(selectedStudent.getID());
                viewModel.insert(student);
                Snackbar.make(view, "Updated", Snackbar.LENGTH_SHORT);
            });

            alert.setNeutralButton("CANCEL", null);
            alert.setCancelable(false);
            alert.create();
            alert.show();
        });

        mEtName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                viewModel.getAllStudents(mEtName.getText().toString()).removeObservers(MainActivity.this);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                viewModel.getAllStudents(mEtName.getText().toString()).observe(MainActivity.this, students ->
                {
                    loadListView(students);
                });

            }
        });
    }

    private void initView()
    {
        mListView = findViewById(R.id.listView);
        mEtName = findViewById(R.id.etName);
    }
}
