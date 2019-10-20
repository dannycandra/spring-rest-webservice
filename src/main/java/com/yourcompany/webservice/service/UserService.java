package com.yourcompany.webservice.service;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.yourcompany.webservice.model.dto.UserDto;
import com.yourcompany.webservice.model.entity.User;
import com.yourcompany.webservice.model.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Page<UserDto> getUsers(Predicate predicate, Pageable pageable) {
		// retrieve all data
		Page<User> pagedResult = userRepository.findAll(predicate, pageable);

		// convert from page<entity> to page<dto>
		Page<UserDto> dtoPage = pagedResult.map(new Function<User, UserDto>() {
			@Override
			public UserDto apply(User entity) {
				return modelMapper.map(entity, UserDto.class);
			}
		});

		return dtoPage;
	}
}
