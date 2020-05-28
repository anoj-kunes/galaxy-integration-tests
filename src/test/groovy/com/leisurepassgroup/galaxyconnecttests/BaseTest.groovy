package com.leisurepassgroup.galaxyconnecttests

import com.fasterxml.jackson.databind.ObjectMapper
import com.leisurepassgroup.galaxyconnecttests.model.response.DestinationResponse
import org.mockserver.client.MockServerClient

import java.net.http.HttpClient

import static org.mockserver.model.HttpResponse.response
import static org.mockserver.model.HttpRequest.request
import spock.lang.Specification

abstract class BaseTest extends Specification {

	static def HOST = "localhost"
	static final HttpClient CLIENT = HttpClient.newBuilder().build();
	def mapper = new ObjectMapper()
	def adminUiChannelServerMock = new MockServerClient(
			HOST, 1080
	)

	def mockDestination(List<DestinationResponse> output, int statusCode = 200) {
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
}
