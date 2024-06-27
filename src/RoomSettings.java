public class RoomSettings {

    private int light;
    private int blinds;
    private int airConditioning;
    private int temperature;


    public RoomSettings(int light, int blinds, int airConditioning, int temperature) {
        this.light = light;
        this.blinds = blinds;
        this.airConditioning = airConditioning;
        this.temperature = temperature;
    }

    public RoomSettings(int light, int blinds, int airConditioning) {
        this.light = light;
        this.blinds = blinds;
        this.airConditioning = airConditioning;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getBlinds() {
        return blinds;
    }

    public void setBlinds(int blinds) {
        this.blinds = blinds;
    }

    public int getAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(int airConditioning) {
        this.airConditioning = airConditioning;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String toStringTemperature(String roomName) {
        return   roomName+";" + light +
                ";" + blinds +
                ";" + airConditioning +
                ";" + temperature;
    }
    public String toStringNoTemperature(String roomName) {
        return   roomName+";" + light +
                ";" + blinds +
                ";" + airConditioning;
    }
}
