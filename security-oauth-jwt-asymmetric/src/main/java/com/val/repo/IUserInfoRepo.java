package com.val.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.val.entity.UserInfo;

public interface IUserInfoRepo extends JpaRepository<UserInfo, Long>{

	Optional<UserInfo> findByEmail(String username);

}
