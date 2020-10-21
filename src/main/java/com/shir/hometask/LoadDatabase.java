package com.shir.hometask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ItemRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Item("item1", 10, "123456")));
      log.info("Preloading " + repository.save(new Item("item2", 20, "456789")));
    }; 
  }
}
