package aplication.sensor.logic.modelsDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SensorDTO {


    @NotBlank(message = "Name not be empty!")
    @Size(min = 3,max = 30,message = "Sensor name should be between 3 to 30 letters")
    private String name;

    public SensorDTO() {
    }


    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
