package com.rionacko.proxyScraperDB.proxy.service;

import com.rionacko.proxyScraperDB.proxy.document.Proxy;
import com.rionacko.proxyScraperDB.proxy.document.ProxyRepository;
import com.rionacko.proxyScraperDB.proxy.helper.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProxyService implements CommandLineRunner {
    private final ProxyRepository PROXYREPOSITORY;
    private static List<Proxy> proxyList = new ArrayList<>();

    public ProxyService(ProxyRepository proxyRepository){
        this.PROXYREPOSITORY = proxyRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        getProxyData();
        this.PROXYREPOSITORY.deleteAll();
        for(int i = 0; i < proxyList.size(); i++){
            Proxy proxy = proxyList.get(i);
            proxy.setId((i));
            this.PROXYREPOSITORY.save(proxy);
        }

    }

    public void getProxyData(){
        Utils utils = new Utils();
        proxyList = utils.getProxyListForRepository();
    }
}
