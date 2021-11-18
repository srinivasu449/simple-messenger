package com.messenger.repository;

import java.util.List;

import com.messenger.entity.Messages;

public interface CustomMessageRepository {

	// Fetches recent messages for a recipient from a specific sender with a limit 100.
	List<Messages> findBySenderIdAndRecipientIdWithLimit(Integer recipientId, Integer senderId);
	
	// Fetches recent messages from all senders with a limit of 100.
	List<Messages> findByRecipientIdAndLimit(Integer recipientId);
}
