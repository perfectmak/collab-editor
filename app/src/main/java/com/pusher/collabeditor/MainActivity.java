package com.pusher.collabeditor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.util.HttpAuthorizer;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = MainActivity.class.getSimpleName();
    private static final String PUSHER_API_KEY = "YOUR PUSHER KEY HERE";
    private static final String PUSHER_CLUSTER = "YOUR PUSHER APP CLUSTER";
    private static final String AUTH_ENDPOINT = "AUTHENTICATION ENDPOINT";

    private Pusher pusher;
    private EditText textEditor;
    private TextWatcher textEditorWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textEditor = (EditText) findViewById(R.id.textEditor);

        pusher = new Pusher(PUSHER_API_KEY, new PusherOptions()
                .setEncrypted(true)
                .setCluster(PUSHER_CLUSTER)
                .setAuthorizer(new HttpAuthorizer(AUTH_ENDPOINT)));

        PrivateChannelEventListener subscriptionEventListener = new PrivateChannelEventListener() {


            @Override
            public void onAuthenticationFailure(String message, Exception e) {
                Log.d(DEBUG_TAG, "Authentication failed.");
                Log.d(DEBUG_TAG, message);
                e.printStackTrace();
            }

            @Override
            public void onSubscriptionSucceeded(String message) {
                Log.d(DEBUG_TAG, "Subscription Successful");
                Log.d(DEBUG_TAG, message);
            }

            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(DEBUG_TAG, data);
                        EditorUpdate editorUpdate = new Gson().fromJson(data, EditorUpdate.class);
                        textEditor.removeTextChangedListener(textEditorWatcher);
                        textEditor.setText(editorUpdate.data);
                        textEditor.addTextChangedListener(textEditorWatcher);
                    }
                });
            }
        };

        final PrivateChannel noteChannel = pusher.subscribePrivate("private-editor", subscriptionEventListener);
        noteChannel.bind("client-update", subscriptionEventListener);

        textEditorWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String text = charSequence.toString();
                EditorUpdate editorUpdate = new EditorUpdate(text);
                noteChannel.trigger("client-update", new Gson().toJson(editorUpdate));
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };
        textEditor.addTextChangedListener(textEditorWatcher);

    }

    @Override
    protected void onResume() {
        super.onResume();
        pusher.connect();
    }

    @Override
    protected void onPause() {
        pusher.disconnect();
        super.onPause();
    }
}
