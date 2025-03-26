package com.example.identityservice.entity;

import com.example.identityservice.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tbl_users")
public class UserEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "first_name", nullable = false,length = 50)
	String firstName;

	@Column(name = "last_name", nullable = false)
	String lastName;

	@Column(name = "email", nullable = false)
	String email;

	@Column(name = "full_name", nullable = false)
	String fullName;

	@Column(name = "phone_number")
	String phoneNumber;

	@Column(name = "avt_url")
	String avtUrl;

	Boolean gender;

}
