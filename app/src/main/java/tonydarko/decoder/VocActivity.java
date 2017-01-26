package tonydarko.decoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

public class VocActivity extends Activity {

    int S,B;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voc);

        Intent intent = getIntent();

        S = intent.getIntExtra("S",0);
        B = intent.getIntExtra("B",0);

        System.out.println("Voc :" + S + B);
        if (S == 0){
            System.out.println("Proverka--------------------");
            S = new MainActivity().S;
            B = new MainActivity().B;
        }

        final CodesGen cG = new CodesGen(S,B);

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater ltInflater = getLayoutInflater();

        for (Map.Entry<Character, String> e : cG.hm.entrySet()) {
            View item = ltInflater.inflate(R.layout.item, linLayout, false);
            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            tvName.setText(" " + e.getKey());
            TextView tvPosition = (TextView) item.findViewById(R.id.tvPosition);
            tvPosition.setText(": " + e.getValue());

            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            linLayout.addView(item);
        }

    }
}