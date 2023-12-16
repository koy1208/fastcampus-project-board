package com.fastcampus.projectboard.config;

import com.fastcampus.projectboard.domain.UserAccount;
import com.fastcampus.projectboard.repository.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

@Import(SecurityConfig.class)
public class TestSecurityConfig {
    @MockBean
    private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void SecuritySetUp() {
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "testAccount",
                "pw",
                "test@email.com",
                "testNickname",
                "test memo"
        )));
    }
}
