package shareitgateway.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareitgateway.client.BaseClient;
import ru.practicum.shareitgateway.user.dto.UserDto;

@Service
public class UserClient extends BaseClient {

    private static final String API_PREFIX = "/users";

    @Autowired
    public UserClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> save(final UserDto userDto) {
        return post("", userDto);
    }

    public ResponseEntity<Object> update(final Integer userId,
                                         final UserDto userDto) {
        return patch("/" + userId, userDto);
    }

    public ResponseEntity<Object> findById(final Integer userId) {
        return get("/" + userId);
    }

    public ResponseEntity<Object> findAll() {
        return get("");
    }

    public ResponseEntity<Object> delete(final Integer userId) {
        return delete("/" + userId);
    }
}