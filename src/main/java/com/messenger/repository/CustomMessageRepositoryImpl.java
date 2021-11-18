package com.messenger.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.messenger.entity.Messages;

//Custom repository to fetch messages from database and limiting to 100.

public class CustomMessageRepositoryImpl implements CustomMessageRepository {

	@Autowired
	private EntityManager em;

	@Override
	public List<Messages> findBySenderIdAndRecipientIdWithLimit(Integer recipientId, Integer senderId) {
		// Query to fetch recent messages for a recipient from a specific sender with a
		// limit 100.

		return em.createQuery(
				"SELECT m FROM Messages m WHERE m.senderId = :senderId and m.recipientId = :recipientId ORDER BY m.createdAt")
				.setParameter("senderId", senderId).setParameter("recipientId", recipientId).setMaxResults(100)
				.getResultList();
	}

	@Override
	public List<Messages> findByRecipientIdAndLimit(Integer recipientId) {
		// Query to fetch recent messages from all senders with a limit of 100.
		return em.createQuery("SELECT m FROM Messages m WHERE m.recipientId = :recipientId ORDER BY m.createdAt")
				.setParameter("recipientId", recipientId).setMaxResults(100).getResultList();

	}

}
