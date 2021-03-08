package com.rionacko.proxyScraperDB.proxy.document;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProxyRepository extends MongoRepository<Proxy,Integer> {


}
