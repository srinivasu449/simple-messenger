package com.messenger.model;

import io.swagger.annotations.ApiModelProperty;

public class MessageRequest {

	@ApiModelProperty(value = "senderId", example = "1", required = true)
	private Integer senderId;
	@ApiModelProperty(value = "recipientId", example = "2", required = true)
	private Integer recipientId;
	@ApiModelProperty(value = "message", example = "sender message", required = true)
	private String message;

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

	@Override
	public String toString() {
		return "MessageRequest [senderId=" + senderId + ", recipientId=" + recipientId + ", message=" + message + "]";
	}

}
