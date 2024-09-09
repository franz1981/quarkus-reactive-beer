package redhat.perf;

import io.smallrye.common.annotation.NonBlocking;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/greeting")
public class Greeting {

      @GET
      @Produces(MediaType.TEXT_PLAIN)
      @NonBlocking
      public String hello() {
         return "Hello World";
      }
}
