package tonydarko.decoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends Activity implements View.OnClickListener{

    TextView tvRes;
    EditText edSys, edBit;
    Button toMain;
    String S, B;
    int s, b, res = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        edSys = (EditText) findViewById(R.id.edInSys);
        edBit = (EditText) findViewById(R.id.edInBit);

        tvRes = (TextView) findViewById(R.id.tvres);

        toMain = (Button) findViewById(R.id.toMain);
        toMain.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

            S = edSys.getText().toString();
            B = edBit.getText().toString();

            s = Integer.parseInt(S);
            b = Integer.parseInt(B);

        for (int i = 0; i < b; i++) {
            res *= s;
        }

        Toast.makeText(getApplicationContext(),"Количество кодов = " + res, Toast.LENGTH_LONG).show();

        if (res >= 97){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("System", s);
        intent.putExtra("Bites", b);
        startActivity(intent);
    } else {
            Toast.makeText(getApplicationContext(),"Не хватает кодов", Toast.LENGTH_LONG).show();
        }
    }
}
