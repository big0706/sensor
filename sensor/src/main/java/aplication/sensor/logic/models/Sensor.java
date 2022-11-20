package aplication.sensor.logic.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The sensor name shouldn't be empty!")
    @Size(min = 3,max = 30,message = "Sensor name should be between 3 to 30 letters")
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sensor")
    private List<TemperatureValues> listOfStatus;

    public Sensor() {
    }

    public Sensor(String name) {
        this.name = name;
    }

    public Sensor(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TemperatureValues> getListOfStatus() {
        return listOfStatus;
    }

    public void setListOfStatus(List<TemperatureValues> listOfStatus) {
        this.listOfStatus = listOfStatus;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
