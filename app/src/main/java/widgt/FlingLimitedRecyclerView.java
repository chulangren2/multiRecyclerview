package widgt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;

import recyclerview.chj.com.recyclerview.R;

/**
 * Created by HuaJing.Chu on 2019/6/21.
 */

public class FlingLimitedRecyclerView extends RecyclerView
{
    private final float flingReduction;
    private HashMap<Integer, View> findViewCache;

    public FlingLimitedRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlingLimitedRecyclerView, 0, 0);
        this.flingReduction = typedArray.getFloat(R.styleable.FlingLimitedRecyclerView_flingReduction, 0.0F);
        typedArray.recycle();
    }

    public void clearFindViewByIdCache()
    {
        if (findViewCache != null)
        {
            findViewCache.clear();
        }
    }

    @SuppressLint("UseSparseArrays")
    public View findCachedViewById(int viewId)
    {
        if (findViewCache == null)
        {
            findViewCache = new HashMap<>();
        }

        View view = findViewCache.get(viewId);
        if (view == null)
        {
            view = findViewById(viewId);
            if (view != null)
            {
                findViewCache.put(viewId, view);
            }
        }

        return view;
    }

    @Override
    public boolean fling(int velocityX, int velocityY)
    {
        return super.fling(velocityX, (int) (velocityY * flingReduction));
    }
}
