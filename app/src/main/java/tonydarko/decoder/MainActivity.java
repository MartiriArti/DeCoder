package tonydarko.decoder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button toBin, toString, vocabulary, clear, clear2;
    EditText edbin, edstring;
    TextView tvInBin, tvInStr;
    int S,B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        S = intent.getIntExtra("System",0);
        B = intent.getIntExtra("Bites",0);

        System.out.println("Main: " + S + B);

        final CodesGen cG = new CodesGen(S,B);

        vocabulary = (Button) findViewById(R.id.vocabulary);
        toBin = (Button) findViewById(R.id.btnToBin);
        toString = (Button) findViewById(R.id.btnToString);
        clear = (Button) findViewById(R.id.clear);
        clear2 = (Button) findViewById(R.id.clear2);

        tvInBin = (TextView) findViewById(R.id.tvibbin);
        tvInStr = (TextView) findViewById(R.id.tvinstr);

        edbin = (EditText) findViewById(R.id.ed_to_bin);
        edstring = (EditText) findViewById(R.id.ed_to_string);

        vocabulary.setOnClickListener(this);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvInBin.setText("Закодировать");
                edbin.setText("");
            }
        });

        clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvInStr.setText("Раскодировать");
                edstring.setText("");
            }
        });

        toBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edbin.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Пустое поле", Toast.LENGTH_SHORT).show();
                } else {
                    String res = "";
                    String s = edbin.getText().toString();

                    for (int i = 0; i < s.length(); i++) {
                        for (Map.Entry<Character, String> entry : cG.hm.entrySet()) {
                            Character key = entry.getKey();
                            if (key == s.charAt(i)) {
                                res += cG.hm.get(key);
                            }
                        }
                    }
                    Toast.makeText(getApplicationContext(), "В биты ", Toast.LENGTH_SHORT).show();
                    tvInBin.setText(res);
                }
            }
        });


        toString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edstring.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Пустое поле 2", Toast.LENGTH_SHORT).show();
                } else if (edstring.length() % cG.bites != 0) {
                    Toast.makeText(getApplicationContext(), "Не кратно 3м", Toast.LENGTH_SHORT).show();
                } else {
                    String anres = "";
                    String s = edstring.getText().toString();
                    char[] ch = s.toCharArray();
                    int l = ch.length / cG.bites;
                    String[] sub = new String[l];
                    String ss = "";
                    int b = 0;
                    for (int i = 0; i < ch.length; i++) {
                        ss += ch[i];
                        if (ss.length() == cG.bites) {
                            sub[b] = ss;
                            b++;
                            ss = "";
                        }
                    }

                    for (int y = 0; y < sub.length; y++) {
                        for (Map.Entry entry : cG.hm.entrySet()) {
                            if (entry.getValue().equals(sub[y])) anres += entry.getKey();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "В строку " + anres, Toast.LENGTH_SHORT).show();
                    tvInStr.setText(anres);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, VocActivity.class);
        intent.putExtra("S",S);
        intent.putExtra("B",B);
        startActivity(intent);
    }
}

