import javax.persistence.Embeddable;

/**
 * Created by miral on 7/5/2017.
 */

@Embeddable
public class Address {

    int streetNumber;
    String location;
    String State;



    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetNumber=" + streetNumber +
                ", location='" + location + '\'' +
                ", State='" + State + '\'' +
                '}';
    }
}
