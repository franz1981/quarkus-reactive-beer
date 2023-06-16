package redhat.perf;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/beer")
public class BeerResource {

    @Inject
    BeerRepository beerRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Beer>> beers() {
        return beerRepository.streamAll()
                .filter(b -> b.getAbv() > 15.0)
                .filter(b -> b.getName()
                        .regionMatches(true, 1,
                                "owman's Beard - B-Sides", 0, "owman's Beard - B-Sides".length()) &&
                        b.getTagline().equalsIgnoreCase("English Barley Wine."))
                .collect().asList();
    }

}