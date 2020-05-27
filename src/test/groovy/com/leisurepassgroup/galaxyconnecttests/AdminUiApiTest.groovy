package com.leisurepassgroup.galaxyconnecttests

import com.leisurepassgroup.galaxyconnecttests.model.response.DestinationResponse

class AdminUiApiTest extends BaseTest {
    def "get destination - successfully fetched list of destination from admin ui channel"() {
        given:
        def responses = [
                new DestinationResponse("@@e2ea--DES 1590566620401", "@@e2ea--Destination 1590566620401"),
                new DestinationResponse("@@e2ea--DES 1590566659467", "@@e2ea--Destination 1590566659467"),
                new DestinationResponse("@@e2ea--DES 1590566833891", "@@e2ea--Destination 1590566833891"),
                new DestinationResponse("@@e2ea--DES 1590567695887", "@@e2ea--Destination 1590567695887")
        ]

        // mock admin server with requests
        mockDestination(responses)

        when:
        // call the actual API to

        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:9000").path("/foo").request().get();

        then:
        // test

    }
}
