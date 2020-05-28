package com.leisurepassgroup.galaxyconnecttests

import com.fasterxml.jackson.core.type.TypeReference
import com.leisurepassgroup.galaxyconnecttests.model.response.DestinationResponse
import org.mockserver.verify.VerificationTimes

import java.net.http.HttpRequest
import java.net.http.HttpResponse
import static org.mockserver.model.HttpRequest.request

class AdminUiApiTest extends BaseTest {
    def "get destination - successfully fetched list of destination from admin ui channel"() {
        given:
        HttpHost
        def responses = [
                new DestinationResponse("@@e2ea--DES 1590566620401", "@@e2ea--Destination 1590566620401"),
                new DestinationResponse("@@e2ea--DES 1590566659467", "@@e2ea--Destination 1590566659467"),
                new DestinationResponse("@@e2ea--DES 1590566833891", "@@e2ea--Destination 1590566833891"),
                new DestinationResponse("@@e2ea--DES 1590567695887", "@@e2ea--Destination 1590567695887")
        ]

        mockDestination(responses)

        // mock admin server with requests
        def httpRequest = HttpRequest.newBuilder()
                .uri(new URI("http://$HOST:1080/$CONTEXT_PATH/destinations"))
                .GET()
                .build();

        when:
        // call the actual API to
        def response = CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString())
        def output = mapper.readValue(response.body(), new TypeReference<List<DestinationResponse>>() {})

        then:
        // test
        adminUiChannelServerMock.verify(
                request().withPath("/$CONTEXT_PATH/destinations"),
                VerificationTimes.once()
        )
        output.size() == 4

    }
}
