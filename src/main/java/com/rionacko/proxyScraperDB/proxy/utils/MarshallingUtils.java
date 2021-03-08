package com.rionacko.proxyScraperDB.proxy.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rionacko.proxyScraperDB.proxy.document.Proxy;
import com.rionacko.proxyScraperDB.proxy.helper.ProxyBinding;
import com.rionacko.proxyScraperDB.proxy.scraper.ProxyScraper;

import java.io.*;
import java.util.List;

public class MarshallingUtils {
    private final static ProxyScraper proxyScraper = new ProxyScraper();
    private final static String JSONFileName = "proxyList.json";

    public static List<Proxy> getProxyList() throws IOException {
        return proxyScraper.proxyList();
    }

    public List<Proxy> getProxyListForRepository() {
        ProxyBinding proxyBinding = new ProxyBinding();
        List<Proxy> proxies = null;
        try {
            proxies = MarshallingUtils.getProxyList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        proxyBinding.setProxylist(proxies);
        String json = MarshallingUtils.marshalling(proxyBinding);
        MarshallingUtils.generateJson(JSONFileName, json);
        ProxyBinding bindToUnmarshall = MarshallingUtils.unmarshalling(JSONFileName);
        try {
            return bindToUnmarshall.getProxylist();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void generateJson(String fileName, String fileContent){
        FileWriter fileWriter = null;
        PrintWriter printWriter;
        File file = null;
        try{
            file = new File(fileName);
            fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);
            printWriter.print(fileContent);
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            if(fileWriter!=null){
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }

    }

    public static <T extends Serializable> String marshalling(T item) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ProxyBinding unmarshalling(String file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(file), ProxyBinding.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

