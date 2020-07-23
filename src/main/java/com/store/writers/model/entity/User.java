package com.store.writers.model.entity;

import com.store.writers.model.Role;
import com.store.writers.security.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name="UserInfo")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 255)
	private String firstname;

	@NotBlank
	@Size(max = 255)
	private String lastname;

	@NotBlank
	@Size(max = 255)
	private String username;

	@Enumerated (EnumType.STRING)
	private Role role;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private ShoppingCart shoppingCart;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserShipping> userShippingList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserPayment> userPaymentList;

	@NotNull
	@Size(min = 4, max = 255)
	private String password;

	@Transient
	private String passwordConfirm;

	@NotEmpty
	@Email
	@Size(max = 255)
	@Column(unique = true)
	private String email;

	@Size(max = 20, message = "{store.phone.number.invalid}")
	// A simple phone number checker, allowing an optional international prefix
	// plus a variable number of digits that could be separated by dashes or
	// spaces
	@Pattern(regexp = "^(\\+\\d+)?([ -]?\\d+){4,14}$", message = "{bakery.phone.number.invalid}")
	private String phoneNumber;

	private boolean enable = true;

	public User() {
		// An empty constructor is needed for all beans
	}

	public Long getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean locked) {
		this.enable = enable;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new Authority(role.name()));
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public List<UserShipping> getUserShippingList() {
		return userShippingList;
	}

	public void setUserShippingList(List<UserShipping> userShippingList) {
		this.userShippingList = userShippingList;
	}

	public List<UserPayment> getUserPaymentList() {
		return userPaymentList;
	}

	public void setUserPaymentList(List<UserPayment> userPaymentList) {
		this.userPaymentList = userPaymentList;
	}
}
