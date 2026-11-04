package com.logirock.security_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:h2:mem:securitydb;DB_CLOSE_DELAY=-1;MODE=MySQL",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
		"spring.cloud.config.enabled=false",
		"eureka.client.enabled=false",
		"JWT_SECRET_BASE64=dGVzdC1rZXktcGFyYS1sb3MtdGVzdHMtMzItYnl0ZXM=",
		"JWT_ISSUER=security-service",
		"ADMIN_USERNAME=admin_test",
		"ADMIN_PASSWORD=admin_test_123"
})
class SecurityServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
