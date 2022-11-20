package aplication.sensor.logic.services;

import aplication.sensor.logic.models.TemperatureValues;
import aplication.sensor.logic.repositories.SensorRepository;
import aplication.sensor.logic.repositories.TemperatureValuesRepository;
import aplication.sensor.logic.util.exceptions.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class TemperatureValuesService  {

    private final TemperatureValuesRepository temperature;
    private final SensorRepository sensor;


    @Autowired
    public TemperatureValuesService(TemperatureValuesRepository temperature,SensorRepository sensor) {
        this.temperature = temperature;
        this.sensor = sensor;

    }

    public List<TemperatureValues> getMeasurements(){
        return temperature.findAll();
    }

    @Transactional
    public void saveValues(TemperatureValues t){
        t.setDate(LocalDateTime.now());
        t.setSensor(sensor.findByName(t.getSensor().getName()).orElseThrow(SensorNotFoundException::new));
        temperature.save(t);
    }

    public int rainDaysCount(){
        return temperature.rainingDays();
    }


}
