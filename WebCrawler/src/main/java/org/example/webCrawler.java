package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;

/*
implementing dfs (limited depth ie maximum depth of dfs is fixed(stored in maxDepth));
root will be the javatpoint home webpage and from that we access all the hyperlinks present on webpage;
*/

public class webCrawler {
    HashSet<String> urlSet; //to avoid duplicates;
    int maxDepth=2;         //max depth for dfs;
    webCrawler(){
        urlSet = new HashSet<>();
    }
    public void getWebpagesAndLinks(String url,int depth) {
        if(urlSet.contains(url)) return;
        if(depth>=maxDepth) return;
        try{
            //parsing, converting url into java obj( html obj->java obj ) and set timeout 10000ms (it will wait for this much time);
            Document document= Jsoup.connect(url).timeout(10000).get();
            //indexer (stores the desired data in database)
            Indexer indexer=new Indexer(document,url);
            urlSet.add(url);
            System.out.println(url+"\n"+document.title());

            //get all hyperlinks present in webpage and hyperlinks are present in <a> tag;
            Elements elementLinks = document.select("a[href]");
            //dfs call -> iterate through each hyperlink;
            for (Element curLink:elementLinks) {
                //get link from curLink element by attr method;
                getWebpagesAndLinks(curLink.attr("abs:href"),depth+1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        webCrawler crawler=new webCrawler();
        //access all links present in this web page parse it and then store it in database;
        crawler.getWebpagesAndLinks("https://www.javatpoint.com",0);
    }
}