package com.cts.adb.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.adb.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	boolean existsByMailId(String mailId);
	Optional<Contact> findByMailId(String mailId);
	List<Contact> findAllByFullName(String fullName);

}
