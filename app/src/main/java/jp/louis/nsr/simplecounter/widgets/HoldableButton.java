package jp.louis.nsr.simplecounter.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import jp.louis.nsr.simplecounter.R;

public class HoldableButton extends AppCompatButton {
    private int delayMsec;
    private int intervalMsec;

    private MainTask mainTask;
    private final Handler handler = new Handler();
    private Runnable runnableCode;

    //
    //
    public HoldableButton(Context context) {
        this(context, null);
    }
    public HoldableButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public HoldableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(
            context.obtainStyledAttributes(
                attrs, R.styleable.HoldableButton, defStyleAttr, 0
            )
        );
        setMainTask(() -> {});
    }
    private void initAttrs(TypedArray typedArray) {
        delayMsec = typedArray.getInteger(
            R.styleable.HoldableButton_delay_msec, 400
        );
        intervalMsec = typedArray.getInteger(
            R.styleable.HoldableButton_interval_msec, 100
        );
        typedArray.recycle();
    }
    private void initListener() {
        setOnClickListener(view -> mainTask.doTask());

        //
        runnableCode = () -> {
            mainTask.doTask();
            handler.postDelayed(runnableCode, intervalMsec);
        };
        setOnTouchListener(
            (view, motionEvent) -> {
                switch(motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    handler.postDelayed(runnableCode, delayMsec);
                    break;

                case MotionEvent.ACTION_UP:
                    handler.removeCallbacks(runnableCode);
                    view.performClick();
                    break;

                default:
                    break;
                }

                return true;
            }
        );
    }

    //
    public void setMainTask(MainTask mainTask) {
        this.mainTask = mainTask;
        initListener();
    }

    //
    //
    @FunctionalInterface
    public interface MainTask {
        void doTask();
    }
}
