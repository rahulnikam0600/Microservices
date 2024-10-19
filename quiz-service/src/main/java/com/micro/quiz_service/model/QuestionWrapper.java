package com.micro.quiz_service.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionWrapper {

	private Integer id;
	
	private String question;
	
	private String option1;
	
	private String option2;
	
	private String option3;
	
	private String option4;
	
}
