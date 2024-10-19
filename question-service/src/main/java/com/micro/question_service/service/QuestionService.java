package com.micro.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.micro.question_service.dao.QuestionDao;
import com.micro.question_service.model.Question;
import com.micro.question_service.model.QuestionWrapper;


@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList(), HttpStatus.BAD_REQUEST);
	}
	

	public List<Question> getQuestionsByCategory(String cat) {
		// TODO Auto-generated method stub
		return questionDao.findByCategory(cat);
	}


	public ResponseEntity<String> addQuestion(Question question) {
		try {
			questionDao.save(question);
			return new ResponseEntity<>("Success", HttpStatus.CREATED);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
	}


	public ResponseEntity<List<Integer>> getRandomQuestions(String category, Integer numOfQuestions) {
		// TODO Auto-generated method stub
		
		Pageable pageable = PageRequest.of(0, numOfQuestions);
		
		List<Integer> questinoids = questionDao.findRandomQuestionByCategory(category, pageable);
		
		return new ResponseEntity<List<Integer>>(questinoids,HttpStatus.OK);
	}


	public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> ids) {
		
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		
		for(Integer id: ids) {
			
			Question question = questionDao.findById(id).get();
			
			QuestionWrapper qWrapper = QuestionWrapper.builder()
					.id(id)
					.question(question.getQuestion())
					.option1(question.getOption1())
					.option2(question.getOption2())
					.option3(question.getOption3())
					.option4(question.getOption4())
					.build();

			questionsForUser.add(qWrapper);
		}
		
		return new ResponseEntity<List<QuestionWrapper>>(questionsForUser,HttpStatus.OK);
		
	}
}
