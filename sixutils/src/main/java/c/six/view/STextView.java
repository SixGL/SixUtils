package c.six.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import c.six.R;

/**
 * 适用于去除Textview的内边距
 * */
public class STextView extends AppCompatTextView {

    private Paint mPaint = getPaint();
    private Rect mBounds = new Rect();
    private Boolean mRemoveFontPadding = false;//是否去除字体内边距，true：去除 false：不去除

    public STextView(Context context) {
        super(context);
    }

    public STextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
    }

    public STextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mRemoveFontPadding) {
            calculateTextParams();
            setMeasuredDimension(mBounds.right - mBounds.left, -mBounds.top + mBounds.bottom);
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    protected void onDraw(Canvas canvas) {
        drawText(canvas);
    }

    /**
     * 初始化属性
     */
    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.STextView);
        mRemoveFontPadding = typedArray.getBoolean(R.styleable.STextView_removeDefaultPadding, false);
        typedArray.recycle();
    }

    /**
     * 计算文本参数
     */
    private String calculateTextParams() {
        String text = getText().toString();
        int textLength = text.length();
        mPaint.getTextBounds(text, 0, textLength, mBounds);
        if (textLength == 0) {
            mBounds.right = mBounds.left;
        }
        return text;
    }

    /**
     * 绘制文本
     */
    private void drawText(Canvas canvas) {
        String text = calculateTextParams();
        int left = mBounds.left;
        int bottom = mBounds.bottom;
        mBounds.offset(-mBounds.left, -mBounds.top);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getCurrentTextColor());
        canvas.drawText(text, (float) (-left), (float) (mBounds.bottom - bottom), mPaint);
    }
}
