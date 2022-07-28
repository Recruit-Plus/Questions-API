package com.RecruitPlus.QuizPlatform.controller;

import com.RecruitPlus.QuizPlatform.dto.QuestionDto;
import com.RecruitPlus.QuizPlatform.model.Question;
import com.RecruitPlus.QuizPlatform.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/recruitPlus")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    @ResponseBody
    public  List<Question> getAllQuestions( ){
        List<Question> questionsList=questionService.getAllQuestions();
        return questionsList;
    }

    @GetMapping("/questions/{questionId}")
    @ResponseBody
    public Optional<Question> getQuestionById(@PathVariable  String questionId){

        Optional<Question> questionsListById=questionService.getQuestionById(questionId);
        return questionsListById;
    }
    @PostMapping("/questions")
    @ResponseStatus(code = HttpStatus.CREATED)
    public QuestionDto saveQuestion(@RequestBody Question question)
    {

        Question savedQuestion  = questionService.saveNewQuestion(question);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(savedQuestion,questionDto);

        return questionDto;

    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/question/{id}")
    public boolean deleteQuestion(@PathVariable String id){
        questionService.deleteQuestion(id);
        return true;

    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/questions")
    public boolean deleteAllQuestion(){
        questionService.deleteAllQuestion();
        return true;

    }
}
