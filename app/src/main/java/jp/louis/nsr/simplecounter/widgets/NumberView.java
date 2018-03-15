package jp.louis.nsr.simplecounter.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class NumberView extends AppCompatTextView {
    @SuppressWarnings("FieldCanBeLocal")
    private final int defaultNumber = 0;

    public NumberView(Context context) {
        this(context, null);
    }
    public NumberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public NumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        reset();
    }
    public void reset() {
        setNumber(defaultNumber);
    }

    public void setNumber(int number) {
        setText(
            String.valueOf(number)
        );
    }
    private int getNumber() {
        return Integer.valueOf(
            getText().toString()
        );
    }
    public void addNumber(int diff) {
        setNumber(
            getNumber() + diff
        );
    }
}
