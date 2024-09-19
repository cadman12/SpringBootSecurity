package edu.pnu.config.auth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import edu.pnu.domain.Member;

public class AuthUser implements UserDetails, OAuth2User {
	private static final long serialVersionUID = 1L;

	private Member member;
	private Map<String, Object> attributes;
	
	public AuthUser(Member member) {
		this.member = member;
	}

	public AuthUser(Member member, Map<String, Object> attributes) {
		this.member = member;
		this.attributes = attributes;
	}	

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(member.getRole());
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "AuthUser [" + member + "]";
	}
}
