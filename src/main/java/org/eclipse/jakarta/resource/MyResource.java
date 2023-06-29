package org.eclipse.jakarta.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.jakarta.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@Path("/myresource")
public class MyResource {

    private static Map<Integer, String> data = new HashMap<>();
    private static Integer currentId = 1;
    private AuthService authService = new AuthService();

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response create(@HeaderParam("authorization") String token, String entity) {
        if (!authService.validateToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        data.put(currentId, entity);
        return Response.ok(currentId++).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response read(@HeaderParam("authorization") String token, @PathParam("id") Integer id) {
        if (!authService.validateToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String entity = data.get(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(entity).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response update(@HeaderParam("authorization") String token, @PathParam("id") Integer id, String entity) {
        if (!authService.validateToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        if (!data.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        data.put(id, entity);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@HeaderParam("authorization") String token, @PathParam("id") Integer id) {
        if (!authService.validateToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        if (!data.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        data.remove(id);
        return Response.ok().build();
    }
}
