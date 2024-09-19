package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

// 일반적인 데이터를 sql로 생성할 수 있으면 resource 폴더에 import.sql/data.sql 이름으로 sql문을 저장해두면 자동으로 실행된다.
// Member 데이터의 경우 암호화 프로세싱을 거쳐야 해서 여기에서 처리한다.
//
// 입력 sql 파일이 data.sql이면 
// application.properties에 spring.jpa.defer-datasource-initialization=true 설정을 추가해줘야 한다.
//
// import.sql이면 별도의 설정없이 입력된다.
// 

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
