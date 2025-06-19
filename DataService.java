package api.services;

import api.models.DataEntity;
import api.models.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    private final DatabaseService databaseService;
    private final CacheService cacheService;
    
    public DataService(DatabaseService databaseService, CacheService cacheService) {
        this.databaseService = databaseService;
        this.cacheService = cacheService;
    }
    
    public ApiResponse<DataEntity> getData(String id) {
        // Проверяем кэш для read-only данных
        DataEntity cached = cacheService.getFromCache(id);
        if (cached != null) {
            return new ApiResponse<>(true, "Data from cache", cached);
        }
        
        // Для writable данных или если нет в кэше - идем в БД
        DataEntity fromDb = databaseService.getById(id);
        if (fromDb != null) {
            return new ApiResponse<>(true, "Data from database", fromDb);
        }
        
        return new ApiResponse<>(false, "Data not found", null);
    }
    
    public ApiResponse<List<DataEntity>> getAllData(boolean onlyReadOnly) {
        if (onlyReadOnly) {
            return new ApiResponse<>(true, "All read-only data", 
                cacheService.getAllCached());
        } else {
            return new ApiResponse<>(true, "All data", 
                databaseService.getAll());
        }
    }
    
    public ApiResponse<DataEntity> saveData(DataEntity entity) {
        DataEntity saved = databaseService.save(entity);
        cacheService.updateCache(saved);
        return new ApiResponse<>(true, "Data saved", saved);
    }
    
    public ApiResponse<Void> deleteData(String id) {
        databaseService.delete(id);
        cacheService.removeFromCache(id);
        return new ApiResponse<>(true, "Data deleted", null);
    }
}
