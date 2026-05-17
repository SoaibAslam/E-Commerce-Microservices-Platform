package com.ekartgateway.filter;

import com.ekartgateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		String path = exchange.getRequest().getURI().getPath();

		// ✅ PUBLIC ROUTES (no auth required)
		if (isPublicPath(path)) {
			return chain.filter(exchange);
		}

		String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

		// ❌ Missing or invalid header
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}

		String token = authHeader.substring(7);

		// ❌ Invalid token
		if (!jwtUtil.validateToken(token)) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}

		// ✅ Token valid → continue
		return chain.filter(exchange);
	}

	// ✅ Public path check
	private boolean isPublicPath(String path) {
		return

		// 🔓 Auth APIs
		path.startsWith("/api/auth") ||

		// 🔓 Swagger UI
				path.startsWith("/swagger-ui") || path.startsWith("/swagger-ui.html")
				|| path.startsWith("/swagger-resources") ||

				// 🔓 OpenAPI docs (important)
				path.startsWith("/v3/api-docs") ||

				// 🔓 Gateway aggregated docs (FIX)
				path.contains("/v3/api-docs");
	}

	@Override
	public int getOrder() {
		return -1; // Highest priority
	}
}