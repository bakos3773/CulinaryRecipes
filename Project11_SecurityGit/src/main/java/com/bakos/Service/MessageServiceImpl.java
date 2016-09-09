package com.bakos.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bakos.UserDAO.MessageDAO;
import com.bakos.UserDTO.Messages;

public class MessageServiceImpl implements MessageSerivice{
	
	@Autowired
	MessageDAO messageDAO;

	@Override
	public void save(Messages message) {
		messageDAO.save(message);
	}

}
