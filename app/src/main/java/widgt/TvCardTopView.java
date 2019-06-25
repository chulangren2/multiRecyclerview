package widgt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import bean.Program;
import recyclerview.chj.com.recyclerview.R;

/**
 * Created by HuaJing.Chu on 2019/6/23.
 */

public class TvCardTopView extends LinearLayout implements TvCardBaseView.ICardView
{
    private final int baseTextPadding = getResources().getDimensionPixelSize(R.dimen.tv_compact_text_padding_bottom);
    private final int bottomCardHeight = getResources().getDimensionPixelSize(R.dimen.tv_compact_height);
    private final int heightOfBottomCardTextContainer = this.bottomCardHeight - this.baseTextPadding * 2;
    private final float parallaxRatio = 0.1F;

    public TvCardTopView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public void displayImage(@NotNull Program program)
    {
        ImageView imageView = findViewById(R.id.topImage);
        if (imageView != null)
        {
            Glide.with(getContext()).load(program.getsImg()).centerCrop().into(imageView);
            //            imageView.setBackgroundColor(Color.parseColor(program.getColorStr()));

        }
    }

    @Override
    public void displaySubtitle(@Nullable String subTitle)
    {
        TextView textView = findViewById(R.id.subtitle);
        if (textView != null)
        {
            textView.setText(subTitle);
        }
    }

    @Override
    public void displayTitle(@Nullable String title)
    {
        TextView textView = findViewById(R.id.title);
        if (textView != null)
        {
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
        int i = 1;
        if (paramBoolean)
        {
            int j = (int) ((1 - paramFloat) * this.bottomCardHeight - this.baseTextPadding);
            int k = (int) (getHeight() * paramFloat);
            int m = this.heightOfBottomCardTextContainer;
            LinearLayout localLinearLayout = findViewById(R.id.textContainer);
            localLinearLayout.setPadding(localLinearLayout.getPaddingLeft(), 0, 0, k + j - m);
            TextView textView = localLinearLayout.findViewById(R.id.subtitle);
            Log.d("cardtop", "CardTop padding bottom + " + textView.getText() + "--" + (k + j - m));
        }
        if (paramBoolean)
        {
            i = -1;
        }
        ImageView imageView = findViewById(R.id.topImage);
        imageView.setY(i * getHeight() * this.parallaxRatio * paramFloat);
    }

    public void setTextContainerViewPadding()
    {
        View view = findViewById(R.id.textContainer);
        if(view != null){
            view.setPadding(view.getPaddingLeft(),0,0,baseTextPadding);
        }
    }
}
