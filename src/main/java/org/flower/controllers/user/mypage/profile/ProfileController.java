package org.flower.controllers.user.mypage.profile;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/mypage/profile/profilePage")
public class ProfileController {
    @GetMapping
    public String main(){
        return "/front/mypage/profile/profilePage";
    }


}
