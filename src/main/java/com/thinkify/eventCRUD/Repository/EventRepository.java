package com.thinkify.eventCRUD.Repository;

import com.thinkify.eventCRUD.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {

}
