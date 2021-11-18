package com.messenger.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.entity.Messages;
import com.messenger.entity.Users;
import com.messenger.model.MessageRequest;
import com.messenger.repository.MessageRepository;
import com.messenger.repository.UserRepository;

import javassist.NotFoundException;

//Service class which consists business logic.

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";

	// Simple method to save the message posted.

	public void saveMessage(MessageRequest messageRequest) throws NotFoundException {

		Optional<Users> sender = userRepository.findById(messageRequest.getSenderId());
		if (!sender.isPresent()) {
			throw new NotFoundException("Not a valid user.");
		}

		Optional<Users> recipient = userRepository.findById(messageRequest.getRecipientId());
		if (!recipient.isPresent()) {
			throw new NotFoundException("Not a valid Recipient.");
		}

		Messages message = new Messages();
		message.setMessage(messageRequest.getMessage());
		message.setRecipientId(messageRequest.getRecipientId());
		message.setSenderId(messageRequest.getSenderId());
		message.setCreatedAt(getCurrentTime());

		messageRepository.save(message);

	}

	// Simple method to fetch messages for a recipient depends on the query
	// parameters.

	public List<Messages> getMessages(Integer recipientId, Integer senderId, boolean limit) throws NotFoundException {
		Optional<Users> recipient = userRepository.findById(recipientId);
		if (!recipient.isPresent()) {
			throw new NotFoundException("Not a valid Recipient.");
		}
		List<Messages> messages = null;

		if (senderId != null & limit) {
			// Fetches recent messages for a recipient from a specific sender with a limit
			// 100.
			messages = messageRepository.findBySenderIdAndRecipientIdWithLimit(recipientId, senderId);
		} else if (senderId != null) {
			// Fetches recent messages for a recipient from a specific sender in last 30
			// days.
			messages = messageRepository.findBySenderIdAndRecipientIdAndCreatedAt(senderId, recipientId,
					getCurrentTime(), getThirtyDayTime());
		} else if (limit) {
			// Fetches recent messages from all senders with a limit of 100.
			messages = messageRepository.findByRecipientIdAndLimit(recipientId);
		} else {
			// Fetches recent messages from all senders in last 30 days.
			messages = messageRepository.findByRecipientIdAndCreatedAt(recipientId, getCurrentTime(),
					getThirtyDayTime());
		}

		return messages;
	}
	
	public List<Users> getUsers() {
		List<Users> users =  userRepository.findAllUsers();
		return users;
	}

	// Simple helper method to generate latest timestamp.

	private String getCurrentTime() {
		String timeStamp = new SimpleDateFormat(dateFormat).format(new Date());
		return timeStamp;
	}

	// Simple helper method to generate last 30 day timestamp.

	private String getThirtyDayTime() {
		try {
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, -30);
			String timeStamp = new SimpleDateFormat(dateFormat).format(cal.getTime());
			return timeStamp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
