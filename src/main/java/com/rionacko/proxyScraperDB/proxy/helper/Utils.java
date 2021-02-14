package com.rionacko.proxyScraperDB.proxy.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rionacko.proxyScraperDB.proxy.document.Proxy;

import java.io.*;
import java.util.List;

public class Utils {
    private static ProxyScraper proxyScraper = new ProxyScraper();
    private String JSONFileName = "proxyList.json";

    public static List<Proxy> getProxyList() throws IOException {
        return proxyScraper.proxyList();
    }

    public List<Proxy> getProxyListForRepository() {
        ProxyBinding proxyBinding = new ProxyBinding();
        List<Proxy> proxies = null;
        try {
            proxies = Utils.getProxyList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        proxyBinding.setProxylist(proxies);
        String json = Utils.marshallJSON(proxyBinding);
        Utils.generateJson(JSONFileName, json);
        ProxyBinding bindToUnmarshall = Utils.unmarshallJSON(JSONFileName);
        try {
            return bindToUnmarshall.getProxylist();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File generateJson(String fileName, String fileContent){
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
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

        return file;
    }

    public static <T extends Serializable> String marshallJSON(T item) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ProxyBinding unmarshallJSON(String file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(file), ProxyBinding.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }







}

