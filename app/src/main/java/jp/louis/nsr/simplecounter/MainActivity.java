package jp.louis.nsr.simplecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final int defaultValue = 0;

    private final int defaultChangeWidth = 1;
    private int changeWidth = defaultChangeWidth;

    private TextView numberView;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
        initViews();
    }

    private void setViews() {
        numberView = findViewById(R.id.numberView);
        numberPicker = findViewById(R.id.numberPicker);
    }

    private void initViews() {
        //
        numberView.setText(String.valueOf(defaultValue));

        //
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(20);
        numberPicker.setValue(defaultChangeWidth);

        numberPicker.setOnValueChangedListener(
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    changeWidth = newVal;
                }
            }
        );

        //
        Button plusButton = findViewById(R.id.plusButton);
        plusButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberView.setText(
                        String.valueOf(Integer.valueOf(numberView.getText().toString()) + changeWidth)
                    );
                }
            }
        );

        Button minusButton = findViewById(R.id.minusButton);
        minusButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberView.setText(
                        String.valueOf(Integer.valueOf(numberView.getText().toString()) - changeWidth)
                    );
                }
            }
        );

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberPicker.setValue(defaultChangeWidth);
                    changeWidth = defaultChangeWidth;
                    numberView.setText(String.valueOf(defaultValue));
                }
            }
        );
    }
}
