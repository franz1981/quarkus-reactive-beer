package redhat.perf;

import io.smallrye.mutiny.Multi;
import jakarta.inject.Singleton;

import java.util.Arrays;

@Singleton
public class BeerRepository {

    private static final Beer[] BEERS;

    static {
        var beer = Beer.of("Bowman's Beard - B-Sides", "English Barley Wine.", 18.3);
        // this is about 1K of produced JSON
        BEERS = new Beer[12];
        Arrays.fill(BEERS, beer);
    }

    public Multi<Beer> streamAll() {
        return Multi.createFrom().items(BEERS);
    }

}
