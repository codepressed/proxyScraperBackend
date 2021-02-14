package com.rionacko.proxyScraperDB.proxy.controller;

import com.rionacko.proxyScraperDB.proxy.document.Proxy;
import com.rionacko.proxyScraperDB.proxy.document.ProxyRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/proxy")
public class ProxyController {
    private final ProxyRepository PROXYREPOSITORY;

    public ProxyController(ProxyRepository proxyRepository){
        this.PROXYREPOSITORY = proxyRepository;
    }

    @RequestMapping(value = "/all")
    public Iterable<Proxy> getAll(){
        return PROXYREPOSITORY.findAll();
    }







}





