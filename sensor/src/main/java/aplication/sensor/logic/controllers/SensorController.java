package aplication.sensor.logic.controllers;

import aplication.sensor.logic.models.Sensor;
import aplication.sensor.logic.models.TemperatureValues;
import aplication.sensor.logic.modelsDTO.SensorDTO;
import aplication.sensor.logic.modelsDTO.TemperatureValuesDTO;
import aplication.sensor.logic.services.SensorService;
import aplication.sensor.logic.services.TemperatureValuesService;
import aplication.sensor.logic.util.castomException.SensorErrorResponse;
import aplication.sensor.logic.util.exceptions.SensorDuplicateFoundException;
import aplication.sensor.logic.util.exceptions.SensorNotFoundException;
import aplication.sensor.logic.util.valodators.SensorAvailabilityValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping()
public class SensorController {

    private final SensorService sensorService;
    private final TemperatureValuesService temperatureValuesService;

    private final SensorAvailabilityValidator availabilityValidator;
    private final ModelMapper mapper;

    @Autowired
    public SensorController(SensorService sensorService, TemperatureValuesService temperatureValuesService,
                             SensorAvailabilityValidator availabilityValidator, ModelMapper mapper) {
        this.sensorService = sensorService;
        this.temperatureValuesService = temperatureValuesService;
        this.availabilityValidator = availabilityValidator;
        this.mapper = mapper;
    }


    @PostMapping("/sensors/registration")
    public ResponseEntity<HttpStatus> registration(@Valid @RequestBody SensorDTO sensorDTO,
                                                   BindingResult bindingResult){
        availabilityValidator.validate(sensorDTO,bindingResult);
        bindingErrors(bindingResult);
        sensorService.addSensor(convectorToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }



    @PostMapping("/measurements/add")
    public ResponseEntity<HttpStatus> measurementsAdd(@Valid @RequestBody TemperatureValuesDTO dto,
                                                      BindingResult bindingResult){
        bindingErrors(bindingResult);
        TemperatureValues tm = convectorToMeasurements(dto);
        System.out.println(tm.getSensor().getName());
        temperatureValuesService.saveValues(convectorToMeasurements(dto));
         return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/measurements")
    public List<TemperatureValues> getMeasurements(){
        return temperatureValuesService.getMeasurements();
    }

    @GetMapping("/measurements/rainyDaysCount")
    private int rainyDays(){
       return temperatureValuesService.rainDaysCount();
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorDuplicateFoundException exception){
        SensorErrorResponse response = new SensorErrorResponse(
                exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> notFoundSensor(SensorNotFoundException e){
        SensorErrorResponse response= new SensorErrorResponse("This sensor not found in DataBase!");
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    private void bindingErrors(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errors =new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for(FieldError e:errorList){
                errors.append(e.getField())
                        .append(" - ")
                        .append(e.getDefaultMessage())
                        .append(";");
            }

            throw new SensorDuplicateFoundException(errors.toString());
        }
    }

    private Sensor convectorToSensor(SensorDTO s) {
        return mapper.map(s, Sensor.class);
    }

    private TemperatureValues convectorToMeasurements(TemperatureValuesDTO t) {
        return mapper.map(t,TemperatureValues.class);
    }
}
