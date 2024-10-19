package com.micro.question_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.question_service.model.Question;
import com.micro.question_service.model.QuestionWrapper;
import com.micro.question_service.model.Response;
import com.micro.question_service.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@GetMapping("all_questions")
	public ResponseEntity<List<Question>> getAllQuestion() {
		return questionService.getAllQuestions();
	}
	
	
	@GetMapping("category/{category}")
	public List<Question> getQuestionsByCategory(@PathVariable String category){
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("addquestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	//Method to generate QuizQuestions, This method will provide the question id's only
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsIdsQuiz(
			@RequestParam String category, @RequestParam Integer numOfQuestions){
				
		
		return questionService.getRandomQuestions(category,numOfQuestions);
		
	}
	
	//Method to get questions using list of question id's
	
	@PostMapping("quiz/questions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> ids){
		return questionService.getQuestionsByIds(ids);
	}
	
	//Method to calculate score
	
	@PostMapping("score")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		
		return questionService.calculateScore(responses);
		
	}
}
