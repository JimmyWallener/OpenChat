package se.gritacademy.openchat;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import se.gritacademy.openchat.models.ChatModel;
import se.gritacademy.openchat.models.UserModel;


public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "UserChat";
    ImageView image;
    EditText userMessage;
    FirebaseAuth mAuth;
    DatabaseReference mChat, mUser;
    FirebaseListAdapter adapter;
    String username = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mAuth = FirebaseAuth.getInstance();
        userMessage = findViewById(R.id.user_message);
        image = findViewById(R.id.send_image);
        String UIID = mAuth.getUid();

        mChat = FirebaseDatabase.getInstance().getReference().child("chats");
        assert UIID != null;
        mUser = FirebaseDatabase.getInstance().getReference("Users").child(UIID);

        showChatMessage();

        // Get username from db for use in sendMessage method
        mUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel user = dataSnapshot.getValue(UserModel.class);
                assert user != null;
                username = user.getUsername();
                Log.d(TAG, "Value of username " + username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    // Pushes message,username,timestamp via constructor to db
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void sendMessage(View view) {

        if(userMessage.getText() != null) {
            if(!userMessage.getText().toString().equals("") && username != null) {
                mChat.push().setValue(new ChatModel(userMessage.getText().toString(), username, System.currentTimeMillis()));
                Log.d(TAG, "usermessage pushed: success " + username + " " + userMessage);

            }
        }
        Objects.requireNonNull(userMessage.getText()).clear();
    }

    // Using firebaselistoption to populate chatscreen.
    public void showChatMessage(){
        ListView messages = findViewById(R.id.list_message);

        Query query = mChat;
        Log.d(TAG,"mChat data: " +mChat);
        FirebaseListOptions<ChatModel> options =
                new FirebaseListOptions.Builder<ChatModel>().setQuery(query, ChatModel.class)
                        .setLayout(R.layout.messages).setLifecycleOwner(this).build();

        adapter = new FirebaseListAdapter<ChatModel>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull ChatModel model, int position) {
                TextView messageText, messageUser, messageTime;

                messageText = v.findViewById(R.id.message_text);
                messageUser = v.findViewById(R.id.message_user);
                messageTime = v.findViewById(R.id.message_time);

                messageText.setText(model.getMessage());
                messageUser.setText(model.getName());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTimestamp()));
            }
        };
        messages.setAdapter(adapter);
        }


    }

