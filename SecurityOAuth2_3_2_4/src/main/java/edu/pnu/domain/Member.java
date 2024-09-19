package edu.pnu.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
	@Id
	private String username;
	private String password;
	private String role;
	private Boolean enabled;

	public Collection<GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(role);
	}

	@Override
	public String toString() {
		return "Member [username=" + username + ", password=[PROTECTED], role=" + role + ", enabled=" + enabled + "]";
	}
}
