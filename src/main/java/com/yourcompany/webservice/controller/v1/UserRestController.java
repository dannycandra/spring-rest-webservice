package com.yourcompany.webservice.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.yourcompany.webservice.error.exception.UnauthorizedException;
import com.yourcompany.webservice.model.dto.UserDto;
import com.yourcompany.webservice.model.entity.QUser;
import com.yourcompany.webservice.model.entity.User;
import com.yourcompany.webservice.service.UserService;
import com.yourcompany.webservice.service.security.ExtraInfoService;

import io.swagger.annotations.Api;

@Api(tags = "Webservice - user endpoint")
@RestController
@RequestMapping("v1/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private ExtraInfoService extraInfoService;

	@GetMapping
	public Page<UserDto> getUsers(Pageable pageable, @QuerydslPredicate(root = User.class) Predicate predicate) {
		if (predicate == null) {
			predicate = QUser.user.userId.isNotNull();
		}

		return userService.getUsers(predicate, pageable);
	}

	@GetMapping("/{userId}/authentication")
	public Authentication getAuthentication(@PathVariable(value = "userId") Long userId,
			Authentication authentication) {

		// retrieve userId from token
		Long userIdFromToken = extraInfoService.getUserId(authentication);

		// checking if user is allowed to retrieve data
		if (userIdFromToken.equals(userId)) {
			return authentication;
		}

		throw new UnauthorizedException();
	}
}
