package core.enums;

public enum Country {
    LT("en-LT"),
    LV("en-LV"),
    EE("en-EE"),
    RU("en-RU");

    private final String country;

    Country(String country) {
        this.country = country;
    }

    public String resolution() {
        return this.country;
    }
}
