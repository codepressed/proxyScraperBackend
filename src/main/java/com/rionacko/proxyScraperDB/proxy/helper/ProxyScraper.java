package com.rionacko.proxyScraperDB.proxy.helper;

import com.rionacko.proxyScraperDB.proxy.document.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProxyScraper {
    final Date date = new Date();
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    final String website = "https://hidemy.name/es/proxy-list/";
    final String cssQueryTable = "table";
    final String cssQueryTr = "tr";
    final String CSVfileName = "proxyList " + dateFormat.format(date) + ".csv";
    final int timeOut = 10000;

    public List<Proxy> proxyList() throws IOException {
        List<Proxy> proxyArray = new ArrayList<>();

        final Document proxyPage = Jsoup.connect(website).timeout(timeOut).get();
        Element table = proxyPage.select(cssQueryTable).get(0);
        Elements rows = table.select(cssQueryTr);

        final PrintWriter out = new PrintWriter(CSVfileName);
        out.write("IP; Port; Location; MS; Proxy type; Safety\n");

        for (int i = 1 ; i < rows.size(); i++) {
            Element row = rows.get(i);
            final String ip = row.select("td:eq(0)").text();
            final int port = Integer.parseInt(row.select("td:eq(1)").text());
            final String location = row.select("td:eq(2)").text();
            final String ms = row.select("td:eq(3)").text();
            final String proxyType = row.select("td:eq(4)").text();
            final String safety = row.select("td:eq(5)").text();
            proxyArray.add(new Proxy(ip, port, location, ms, proxyType, safety));
            out.write(ip + "; " + port + "; " + location + "; " + ms + "; " + proxyType + "; " + safety + "\n");
        }
        out.close();
        return proxyArray;

    }
}

