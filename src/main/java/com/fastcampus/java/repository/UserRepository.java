package com.fastcampus.java.repository;

import com.fastcampus.java.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // QueryMethod : query문을 method형태로 작성함.
    // select 검색
    // select * from user where account = ?;
    // findBy : where절
    //Optional<User> findByAccount(String account);
    //Optional<User> findByEmail(String email);

    // 여러 가지를 한 번에 검색
    // select * from user where account = ? and email = ?;
    //Optional<User> findByAccountAndEmail(String account, String email);

    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
