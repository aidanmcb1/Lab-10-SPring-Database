package edu.cscc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cscc.model.Policy;
import edu.cscc.model.PolicyRequest;
import edu.cscc.repository.policyRepository;

@RestController
@RequestMapping("/v1")
public class PolicyController {
	
	@Autowired
	private policyRepository repository;
	
	@GetMapping("/policies")
	public ResponseEntity<Iterable<Policy>> getPolicies() {
		var policies = repository.findAll();
		return new ResponseEntity<Iterable<Policy>>(policies, HttpStatus.OK);
	}
	
	@GetMapping("/policies/{id}")
	public ResponseEntity<Optional<Policy>> getPolicy(@PathVariable long id) {
			if (repository.existsById(id)) {
				return new ResponseEntity<Optional<Policy>>(repository.findById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<Optional<Policy>>(HttpStatus.NOT_FOUND);
			}
		}
	
	@PostMapping("/policies")
	public ResponseEntity<Policy> postPolicy(@RequestBody PolicyRequest policy) {
		Policy created = repository.save(new Policy(policy.companyCode, policy.coverageAmount));
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@PutMapping("/policies/{id}")
	public ResponseEntity<Policy> putPolicy(@PathVariable long id, @RequestBody PolicyRequest policy) {
		if (repository.existsById(id) ) {
			var update = new Policy(id, policy.companyCode, policy.coverageAmount);
			repository.save(update);
			return new ResponseEntity<Policy>(update, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// TODO - implement to delete a policy from the database if it exists
	//        otherwise, return a not found response
	@DeleteMapping("/policies/{id}")
	public ResponseEntity<Void> deletePolicy(@PathVariable long id) {
		if (repository.existsById(id) ) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	 
}
