package widgt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bean.Program;
import recyclerview.chj.com.recyclerview.R;
import widgt.TvAdapter.TvViewHolder;

/**
 * Created by HuaJing.Chu on 2019/6/22.
 */

public class TvAdapter extends RecyclerView.Adapter<TvViewHolder>
{
    private List<Program> list;
    private ItemType itemType;
    public TvAdapter(List<Program> list,ItemType itemType)
    {
        this.list = list;
        this.itemType = itemType;
    }

    public List<Program> getList()
    {
        return list;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position)
    {
        if(itemType == ItemType.TOPITEM){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_tv_top,viewGroup,false);
            Log.d("CardTop", "onCreateViewHolder");
            return new TvViewHolder(view);
        }else {
            View bottomView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_tv_bottom,viewGroup,false);
            return new TvViewHolder(bottomView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder viewHolder, int position)
    {
        Program program = list.get(position);
        if(itemType == ItemType.TOPITEM){
            Log.d("CardTop", "onCreateViewHolder");

            TvCardTopView view = (TvCardTopView) viewHolder.itemView;
            view.setTextContainerViewPadding();
            view.displayImage(program);
            view.displaySubtitle(program.getDes());
            view.displayTitle(program.getTitle());
        }else {
            TvCardBottomView view = (TvCardBottomView) viewHolder.itemView;
            view.displayImage(program);
            view.displaySubtitle(program.getDes());
            view.displayTitle(program.getTitle());
        }
    }

    @Override
    public int getItemCount()
    {
        return this.list.size();
    }

    static class TvViewHolder extends ViewHolder
    {

        public TvViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }

    public enum ItemType
    {
        TOPITEM,BOTTOMITEM
    }
}
