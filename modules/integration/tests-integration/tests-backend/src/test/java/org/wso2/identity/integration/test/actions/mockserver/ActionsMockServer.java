/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.identity.integration.test.actions.mockserver;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import org.apache.commons.lang.StringUtils;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * Provides a mock server using WireMock for testing purposes.
 * This class starts a mock server on a specified port and sets up predefined
 * responses for POST requests to simulate various operations relation to action execution.
 */
public class ActionsMockServer {

    private WireMockServer wireMockServer;

    public void startServer() {

        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8587));
        wireMockServer.start();
    }

    public void stopServer() {

        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    public void setupStub(String url, String authMethod, String responseBody) {

        wireMockServer.stubFor(post(urlEqualTo(url))
                .withHeader("Authorization", matching(authMethod))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }

    public String getReceivedRequestPayload(String url) {

        List<LoggedRequest> requestList = wireMockServer.findAll(postRequestedFor(urlEqualTo(url)));
        if (requestList == null || requestList.isEmpty()) {
            return StringUtils.EMPTY;
        }

        return requestList.get(0).getBodyAsString();
    }

    /**
     * Create a mock server with wiremock.
     *
     * @throws Exception If an error occurred while creating the server
     */
    public void createMockServer(String mockEndpoint) throws Exception {

        wireMockServer = new WireMockServer(wireMockConfig().port(8587));

        wireMockServer.start();

        try {
            // TODO: Read the response from a file
            // TODO: Filter the response from the action type
            String jsonResponse = "{\n" +
                    "    \"actionStatus\": \"SUCCESS\",\n" +
                    "    \"operations\": [\n" +
                    "      {\n" +
                    "        \"op\": \"add\",\n" +
                    "        \"path\": \"/accessToken/scopes/-\",\n" +
                    "        \"value\": \"new_test_custom_scope_1\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"add\",\n" +
                    "        \"path\": \"/accessToken/scopes/-\",\n" +
                    "        \"value\": \"new_test_custom_scope_2\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"add\",\n" +
                    "        \"path\": \"/accessToken/scopes/-\",\n" +
                    "        \"value\": \"new_test_custom_scope_3\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"add\",\n" +
                    "        \"path\": \"/accessToken/claims/aud/-\",\n" +
                    "        \"value\": \"zzz1.com\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"add\",\n" +
                    "        \"path\": \"/accessToken/claims/aud/-\",\n" +
                    "        \"value\": \"zzz2.com\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"add\",\n" +
                    "        \"path\": \"/accessToken/claims/aud/-\",\n" +
                    "        \"value\": \"zzz3.com\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"add\",\n" +
                    "        \"path\": \"/accessToken/claims/-\",\n" +
                    "        \"value\": {\n" +
                    "          \"name\": \"custom_claim_string_1\",\n" +
                    "          \"value\": \"testCustomClaim1\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"add\",\n" +
                    "        \"path\": \"/accessToken/claims/-\",\n" +
                    "        \"value\": {\n" +
                    "          \"name\": \"custom_claim_number_1\",\n" +
                    "          \"value\": 78\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"add\",\n" +
                    "        \"path\": \"/accessToken/claims/-\",\n" +
                    "        \"value\": {\n" +
                    "          \"name\": \"custom_claim_boolean_1\",\n" +
                    "          \"value\": true\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"add\",\n" +
                    "        \"path\": \"/accessToken/claims/-\",\n" +
                    "        \"value\": {\n" +
                    "          \"name\": \"custom_claim_string_array_1\",\n" +
                    "          \"value\": [\n" +
                    "            \"TestCustomClaim1\",\n" +
                    "            \"TestCustomClaim2\",\n" +
                    "            \"TestCustomClaim3\"\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"replace\",\n" +
                    "        \"path\": \"/accessToken/scopes/7\",\n" +
                    "        \"value\": \"replaced_scope\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"replace\",\n" +
                    "        \"path\": \"/accessToken/claims/aud/-\",\n" +
                    "        \"value\": \"zzzR.com\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"replace\",\n" +
                    "        \"path\": \"/accessToken/claims/expires_in\",\n" +
                    "        \"value\": 7200\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"remove\",\n" +
                    "        \"path\": \"/accessToken/scopes/6\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"op\": \"remove\",\n" +
                    "        \"path\": \"/accessToken/claims/aud/-\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }";

            // TODO: Handle status codes related to different scenarios
            wireMockServer.stubFor(post(urlEqualTo(mockEndpoint))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody(jsonResponse)));

        } catch (Exception e) {
            throw new Exception("Error occurred while creating the mock server: " + e);
        }
    }

    /**
     * Shut down the wiremock server instance.
     */
    public void shutDownMockServer() {

        wireMockServer.stop();
    }
}
