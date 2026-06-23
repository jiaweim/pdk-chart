package pdk.chart.demo;

public class DemoDescription {
    private Class demoClass;
    private String description;

    public DemoDescription(Class demoClass, String demoDescription) {
        this.demoClass = demoClass;
        this.description = demoDescription;
    }

    public Class getDemoClass() {
        return this.demoClass;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.description;
    }
}
