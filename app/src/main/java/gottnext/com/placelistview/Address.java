package gottnext.com.placelistview;

public class Address {
    private String _street;
    private String _colony;
    private String _latitude;
    private String _logitude;

    public String get_street() { return _street; }
    public void set_street(String _street) { this._street = _street; }
    public String get_colony() { return _colony; }
    public void set_colony(String _colony) { this._colony = _colony; }
    public String get_latitude() { return _latitude; }
    public void set_latitude(String _latitude) { this._latitude = _latitude; }
    public String get_logitude() { return _logitude; }
    public void set_logitude(String _logitude) { this._logitude = _logitude; }

    public Address() {
        this._street = "";
        this._colony = "";
        this._latitude = "";
        this._logitude = "";
    }

    public Address(String _street, String _colony, String _latitude, String _logitude) {
        this._street = _street;
        this._colony = _colony;
        this._latitude = _latitude;
        this._logitude = _logitude;
    }
}
