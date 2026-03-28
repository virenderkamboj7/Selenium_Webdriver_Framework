package com.practice.testcases;

import java.security.GeneralSecurityException;
import org.testng.annotations.Test;
import com.practice.utilities.ReadConfig;
import com.practice.utilities.TokenCryptoUtils;

public class Testing {

	
	@Test
	public void tes() throws GeneralSecurityException {
		ReadConfig readConfig = new ReadConfig();
		System.out.println(System.getenv("FRAMEWORK_ENCRYPTION_KEY"));
		System.out.println(TokenCryptoUtils.decrypt(readConfig.mobile(), System.getenv("FRAMEWORK_ENCRYPTION_KEY")));
		System.out.println(TokenCryptoUtils.decrypt(readConfig.password(), System.getenv("FRAMEWORK_ENCRYPTION_KEY")));
	}
}
