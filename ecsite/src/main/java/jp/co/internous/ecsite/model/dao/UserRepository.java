package jp.co.internous.ecsite.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.internous.ecsite.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByUserNameAndPassword(String userName, String password);

}
