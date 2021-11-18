package com.messenger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.messenger.entity.Messages;
import com.messenger.entity.Users;
import com.messenger.model.MessageRequest;
import com.messenger.service.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

//Controller class where we can define our API's 

@Controller
@Api(tags = "Simple Messenger")
public class MessengerController {

	@Autowired
	private MessageService messageService;

	// This API save the MessageRequest sent from the sender.

	@ApiOperation(value = "Send message")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found", response = String.class) })
	@PostMapping("/message")
	public ResponseEntity<Void> saveMessage(@RequestBody MessageRequest messageRequest) throws NotFoundException {
		messageService.saveMessage(messageRequest);
		ResponseEntity<Void> entity = new ResponseEntity<Void>(HttpStatus.OK);
		return entity;
	}

	// This API retrieves message of a specific recipient.
	// Query parameters functionality:
	// senderId: if present it fetches messages of specific sender, if not present
	// then fetch all senders messages.
	// limit: if boolean value true then limit to 100 messages in response, else
	// messages from last 30 days.

	@ApiOperation(value = "Get recipient messages", response = Messages.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found", response = String.class) })
	@GetMapping("/message/list/{recipientId}")
	public ResponseEntity<List<Messages>> getMessages(
			@PathVariable(value = "recipientId", required = true) Integer recipientId,
			@ApiParam(value = "If present it fetches messages of specific sender, if not present then fetch all senders messages.", required = false) @RequestParam(value = "senderId", required = false) Integer senderId,
			@ApiParam(value = "If boolean value true then limit to 100 messages in response, else messages from last 30 days.", required = false) @RequestParam(value = "limit", required = false) boolean limit)
			throws NotFoundException {
		List<Messages> response = messageService.getMessages(recipientId, senderId, limit);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//Returns all users in database
	
	@ApiOperation(value = "Get users", response = Users.class)
	@GetMapping("/users/list")
	public ResponseEntity<List<Users>> getUsers() {
		List<Users> response = messageService.getUsers();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
