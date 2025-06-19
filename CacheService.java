package api.services;

import api.models.DataEntity;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CacheService {
    private final ConcurrentHashMap<String, DataEntity> cache = new ConcurrentHashMap<>();
    private final DatabaseService databaseService;
    
    public CacheService(DatabaseService databaseService) {
        this.databaseService = databaseService;
        initializeCache();
    }
    
    private void initializeCache() {
        List<DataEntity> readOnlyData = databaseService.getReadOnlyData();
        readOnlyData.forEach(entity -> cache.put(entity.getId(), entity));
    }
    
    public DataEntity getFromCache(String id) {
        return cache.get(id);
    }
    
    public void updateCache(DataEntity entity) {
        if (entity.isReadOnly()) {
            cache.put(entity.getId(), entity);
        }
    }
    
    public void removeFromCache(String id) {
        cache.remove(id);
    }
}
