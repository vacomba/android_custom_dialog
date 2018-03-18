package vacomba.com.customdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        new DialogCustom.Builder(MainActivity.this)
                .setTitle(R.string.title)
                .setMessage(R.string.message)
                .setPositiveButton("POSITIVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "POSSSSSS", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("NEGATIVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "IIIISDSSD", Toast.LENGTH_LONG).show();
                    }
                })
                .setIcon(R.drawable.lemon)
                .show();
    }
}

