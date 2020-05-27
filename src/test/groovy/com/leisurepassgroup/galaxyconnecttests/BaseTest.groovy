package com.leisurepassgroup.galaxyconnecttests

import com.fasterxml.jackson.databind.ObjectMapper
import com.leisurepassgroup.galaxyconnecttests.model.response.DestinationResponse
import org.mockserver.client.MockServerClient
import static org.mockserver.model.HttpResponse.response
import static org.mockserver.model.HttpRequest.request
import spock.lang.Specification

abstract class BaseTest extends Specification {

	private static def HOST = "https://mockserver.dev.passport.lpgdev.co"
	def mapper = new ObjectMapper()
	def adminUiChannelServerMock = new MockServerClient(
			HOST , 1080, "/admin-ui-channel"
	).withSecure(true)


	def mockDestination(DestinationResponse output, int statusCode = 200) {
		def json = mapper.writeValueAsString(output)
		adminUiChannelServerMock
				.when(
						request()
								.withMethod("GET")
								.withPath("/destinations"))
				.respond(
						response()
								.withHeader("Content-Type", "application/json")
								.withStatusCode(statusCode)
								.withBody(json)
				)
	}

	def setup() {
		adminUiChannelServerMock.reset()
	}
}
