package widgt;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import recyclerview.chj.com.recyclerview.R;

/**
 * Created by HuaJing.Chu on 2019/6/24.
 */

public class NLFixedRatioLayout extends FrameLayout
{
    private static final float DEFAULT_WIDTH_RATIO = 16F;

    private static final float DEFAULT_HEIGHT_RATIO = 9F;

    private float mWidthRatio;

    private float mHeightRatio;

    public NLFixedRatioLayout(Context context)
    {
        super(context);

        initialize(context, null);
    }

    public NLFixedRatioLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs)
    {
        mWidthRatio = DEFAULT_WIDTH_RATIO;

        mHeightRatio = DEFAULT_HEIGHT_RATIO;

        initStyleable(context, attrs);
    }

    private void initStyleable(Context context, AttributeSet attrs)
    {
        if (attrs == null)
        {
            return;
        }

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NLFixedRatioLayout, 0, 0);

        if (a.hasValue(R.styleable.NLFixedRatioLayout_ratio))
        {
            setRatio(a.getString(R.styleable.NLFixedRatioLayout_ratio));
        }

        a.recycle();
    }

    public void setRatio(String ratio) throws IllegalArgumentException
    {
        if (ratio == null || (ratio = ratio.trim()).length() == 0)
        {
            setRatio(0F, 0F);

            return;
        }

        final String[] array = ratio.split(":");

        if (array.length != 2)
        {
            throw new IllegalArgumentException("Illegal ratio string: " + ratio);
        }

        try
        {
            setRatio(Float.parseFloat(array[0].trim()), Float.parseFloat(array[1].trim()));
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Illegal ratio string: " + ratio, e);
        }
    }

    public void setRatio(float widthRatio, float heightRatio)
    {
        widthRatio = Math.max(widthRatio, 0);

        heightRatio = Math.max(heightRatio, 0);

        if (mWidthRatio != widthRatio || mHeightRatio != heightRatio)
        {
            if (isFixingEnabled(widthRatio, heightRatio) || isFixingEnabled(mWidthRatio, mHeightRatio))
            {
                mWidthRatio = widthRatio;

                mHeightRatio = heightRatio;

                requestLayout();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        final float widthRatio = mWidthRatio;

        final float heightRatio = mHeightRatio;

        if (isFixingEnabled(widthRatio, heightRatio))
        {
            final int widthMode = MeasureSpec.getMode(widthMeasureSpec);

            final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

            if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY)
            {
                int height = (int) (MeasureSpec.getSize(widthMeasureSpec) * heightRatio / widthRatio);

                if (heightMode == MeasureSpec.AT_MOST)
                {
                    height = Math.min(height, MeasureSpec.getSize(heightMeasureSpec));
                }

                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            }
            else if (heightMode == MeasureSpec.EXACTLY && widthMode != MeasureSpec.EXACTLY)
            {
                int width = (int) (MeasureSpec.getSize(heightMeasureSpec) * widthRatio / heightRatio);

                if (widthMode == MeasureSpec.AT_MOST)
                {
                    width = Math.min(width, MeasureSpec.getSize(widthMeasureSpec));
                }

                widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private static final boolean isFixingEnabled(float width, float height)
    {
        return width > 0 && height > 0;
    }

}
