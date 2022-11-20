package aplication.sensor.logic.services;


import aplication.sensor.logic.models.Sensor;
import aplication.sensor.logic.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService  {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> sensor(String name){
        Optional<Sensor> s = sensorRepository.findByName(name);
        return Optional.ofNullable(s.orElse(null));
    }

    @Transactional
    public void addSensor(Sensor s){
        sensorRepository.save(s);
    }




}
