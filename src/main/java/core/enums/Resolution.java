package core.enums;

public enum Resolution {
    DESKTOP("1920x1080"),
    TABLET("768x1024"),
    MOBILE("360x640");

    private final String resolution;

    Resolution(String resolution) {
        this.resolution = resolution;
    }

    public String resolution() {
        return this.resolution;
    }
}
