package com.thy.mercury;

import reactor.core.publisher.Mono;

import java.util.List;

public interface ClusterMembersDiscovery {
	Mono<List<MemberInfo>> discover();
	Mono<MemberInfo> thisMember();
}
