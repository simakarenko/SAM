package com.gmail.simakarenko93.sam.config;

import com.gmail.simakarenko93.sam.dto.*;
import com.gmail.simakarenko93.sam.model.markers.PlannedCategory;
import com.gmail.simakarenko93.sam.model.markers.RegularCategory;
import com.gmail.simakarenko93.sam.model.markers.TypeDB;
import com.gmail.simakarenko93.sam.model.markers.UnexpectedCategory;
import com.gmail.simakarenko93.sam.services.interf.SAMUserGeneralService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class AuthHandler implements AuthenticationSuccessHandler {

    private final SAMUserGeneralService samUserGeneralService;

    public AuthHandler(SAMUserGeneralService samUserGeneralService) {
        this.samUserGeneralService = samUserGeneralService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User user = token.getPrincipal();

        Map<String, Object> attributes = user.getAttributes();

        SAMUserDTO samUserDTO = SAMUserDTO.of(
                (String) attributes.get("email"),
                (String) attributes.get("name"),
                (String) attributes.get("picture")
        );

        samUserGeneralService.addUser(samUserDTO,
                testIncomeList(),
                testPlannedSpendingList(),
                testRegularSpendingList(),
                testSavingList(),
                testUnexpectedSpendingList());

        httpServletResponse.sendRedirect("/");
    }

    private List<IncomeDTO> testIncomeList() {
        List<IncomeDTO> result = Arrays.asList(
                IncomeDTO.of(TypeDB.PLANNED, new Date(), "Частка за просування додатку.", null, 20),
                IncomeDTO.of(TypeDB.REGULAR, new Date(), "Заробітня плата", "Компанія Альф", 1000)
        );
        return result;
    }

    private List<SavingDTO> testSavingList() {
        List<SavingDTO> result = Arrays.asList(
                SavingDTO.of(TypeDB.REGULAR, new Date(), "Відсоток від зп", "10%", 100),
                SavingDTO.of(TypeDB.UNEXPECTED, new Date(), "Залишок на кінець місяця", null, 50)
        );
        return result;
    }

    private List<PlannedSpendingDTO> testPlannedSpendingList() {
        List<PlannedSpendingDTO> result = Arrays.asList(
                PlannedSpendingDTO.of(PlannedCategory.ZOO, new Date(), "Спальне місце Блексі",
                        "На новий рік", 1000),
                PlannedSpendingDTO.of(PlannedCategory.FOR_HOME, new Date(), "Зелений диван",
                        null, 10000)
        );
        return result;
    }

    private List<RegularSpendingDTO> testRegularSpendingList() {
        List<RegularSpendingDTO> result = Arrays.asList(
                RegularSpendingDTO.of(RegularCategory.FOOD, new Date(), "Закуп продуктів",
                        "Стандартне меню", 2000),
                RegularSpendingDTO.of(RegularCategory.TOYS, new Date(), "Тижневий ліміт Мирона",
                        null, 1000)
        );
        return result;
    }

    private List<UnexpectedSpendingDTO> testUnexpectedSpendingList() {
        List<UnexpectedSpendingDTO> result = Arrays.asList(
                UnexpectedSpendingDTO.of(UnexpectedCategory.TAXI, new Date(), "Таксі в Епіцентр",
                        "Вантажне таксі", 300),
                UnexpectedSpendingDTO.of(UnexpectedCategory.HOLIDAYS, new Date(), "Тактильний зоопарк",
                        null, 1000)
        );
        return result;
    }
}
