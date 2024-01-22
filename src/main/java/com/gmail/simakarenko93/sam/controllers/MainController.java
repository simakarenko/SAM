package com.gmail.simakarenko93.sam.controllers;

import com.gmail.simakarenko93.sam.dto.PageCountDTO;
import com.gmail.simakarenko93.sam.dto.SAMUserDTO;
import com.gmail.simakarenko93.sam.dto.results.BadRequestResult;
import com.gmail.simakarenko93.sam.dto.results.ResultDTO;
import com.gmail.simakarenko93.sam.dto.results.SuccessResult;
import com.gmail.simakarenko93.sam.services.interf.SAMUserGeneralService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private static final int PAGE_SIZE = 5;

    private final SAMUserGeneralService samUserGeneralService;

    public MainController(SAMUserGeneralService samUserGeneralService) {
        this.samUserGeneralService = samUserGeneralService;
    }

    @GetMapping("user")
    public SAMUserDTO samUser(OAuth2AuthenticationToken auth) {
        Map<String, Object> attrs = auth.getPrincipal().getAttributes();

        String email = (String) attrs.get("email");
        String name = (String) attrs.get("name");
        String pictureUrl = (String) attrs.get("picture");

        return SAMUserDTO.of(email, name, pictureUrl);
    }

    @GetMapping("count_all")
    public PageCountDTO count(OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        return PageCountDTO.of(samUserGeneralService.count(email), PAGE_SIZE);
    }

    @GetMapping("all_list")//todo переробити метод для виводу усіх завдань
    public List<Object> allList(OAuth2AuthenticationToken auth,
                                @RequestParam(required = false, defaultValue = "0") Integer page) {
        String email = getEmail(auth);

        return samUserGeneralService.getAllLists(email,
                PageRequest.of(
                        page,
                        PAGE_SIZE,
                        Sort.Direction.DESC,
                        "id"
                )
        );
    }
    @PostMapping("delete")
//todo подумати, чи потрібен цей метод в кожному контроллері чи можна спільний засунути в головний контроллер
    public ResponseEntity<ResultDTO> delete(@RequestParam(name = "toDelete[]", required = false) Long[] idList) {
        samUserGeneralService.delete(Arrays.asList(idList));
        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDTO> handleException() {
        return new ResponseEntity<>(new BadRequestResult(), HttpStatus.BAD_REQUEST);
    }

    private String getEmail(OAuth2AuthenticationToken auth) {
        return (String) auth.getPrincipal().getAttributes().get("email");
    }
}
