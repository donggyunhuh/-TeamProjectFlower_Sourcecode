package org.flower.controllers.user.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.flower.models.user.UserJoinService;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user/join")
@RequiredArgsConstructor
public class JoinController {

    // 회원 가입 추가 유효성 검사
    private final UserJoinValidator joinValidator;

    // 회원 정보 저장 서비스(가입, 수정)
    private final UserJoinService service;

    private final UserRepository repository;

    // 회원가입 양식 - GET / user
    @GetMapping
    public String join(@ModelAttribute UserJoin userJoin, Model model){
        commonProcess(model);

        // 달력 부분 1900년도까지 minDate 설정
        model.addAttribute("minDate", "1900-01-01");

        return "front/user/join";
    }

    @GetMapping("/checkNickNm")
    @ResponseBody
    public Map<String, Boolean> checkNickname(@RequestParam String nickNm) {
        boolean exists = repository.isNickNmExists(nickNm);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }

    // 회원가입 처리 - POST / user
    @PostMapping
    public String joinPs(@Valid UserJoin userJoin, Errors errors, Model model){
        commonProcess(model);

        joinValidator.validate(userJoin, errors);

        if(errors.hasErrors()){
            return "front/user/join";
        }

        service.join(userJoin);

        return "redirect:/user/login";          // 회원가입 성공시 -> 로그인 페이지로 이동
    }

    private void commonProcess(Model model){
        model.addAttribute("addCss", new String[]{"user/style"});
        model.addAttribute("pageTitle", "회원가입");
    }
}
