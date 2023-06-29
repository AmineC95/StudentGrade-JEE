package org.eclipse.jakarta.resource;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.jakarta.service.AuthService; // importez votre service d'authentification

@Path("/auth")
public class AuthenticationResource {

    private AuthService authService = new AuthService(); // Créez une instance de votre service d'authentification

    @POST
    @Path("/login")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        String token = authService.login(username, password); // Utilisez votre service d'authentification pour vérifier
                                                              // l'utilisateur et générer un token
        if (token == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build(); // Si le login échoue, renvoyez un status
                                                                          // UNAUTHORIZED
        } else {
            return Response.ok(token).build(); // Si le login réussit, renvoyez le token dans la réponse
        }
    }

    @POST
    @Path("/register")
    public Response register(@FormParam("username") String username, @FormParam("password") String password) {
        boolean success = authService.register(username, password); // Utilisez votre service d'authentification pour
                                                                    // enregistrer un nouvel utilisateur
        if (success) {
            return Response.status(Response.Status.CREATED).build(); // Si l'enregistrement réussit, renvoyez un status
                                                                     // CREATED
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build(); // Si l'enregistrement échoue, renvoyez un
                                                                         // status BAD_REQUEST
        }
    }

    @POST
    @Path("/logout")
    public Response logout(@HeaderParam("authorization") String token) {
        authService.logout(token); // Utilisez votre service d'authentification pour invalider le token
        return Response.ok("Logged out successfully.").build(); // Renvoyez un message de succès
    }
}
