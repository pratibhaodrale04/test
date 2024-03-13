package com.val.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.val.entity.UserInfo;

public interface IUserInfoRepo extends JpaRepository<UserInfo, Long>{

}
