package widgt;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;

import java.util.Locale;

/**
 * Created by HuaJing.Chu on 2019/6/21.
 */

public class GravityDelegate
{

    private int gravity;
    private OrientationHelper horizontalHelper;
    private boolean isRtl;
    private int lastSnappedPosition;
    private GravitySnapHelper.SnapListener listener;
    private RecyclerView recyclerView;
    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener()
    {
        public void onScrollStateChanged(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt)
        {
            super.onScrollStateChanged(paramAnonymousRecyclerView, paramAnonymousInt);
            if ((paramAnonymousInt == 0) && (GravityDelegate.this.snapping) && (GravityDelegate.this.listener != null))
            {
                if (GravityDelegate.this.lastSnappedPosition != -1)
                {
                    GravityDelegate.this.listener.onSnap(GravityDelegate.this.lastSnappedPosition);
                }
                //TODO do something
                //                GravityDelegate.access$002(GravityDelegate.this, false);
//                GravityDelegate.this.enableLastItemSnap(false);
            }
        }
    };
    private boolean snapLastItem;
    private boolean snapping;
    private OrientationHelper verticalHelper;

    public GravityDelegate(int paramInt, boolean paramBoolean, @Nullable GravitySnapHelper.SnapListener paramSnapListener)
    {
        if ((paramInt != Gravity.START) && (paramInt != Gravity.END) && (paramInt != Gravity.BOTTOM) && (paramInt != Gravity.TOP))
        {
            throw new IllegalArgumentException("Invalid gravity value. Use START | END | BOTTOM | TOP constants");
        }
        this.snapLastItem = paramBoolean;
        this.gravity = paramInt;
        this.listener = paramSnapListener;
    }

    private int distanceToEnd(View paramView, LinearLayoutManager paramLinearLayoutManager, @NonNull OrientationHelper paramOrientationHelper)
    {
        int i = this.recyclerView.getChildLayoutPosition(paramView);
        int j;
        if (((i == 0) && ((this.isRtl) || (paramLinearLayoutManager.getReverseLayout()))) || ((i == paramLinearLayoutManager.getItemCount() - 1) && ((!this.isRtl) || (paramLinearLayoutManager.getReverseLayout())) && (!this.recyclerView.getClipToPadding())))
        {
            i = paramOrientationHelper.getDecoratedEnd(paramView);
            if (i >= paramOrientationHelper.getEnd() - (paramOrientationHelper.getEnd() - paramOrientationHelper.getEndAfterPadding()) / 2)
            {
                i = paramOrientationHelper.getDecoratedEnd(paramView);
                j = paramOrientationHelper.getEnd();
            }
            else
            {
                return i - paramOrientationHelper.getEndAfterPadding();
            }
        }
        else
        {
            i = paramOrientationHelper.getDecoratedEnd(paramView);
            j = paramOrientationHelper.getEnd();
        }
        return i - j;
    }

    private int distanceToStart(View paramView, LinearLayoutManager paramLinearLayoutManager, @NonNull OrientationHelper paramOrientationHelper)
    {
        int i = this.recyclerView.getChildLayoutPosition(paramView);
        if (((i == 0) && ((!this.isRtl) || (paramLinearLayoutManager.getReverseLayout()))) || ((i == paramLinearLayoutManager.getItemCount() - 1) && ((this.isRtl) || (paramLinearLayoutManager.getReverseLayout())) && (!this.recyclerView.getClipToPadding())))
        {
            int j = paramOrientationHelper.getDecoratedStart(paramView);
            i = j;
            if (j >= paramOrientationHelper.getStartAfterPadding() / 2)
            {
                return j - paramOrientationHelper.getStartAfterPadding();
            }
        }
        else
        {
            i = paramOrientationHelper.getDecoratedStart(paramView);
        }
        return i;
    }

    @Nullable
    private View findEdgeView(LinearLayoutManager paramLinearLayoutManager, OrientationHelper paramOrientationHelper, boolean paramBoolean)
    {
        int i = paramLinearLayoutManager.getChildCount();
        Object localObject = null;
        if (i == 0)
        {
            return null;
        }
        if ((isAtEndOfList(paramLinearLayoutManager)) && (!this.snapLastItem))
        {
            return null;
        }
        int k = Integer.MAX_VALUE;
        int j = 0;
        while (j < paramLinearLayoutManager.getChildCount())
        {
            View localView = paramLinearLayoutManager.getChildAt(j);
            if (((paramBoolean) && (!this.isRtl)) || ((!paramBoolean) && (this.isRtl)))
            {
                i = Math.abs(paramOrientationHelper.getDecoratedStart(localView));
            }
            else
            {
                i = Math.abs(paramOrientationHelper.getDecoratedEnd(localView) - paramOrientationHelper.getEnd());
            }
            int m = k;
            if (i < k)
            {
                localObject = localView;
                m = i;
            }
            j += 1;
            k = m;
        }
        return (View) localObject;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager paramLayoutManager)
    {
        if (this.horizontalHelper == null)
        {
            this.horizontalHelper = OrientationHelper.createHorizontalHelper(paramLayoutManager);
        }
        return this.horizontalHelper;
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager paramLayoutManager)
    {
        if (this.verticalHelper == null)
        {
            this.verticalHelper = OrientationHelper.createVerticalHelper(paramLayoutManager);
        }
        return this.verticalHelper;
    }

    private boolean isAtEndOfList(LinearLayoutManager paramLinearLayoutManager)
    {
        boolean isReverse = paramLinearLayoutManager.getReverseLayout();
        if (((!isReverse) && (this.gravity == Gravity.START)) || ((paramLinearLayoutManager.getReverseLayout()) && (this.gravity == Gravity.END)))
        {
            return paramLinearLayoutManager.findLastCompletelyVisibleItemPosition() == paramLinearLayoutManager.getItemCount() - 1;
        }
        return paramLinearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
    }

    private void scrollTo(int paramInt, boolean paramBoolean)
    {
        if (this.recyclerView.getLayoutManager() != null)
        {
            ViewHolder holder = this.recyclerView.findViewHolderForAdapterPosition(paramInt);
            if (holder != null)
            {
                int[] position = calculateDistanceToFinalSnap(this.recyclerView.getLayoutManager(), holder.itemView);
                if (paramBoolean)
                {
                    this.recyclerView.smoothScrollBy(position[0], position[1]);
                    return;
                }
                this.recyclerView.scrollBy(position[0], position[1]);
                return;
            }
            if (paramBoolean)
            {
                this.recyclerView.smoothScrollToPosition(paramInt);
                return;
            }
            this.recyclerView.scrollToPosition(paramInt);
        }
    }

    public void attachToRecyclerView(@Nullable RecyclerView paramRecyclerView)
    {
        if (paramRecyclerView != null)
        {
            paramRecyclerView.setOnFlingListener(null);
            int i = this.gravity;
            if ((i == Gravity.START) || (i == Gravity.END))
            {
                i = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault());
                boolean bool = true;
                if (i != 1)
                {
                    bool = false;
                }
                this.isRtl = bool;
            }
            if (this.listener != null)
            {
                paramRecyclerView.addOnScrollListener(this.scrollListener);
            }
            this.recyclerView = paramRecyclerView;
        }
    }

    @NonNull
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager paramLayoutManager, @NonNull View paramView)
    {
        int[] arrayOfInt = new int[2];
        if (!(paramLayoutManager instanceof LinearLayoutManager))
        {
            return arrayOfInt;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) paramLayoutManager;
        if (paramLayoutManager.canScrollHorizontally())
        {
            if (((this.isRtl) && (this.gravity == Gravity.END)) || ((!this.isRtl) && (this.gravity == Gravity.START)))
            {
                arrayOfInt[0] = distanceToStart(paramView, layoutManager, getHorizontalHelper(layoutManager));
            }
            else
            {
                arrayOfInt[0] = distanceToEnd(paramView, layoutManager, getHorizontalHelper(layoutManager));
            }
        }
        else
        {
            arrayOfInt[0] = 0;
        }
        if (paramLayoutManager.canScrollVertically())
        {
            if (this.gravity == Gravity.END)
            {
                arrayOfInt[1] = distanceToStart(paramView, layoutManager, getVerticalHelper(layoutManager));
                return arrayOfInt;
            }
            arrayOfInt[1] = distanceToEnd(paramView, layoutManager, getVerticalHelper(layoutManager));
            return arrayOfInt;
        }
        arrayOfInt[1] = 0;
        return arrayOfInt;
    }

    public void enableLastItemSnap(boolean paramBoolean)
    {
        this.snapLastItem = paramBoolean;
    }

    @Nullable
    public View findSnapView(RecyclerView.LayoutManager paramLayoutManager)
    {
        if (!(paramLayoutManager instanceof LinearLayoutManager))
        {
            return null;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) paramLayoutManager;
        View view;
        int i = this.gravity;
        if (i != Gravity.TOP)
        {
            if (i != Gravity.BOTTOM)
            {
                if (i != Gravity.START)
                {
                    if (i != Gravity.END)
                    {
                        view = null;
                    }
                    else
                    {
                        view = findEdgeView(layoutManager, getHorizontalHelper(paramLayoutManager), false);
                    }
                }
                else
                {
                    view = findEdgeView(layoutManager, getHorizontalHelper(paramLayoutManager), true);
                }
            }
            else
            {
                view = findEdgeView(layoutManager, getVerticalHelper(paramLayoutManager), false);
            }
        }
        else
        {
            view = findEdgeView(layoutManager, getVerticalHelper(paramLayoutManager), true);
        }

        this.snapping = (view != null);

        if (view != null)
        {
            this.lastSnappedPosition = this.recyclerView.getChildAdapterPosition(view);
        }
        return view;
    }

    public void scrollToPosition(int paramInt)
    {
        scrollTo(paramInt, false);
    }

    public void smoothScrollToPosition(int paramInt)
    {
        scrollTo(paramInt, true);
    }
}
