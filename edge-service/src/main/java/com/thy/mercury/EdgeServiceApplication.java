package com.thy.mercury;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.Validator;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableDiscoveryClient
public class EdgeServiceApplication {

	@Bean
	MemberAvailabilityChecker memberAvailabilityChecker() {
		return new HostnameResolvableAvailabilityChecker();
	}

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		return loggingFilter;
	}

	@Bean
	ClusterMembersDiscovery clusterMembersDiscovery(@Value("${vcap.application.uris:TD11442.thynet.thy.com}") List<String> uris,
													@Value("${CF_INSTANCE_INDEX:3}") int instanceIndex,
													MemberAvailabilityChecker memberAvailabilityChecker) {
		return new HazelcastHostsDiscovery(uris, instanceIndex, memberAvailabilityChecker);
	}

	@Bean
	public HazelcastRateLimiter rateLimiter(Validator defaultValidator, ClusterMembersDiscovery clusterMembersDiscovery) {
		Mono<List<MemberInfo>> members = clusterMembersDiscovery.discover();
		return new HazelcastRateLimiter(defaultValidator, "mrc", members);
	}


/*

	@Bean
	public Config hazelcastConfig() {
		Config config = new Config();
//		config.getNetworkConfig().setPort(hazelcastPort);
		config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(tru);
		config.getNetworkConfig().getJoin().getEurekaConfig()
				.setEnabled(true)
				.setProperty("self-registration", "true")
				.setProperty("namespace", "mercury-cloud");
		return config;
	}
*/

/*

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

		return builder.routes()
				.route("trophy_abuse", r-> r.path("/trophyws/**")
						.filters( f-> f.setRequestHeader("Authorization", "Basic TUVSQ1VSWUNPUkU6TXJjNzUzOTUx"))
						.uri("https://wsdev.thy.com/trophyws"))
//						.uri("http://127.0.0.1:8000/reservations"))
				.build();


*/
/*		return builder.routes()
				.route("path_route", r -> r.path("/get")
						.uri("http://httpbin.org"))
				.route("host_route", r -> r.host("*.myhost.org")
						.uri("http://httpbin.org"))
				.route("rewrite_route", r -> r.host("*.rewrite.org")
						.filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
						.uri("http://httpbin.org"))
				.route("hystrix_route", r -> r.host("*.hystrix.org")
						.filters(f -> f.hystrix(c -> c.setName("slowcmd")))
						.uri("http://httpbin.org"))
				.route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
						.filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
						.uri("http://httpbin.org"))
				.route("limit_route", r -> r
						.host("*.limited.org").and().path("/anything/**")
						.filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
						.uri("http://httpbin.org"))
				.build();*//*

	}
*/

	@Bean
	DiscoveryClientRouteDefinitionLocator discoveryRoutes(DiscoveryClient client, DiscoveryLocatorProperties properties) {
/*
		DiscoveryLocatorProperties properties = new DiscoveryLocatorProperties();
		properties.setRouteIdPrefix("lb");
		properties.setEnabled(true);
		PredicateDefinition predicate = new PredicateDefinition("predicate");

		properties.getPredicates().add(predicate);
*/
		return new DiscoveryClientRouteDefinitionLocator(client, properties);
	}

	public static void main(String[] args) {
		SpringApplication.run(EdgeServiceApplication.class, args);
	}

}
