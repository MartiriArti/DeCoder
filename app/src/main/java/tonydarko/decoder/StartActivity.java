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

public class StartActivity extends Activity implements View.OnClickListener {

    Boolean Latin, Kiril, Num, Symb;
    CheckBox cbLatin, cbKiril, cbNum, cbSymb;
    TextView tvRes;
    EditText edSys, edBit;
    Button toMain;
    String S, B;
    int s, b, res, sum = 0;

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
                if (cbKiril.isChecked()) {
                    sum += 64;
                    Kiril = true;
                    tvRes.setText(getString(R.string.start_tvres_voc) + sum);
                    Toast.makeText(getApplicationContext(), getString(R.string.start_add_kiril), Toast.LENGTH_SHORT).show();
                } else if (!cbKiril.isChecked()) {
                    sum -= 64;
                    Kiril = false;
                    tvRes.setText(getString(R.string.start_tvres_voc) + sum);
                    Toast.makeText(getApplicationContext(), getString(R.string.start_deleted_kiril), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbLatin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbLatin.isChecked()) {
                    sum += 52;
                    Latin = true;
                    tvRes.setText(getString(R.string.start_tvres_voc) + sum);
                    Toast.makeText(getApplicationContext(), getString(R.string.start_add_latin), Toast.LENGTH_SHORT).show();
                } else if (!cbLatin.isChecked()) {
                    sum -= 52;
                    Latin = false;
                    tvRes.setText(getString(R.string.start_tvres_voc) + sum);
                    Toast.makeText(getApplicationContext(), getString(R.string.start_deleted_latin), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbNum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbNum.isChecked()) {
                    sum += 10;
                    Num = true;
                    tvRes.setText(getString(R.string.start_tvres_voc) + sum);
                    Toast.makeText(getApplicationContext(), getString(R.string.start_add_numbers), Toast.LENGTH_SHORT).show();
                } else if (!cbNum.isChecked()) {
                    sum -= 10;
                    Num = false;
                    tvRes.setText(getString(R.string.start_tvres_voc) + sum);
                    Toast.makeText(getApplicationContext(), getString(R.string.start_deleted_numders), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbSymb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbSymb.isChecked()) {
                    sum += 15;
                    Symb = true;
                    tvRes.setText(getString(R.string.start_tvres_voc) + sum);
                    Toast.makeText(getApplicationContext(), getString(R.string.start_add_symb), Toast.LENGTH_SHORT).show();
                } else if (!cbSymb.isChecked()) {
                    sum -= 15;
                    Symb = false;
                    tvRes.setText(getString(R.string.start_tvres_voc) + sum);
                    Toast.makeText(getApplicationContext(), getString(R.string.start_deleted_symb), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
        if (edBit.getText().length() != 0 || edSys.getText().length() != 0) {
            res = 1;
            S = edSys.getText().toString();
            B = edBit.getText().toString();

            s = Integer.parseInt(S);
            b = Integer.parseInt(B);

            if (s <= 10) {

                for (int i = 0; i < b; i++) {
                    res *= s;
                }


                tvRes.setText("Кодов = " + res + " / " + "Словарь = " + sum);

                Toast.makeText(getApplicationContext(), getString(R.string.start_count_codes) + res + " / " +
                        getString(R.string.start_count_symb) + sum, Toast.LENGTH_LONG).show();

                if (res >= sum) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("System", s);
                    intent.putExtra("Bites", b);
                    intent.putExtra("Latin", Latin);
                    intent.putExtra("Kiril", Kiril);
                    intent.putExtra("Num", Num);
                    intent.putExtra("Symb", Symb);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.start_nedd_more_codes, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), R.string.start_not_11, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), R.string.start_fields_empty, Toast.LENGTH_LONG).show();
        }
    }

}
