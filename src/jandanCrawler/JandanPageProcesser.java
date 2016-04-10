package jandanCrawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * //
 * //   █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * // ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * // ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * // ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * // ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * //  ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * Created by zym on 16-4-7.
 */
public class JandanPageProcesser implements PageProcessor {
    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);

    @Override
    public void process(Page page) {
//        page.putField("text", page.getHtml().$(".text p").all().toString());
        page.putField("text", page.getHtml().xpath("div[@class='text']/p/text()").all());
        page.putField("id", page.getHtml().xpath("//span[@class='righttext']/a/text()").all().toString());
        page.putField("support", page.getHtml().xpath("//div[@class='vote']/span/text()").regex("^\\d+$").all().toString());
        page.putField("page", page.getHtml().xpath("//span[@class='current-comment-page']/text()").regex("\\d+").toString());

        //遍历网址
//        page.addTargetRequests(page.getHtml().links().regex("^http://jandan\\.net/duan/page.\\d*").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new JandanPageProcesser())
                .addUrl("http://jandan.net/duan/page-1038")
                .thread(5)
                .addPipeline(new ConsolePipeline())
//                .addPipeline(new JandanPipeline())
//                .addPipeline(new FilePipeline("/home/workstation"))
                .run();
    }
}
