package jp.louis.nsr.simplecounter.widgets;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class HoldableButton extends AppCompatButton {
    private int delayMsec = 400;
    private int intervalMsec = 100;

    private MainTask mainTask = new MainTask() {
        @Override
        public void doTask() {}
    };
    private Handler handler = new Handler();
    private Runnable runnableCode;

    public HoldableButton(Context context) {
        this(context, null);
    }
    public HoldableButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public HoldableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initListener();
    }
    private void initListener() {
        setOnClickListener(
            new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainTask.doTask();
                }
            }
        );

        //
        runnableCode = new Runnable() {
            @Override
            public void run() {
                mainTask.doTask();
                handler.postDelayed(runnableCode, intervalMsec);
            }
        };
        setOnTouchListener(
            new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
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
            }
        );
    }

    public void setMainTask(MainTask mainTask) {
        this.mainTask = mainTask;
        initListener();
    }

    abstract public static class MainTask {
        abstract public void doTask();
    }
}
