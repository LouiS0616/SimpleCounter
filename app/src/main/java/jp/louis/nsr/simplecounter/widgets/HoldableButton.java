package jp.louis.nsr.simplecounter.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class HoldableButton extends AppCompatButton {
    public HoldableButton(Context context) {
        this(context, null);
    }
    public HoldableButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public HoldableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
