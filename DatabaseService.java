package api.services;

import api.models.DataEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseServiceImpl implements DatabaseService {
    private final ConcurrentHashMap<String, DataEntity> database = new ConcurrentHashMap<>();
    
    @Override
    public DataEntity getById(String id) {
        return database.get(id);
    }
    
    @Override
    public List<DataEntity> getAll() {
        return new ArrayList<>(database.values());
    }
    
    @Override
    public DataEntity save(DataEntity entity) {
        entity.setLastUpdated(System.currentTimeMillis());
        database.put(entity.getId(), entity);
        return entity;
    }
    
    @Override
    public void delete(String id) {
        database.remove(id);
    }
    
    @Override
    public List<DataEntity> getReadOnlyData() {
        List<DataEntity> result = new ArrayList<>();
        for (DataEntity entity : database.values()) {
            if (entity.isReadOnly()) {
                result.add(entity);
            }
        }
        return result;
    }
    
    @Override
    public List<DataEntity> getWritableData() {
        List<DataEntity> result = new ArrayList<>();
        for (DataEntity entity : database.values()) {
            if (!entity.isReadOnly()) {
                result.add(entity);
            }
        }
        return result;
    }
}
