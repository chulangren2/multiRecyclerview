package recyclerview.chj.com.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import bean.Program
import widgt.TvMultiScrollView

class MainActivity : AppCompatActivity()
{
    private var scrollView: TvMultiScrollView? = null
    private var sImageS = arrayOf(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561353485253&di=9f140bef980ad3c3faa4c3d5154cfcff&imgtype=0&src=http%3A%2F%2Fpic26.nipic.com%2F20121207%2F10467706_123435117303_2.jpg" ,
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561454589533&di=2bbe1fc7014849bbd10067345dfcd7e0&imgtype=0&src=http%3A%2F%2Fpic23.nipic.com%2F20120831%2F10705080_084938639135_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561353485259&di=4fe3d2f250af2000d10a785b9fc84bc7&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang" +
                    ".com%2Fuploads%2Fblog%2F201409%2F06%2F20140906090442_uHBFr.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790695&di=64c3c2db1ab61be52d7d655458414263&imgtype=0&src=http%3A%2F%2Fpic38.nipic" +
                    ".com%2F20140213%2F1361020_045854910000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790696&di=6b6b1181881f81a6568a45f30c25b956&imgtype=0&src=http%3A%2F%2Fpic21.nipic.com%2F20120527%2F10065112_014053117196_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790696&di=7a64d865c0bb7e5b44578c28702c9b74&imgtype=0&src=http%3A%2F%2Fpic26.nipic.com%2F20130116%2F1773545_175930943000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790696&di=3209e351fb31f4a6d0dad1db74a8f882&imgtype=0&src=http%3A%2F%2Fpic27.nipic.com%2F20130130%2F10560655_081950154000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790697&di=8c7697084d863d5d9a235428facc7af1&imgtype=0&src=http%3A%2F%2Fpic14.nipic.com%2F20110531%2F7232897_091336235197_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790698&di=5f503fcb9cb1851cbb01d5e252dc893b&imgtype=0&src=http%3A%2F%2Fpic27.nipic.com%2F20130219%2F9644879_142341948000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790699&di=6bb9cb620056ea61ad65527be7971d48&imgtype=0&src=http%3A%2F%2Fpic27.nipic.com%2F20130317%2F4418044_184340508000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790700&di=1d6b384642d4a2e5203883245099292e&imgtype=0&src=http%3A%2F%2Fpic65.nipic.com%2Ffile%2F20150423%2F3101644_231448190936_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790700&di=2c33e3b8a1de6ae3825258bb50f8961d&imgtype=0&src=http%3A%2F%2Fpic19.nipic.com%2F20120217%2F9355535_193838499183_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790701&di=de3604166f5ca2033b3c4c154713f092&imgtype=0&src=http%3A%2F%2Fpic44.nipic.com%2F20140717%2F12131878_205038320000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561453790693&di=d0ece985d001ad1377fef1423e878bd5&imgtype=0&src=http%3A%2F%2Fpic13.nipic.com%2F20110324%2F5248384_131521432325_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561454589533&di=a77de376bed1846b1769dffeb4adcc31&imgtype=0&src=http%3A%2F%2Fpic22.nipic.com%2F20120714%2F9007576_172444006397_2.jpg")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.tv_layout_container)

        scrollView = findViewById(R.id.multiRecyclerView)

        loadData()
    }

    private fun loadData()
    {
        val list: ArrayList<Program> = ArrayList()
        for (index in 0 until 15)
        {
            val program = Program()
            program.title = "title "+index
            program.des = "subtitle "+index
            program.setbImg(sImageS[index])
            program.setsImg(sImageS[index])
            program.colorStr = "#FF4081"
            program.setsColorStr("#303F9F")
            list.add(program)
        }

        scrollView?.loadData(list)
    }


}
