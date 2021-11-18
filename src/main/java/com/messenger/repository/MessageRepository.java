package com.messenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.messenger.entity.Messages;

@Repository
public interface MessageRepository extends CrudRepository<Messages, Integer>, CustomMessageRepository {

	// Query to fetch recent messages for a recipient from a specific sender in last 30 days.
	
	@Query("Select m FROM Messages m WHERE m.recipientId = :recipientId and  m.senderId = :senderId and m.createdAt between :thirtyDayTime and :currentTime  ORDER BY m.createdAt DESC")
	List<Messages> findBySenderIdAndRecipientIdAndCreatedAt(Integer senderId, Integer recipientId, String currentTime,
			String thirtyDayTime);

	// Query to fetch recent messages from all senders in last 30 days.

	@Query("Select m FROM Messages m WHERE m.recipientId = :recipientId and m.createdAt between :thirtyDayTime and :currentTime ORDER BY m.createdAt DESC")
	List<Messages> findByRecipientIdAndCreatedAt(Integer recipientId, String currentTime, String thirtyDayTime);

}
