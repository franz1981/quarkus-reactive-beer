package redhat.perf;

public class Beer {

    private String name;

    private String tagline;

    private double abv;

    private Beer(String name, String tagline, double abv) {
        this.name = name;
        this.tagline = tagline;
        this.abv = abv;
    }

    public static Beer of(String name, String tagline, double abv) {
        return new Beer(name, tagline, abv);
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public double getAbv() {
        return abv;
    }

}
