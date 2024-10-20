package com.micro.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.micro.quiz_service.dao.QuizDao;
import com.micro.quiz_service.feign.QuizInterface;
import com.micro.quiz_service.model.Question;
import com.micro.quiz_service.model.QuestionWrapper;
import com.micro.quiz_service.model.Quiz;
import com.micro.quiz_service.model.Response;


@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface quizInterface;

		
	public ResponseEntity<String> createQuiz(String category, int noOfQuestions, String title) {
		List<Integer> questions = quizInterface.getQuestionsIdsQuiz(category, noOfQuestions).getBody();			
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		
		quizDao.save(quiz);
		
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		List<QuestionWrapper> quizQuestions = quizInterface.getQuestionsForQuiz(quizDao.findById(id).get().getQuestionIds()).getBody();
		 return new ResponseEntity<List<QuestionWrapper>>(quizQuestions,HttpStatus.OK);
	}

	public ResponseEntity<Integer> quizResult(List<Response> responses) {
		
		Integer score = quizInterface.getScore(responses).getBody();
		
		return new ResponseEntity<Integer>(score,HttpStatus.OK);

	}

}
