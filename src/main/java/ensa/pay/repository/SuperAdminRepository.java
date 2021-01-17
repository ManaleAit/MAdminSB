package ensa.pay.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ensa.pay.domain.Admin;

public interface SuperAdminRepository  extends MongoRepository<Admin, Long> {

}

