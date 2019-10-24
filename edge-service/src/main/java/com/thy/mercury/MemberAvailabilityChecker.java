package com.thy.mercury;

import reactor.core.publisher.Mono;

public interface MemberAvailabilityChecker {
	Mono<MemberInfo> check(MemberInfo memberInfo);
}
