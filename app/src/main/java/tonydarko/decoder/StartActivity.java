package tonydarko.decoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends Activity implements View.OnClickListener{

    Boolean Latin, Kiril, Num, Symb;
    CheckBox cbLatin, cbKiril, cbNum, cbSymb;
    TextView tvRes;
    EditText edSys, edBit;
    Button toMain;
    String S, B;
    int s, b, res = 1, sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        edSys = (EditText) findViewById(R.id.edInSys);
        edBit = (EditText) findViewById(R.id.edInBit);

        cbKiril = (CheckBox) findViewById(R.id.cbKiril);
        cbLatin = (CheckBox) findViewById(R.id.cbLatin);
        cbNum = (CheckBox) findViewById(R.id.cbNum);
        cbSymb = (CheckBox) findViewById(R.id.cbSymb);

        tvRes = (TextView) findViewById(R.id.tvres);

        toMain = (Button) findViewById(R.id.toMain);
        toMain.setOnClickListener(this);

        cbKiril.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbKiril.isChecked()){
                    sum += 64;
                    Kiril = true;
                    Toast.makeText(getApplicationContext(),"Добавленна Кириллица " + sum, Toast.LENGTH_SHORT).show();
                }else if (!cbKiril.isChecked()){
                    sum -= 64;
                    Kiril = false;
                    Toast.makeText(getApplicationContext(),"Удаленна Кириллица " + sum, Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbLatin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbLatin.isChecked()){
                    sum += 52;
                    Latin = true;
                    Toast.makeText(getApplicationContext(),"Добавленна Латиница " + sum, Toast.LENGTH_SHORT).show();
                }else if (!cbLatin.isChecked()){
                    sum -= 52;
                    Latin = false;
                    Toast.makeText(getApplicationContext(),"Удалеленна Латиница " + sum, Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbNum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbNum.isChecked()){
                    sum += 10;
                    Num = true;
                    Toast.makeText(getApplicationContext(),"Добавленны Числа "+ sum, Toast.LENGTH_SHORT).show();
                }else if (!cbNum.isChecked()){
                    sum -= 10;
                    Num = false;
                    Toast.makeText(getApplicationContext(),"Удаленны Числа " + sum, Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbSymb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbSymb.isChecked()){
                    sum += 15;
                    Symb = true;
                    Toast.makeText(getApplicationContext(),"Добавленны Символы " + sum, Toast.LENGTH_SHORT).show();
                }else if (!cbSymb.isChecked()){
                    sum -= 15;
                    Symb = false;
                    Toast.makeText(getApplicationContext(),"Удаленны Символы " + sum, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    public void onClick(View view) {
        if (edBit.getText().length() != 0 || edSys.getText().length() != 0) {
            S = edSys.getText().toString();
            B = edBit.getText().toString();

            s = Integer.parseInt(S);
            b = Integer.parseInt(B);

        for (int i = 0; i < b; i++) {
            res *= s;
        }

        Toast.makeText(getApplicationContext(),"Количество кодов = " + res, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Количество символов = " + sum, Toast.LENGTH_LONG).show();

        if (res > sum){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("System", s);
        intent.putExtra("Bites", b);
        intent.putExtra("Latin", Latin);
        intent.putExtra("Kiril", Kiril);
        intent.putExtra("Num", Num);
        intent.putExtra("Symb", Symb);
        startActivity(intent);
    } else {
            Toast.makeText(getApplicationContext(),"Не хватает кодов", Toast.LENGTH_LONG).show();
        }
    }else {
            Toast.makeText(getApplicationContext(),"Поля не заполнены", Toast.LENGTH_LONG).show();
        }
    }

}
