//package com.purwadhika.montrackv2.utils;
//
//import com.purwadhika.montrackv2.users.entity.User;
//import com.purwadhika.montrackv2.users.service.UserService;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//public class AuthorizationHandler {
//
//    private final UserService userService;
//
//    public AuthorizationHandler(UserService userService) {
//        this.userService = userService;
//    }
//
//    public Long getAuthorizedUserId() {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication auth = context.getAuthentication();
//        String email = auth.getName();
//        User user = userService.findByEmail(email);
//        return user.getId();
//    }
//}
