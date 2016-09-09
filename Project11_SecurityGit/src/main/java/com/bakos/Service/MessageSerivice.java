package com.bakos.Service;

import org.springframework.stereotype.Service;

import com.bakos.UserDTO.Messages;

@Service
public interface MessageSerivice {

	public void save(Messages message);
}
