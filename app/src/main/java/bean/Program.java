package bean;

import java.io.Serializable;

/**
 * Created by HuaJing.Chu on 2019/6/23.
 */

public class Program implements Serializable
{
    private String title;
    private String des;
    private String sImg;
    private String bImg;
    private String colorStr;
    private String sColorStr;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDes()
    {
        return des;
    }

    public void setDes(String des)
    {
        this.des = des;
    }

    public String getsImg()
    {
        return sImg;
    }

    public void setsImg(String sImg)
    {
        this.sImg = sImg;
    }

    public String getbImg()
    {
        return bImg;
    }

    public void setbImg(String bImg)
    {
        this.bImg = bImg;
    }


    public String getColorStr()
    {
        return colorStr;
    }

    public void setColorStr(String colorStr)
    {
        this.colorStr = colorStr;
    }

    public String getsColorStr()
    {
        return sColorStr;
    }

    public void setsColorStr(String sColorStr)
    {
        this.sColorStr = sColorStr;
    }
}
