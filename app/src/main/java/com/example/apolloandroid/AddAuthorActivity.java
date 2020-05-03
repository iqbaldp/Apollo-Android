package com.example.apolloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apolloandroid.connector.ApolloConnector;

import org.jetbrains.annotations.NotNull;

public class AddAuthorActivity extends AppCompatActivity {

    EditText firstNameEt;
    EditText lastNameEt;
    String firstName;
    String lastName;
    Button saveAuthorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_author);
        firstNameEt = findViewById(R.id.fn_ed);
        lastNameEt = findViewById(R.id.ln_ed);
        saveAuthorBtn = findViewById(R.id.add_author_btn);
        if (saveAuthorBtn != null) {
            saveAuthorBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerAuthor();
                }
            });
        }
    }

    private void registerAuthor() {
        if (!firstNameEt.getText().toString().isEmpty()) {
            firstName = firstNameEt.getText().toString();

            if (!lastNameEt.getText().toString().isEmpty()) {
                lastName = lastNameEt.getText().toString();
                postAuthorDetails(firstName, lastName);

            }else {
                Log.d("ADD", "LastName");
            }
        }else {
            Log.d("ADD", "FirstName");
        }
    }

    public void postAuthorDetails(String myFirst, String myLast) {
        ApolloConnector.setupApollo().mutate(new PostAuthorDataMutation(myFirst, myLast)).enqueue(
                new ApolloCall.Callback<PostAuthorDataMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<PostAuthorDataMutation.Data> response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AddAuthorActivity.this,
                                        "Author Added Successfully !", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddAuthorActivity.this, MainActivity.class));
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AddAuthorActivity.this,
                                        "Failed To Add Author !", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
        );
    }


}
