package com.ssafy.home.qna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.qna.dto.QnaDto;
import com.ssafy.home.qna.service.QnaService;
import com.ssafy.home.response.ResponseDto;

@RestController
@RequestMapping("/qna")
public class QnaController {
	public static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnaService qnaService;
	
	@PostMapping
	public ResponseEntity<ResponseDto> writeQuestion(@RequestBody QnaDto qnaDto) throws Exception {
		ResponseDto responseDto = new ResponseDto();
		int result = -1;
		if(qnaDto.getCategory().equals("q")) {
			result = qnaService.writeQuestion(qnaDto);
		} else {
			result = qnaService.writeAnswer(qnaDto);
		}
		if(result != 0) { 
			responseDto.setMsg(SUCCESS);
			return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
		} else {
			responseDto.setMsg(FAIL);
			return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
