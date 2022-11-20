package aplication.sensor.logic.repositories;

import aplication.sensor.logic.models.Sensor;
import aplication.sensor.logic.models.TemperatureValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemperatureValuesRepository extends JpaRepository<TemperatureValues,Long> {
    @Query("select count(t) from TemperatureValues t where t.raining= true")
    int rainingDays();
}
