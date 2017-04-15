package com.yapp.kindpickyeatingandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.dto.JoinDto;
import com.yapp.kindpickyeatingandroid.dto.ResultStateDto;
import com.yapp.kindpickyeatingandroid.memDataPL.MemPLAPIClient;
import com.yapp.kindpickyeatingandroid.network.KindPickyEatingServerClient;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingServerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2016-08-19.
 */
public class JoinUsCk extends Activity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rgVeg1,rgVeg2;
    private RadioGroup rgRel;
    private JoinDto m;
    private MemPLAPIClient memAPI;
    private ImageButton btnCk;
    private TextView txt;
    RadioGroup group;
    // String str[]={"페스코","폴로","무관","무슬림","힌두교","사찰음식","무"};
    RadioButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;

    KindPickyEatingServerService kindPickyEactingService;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ck);

        KindPickyEatingServerClient kindPickyEatingClient = new KindPickyEatingServerClient(getApplicationContext());
        kindPickyEactingService = kindPickyEatingClient.getKindPickyEactingService();
        rgVeg1 = (RadioGroup) findViewById(R.id.rgVeg1);
        rgRel = (RadioGroup) findViewById(R.id.rgRel);
        rgVeg2 = (RadioGroup) findViewById(R.id.rgVeg2);
        btnCk = (ImageButton) findViewById(R.id.btnCk);
//        txt = (TextView) findViewById(R.id.textView4);
        b0 = (RadioButton) findViewById(R.id.radioButton1);
        b1 = (RadioButton) findViewById(R.id.radioButton2);
        b2 = (RadioButton) findViewById(R.id.radioButton3);
        b3 = (RadioButton) findViewById(R.id.radioButton4);
        b4 = (RadioButton) findViewById(R.id.radioButton5);
        b5 = (RadioButton) findViewById(R.id.radioButton6);
        b6 = (RadioButton) findViewById(R.id.radioButton7);
        b7 = (RadioButton) findViewById(R.id.radioButton21);
        b8 = (RadioButton) findViewById(R.id.radioButton22);
        b9 = (RadioButton) findViewById(R.id.radioButton23);
//        b10 = (RadioButton) findViewById(R.id.radioButton24);

        rgVeg1.clearCheck();
        rgVeg1.setOnCheckedChangeListener(listener1);
        rgVeg2.clearCheck();
        rgVeg2.setOnCheckedChangeListener(listener2);

        m = new JoinDto();
        Intent in = getIntent();
        m.setPassword(in.getExtras().getString("pwd"));
        m.setNickname(in.getExtras().getString("nm"));
        m.setEmail(in.getExtras().getString("eml"));

        rgRel.setOnCheckedChangeListener(this);
        btnckEvent();
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rgVeg2.setOnCheckedChangeListener(null);
                rgVeg2.clearCheck();
                rgVeg2.setOnCheckedChangeListener(listener2);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rgVeg1.setOnCheckedChangeListener(null);
                rgVeg1.clearCheck();
                rgVeg1.setOnCheckedChangeListener(listener1);
            }
        }
    };

    public void btnckEvent() {

        btnCk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MemAstPL mem = new MemAstPL();
//                    String str = mem.execute(m).get();
                    Call<ResultStateDto> call = kindPickyEactingService.join(m);
                    call.enqueue(new Callback<ResultStateDto>() {
                        @Override
                        public void onResponse(Call<ResultStateDto> call, Response<ResultStateDto> response) {
                            try{
                                if(response.body().getState().equals("success")){
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                                Log.i("test",response.body().getState());
                            }
                            catch (NullPointerException e){
                                Toast.makeText(getApplicationContext(), "이메일이 중복됨", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),JoinUs.class);
                                startActivity(intent);
                            }


                        }

                        @Override
                        public void onFailure(Call<ResultStateDto> call, Throwable t) {
                            Log.i("test",t.getLocalizedMessage());
                        }
                    });


            }
        });

    }

    @Override

    public void onCheckedChanged(RadioGroup group, int checkedId) {
 //       m.setRg(group.toString());

        if (group.getCheckedRadioButtonId() == R.id.radioButton1) {
            m.setUserCategory(0);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton2) {
            m.setUserCategory(1);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton3) {
            m.setUserCategory(2);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton4) {
            m.setUserCategory(3);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton5) {
            m.setUserCategory(4);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton6) {
            m.setUserCategory(5);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton7) {
            m.setUserCategory(6);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton21) {
            m.setReligion(7);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton22) {
            m.setReligion(8);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton23) {
            m.setReligion(9);
        }
        /*else if (group.getCheckedRadioButtonId() == R.id.radioButton24) {
            m.setRelS(10);
        }*/
        //     }
       /* for(int j=7;j<11;j++){
            if(((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString().equals(str[j].toString()))
            {
                m.setRelS(j);
               // Toast.makeText(getApplicationContext(),m.getRelS() , Toast.LENGTH_SHORT).show();
            }
            }
*/


          /*  if (group.getCheckedRadioButtonId() == R.id.radioButton1) {
                Toast.makeText(getApplicationContext(), "까리가 선택됨", Toast.LENGTH_LONG).show();
            } else if (group.getCheckedRadioButtonId() == R.id.radioButton2) {
                Toast.makeText(getApplicationContext(), "리그오브레전드가 선택됨", Toast.LENGTH_LONG).show();
            } else if (group.getCheckedRadioButtonId() == R.id.radioButton3) {
                Toast.makeText(getApplicationContext(), "리그오브레전드가 선택됨2", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(), " league of legends", Toast.LENGTH_LONG).show();
            }

            if (group.getCheckedRadioButtonId() == R.id.radioButton21) {
                Toast.makeText(getApplicationContext(), "까리가 선택됨33", Toast.LENGTH_LONG).show();
            } else if (group.getCheckedRadioButtonId() == R.id.radioButton22) {
                Toast.makeText(getApplicationContext(), "리그오브레전드가 선택됨33", Toast.LENGTH_LONG).show();
            } else if (group.getCheckedRadioButtonId() == R.id.radioButton23) {
                Toast.makeText(getApplicationContext(), "리그오브레전드가 선택됨2", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(), " league of legends33", Toast.LENGTH_LONG).show();
            }*/

    }
}

