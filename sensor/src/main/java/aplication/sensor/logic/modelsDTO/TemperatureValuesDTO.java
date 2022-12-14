package aplication.sensor.logic.modelsDTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;

public class TemperatureValuesDTO {
    
    @NotNull
    @DecimalMin(value = "-100.0", message = "The value should be between -100.0 and 100.0")
    @DecimalMax(value = "100.0",message = "The value should be between -100.0 and 100.0")
    @Digits(integer = 3,fraction = 1,message = "The value should be such, for example,in the format as 30.0")
    private Double value;

    private boolean raining;

    private SensorDTO sensor;

    public TemperatureValuesDTO() {
    }

    public TemperatureValuesDTO(Double value, boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }


    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
