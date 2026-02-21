package uzumtech.court.jcourtservice.adapter;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import uzumtech.court.jcourtservice.dto.response.GcpResponse;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class GcpAdapter {

    final RestClient restClient;

    @Value(value = "${services.gcp.url.getUser}")
    String url;

    public GcpResponse getUser(String personalIdentificationNumber){
        return restClient.get()
                .uri(url + "/{personalIdentificationNumber}",  personalIdentificationNumber)
                .retrieve()
                .body(GcpResponse.class);
    }
}
