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
import com.yapp.kindpickyeatingandroid.dto.MemDataPL;
import com.yapp.kindpickyeatingandroid.memDataPL.MemAstPL;
import com.yapp.kindpickyeatingandroid.memDataPL.MemPLAPIClient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by user on 2016-08-19.
 */
public class JoinUsCk extends Activity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rgVeg1,rgVeg2;
    private RadioGroup rgRel;
    private MemDataPL m;
    private MemPLAPIClient memAPI;
    private ImageButton btnCk;
    private TextView txt;
    RadioGroup group;
    // String str[]={"페스코","폴로","무관","무슬림","힌두교","사찰음식","무"};
    RadioButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ck);
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

        m = new MemDataPL();
        Intent in = getIntent();
        m.setPwd(in.getExtras().getString("pwd"));
        m.setNm(in.getExtras().getString("nm"));
        m.setEml(in.getExtras().getString("eml"));



//        Log.i("ohdoking",rgVeg.getCheckedRadioButtonId()+"");
//
        //      radioN();
       /* int i=0;
        if(m.getRg().toString().equals("rgVeg1"))
        {i=1;}else if(m.getRg().toString().equals("rgVeg2"))
        {i=2;}


            switch (i){
                case 1:
                    rgVeg1.setOnCheckedChangeListener(this);
                    break;
                case 2:
                    rgVeg2.setOnCheckedChangeListener(this);
                    break;
            }*/
        rgVeg1.setOnCheckedChangeListener(this);
        rgVeg2.setOnCheckedChangeListener(this);
        rgRel.setOnCheckedChangeListener(this);
        btnckEvent();
    }

    public void btnckEvent() {

        btnCk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemAstPL mem = new MemAstPL();
                try {
                    String str = mem.execute(m).get();
                    txt.setText(str);
                    if(m.getError()==500){
                        Toast.makeText(getApplicationContext(), "이메일이 중복됨", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),JoinUs.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override

    public void onCheckedChanged(RadioGroup group, int checkedId) {
 //       m.setRg(group.toString());

        if (group.getCheckedRadioButtonId() == R.id.radioButton1) {
            m.setVegeS(0);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton2) {
            m.setVegeS(1);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton3) {
            m.setVegeS(2);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton4) {
            m.setVegeS(3);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton5) {
            m.setVegeS(4);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton6) {
            m.setVegeS(5);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton7) {
            m.setVegeS(6);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton21) {
            m.setRelS(7);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton22) {
            m.setRelS(8);
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton23) {
            m.setRelS(9);
        } /*else if (group.getCheckedRadioButtonId() == R.id.radioButton24) {
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

