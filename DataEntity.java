package api.models;

public class DataEntity {
    private String id;
    private String data;
    private boolean readOnly;
    private long lastUpdated;

    // Конструкторы, геттеры и сеттеры
    public boolean isReadOnly() {
        return readOnly;
    }
}
