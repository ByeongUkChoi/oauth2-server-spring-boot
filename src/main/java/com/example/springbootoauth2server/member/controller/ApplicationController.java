package com.example.springbootoauth2server.member.controller;

import com.example.springbootoauth2server.OAuth.dto.ClientDto;
import com.example.springbootoauth2server.OAuth.domain.ClientEntity;
import com.example.springbootoauth2server.member.dto.ApplicationDto;
import com.example.springbootoauth2server.member.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 클라이언트 등록 및 설정 컨트롤러
 */
@Controller
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ModelMapper modelMapper;

    /**
     * 대시보드
     * @return
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<ClientEntity> clients = applicationService.getApplications();
        List<ClientDto> clientDtoPage = clients
                .stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
        model.addAttribute("clients", clientDtoPage);
        return "member/application/dashboard";
    }
    /**
     * 앱 등록 페이지
     * @return
     */
    @GetMapping("/regist")
    public String registForm() {
        return "member/application/registForm";
    }
    /**
     * 앱 등록
     * @return
     */
    @PostMapping("/regist")
    public String regist(HttpServletRequest request,
                         @ModelAttribute ApplicationDto applicationDto,
                         Model model) {
        Principal userPrincipal = request.getUserPrincipal();
        applicationDto.setUsername(userPrincipal.getName());
        ClientEntity clientEntity = applicationService.createApplication(applicationDto);
        ClientDto clientDto = modelMapper.map(clientEntity, ClientDto.class);

        model.addAttribute("client", clientDto);
        return "member/application/registResult";
    }
}
