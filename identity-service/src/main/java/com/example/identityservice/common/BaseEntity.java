package com.example.identityservice.common;

import java.time.LocalDateTime;

import com.example.identityservice.entity.AccountEntity;
import com.example.identityservice.utils.SecurityUtils;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
	@Column(name = "create_date")
	private LocalDateTime createdAt;

	@Column(name = "create_By")
	private String createdBy;
	@Column(name = "modifier_date")
	private LocalDateTime updatedAt;

	@Column(name = "modifier_by")
	private String updatedBy;

	@PrePersist
	public void prePersist() {
		try {
			com.example.identityservice.entity.AccountEntity accountEntity = com.example.identityservice.utils.SecurityUtils.getCurrentAccount();
			this.createdBy = accountEntity.getUsername();
			this.updatedBy = accountEntity.getUsername();
			this.createdAt = LocalDateTime.now();
			this.updatedAt = LocalDateTime.now();
		} catch (Exception e) {
			this.createdBy = "Unknow User";
			this.updatedBy = "Unknow User";
			this.createdAt = LocalDateTime.now();
			this.updatedAt = LocalDateTime.now();
		}

	}

	@PreUpdate
	public void preUpdate() {
		try {
			AccountEntity accountEntity = SecurityUtils.getCurrentAccount();
			this.updatedBy = accountEntity.getUsername();
			this.updatedAt = LocalDateTime.now();
		} catch (Exception e) {
			this.updatedBy = "Unknow User";
			this.updatedAt = LocalDateTime.now();
		}
	}

}
