package com.example.doublessq;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlUtils {


    private static HtmlPage page;


    private static final String TAG = "HtmlUtils";

    public static HtmlPage getContent(String url) {

        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(600 * 1000);
//        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        try {
            page = webClient.getPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return page;
    }


    public static List<ItemBean> getJsoupData() {
        List<ItemBean>  itemBeanList = new ArrayList<>();

        Document parse = null;
        try {
            parse = Jsoup.connect(Constants.ssqUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Elements select = parse.select("tr.t_tr1");

        for (int i = 0; i < select.size(); i++) {
            final Element element = select.get(i);

            final Elements ele = element.select("td");
            final ItemBean itemBean = new ItemBean();
            itemBean.date = ele.get(0).text();
            itemBean.one =  ele.get(1).text();
            itemBean.two =  ele.get(2).text();
            itemBean.three =  ele.get(3).text();
            itemBean.four = ele.get(4).text();
            itemBean.five =  ele.get(5).text();
            itemBean.six =  ele.get(6).text();
            itemBean.blue = ele.get(7).text();

            itemBeanList.add(itemBean);

        }

        return itemBeanList;

    }
}
