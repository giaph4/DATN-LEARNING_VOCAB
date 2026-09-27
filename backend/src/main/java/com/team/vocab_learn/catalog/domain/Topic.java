package com.team.vocab_learn.catalog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Table chu_de — topic tree used by the public catalog (ND). */
@Entity
@Table(name = "chu_de")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cha_id")
	private Long parentId;

	@Column(name = "slug", nullable = false, length = 120)
	private String slug;

	@Column(name = "ten", nullable = false, length = 150)
	private String name;

	@Column(name = "mo_ta", columnDefinition = "TEXT")
	private String description;

	@Column(name = "dang_su_dung", nullable = false)
	private boolean active;
}
