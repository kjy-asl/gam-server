package com.gam.api.controller;

import com.gam.api.common.message.*;
import com.gam.api.common.ApiResponse;
import com.gam.api.common.util.AuthCommon;
import com.gam.api.dto.user.request.UserWorkEditRequestDTO;
import com.gam.api.service.user.UserService;
import com.gam.api.dto.user.request.UserExternalLinkRequestDto;
import com.gam.api.dto.user.request.UserProfileUpdateRequestDto;
import com.gam.api.dto.user.request.UserScrapRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/scrap")
    ResponseEntity<ApiResponse> scrapUser(Principal principal, @RequestBody UserScrapRequestDto request){
        val userId = AuthCommon.getUser(principal);
        val response = userService.scrapUser(userId, request);
        if (response.userScrap()){
            return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_USER_SCRAP.getMessage(),response));
        }
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_USER_DELETE_SCRAP.getMessage(),response));
    }

    @PatchMapping("/link")
    ResponseEntity<ApiResponse> updateExternalLink(Principal principal, @RequestBody UserExternalLinkRequestDto request){
        val userId = AuthCommon.getUser(principal);
        val response = userService.updateExternalLink(userId, request);

        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_UPDATE_EXTERNAL_LINK.getMessage(), response));
    }

    @GetMapping("/my/profile")
    ResponseEntity<ApiResponse> getMyProfile(Principal principal){
        val userId = AuthCommon.getUser(principal);
        val response = userService.getMyProfile(userId);

        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_MY_PROFILE.getMessage(), response));
    }

    @PatchMapping("/introduce")
    ResponseEntity<ApiResponse> updateMyProfile(Principal principal, @RequestBody UserProfileUpdateRequestDto request){
        val userId = AuthCommon.getUser(principal);
        val response = userService.updateMyProfile(userId, request);

        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_UPDATE_EXTERNAL_LINK.getMessage(), response));
    }

    @PatchMapping("/work")
    public ResponseEntity<ApiResponse> updateWork(Principal principal, @RequestBody UserWorkEditRequestDTO request){
        val userId = AuthCommon.getUser(principal);
        val response = userService.updateWork(userId, request);

        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_UPDATE_WORK.getMessage(), response));
    }

}
