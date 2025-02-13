package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.users.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

}