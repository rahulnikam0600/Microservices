package com.micro.quiz_service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizDto {

	private String category;
	private int noOfQuestions;
	private String title;
}
