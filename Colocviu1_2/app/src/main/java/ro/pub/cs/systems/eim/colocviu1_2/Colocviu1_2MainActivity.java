package ro.pub.cs.systems.eim.colocviu1_2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Colocviu1_2MainActivity extends AppCompatActivity {
    Button addButton, computeButton;
    EditText nextTermEditText, allTermsEditText;

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

        addButton.setOnClickListener(view -> {
            int number = Integer.parseInt(nextTermEditText.getText().toString());
            String currTerms = allTermsEditText.getText().toString();

            if (currTerms.isEmpty()) {
                allTermsEditText.setText(String.valueOf(number));
            } else {
                allTermsEditText.setText(allTermsEditText.getText().toString() + " + " + String.valueOf(number));
            }
        });

    }
}