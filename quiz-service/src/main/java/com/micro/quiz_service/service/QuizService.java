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
		/*
		 * // TODO Auto-generated method stub
		 * 
		 * Quiz quiz = quizDao.findById(id).get();
		 * 
		 * List<Question> questionsFromDbList = quiz.getQuestions();
		 * 
		 * List<QuestionWrapper> questionsForUser = new ArrayList<>();
		 * 
		 * for(Question q: questionsFromDbList) {
		 * 
		 * QuestionWrapper qWrapper = QuestionWrapper.builder() .id(q.getId())
		 * .question(q.getQuestion()) .option1(q.getOption1()) .option2(q.getOption2())
		 * .option3(q.getOption3()) .option4(q.getOption4()) .build();
		 * questionsForUser.add(qWrapper); }
		 * 
		 * return new
		 * ResponseEntity<List<QuestionWrapper>>(questionsForUser,HttpStatus.OK);
		 */
		return null;
	}

	public ResponseEntity<Integer> quizResult(Integer id, List<Response> responses) {
		// TODO Auto-generated method stub
		/*
		 * Integer score = 0;
		 * 
		 * Optional<Quiz> quiz = quizDao.findById(id); List<Question>
		 * questionsFromDbList = quiz.get().getQuestions();
		 * System.out.println(questionsFromDbList); int i = 0; for(Response res:
		 * responses) {
		 * 
		 * if(res.getResponse().equals(questionsFromDbList.get(i).getCorrectAnswer())) {
		 * score++; } i++; }
		 * 
		 * return new ResponseEntity<Integer>(score,HttpStatus.OK);
		 */
		
		return null;
	}

}
