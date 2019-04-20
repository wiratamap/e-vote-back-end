package com.personal.evote.lookup.candidatecategory.repository;

import com.personal.evote.lookup.candidatecategory.model.CandidateCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateCategoryRepository extends JpaRepository<CandidateCategory, UUID> {
}
