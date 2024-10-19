package com.micro.question_service.model;

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
