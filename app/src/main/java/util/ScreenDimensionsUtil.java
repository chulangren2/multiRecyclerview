package util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by HuaJing.Chu on 2019/6/21.
 */

public class ScreenDimensionsUtil
{
    public static Point getRealScreenDimensions(Context context)
    {
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point localPoint = new Point();
        display.getRealSize(localPoint);
        return localPoint;
    }
}
