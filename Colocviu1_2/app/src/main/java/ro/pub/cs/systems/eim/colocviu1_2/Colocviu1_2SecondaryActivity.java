    package ro.pub.cs.systems.eim.colocviu1_2;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    public class Colocviu1_2SecondaryActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Log.d("SAVED_SUM", "am intrat in a doua activitate");

            Intent intent = getIntent();
            if (intent != null) {
                if (intent.hasExtra("ALL_TERMS")) {
                    String sumValue = intent.getStringExtra("ALL_TERMS");
                    int result = computeSum(sumValue);
//                    Log.d("DATA", sumValue);
//                    Log.d("DATA", String.valueOf(result));

                    Intent resultIntent = new Intent(this, Colocviu1_2MainActivity.class);
                    resultIntent.putExtra("SUM_VALUE", result);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        }

        private int computeSum(String sumValue) {
            if (sumValue == null) {
                return -1;
            }
            String[] numbers = sumValue.split("\\+");
            int sum = 0;

            for (String num : numbers) {
                sum += Integer.parseInt(num.trim());
            }

            return sum;
        }
    }