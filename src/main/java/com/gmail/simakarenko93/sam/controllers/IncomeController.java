package com.gmail.simakarenko93.sam.controllers;

import com.gmail.simakarenko93.sam.dto.IncomeDTO;
import com.gmail.simakarenko93.sam.dto.PageCountDTO;
import com.gmail.simakarenko93.sam.dto.results.BadRequestResult;
import com.gmail.simakarenko93.sam.dto.results.ResultDTO;
import com.gmail.simakarenko93.sam.dto.results.SuccessResult;
import com.gmail.simakarenko93.sam.services.interf.GeneralService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class IncomeController {
    private static final int PAGE_SIZE = 5;
    private final GeneralService<IncomeDTO> generalService;

    public IncomeController(GeneralService<IncomeDTO> generalService) {
        this.generalService = generalService;
    }

    @GetMapping("count_income")//todo чи потрібен цей метод саме цьому контролеру
    public PageCountDTO count(OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        return PageCountDTO.of(generalService.count(email), PAGE_SIZE);
    }

    @GetMapping("list_all_income")
    public List<IncomeDTO> listAll(OAuth2AuthenticationToken auth,
                                   @RequestParam(required = false, defaultValue = "0") Integer page) {
        String email = getEmail(auth);

        return generalService.getList(email,
                PageRequest.of(
                        page,
                        PAGE_SIZE,
                        Sort.Direction.DESC,
                        "id"
                )
        );
    }

    @PostMapping("add_income")
    public ResponseEntity<ResultDTO> addIncome(OAuth2AuthenticationToken auth,
                                               @RequestBody IncomeDTO incomeDTO) {
        String email = getEmail(auth);
        generalService.addObj(email, incomeDTO);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    //@PostMapping("delete")
//todo подумати, чи потрібен цей метод в кожному контроллері чи можна спільний засунути в головний контроллер
    //public ResponseEntity<ResultDTO> delete(@RequestParam(name = "toDelete[]", required = false) Long[] idList) {
    //  generalService.delete(Arrays.asList(idList));
    //return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    //}

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDTO> handleException() {
        return new ResponseEntity<>(new BadRequestResult(), HttpStatus.BAD_REQUEST);
    }

    private String getEmail(OAuth2AuthenticationToken auth) {
        return (String) auth.getPrincipal().getAttributes().get("email");
    }
}
