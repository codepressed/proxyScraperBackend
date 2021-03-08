package com.rionacko.proxyScraperDB.proxy.helper;

import com.rionacko.proxyScraperDB.proxy.document.Proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProxyBinding implements Serializable {

    @XmlElement(name = "proxy")
    private List<Proxy> proxylist;

    public List<Proxy> getProxylist(){
        return proxylist;
    }

    public void setProxylist(List<Proxy> proxylist){
        this.proxylist = proxylist;
    }

}


