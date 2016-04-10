package jandanCrawler;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.FilePageModelPipeline;

import java.util.List;

/**
 * //
 * //   █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * // ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * // ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * // ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * // ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * //  ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * Created by zym on 16-4-10.
 */
@TargetUrl("http://jandan.net/duan/page*")
@ExtractBy(value = "//div[@class='row']", multi = true)
public class JandanPageProcesserAnno {

    @ExtractBy(value = "//div[@class='text']/p/text()")
    private List<String> text;
    @ExtractBy(value = "//span[@class='righttext']/a/text()")
    private String id;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000), new ConsolePageModelPipeline(), JandanPageProcesserAnno.class)
                .addUrl("http://jandan.net/duan/page-1038")
                .thread(5)
                .run();
    }
}
