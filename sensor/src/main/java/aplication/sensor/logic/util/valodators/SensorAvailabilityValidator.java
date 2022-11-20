package aplication.sensor.logic.util.valodators;

import aplication.sensor.logic.models.Sensor;
import aplication.sensor.logic.modelsDTO.SensorDTO;
import aplication.sensor.logic.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class SensorAvailabilityValidator implements Validator {

    private SensorService sensorService;

    @Autowired
    public SensorAvailabilityValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        if (sensorService.sensor(sensorDTO.getName()).isPresent() ) {
            errors.rejectValue("name","","This sensor is in the database!");
        }

    }
}
