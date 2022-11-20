package aplication.sensor.logic.util.castomException;

public class SensorErrorResponse  {
    private String message;

    public SensorErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
