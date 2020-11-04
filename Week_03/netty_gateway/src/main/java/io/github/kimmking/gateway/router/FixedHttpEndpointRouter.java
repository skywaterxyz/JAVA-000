package io.github.kimmking.gateway.router;

import java.util.List;

import org.apache.commons.codec.binary.StringUtils;

public class FixedHttpEndpointRouter implements HttpEndpointRouter {

	@Override
	public String route(List<String> endpoints) {

		if (endpoints == null || endpoints.isEmpty()) {
			return "http://localhost:8080";
		}
		String endpoint = endpoints.get(0);
		if (StringUtils.equals(endpoint, "test1")) {
			return "http://localhost:8801";
		} else {
			return "http://localhost:8802";
		}
	}

}
