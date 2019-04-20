package com.personal.evote.core.repository;

import com.personal.evote.core.model.RunningVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RunningVoteRepository extends JpaRepository<RunningVote, UUID> {
    Optional<List<RunningVote>> findAllByCandidateCategoryId(UUID candidateCategoryId);
}
