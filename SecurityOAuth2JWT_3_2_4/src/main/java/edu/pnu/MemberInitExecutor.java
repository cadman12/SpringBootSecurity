package edu.pnu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.repository.MemberRepository;

@Component
public class MemberInitExecutor implements ApplicationRunner {

	@Autowired private MemberRepository memRepo;
	@Autowired private PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		memRepo.save(Member.builder()
				.username("user")
				.password(encoder.encode("abcd"))
				.role("ROLE_USER")
				.enabled(true)
				.build());

		memRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("abcd"))
				.role("ROLE_MANAGER")
				.enabled(true)
				.build());
		
		memRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("abcd"))
				.role("ROLE_ADMIN")
				.enabled(true)
				.build());
	}
}
