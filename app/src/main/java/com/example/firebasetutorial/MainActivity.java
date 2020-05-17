package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{

    //instance of button and firebase reference
   // private Button mFirebaseBtn;
   // private EditText userInput;
   // private EditText userEmail;
   // private TextView mNameView;
   // private TextView mUserAge;

    private EditText userEmail;
    private EditText userPw;
    private Button registerBtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ListView mListView;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        databaseReference = FirebaseDatabase.getInstance().getReference();



    }
}




        /*
        THIS IS THE FIRST TUTORIAL

        //declare the button
        mFirebaseBtn = (Button) findViewById(R.id.firebase_btn);

        //to get user input and display in the firebase
        userInput = (EditText) findViewById(R.id.user_input);
        userEmail = (EditText) findViewById(R.id.user_email);

        //declare the database to point it to the Root reference, which is
        //'fir-tutorials-1834f:'
        /*
        if just wants to get 'User_01 in database, just add this:
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User_01"); anything that is
        pushed will be under the child and not the root
        if it does not exist at the start, "User_01" will be created as the first child instead

        mDatabase = FirebaseDatabase.getInstance().getReference();



        //when user click on this button, it will add value to the database
        mFirebaseBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*
                Basic of storing data in database
                1 - Create child in root object
                2 - Assign some value to the child object
                -- setting child name and it's value that will appear in database
                --if user click on the button again it will just overwrite


                //need to convert to string first
                String userName = userInput.getText().toString().trim();
                String email = userEmail.getText().toString().trim();

                //to store both object, use HashMap
                HashMap<String, String> dataMap = new HashMap<String,String>();
                dataMap.put("Name", userName);
                dataMap.put("Email", email);


                // mDatabase.child("Name").setValue(userName);

                //this will push the username to the root or child declared in mDatabase
               // mDatabase.push().setValue(userName);

                /*
                this is to store the HashMap of name and email into database,
                every time when button is clicked, it will create new child under Root
                with name and email.
                add OnCompleteListener to check if the data is added successfully

                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        //data is added successfully
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Data stored", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
    */


        /* THIS IS TO RETRIEVE NAME AND AGE

         mNameView = (TextView) findViewById(R.id.name_view);
        mUserAge = (TextView) findViewById(R.id.age_view);

        //retrieve the value of child
        mDatabase.child("Name").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                //retrieve value using datasnapshot
                //convert to string to store in variable
                String name = dataSnapshot.getValue().toString();

                mNameView.setText("Name : " + name);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

        mDatabase.child("Age").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                //retrieve value using datasnapshot
                //convert to string to store in variable
                String age = dataSnapshot.getValue().toString();

                mUserAge.setText("Age : " + age);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
         */







        /* THIS IS THE DATABASE TUTORIAL 14
         private DatabaseReference mDatabase;
    private ListView mUserList;

    private ArrayList<String> mUserName = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is to retrieve name from database
        //database need to point towards child "Name"
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserList = (ListView) findViewById(R.id.user_list);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUserName);

        mUserList.setAdapter(arrayAdapter);


        mDatabase.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                //when child is added to database, this function will run

                //this is to retrieve the value, for example in this case is the name
                String value = dataSnapshot.getValue(String.class);
                mUserName.add(value);

                //retieve the key, for example in this case is the key 01,02, etc
                String key = dataSnapshot.getKey();
                mKeys.add(key);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                //when any data stored is changed, this will run
                //need to store the key into another array which is above

                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();

                int index = mKeys.indexOf(key);

                mUserName.set(index, value);

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot)
            {
                //when any data stored is removed, this will run
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                //when any data stored is moved, this will run
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

    }
         */









        /* DATABASE TUTORIAL
         userEmail = (EditText) findViewById(R.id.user_email);
        userPw = (EditText) findViewById(R.id.user_password);

        registerBtn = (Button) findViewById(R.id.register_button);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                //if equal to null, user haven't logged in
                //if not nullm user logged in, send user to another activity
                if(firebaseAuth.getCurrentUser() != null)
                {
                    startActivity(new Intent(MainActivity.this, AccountActivity.class));
                }
            }
        };

        registerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startSignIn();
            }
        });
    }




    @Override
    protected void onStart()
    {
        super.onStart();

        //assign authstateListener to FirebaseAuth
        mAuth.addAuthStateListener(mAuthListener);

    }


    private void startSignIn()
    {
        String email = userEmail.getText().toString();
        String password = userPw.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }

        else
            {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    //check the status of the task that is proceeded
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        //meaning user has not logged in
                        if(!task.isSuccessful())
                        {
                                Toast.makeText(MainActivity.this, "Issue signing in", Toast.LENGTH_SHORT).show();
                        }
                    }
            });
        }
    }
         */