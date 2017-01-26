package tonydarko.decoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends Activity implements View.OnClickListener{

    EditText edSys, edBit;
    Button toMain;
    String S, B;
    int s, b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        edSys = (EditText) findViewById(R.id.edInSys);
        edBit = (EditText) findViewById(R.id.edInBit);

        toMain = (Button) findViewById(R.id.toMain);
        toMain.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        S = edSys.getText().toString();
        B = edBit.getText().toString();

        s = Integer.parseInt(S);
        b = Integer.parseInt(B);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("System", s);
        intent.putExtra("Bites", b);
        startActivity(intent);
    }
}
