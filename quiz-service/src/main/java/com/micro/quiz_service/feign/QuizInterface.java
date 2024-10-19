package com.micro.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.micro.quiz_service.model.QuestionWrapper;
import com.micro.quiz_service.model.Response;


@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	//Method to generate QuizQuestions, This method will provide the question id's only
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsIdsQuiz(
			@RequestParam String category, @RequestParam Integer numOfQuestions);
	
	//Method to get questions using list of question id's
	
	@PostMapping("question/quiz/questions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> ids);
	
	//Method to calculate score
	
	@PostMapping("question/score")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
	
}
