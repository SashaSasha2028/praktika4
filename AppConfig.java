package config;

import api.services.DatabaseService;
import api.services.DatabaseServiceImpl;
import api.services.CacheService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public DatabaseService databaseService() {
        return new DatabaseServiceImpl();
    }
    
    @Bean
    public CacheService cacheService(DatabaseService databaseService) {
        return new CacheService(databaseService);
    }
}
