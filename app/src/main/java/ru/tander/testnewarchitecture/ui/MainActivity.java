package ru.tander.testnewarchitecture.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.tander.testnewarchitecture.R;
import ru.tander.testnewarchitecture.database.entity.User;

public class MainActivity extends LifecycleActivity {
    private static final String TAG = "MainActivity";
    private  MainViewModel viewModel;
    private TextView textView;
    private Button btnTest;
    private Button getUsers;
    private Button btnDelete;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tvTest);
        btnTest = (Button) findViewById(R.id.btnTest);
        getUsers = (Button) findViewById(R.id.btnGet);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getUsers.setOnClickListener(v -> {
            viewModel.getUsers().observe(this, users -> {
                StringBuilder b = new StringBuilder();
                for (User u:users){
                            b
                            .append(" ")
                            .append(u.getLastName());
                }
                textView.setText(b.toString());
            });
        });

        btnTest.setOnClickListener(v -> {
            executorService.execute(() -> {
                viewModel.addUser(new User((int)(Math.random()*10), "Andrey", "Savchenko"));
            });
        });

        btnDelete.setOnClickListener(v ->
                executorService.execute(() ->viewModel.deleteAll()));

    }
}
