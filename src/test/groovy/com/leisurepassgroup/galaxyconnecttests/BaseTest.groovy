package com.leisurepassgroup.galaxyconnecttests

import com.fasterxml.jackson.databind.ObjectMapper
import com.leisurepassgroup.galaxyconnecttests.model.response.DestinationResponse
import org.mockserver.client.MockServerClient
import org.mockserver.configuration.ConfigurationProperties
import org.mockserver.matchers.Times

import java.net.http.HttpClient

import static org.mockserver.model.HttpResponse.response
import static org.mockserver.model.HttpRequest.request
import spock.lang.Specification

abstract class BaseTest extends Specification {

	// TODO add dev host URL
	static def HOST = "mockserver.dev.passport.lpgdev.co"
	static def CONTEXT_PATH = "admin-channel-ui"
	static final HttpClient CLIENT = HttpClient.newBuilder().build();
	def mapper = new ObjectMapper()
	def adminUiChannelServerMock = new MockServerClient(
			HOST, 80
	)

	def mockDestination(List<DestinationResponse> output, int statusCode = 200) {
		def json = mapper.writeValueAsString(output)
		adminUiChannelServerMock
				.when(
						request()
								.withMethod("GET")
								.withPath("/$CONTEXT_PATH/destinations"),
						Times.exactly(1)
				)

				.respond(
						response()
								.withHeader("Content-Type", "application/json")
								.withStatusCode(statusCode)
								.withBody(json)
				)
	}

	def setup() {
		ConfigurationProperties.sslCertificateDomainName("mockserver.dev.passport.lpgdev.co")
//		ConfigurationProperties.forwardHttpsProxy("mockserver.dev.passport.lpgdev.co:80")
		ConfigurationProperties.forwardHttpProxy("mockserver.dev.passport.lpgdev.co:80")
		adminUiChannelServerMock.reset()
	}
}
