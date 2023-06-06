package sg.edu.rp.c346.id22013834.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd, btnDel, btnClear;
    ListView lvTask;
    Spinner spn;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.taskEditText);
        btnAdd = findViewById(R.id.addButton);
        btnDel = findViewById(R.id.deleteButton);
        btnClear = findViewById(R.id.clearButton);
        lvTask = findViewById(R.id.taskListView);
        spn = findViewById(R.id.customSpinner);

        alTask = new ArrayList<>();

        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                if (!TextUtils.isEmpty(task)) {
                    alTask.add(task);
                    aaTask.notifyDataSetChanged();
                    etTask.setText("");
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTask.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else {
                    String input = etTask.getText().toString().trim();
                    if (!TextUtils.isEmpty(input) && TextUtils.isDigitsOnly(input)) {
                        int pos = Integer.parseInt(input);
                        if (pos >= 0 && pos < alTask.size()) {
                            alTask.remove(pos);
                            aaTask.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid index", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
                    }
                    etTask.setText("");
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etTask.setHint("Type a new task here");
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        break;
                    case 1:
                        etTask.setHint("Type the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
