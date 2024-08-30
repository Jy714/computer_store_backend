package com.Jy714.computerStore;

import com.Jy714.computerStore.utils.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ComputerStoreApplicationTests {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private Jwt jwt;

	@Test
	void getConnection() throws SQLException {
		System.out.println(dataSource.getConnection());
	}

	@Test
	void testGenJwt(){
		Map<String, Object> claims = new HashMap<>();
		claims.put("username","Jeremy");
		claims.put("uid",3);

		String token = jwt.genJwt(claims);

		System.out.println(token);
	}

	@Test
	void testParseJwt(){

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjMsInVzZXJuYW1lIjoiSmVyZW15IiwiZXhwIjoxNzI0ODE5ODc3fQ.DpFPB5JGLf0wGu5lKodQE93RG7Nm3masL9dkR9kovrc";

		Claims body = jwt.parseJwt(token);

		System.out.println(body);
	}

}
