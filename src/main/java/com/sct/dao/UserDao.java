package com.sct.dao;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sct.Entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long>{
	
   @Query("select u from UserEntity u Where u.userName = ?1")
   List<UserEntity> findUser(String user);
   
   @Query(value = "select u.id AS ID, u.pass AS PS, u.name AS NM, u.status AS ST, u.username AS UNM  from usera u Where u.userName = :user ", nativeQuery =true)
   List<Map<String,Object>> check_login(@Param("user")String user);
   
   @Transactional
   @Modifying
   @Query(value = "UPDATE usera SET opt = :otp WHERE id = :userid", nativeQuery =true)
   void updateOtp(@Param("otp")String otp, @Param("userid")String id);
}
