package api.controllers;

import api.models.DataEntity;
import api.models.ApiResponse;
import api.services.DataService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class DataController {
    private final DataService dataService;
    
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }
    
    @GetMapping("/{id}")
    public ApiResponse<DataEntity> getData(@PathVariable String id) {
        return dataService.getData(id);
    }
    
    @GetMapping
    public ApiResponse<List<DataEntity>> getAllData(
            @RequestParam(required = false) Boolean onlyReadOnly) {
        return dataService.getAllData(onlyReadOnly != null && onlyReadOnly);
    }
    
    @PostMapping
    public ApiResponse<DataEntity> saveData(@RequestBody DataEntity entity) {
        return dataService.saveData(entity);
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteData(@PathVariable String id) {
        return dataService.deleteData(id);
    }
}
