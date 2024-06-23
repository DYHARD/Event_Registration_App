package com.thinkify.eventCRUD.Repository;

import com.thinkify.eventCRUD.Model.Event;
import com.thinkify.eventCRUD.Model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByName(String userName);
}
