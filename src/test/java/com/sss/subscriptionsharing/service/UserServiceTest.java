package com.sss.subscriptionsharing.service;

import com.sss.subscriptionsharing.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void join() throws Exception {
        //given
        //when
        User user = userService.join("ldk", "1234", "이동규",
                "dk", "안녕", "ldk980130@gmail.com");
        //then
        User findUser = userService.findByLoginId("ldk").get();
        assertThat(user).isEqualTo(findUser);
    }

    @Test
    public void editInfo() throws Exception {
        //given
        User user = userService.join("ldk", "1234", "이동규",
                "dk", "안녕", "ldk980130@gmail.com");

        //when
        userService.editInfo(user.getId(), "ldk12", "123", "이동규",
                "dd", "", "ldk980130@naver.com");

        //then
        Optional<User> findUser = userService.findByLoginId("ldk12");
        assertThat(findUser.get().getNickName()).isEqualTo("dd");
    }

    @Test(expected = IllegalStateException.class)
    public void validateLoginId() throws Exception {
        //given

        //when
        User user1 = userService.join("ldk", "1234", "이동규",
                "dka", "안녕", "980130@gmail.com");
        User user2 = userService.join("ldk", "1234", "이동규",
                "dk", "안녕", "ldk980130@gmail.com");

        //then
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void validateNickName() throws Exception {
        //given

        //when
        User user1 = userService.join("ldk2", "1234", "이동규",
                "dk", "안녕", "980130@gmail.com");
        User user2 = userService.join("ldk", "1234", "이동규",
                "dk", "안녕", "ldk980130@gmail.com");

        //then
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void validateEmail() throws Exception {
        //given

        //when
        User user1 = userService.join("ldk1", "1234", "이동규",
                "dk", "안녕", "ldk980130@gmail.com");
        User user2 = userService.join("ldk", "1234", "이동규",
                "dk", "안녕", "ldk980130@gmail.com");

        //then
        fail();
    }

    @Test
    public void withdrawal() throws Exception {
        //given
        User user = userService.join("ldk", "1234", "이동규",
                "dk", "안녕", "ldk980130@gmail.com");

        //when
        userService.withdrawal(user.getId());

        //then
        Optional<User> findUser = userService.findByLoginId("ldk");
        assertThat(findUser).isEmpty();
    }
}