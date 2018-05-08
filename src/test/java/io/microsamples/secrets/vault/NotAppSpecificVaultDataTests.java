package io.microsamples.secrets.vault;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotAppSpecificVaultDataTests {

	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;

	@Value("${username_common}")
	private String usernameCommon;

	@Value("${password_common}")
	private String passwordCommon;

	@Test
	public void shouldRenderAppSpecific() {
		assertThat(username, equalTo("app_user"));
		assertThat(password, equalTo("app_password"));
	}

	@Test
	public void shouldRenderCommon() {
		assertThat(usernameCommon, equalTo("common_user"));
		assertThat(passwordCommon, equalTo("common_password"));

	}

}
