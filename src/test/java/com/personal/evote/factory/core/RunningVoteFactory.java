package com.personal.evote.factory.core;

import com.personal.evote.core.model.RunningVote;

import java.util.UUID;

public class RunningVoteFactory {

    private static RunningVote runningVote;

    public static RunningVoteFactory construct() {
        runningVote = RunningVote.builder()
                .voterId(UUID.randomUUID())
                .candidateId(UUID.randomUUID())
                .build();

        return new RunningVoteFactory();
    }

    public RunningVoteFactory voterId(UUID voterId) {
        runningVote.setVoterId(voterId);
        return this;
    }

    public RunningVoteFactory candidateId(UUID candidateId) {
        runningVote.setCandidateId(candidateId);
        return this;
    }

    public RunningVote get() {
        return runningVote;
    }
}
