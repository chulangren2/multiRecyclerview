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
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=0&spn=0&di=107250&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=170309107%2C348767854&os=199650472%2C201497672&" +
                    "simid=3531051181%2C453117941&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic15.nipic.com%2F20110630%2F7830170_073943387126_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FnAzdH3F8dnAzdH3F900n8dbhjmbua99k_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561353485259&di=4fe3d2f250af2000d10a785b9fc84bc7&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang" +
                    ".com%2Fuploads%2Fblog%2F201409%2F06%2F20140906090442_uHBFr.jpeg",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=7&spn=0&di=142230&pi=0&rn=1&tn" +
                    "=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2909697926%2C3715333473&os=1647197302%2C2407023952&simid=4285785551%2C649089007&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined" +
                    "&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F03%2F15%2F145801556526152391.JPEG&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bf5i7_z%26e3Bv54AzdH3FwAzdH3Fmnccadnc_880clc&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=8&spn=0&di=49280&pi=0&rn=1&tn" +
                    "=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2720625662%2C4207476265&os=468696014%2C334508326&simid=4130560004%2C658254358&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic44.nipic.com%2F20140717%2F12131878_205038320000_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Fzi7wgptAzdH3F89ln8cc_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=10&spn=0&di=174900&pi=0&rn=1&tn" +
                    "=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1732158648%2C3104859938&os=344182919%2C543108577&simid=4182639938%2C812792244&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic19.nipic.com%2F20120217%2F9355535_193838499183_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Fejvp56AzdH3Fzi7wgptAzdH3F8a0lndl_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=11&spn=0&di=227810&pi=0&rn=1&tn" +
                    "=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=105868100%2C3563649492&os=2009837432%2C726799631&simid=0%2C0&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic65.nipic.com%2Ffile%2F20150423%2F3101644_231448190936_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3F1jft2gAzdH3Fzi7wgptAzdH3F8988mbc_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=14&spn=0&di=51480&pi=0&rn=1&tn" +
                    "=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1281382635%2C591139531&os=4249541637%2C74557924&simid=4239748341%2C651854836&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic27.nipic.com%2F20130317%2F4418044_184340508000_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3F00cc8la_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=15&spn=0&di=217250&pi=0&rn=1&tn" +
                    "=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2437398066%2C2502212313&os=142143707%2C321885664&simid=4281911652%2C796715036&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic27.nipic.com%2F20130219%2F9644879_142341948000_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3F9AzdH3F8acAzdH3F0cmann8hmbu9v98j_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=17&spn=0&di=118580&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=200175%2C1798546496&os=155574656%2C276189963&simid=0%2C0&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=" +
                    "undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic14.nipic.com%2F20110531%2F7232897_091336235197_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FnAzdH3FdmAzdH3F9mma8b0hmcavkwk9_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=20&spn=0&di=221760&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2643136906%2C1436675719&os=497094554%2C166511446&simid=3432106460%2C307848033&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=" +
                    "undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic27.nipic.com%2F20130130%2F10560655_081950154000_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3F0cdnaml_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=21&spn=0&di=195030&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2327294840%2C416689359&os=92772055%2C236682581&simid=0%2C0&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic26.nipic.com%2F20130116%2F1773545_175930943000_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Fzi7wgptAzdH3F8dllacd_8b_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=22&spn=0&di=52800&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1141694906%2C723711818&os=422634091%2C157912913&simid=4208861229%2C556316862&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic21.nipic.com%2F20120527%2F10065112_014053117196_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FdAzdH3FmaAzdH3Fmd0lb0dh998abka8_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=23&spn=0&di=31900&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2908947832%2C3466741028&os=4230896072%2C76563487&simid=4171946743%2C680878655&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic38.nipic.com%2F20140213%2F1361020_045854910000_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3Fl0am8b0_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=31&spn=0&di=90530&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2218362081%2C1577879391&os=739884486%2C366034499&simid=72719757%2C707636383&adpicid=0&lpn=0&ln=1713&fr=&fmq=1561443705637_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic23.nipic.com%2F20120831%2F10705080_084938639135_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FdAzdH3FmaAzdH3Fm000nclhk8ub1vkv_z%26e3Bip4s&gsm=1e&rpstart=0&rpnum=0&islist=&querylist=&force=undefined")
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
            program.setbImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561353485253&di=9f140bef980ad3c3faa4c3d5154cfcff&imgtype=0&src=http%3A%2F%2Fpic26.nipic.com%2F20121207%2F10467706_123435117303_2.jpg")
            program.setsImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561353485253&di=9f140bef980ad3c3faa4c3d5154cfcff&imgtype=0&src=http%3A%2F%2Fpic26.nipic.com%2F20121207%2F10467706_123435117303_2.jpg")
            program.colorStr = "#FF4081"
            program.setsColorStr("#303F9F")
            list.add(program)
        }

        scrollView?.loadData(list)
    }


}
