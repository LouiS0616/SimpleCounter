package jp.louis.nsr.simplecounter.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import java.text.DecimalFormat;

import jp.louis.nsr.simplecounter.R;


public class NumberView extends AppCompatTextView {
    private double defaultValue;
    private double nowValue;

    private DecimalFormat decimalFormat;

    public NumberView(Context context) {
        this(context, null);
    }
    public NumberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public NumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(
            context.obtainStyledAttributes(
                attrs, R.styleable.NumberView, defStyleAttr, 0
            )
        );

        resetValue();
    }
    private void initAttr(TypedArray typedArray) {
        defaultValue = typedArray.getFloat(
            R.styleable.NumberView_default_value, .0f
        );

        //
        if(typedArray.hasValue(R.styleable.NumberView_display_mode)) {
            int mode = typedArray.getInt(
                R.styleable.NumberView_display_mode, -1
            );
            switch(mode) {
            case 0:
                // integer_mode
                decimalFormat = new DecimalFormat("#");
                break;
            case 1:
                // float_mode
                decimalFormat = new DecimalFormat("#.00");
                break;
            case 2:
                // percent_mode
                decimalFormat = new DecimalFormat("#.#\'%\'");
                break;
            }
        }
        else {
            decimalFormat = new DecimalFormat(
                typedArray.getString(
                    R.styleable.NumberView_display_format
                )
            );
        }

        typedArray.recycle();
    }
    public void resetValue() {
        setValue(defaultValue);
    }

    public void setValue(double value) {
        nowValue = value;
        setText(
            decimalFormat.format(value)
        );
    }
    private double getValue() {
        return nowValue;
    }
    public void addValue(double diff) {
        setValue(
            getValue() + diff
        );
    }
}
