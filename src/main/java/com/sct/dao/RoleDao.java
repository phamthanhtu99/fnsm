package com.sct.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sct.Entity.RoleEntity;

public interface RoleDao extends JpaRepository<RoleEntity, Long>{
	
	@Query(value = "SELECT r.id AS ID ,r.name AS NM FROM user_role u join role r  on u.role_id = r.id WHERE u.user_id =:id",  nativeQuery =true)
	List<Map<String, Object>> findroleByUserID(@Param("id")String id);
}
