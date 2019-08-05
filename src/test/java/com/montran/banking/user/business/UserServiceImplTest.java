package com.montran.banking.user.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import com.montran.banking.user.domain.dto.UserCreateDTO;
import com.montran.banking.user.domain.dto.UserUpdateDTO;
import com.montran.banking.user.domain.entity.User;

@SpringBootTest
@WithMockUser(username = "mockUsername")
class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@BeforeEach
	public void setUp() {
		userService.create(new UserCreateDTO("Donald Trump", "White House", "dtrump@gov.com", "buildTheWall",
				"donaldtrumpbestxoxo"));
	}

	@AfterEach
	public void tearDown() {
		userService.deleteByUsername("buildTheWall");
	}

	@Test
	public void testFindAll() {
		userService.findAll().forEach(user -> {
			assertNotNull(user);
		});
	}

	@Test
	public void testFindById() {
		assertNotNull(userService.findById((long) 1));
	}

	@Test
	public void testFindByIdNonExistentIdShouldThrowException() {
		assertThrows(RuntimeException.class, () -> userService.findById((long) 9999));
	}

	@Test
	public void testFindByUsername() {
		assertNotNull(userService.findByUsername("buildTheWall"));
	}

	@Test
	public void testFindByUsernameNonExistentUsernameShouldThrowException() {
		assertThrows(RuntimeException.class, () -> userService.findByUsername("nonExistentUsername"));
	}

	@Test
	public void testCreate() {
		// no 'russianTank' before create
		assertThrows(RuntimeException.class, () -> userService.findByUsername("russianTank"));
		// create 'russianTank'
		User user = userService.create(
				new UserCreateDTO("Vladimir Putin", "Moscow", "vputin@kgb.ru", "russianTank", "mmjkk2jb3b66#!@#441"));
		assertNotNull(user);
		// a 'russianTank' after create
		assertNotNull(userService.findByUsername("russianTank"));
		// cleanup
		userService.deleteById(user.getId());
	}

	@Test
	public void testUpdate() {
		User user = userService.findByUsername("buildTheWall");
		// check fullname is 'Donald Trump'
		assertEquals("Donald Trump", user.getFullname());
		// update fullname to 'Donald Trump BOSS'
		assertNotNull(userService
				.update(new UserUpdateDTO(user.getId(), "Donald Trump BOSS", "White House", "dtrump@gov.com")));
		// check fullname is 'Donald Trump BOSS'
		assertEquals("Donald Trump BOSS", userService.findById(user.getId()).getFullname());
		// cleanup
		userService.update(new UserUpdateDTO(user.getId(), "Donald Trump", "White House", "dtrump@gov.com"));
	}

//	@Test
//	public void testDeleteById() {
//		// check user with id = 1 exists
//		User user = userService.findById((long) 1);
//		assertNotNull(user);
//		// detele user with id = 1
//		userService.deleteById((long) 1);
//		// check user with id = 1 does not exist anymore
//		assertThrows(RuntimeException.class, () -> userService.findById((long) 1));
//		// cleanup
//		userService.create(userCreateDTO);
//	}
}
