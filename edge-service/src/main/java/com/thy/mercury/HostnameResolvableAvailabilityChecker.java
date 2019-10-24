package com.thy.mercury;

import reactor.core.publisher.Mono;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class HostnameResolvableAvailabilityChecker implements MemberAvailabilityChecker {

	@Override
	public Mono<MemberInfo> check(MemberInfo memberInfo) {
		try {
			Inet4Address.getByName(memberInfo.getHost());
			return Mono.just(memberInfo);
		}
		catch (UnknownHostException e) {
			return Mono.error(e);
		}
	}
}
