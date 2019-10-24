package com.thy.mercury;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HazelcastHostsDiscovery implements ClusterMembersDiscovery {

    public HazelcastHostsDiscovery(List<String> uris, int instanceIndex, MemberAvailabilityChecker memberAvailabilityChecker) {

    }

    @Override
    public Mono<List<MemberInfo>> discover() {
        List<MemberInfo> memberInfos = new ArrayList<>();
/*
        Set<Member> members = this.hz.getCluster().getMembers();
        for (Member member : members) {
            MemberInfo memberInfo = new MemberInfo(member.getAddress().getHost(), member.getAddress().getPort());
            memberInfos.add(memberInfo);
        }
*/
        memberInfos.add(new MemberInfo("10.26.15.68", 5701));
        memberInfos.add(new MemberInfo("10.26.15.68", 5702));
        return Mono.just(memberInfos);
    }

    @Override
    public Mono<MemberInfo> thisMember() {
        return null;
    }
}
