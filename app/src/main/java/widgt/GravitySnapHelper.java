package widgt;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by HuaJing.Chu on 2019/6/21.
 */

public class GravitySnapHelper extends LinearSnapHelper
{
    private final GravityDelegate delegate;

    private GravitySnapHelper(int paramInt)
    {
        this(paramInt, false, null);
    }

    public GravitySnapHelper(int paramInt, boolean paramBoolean)
    {
        this(paramInt, paramBoolean, null);
    }

    private GravitySnapHelper(int paramInt, boolean paramBoolean, @Nullable SnapListener paramSnapListener)
    {
        this.delegate = new GravityDelegate(paramInt, paramBoolean, paramSnapListener);
    }

    public void attachToRecyclerView(@Nullable RecyclerView paramRecyclerView)
            throws IllegalStateException
    {
        this.delegate.attachToRecyclerView(paramRecyclerView);
        super.attachToRecyclerView(paramRecyclerView);
    }

    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager paramLayoutManager, @NonNull View paramView)
    {
        return this.delegate.calculateDistanceToFinalSnap(paramLayoutManager, paramView);
    }

    public void enableLastItemSnap(boolean paramBoolean)
    {
        this.delegate.enableLastItemSnap(paramBoolean);
    }

    public View findSnapView(RecyclerView.LayoutManager paramLayoutManager)
    {
        return this.delegate.findSnapView(paramLayoutManager);
    }

    public void scrollToPosition(int paramInt)
    {
        this.delegate.scrollToPosition(paramInt);
    }

    public void smoothScrollToPosition(int paramInt)
    {
        this.delegate.smoothScrollToPosition(paramInt);
    }

    public static abstract interface SnapListener
    {
        public abstract void onSnap(int paramInt);
    }
}
