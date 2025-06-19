package api.controllers;

import api.models.ApiResponse;
import api.services.DataService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final DataService dataService;
    
    public ReportController(DataService dataService) {
        this.dataService = dataService;
    }
    
    @GetMapping("/generate")
    public ApiResponse<String> generateReport(
            @RequestParam(required = false) Boolean useCache) {
        // В реальной системе здесь была бы логика генерации отчета
        return new ApiResponse<>(true, "Report generated", 
            "Report data: " + (useCache != null && useCache ? "from cache" : "from database"));
    }
    
    @GetMapping("/export")
    public ApiResponse<String> exportData(
            @RequestParam(required = false) Boolean useCache) {
        // Логика экспорта данных
        return new ApiResponse<>(true, "Data exported", 
            "Exported data: " + (useCache != null && useCache ? "from cache" : "from database"));
    }
}
