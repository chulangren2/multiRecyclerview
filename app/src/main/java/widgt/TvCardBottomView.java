package widgt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import bean.Program;
import recyclerview.chj.com.recyclerview.R;

/**
 * Created by HuaJing.Chu on 2019/6/23.
 */

public class TvCardBottomView extends FrameLayout implements TvCardBaseView.ICardView
{
    public TvCardBottomView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public void displayImage(@NotNull Program program)
    {
        ImageView imageView = findViewById(R.id.bottomImage);
        if(imageView != null){
            Glide.with(getContext()).load(program.getbImg()).centerCrop().into(imageView);
//            imageView.setBackgroundColor(Color.parseColor(program.getsColorStr()));
        }

    }

    @Override
    public void displaySubtitle(@Nullable String subTitle)
    {
        TextView textView = findViewById(R.id.subtitle);
        if(textView != null){
            textView.setText(subTitle);
        }
    }

    @Override
    public void displayTitle(@Nullable String title)
    {
        TextView textView = findViewById(R.id.title);
        if(textView != null){
            textView.setText(title);
        }
    }

    @Override
    public void hidePreview()
    {

    }

    @Override
    public void showPreview(@NotNull Program program)
    {

    }

    @Override
    public void updateViewBasedOnScrollPosition(float paramFloat, boolean paramBoolean)
    {

    }
}
