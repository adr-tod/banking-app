package com.montran.banking.user.business;

import static org.junit.jupiter.api.Assertions.*;

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
	@WithMockUser(username = "someUsername")
	public void testCreate() {
		// no 'russianTank' before test
		assertThrows(RuntimeException.class, () -> userService.findByUsername("russianTank"));
		// create 'russianTank'
		User user = userService.create(
				new UserCreateDTO("Vladimir Putin", "Moscow", "vputin@kgb.ru", "russianTank", "mmjkk2jb3b66#!@#441"));
		assertNotNull(user);
		// a 'russianTank' after test
		assertNotNull(userService.findByUsername("russianTank"));
		// cleanup
		userService.deleteById(user.getId());
	}

	@Test
	@WithMockUser(username = "someUsername")
	public void testUpdate() {
		User user = userService.findByUsername("buildTheWall");
		// check fullname is 'Donald Trump'
		assertEquals("Donald Trump", user.getFullname());
		// update fullname to 'Donald Trump BOSS'
		assertNotNull(userService.update(new UserUpdateDTO((long) 1, "Dr. Kamryn Koch",
				"7477 Sipes Stream Suite 714\nRileymouth, PA 94788-9629", "ondricka.madisyn@example.com")));
		// check fullname is 'Dr. Kamryn Koch UPDATED'
		assertEquals("Dr. Kamryn Koch", userService.findById((long) 1).getFullname());
		// cleanup
		userService.update(new UserUpdateDTO((long) 1, "Dr. Kamryn Koch",
				"7477 Sipes Stream Suite 714\nRileymouth, PA 94788-9629", "ondricka.madisyn@example.com"));
	}

	@Test
	@WithMockUser(username = "someUsername")
	public void testDeleteById() {
//		// check user with id = 1 exists
//		User user = userService.findById((long) 1);
//		assertNotNull(user);
//		// detele user with id = 1
//		userService.deleteById((long) 1);
//		// check user with id = 1 does not exist anymore
//		assertThrows(RuntimeException.class, () -> userService.findById((long) 1));
//		// cleanup
//		userService.create(userCreateDTO)
	}
}
