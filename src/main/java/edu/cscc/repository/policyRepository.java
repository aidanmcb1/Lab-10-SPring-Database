package edu.cscc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.cscc.model.Policy;

@Repository
public interface policyRepository extends CrudRepository<Policy, Long> {}