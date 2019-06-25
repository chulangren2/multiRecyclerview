package widgt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import java.util.HashMap;
import java.util.List;

import bean.Program;
import recyclerview.chj.com.recyclerview.R;
import util.ScreenDimensionsUtil;
import widgt.TvAdapter.ItemType;

/**
 * Created by HuaJing.Chu on 2019/6/21.
 */

public class TvMultiScrollView extends FrameLayout
{
    private final String TAG = "chuhuajing";
    private final int bottomCardHeight = getResources().getDimensionPixelSize(R.dimen.tv_compact_height);
    private LinearLayoutManager layoutManagerBottom;
    private LinearLayoutManager layoutManagerTop;
    private final int screenPortraitWidth;
    private HashMap<Integer, View> findViewCache;
    private TvAdapter topAdapter;
    private TvAdapter bottomAdapter;

    public TvMultiScrollView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        Resources resources = getResources();
        if (resources.getConfiguration().orientation == 2)
        {
            screenPortraitWidth = ScreenDimensionsUtil.getRealScreenDimensions(context).y;
        }
        else
        {
            screenPortraitWidth = ScreenDimensionsUtil.getRealScreenDimensions(context).x;
        }
    }

    private final int getTopCardHeight()
    {
        return (int) (this.screenPortraitWidth * 0.5625F);
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

    public final void loadData(List<Program> list)
    {
        topAdapter = new TvAdapter(list, ItemType.TOPITEM);
        final RecyclerView topRecyclerView = (RecyclerView) findCachedViewById(R.id.recyclerViewTop);
        topRecyclerView.setAdapter(topAdapter);

        final FlingLimitedRecyclerView bottomRecyclerView = (FlingLimitedRecyclerView) findCachedViewById(R.id.recyclerViewBottom);
        bottomAdapter = new TvAdapter(list,ItemType.BOTTOMITEM);
        bottomRecyclerView.setAdapter(bottomAdapter);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        layoutManagerTop = new LinearLayoutManager(getContext());
        layoutManagerTop.setRecycleChildrenOnDetach(true);
        layoutManagerTop.setItemPrefetchEnabled(true);
        final RecyclerView topRecyclerView = (RecyclerView) findCachedViewById(R.id.recyclerViewTop);
        final FlingLimitedRecyclerView bottomRecyclerView = (FlingLimitedRecyclerView) findCachedViewById(R.id.recyclerViewBottom);
        topRecyclerView.setLayoutManager(layoutManagerTop);
        topRecyclerView.setPreserveFocusAfterLayout(false);
        topRecyclerView.setItemAnimator(null);
        topRecyclerView.setHasFixedSize(true);
        topRecyclerView.getLayoutParams().height = getTopCardHeight();
        topRecyclerView.getLayoutParams().width = screenPortraitWidth;
        topRecyclerView.addOnItemTouchListener(new OnItemTouchListener()
        {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
            {
                return bottomRecyclerView.onInterceptTouchEvent(motionEvent);
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
            {
                bottomRecyclerView.onTouchEvent(motionEvent);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b)
            {

            }
        });

        layoutManagerBottom = new LinearLayoutManager(getContext());
        layoutManagerBottom.setRecycleChildrenOnDetach(true);
        bottomRecyclerView.setLayoutManager(layoutManagerBottom);
        bottomRecyclerView.setItemAnimator(null);
        bottomRecyclerView.setPreserveFocusAfterLayout(false);
        bottomRecyclerView.setHasFixedSize(true);
        new GravitySnapHelper(Gravity.TOP, true).attachToRecyclerView(bottomRecyclerView);
        final boolean[] isFirstProgram = {true};
        bottomRecyclerView.addOnScrollListener(new OnScrollListener()
        {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
            {
                int bottomFirstVisiblePosition = layoutManagerBottom.findFirstVisibleItemPosition();

                ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(bottomFirstVisiblePosition);
                if (holder == null)
                {
                    return;
                }
                View bottomFirstVisibleView = holder.itemView;

                Log.d(TAG,"322 bottomFirstVisibleView bottom ->" + bottomFirstVisibleView.getBottom());
                float f = (float) bottomFirstVisibleView.getBottom() / bottomCardHeight;
                Log.d(TAG,"bottomFirstVisibleView f ->" + f);
                if (isFirstProgram[0] && f < 1)
                {
                    isFirstProgram[0] = false;

                    bottomFirstVisiblePosition += 1;
                    layoutManagerTop.scrollToPosition(bottomFirstVisiblePosition);
                    layoutManagerBottom.scrollToPosition(bottomFirstVisiblePosition);
                    return;
                }
                isFirstProgram[0] = false;

                int bottomViewPosition = bottomFirstVisiblePosition + 1;

                if (bottomViewPosition == bottomAdapter.getItemCount())
                {
                    layoutManagerTop.scrollToPosition(bottomFirstVisiblePosition);
                    return;
                }
                Log.d(TAG,"layoutManagerTop scrollToPositionWithOffset ->" + bottomViewPosition +
                      " offset " +(int) (getTopCardHeight() * f));
                layoutManagerTop.scrollToPositionWithOffset(bottomViewPosition, (int) (getTopCardHeight() * f));

                ViewHolder topViewHolderFirst = topRecyclerView.findViewHolderForAdapterPosition(bottomFirstVisiblePosition);
                if (topViewHolderFirst != null)
                {
                    ((TvCardTopView)(topViewHolderFirst.itemView)).updateViewBasedOnScrollPosition(1-f,false);

                    ViewHolder topViewHolderSecond = topRecyclerView.findViewHolderForAdapterPosition(bottomViewPosition);
                    if (topViewHolderSecond == null)
                    {
                        Log.d("cardtop","top second null");
                        return;
                    }
                    ((TvCardTopView)(topViewHolderSecond.itemView)).updateViewBasedOnScrollPosition(f,true);
                }
            }
        });

        bottomRecyclerView.addItemDecoration(new ItemDecoration()
        {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull State state)
            {
                if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1)
                {
                    outRect.bottom = getMeasuredHeight() - getTopCardHeight() + bottomCardHeight;
                }
            }
        });

        getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener()
        {
            @Override
            public boolean onPreDraw()
            {
                if (getMeasuredHeight() == 0)
                {
                    return true;
                }
                getViewTreeObserver().removeOnPreDrawListener(this);
                ViewGroup.LayoutParams layoutParams = bottomRecyclerView.getLayoutParams();
                int measuredHeight = getMeasuredHeight();
                layoutParams.height = (measuredHeight - topRecyclerView.getLayoutParams().height + bottomCardHeight);
                layoutParams.width = screenPortraitWidth;
                return true;
            }
        });
    }
}
