package widgt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.jetbrains.annotations.NotNull;


/**
 * Created by HuaJing.Chu on 2019/6/23.
 */

public class AspectRatioFrameLayout extends FrameLayout
{

    public AspectRatioFrameLayout(@NotNull Context paramContext, @NotNull AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    private final float getRatio()
    {
        if (getTag() != null) {
            return Float.parseFloat(getTag().toString());
        }
        return 0.0F;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (getRatio() == 0.0F)
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
        if (localLayoutParams != null)
        {
            if ((localLayoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT) && (View.MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY))
            {
                int measureSize = View.MeasureSpec.getSize(heightMeasureSpec);
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.round(measureSize * getRatio()), MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(measureSize, MeasureSpec.EXACTLY));
                return;
            }
            if ((localLayoutParams.width != ViewGroup.LayoutParams.WRAP_CONTENT) && (View.MeasureSpec.getMode(widthMeasureSpec) !=  MeasureSpec.EXACTLY))
            {
                int measureSize = View.MeasureSpec.getSize(widthMeasureSpec);
                int measureSize2 = Math.round(measureSize * getRatio());
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(measureSize, MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(measureSize2, MeasureSpec.EXACTLY));
                return;
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setTag(@Nullable Object paramObject)
    {
        super.setTag(paramObject);
        requestLayout();
    }
}
