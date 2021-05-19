package voide.sound;

public class Music extends Sound {

    private String name;
    private int intensity;

    public Music() {}

    public Music(String path, String name, int intensity) {
        super(path);
        this.name = name;
        this.intensity = intensity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public String toString() {
        return "Music - " + name + " [" + intensity + "]";
    }
}
