package br.com.application;

import br.com.application.request.UserRequest;
import br.com.application.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.util.JsonSerialization;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KeycloakAdminController {

    private final KeycloakUserService keycloakUserService;

    @GetMapping(path = "/users")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public List<UserRepresentation> getAllUsers() {

//        try {
        return keycloakUserService.findAllUsers();
//        } catch (ClientErrorException e) {
//            handleClientErrorException(e);
//        } catch (Exception e) {
//            Throwable cause = e.getCause();
//            if (cause instanceof ClientErrorException) {
//                handleClientErrorException((ClientErrorException) cause);
//            } else {
//                e.printStackTrace();
//            }
//        }
    }

    @PostMapping(path = "/users")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public void createUser(@RequestBody UserRequest userRequest) {
        keycloakUserService.createUser(userRequest);
    }

    @PatchMapping(path = "/users/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public void updatePassword(@RequestBody UserRequest userRequest, @PathVariable String id) {
        keycloakUserService.updatePassword(userRequest, id);
    }

    @GetMapping(path = "/users/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public UserRepresentation findUserById(@PathVariable String id) {
        return keycloakUserService.findUserById(id);
    }

    @GetMapping(path = "/users/search")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public List<UserRepresentation> findUserByParams(@RequestParam(required = false) String username, @RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname, @RequestParam(required = false) String email, @RequestParam(required = false) Integer first) {
        return keycloakUserService.findUserByParams(username, firstname, lastname, email, first);
    }

    private static void handleClientErrorException(ClientErrorException e) {
        e.printStackTrace();
        Response response = e.getResponse();
        try {
            System.out.println("status: " + response.getStatus());
            System.out.println("reason: " + response.getStatusInfo().getReasonPhrase());
            Map error = JsonSerialization.readValue((ByteArrayInputStream) response.getEntity(), Map.class);
            System.out.println("error: " + error.get("error"));
            System.out.println("error_description: " + error.get("error_description"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
