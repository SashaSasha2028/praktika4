package api.services;

import api.models.DataEntity;
import java.util.List;

public interface DatabaseService {
    DataEntity getById(String id);
    List<DataEntity> getAll();
    DataEntity save(DataEntity entity);
    void delete(String id);
    List<DataEntity> getReadOnlyData();
    List<DataEntity> getWritableData();
}
