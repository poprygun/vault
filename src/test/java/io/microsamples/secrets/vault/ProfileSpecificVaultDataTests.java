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
@ActiveProfiles("stage")
public class ProfileSpecificVaultDataTests {

	@Value("${username_stage}")
	private String username;

	@Value("${password_stage}")
	private String password;

	@Value("${username_common_stage}")
	private String usernameCommon;

	@Value("${password_common_stage}")
	private String passwordCommon;

	@Test
	public void shouldRenderAppSpecific() {
		assertThat(username, equalTo("app_stage_user"));
		assertThat(password, equalTo("app_stage_password"));
	}

	@Test
	public void shouldRenderCommon() {
		assertThat(usernameCommon, equalTo("common_stage_user"));
		assertThat(passwordCommon, equalTo("common_stage_password"));

	}

}
