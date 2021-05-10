package br.com.application.service;

import br.com.application.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakUserService {

    @Value("${keycloak.realm}")
    private String realm;

    @Autowired
    private final Keycloak keycloak;


    public List<UserRepresentation> findAllUsers() {
        return keycloak.realm(realm).users().list();
    }

    public Response createUser(final UserRequest request) {
        CredentialRepresentation password = preparePasswordRepresentation(request.getPassword());
        UserRepresentation user = prepareUserRepresentation(request, password);
        return keycloak.realm(realm).users().create(user);
    }

    public void updatePassword(final UserRequest request, final String id) {
        UserRepresentation userRepresentation = keycloak.realm(realm).users().get(id).toRepresentation();

        CredentialRepresentation password = preparePasswordRepresentation(request.getPassword());
        UserRepresentation user = prepareUserRepresentation(request, password);
        user.setCredentials(List.of(password));
    }

    public List<UserRepresentation> findByUsername(final String username) {
        return keycloak.realm(realm).users().search(username);
    }

    public UserRepresentation findUserById(final String id) {
        return keycloak.realm(realm).users().get(id).toRepresentation();
    }

    public List<UserRepresentation> findUserByParams(final String username, final String firstname, final String lastname, final String email, final Integer first) {
        return keycloak.realm(realm).users().search(username, firstname, lastname, email, first, null);
    }


    private CredentialRepresentation preparePasswordRepresentation(String password) {
        CredentialRepresentation cR = new CredentialRepresentation();
        cR.setTemporary(false);
        cR.setType(CredentialRepresentation.PASSWORD);
        cR.setValue(password);
        return cR;
    }

    private UserRepresentation prepareUserRepresentation(UserRequest request, CredentialRepresentation cR) {
        UserRepresentation newUser = new UserRepresentation();
        newUser.setUsername(request.getUsername());
        newUser.setCredentials(List.of(cR));
        newUser.setEnabled(true);
        return newUser;
    }
}