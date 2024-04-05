package ro.pub.cs.systems.eim.colocviu1_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Colocviu1_2MainActivity extends AppCompatActivity {
    Button addButton, computeButton;
    EditText nextTermEditText, allTermsEditText;
    private ActivityResultLauncher<Intent> startActivityForResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_2_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addButton = findViewById(R.id.addButton);
        computeButton = findViewById(R.id.computeButton);
        nextTermEditText = findViewById(R.id.nextTermEditText);
        allTermsEditText = findViewById(R.id.allTermsEditText);

        startActivityForResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                if (result.getData() != null) {
                    int sum;

                    if (intent.hasExtra("SUM_VALUE")) {
                        sum = intent.getIntExtra("SUM_VALUE", 0);
//                        Log.d("DATA", String.valueOf(sum));
                        Toast.makeText(getApplication(), String.valueOf(sum), Toast.LENGTH_SHORT).show();
                    }
                }
//                Toast.makeText(getApplication(), "OK", Toast.LENGTH_SHORT).show();
            } else if (result.getResultCode() == RESULT_CANCELED){
                Toast.makeText(getApplication(), "CANCEL", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        addButton.setOnClickListener(view -> {
            int number = Integer.parseInt(nextTermEditText.getText().toString());
            String currTerms = allTermsEditText.getText().toString();

            if (currTerms.isEmpty()) {
                allTermsEditText.setText(String.valueOf(number));
            } else {
                allTermsEditText.setText(allTermsEditText.getText().toString() + " + " + String.valueOf(number));
            }
        });

        computeButton.setOnClickListener(view -> {
            String allTerms = allTermsEditText.getText().toString();

            Intent intent = new Intent("ro.pub.cs.systems.eim.colocviu1_2.intent.action.Colocviu1_2SecondaryActivity");
            intent.putExtra("ALL_TERMS", allTerms);
            startActivityForResultLauncher.launch(intent);
        });

    }
}