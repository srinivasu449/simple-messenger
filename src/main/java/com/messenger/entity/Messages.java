package com.messenger.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//Describes messages table

@Entity
@Table(name = "messages")
@NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m")
public class Messages implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Integer messageId;

	@Column(name = "sender_id")
	private Integer senderId;

	@Column(name = "recipient_id")
	private Integer recipientId;

	private String message;

	@Column(name = "created_at", nullable = false, updatable = false)
	private String createdAt;

	public Messages() {

	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Integer recipientId) {
		this.recipientId = recipientId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Messages(Integer senderId, Integer recipientId, String message, String createdAt) {
		super();
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.message = message;
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Messages [messageId=" + messageId + ", senderId=" + senderId + ", recipientId=" + recipientId + ", message=" + message
				+ ", createdAt=" + createdAt + "]";
	}

}
