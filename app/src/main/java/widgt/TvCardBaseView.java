package widgt;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.engine.Resource;

import org.jetbrains.annotations.NotNull;

import bean.Program;

/**
 * Created by HuaJing.Chu on 2019/6/23.
 */

public class TvCardBaseView
{


    public static abstract interface ICardView
    {
        public abstract void displayImage(@NotNull Program program);

        public abstract void displaySubtitle(@Nullable String subTitle);

        public abstract void displayTitle(@Nullable String title);

        public abstract void hidePreview();

        public abstract void showPreview(@NotNull Program program);

        public abstract void updateViewBasedOnScrollPosition(float paramFloat, boolean paramBoolean);
    }
}
