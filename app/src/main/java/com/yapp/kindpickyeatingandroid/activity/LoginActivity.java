package com.yapp.kindpickyeatingandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.dto.UserDto;
import com.yapp.kindpickyeatingandroid.network.KindPickyEatingServerClient;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingServerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private ImageButton btnLGN;
    private ImageButton btnJN;
    ImageButton btnJNFb;
    ImageButton btnJNNaver;
    private EditText edtID;
    private EditText edtPwd;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    KindPickyEatingServerService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        btnLGN = (ImageButton) findViewById(R.id.btnLGN);
        btnJN = (ImageButton) findViewById(R.id.btnJN);
        btnJNFb = (ImageButton) findViewById(R.id.btnLGN_fb);
        btnJNNaver = (ImageButton) findViewById(R.id.btnLGN_nv);
        edtID = (EditText) findViewById(R.id.editID);
        edtPwd = (EditText) findViewById(R.id.edtPwd);

        KindPickyEatingServerClient kindPickyEatingClient = new KindPickyEatingServerClient(getApplicationContext());

        service = kindPickyEatingClient.getKindPickyEactingService();

        btnLGN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<UserDto> mem = service.login(edtID.getText().toString(), edtPwd.getText().toString());

                mem.enqueue(new Callback<UserDto>() {
                    @Override
                    public void onResponse(Call<UserDto> call, Response<UserDto> response) {

                        Log.i("test", response.body().getEmail() + " == " + edtID.getText().toString());

                        String editTextValue = edtID.getText().toString();

                        if(editTextValue.equals("")){
                            Toast.makeText(LoginActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }else if (response.body().getEmail().equals(editTextValue)) {
                            edtID.setText("");
                            edtPwd.setText("");
                            Intent in=new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(in);
                        } else {
                            Toast.makeText(LoginActivity.this, "아이디나 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDto> call, Throwable t) {

                        Toast.makeText(LoginActivity.this, "아이디나 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btnJNNaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
        });
        btnJNFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
        });
        btnJN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinUs.class);
                startActivity(intent);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


}
