package jp.louis.nsr.simplecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;

import jp.louis.nsr.simplecounter.widgets.HoldableButton;
import jp.louis.nsr.simplecounter.widgets.NumberView;

public class MainActivity extends AppCompatActivity {
    private final int defaultChangeWidth = 1;
    private int changeWidth = defaultChangeWidth;

    private NumberView numberView;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        numberView = findViewById(R.id.numberView);
        numberPicker = findViewById(R.id.numberPicker);

        //
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(20);
        numberPicker.setValue(defaultChangeWidth);

        numberPicker.setOnValueChangedListener(
            (picker, oldVal, newVal) -> changeWidth = newVal
        );

        //
        HoldableButton plusButton = findViewById(R.id.plusButton);
        plusButton.setMainTask(
            () -> numberView.addValue(changeWidth)
        );

        HoldableButton minusButton = findViewById(R.id.minusButton);
        minusButton.setMainTask(
            () -> numberView.addValue(-changeWidth)
        );

        //
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(
            view -> numberView.resetValue()
         );
        resetButton.setOnLongClickListener(
            view -> {
                numberPicker.setValue(defaultChangeWidth);
                changeWidth = defaultChangeWidth;
                numberView.resetValue();
                return true;
            }
        );
    }
}
