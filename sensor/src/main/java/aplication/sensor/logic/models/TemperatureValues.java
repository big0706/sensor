package aplication.sensor.logic.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;



import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_measurements")
public class TemperatureValues implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @DecimalMin(value = "-100.0", message = "The value should be between -100.0 and 100.0")
    @DecimalMax(value = "100.0",message = "The value should be between -100.0 and 100.0")
    @Digits(integer = 3,fraction = 1,message = "The value should be such, for example,in the format as 30.0")
    @Column(name = "temperature_values")
    private Double value;

    @Column(name = "raining")
    private boolean raining;

    @Column(name = "date")
    private LocalDateTime date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_name",referencedColumnName = "name")
    private Sensor sensor;

    public TemperatureValues() {
    }

    public TemperatureValues(Double value, boolean raining, LocalDateTime currentDate) {
        this.value = value;
        this.raining = raining;
        this.date = currentDate;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }


}
