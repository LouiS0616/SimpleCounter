package jp.louis.nsr.simplecounter.widgets.numberview;

import android.content.Context;
import android.util.AttributeSet;

public class IntegerView extends NumberView {
    public IntegerView(Context context) {
        this(context, null);
    }
    public IntegerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public IntegerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
