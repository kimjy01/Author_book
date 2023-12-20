package com.spring.author.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
    private Long id;

	@Column(name = "rate")
    private int rate;
	
	@Column(name = "rate_info")
    private String rate_info;
	
	@Column(name="rate_image")
	private String rate_image;

}
