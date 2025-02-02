package com.multitenant.config;

import com.multitenant.SharedCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = SharedCollection.class))
public class MultiTenantMongoAppConfig extends AbstractMongoClientConfiguration {

  @Autowired
  private MongoConfigProperties mongoConfigProperties;

  @Override
  protected String getDatabaseName() {
    return null;
  }

  @Override
  @Primary
  @Bean
  public MongoDatabaseFactory mongoDbFactory() {
    String globalDB = mongoConfigProperties.getDataBaseName();
    return new MultiTenantMongoDBFactory(mongoClient(), globalDB);
  }

  @Override
  @Bean
  @Primary
  public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory, MappingMongoConverter converter) {
    return new MongoTemplate(mongoDbFactory, converter);
  }

  @Bean
  public MongoTemplate mongoTemplateShared(MongoConfigProperties mongoConfigProperties) {
    MongoDatabaseFactory mongoDbFactory = new SimpleMongoClientDatabaseFactory(mongoClient(), mongoConfigProperties.getDataBaseName());
    return new MongoTemplate(mongoDbFactory);
  }

}
