package jandanCrawler;

import java.util.Arrays;

/**
 * //
 * //   █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * // ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * // ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * // ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * // ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * //  ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * Created by joker on 16-4-10.
 */
public class test {
    public static void main(String[] args) {
        String a = "jandanCrawler.JandanPageProcesserAnno@5502b13c[text=[一些弹幕低俗评论影响未成年成长，需设审查机制, 目前在校大学生大多都浏览过“弹幕”网站，且“弹幕”使用人群有低龄化趋势。以bilibili为例，常年活跃用户在20万左右，其中中小学生占很大比重。有专家认为，低俗“弹幕”会影响未成年人健康成长，需要一定的审查机制。（法制日报）],id=25946]";
        String text = a.substring(a.indexOf("=[")+2, a.indexOf("],"));
        String id = a.substring(a.indexOf("id=")+3, a.lastIndexOf("]"));
        System.out.println(id + ":" + text);
    }
}
