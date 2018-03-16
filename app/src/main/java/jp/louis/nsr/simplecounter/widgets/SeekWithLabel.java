package jp.louis.nsr.simplecounter.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.TextView;

import jp.louis.nsr.simplecounter.R;


public class SeekWithLabel extends ConstraintLayout {
    public SeekWithLabel(Context context) {
        this(context, null);
    }
    public SeekWithLabel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public SeekWithLabel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(
            R.layout.seek_with_label, this, true
        );
        initAttrs(
            context.obtainStyledAttributes(
                attrs, R.styleable.SeekWithLabel, defStyleAttr, 0
            )
        );
        initViews();
    }
    private void initAttrs(TypedArray typedArray) {
        TextView label = findViewById(R.id.label);

        //
        label.setText(
            typedArray.getString(R.styleable.SeekWithLabel_label)
        );
    }
    private void initViews() {
        final NumberView value = findViewById(R.id.value);
        value.setValue(0);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
