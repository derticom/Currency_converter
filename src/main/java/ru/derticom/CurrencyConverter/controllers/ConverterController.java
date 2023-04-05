package ru.derticom.CurrencyConverter.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.derticom.CurrencyConverter.services.ConverterService;


@Controller
public class ConverterController {

    private ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping("/")
    public String indexMapping() {
        return "/index";
    }

    @PostMapping("/")
    public String getData(HttpServletRequest request, Model model) {

        double initAmount = Double.parseDouble(request.getParameter("initAmount"));
        String initCurrent = request.getParameter("initCurrency");
        String destCurrent = request.getParameter("destCurrency");

        if (initCurrent.equals(destCurrent)) {
            model.addAttribute("initCurrent", initCurrent);
            model.addAttribute("destCurrent", destCurrent);
            model.addAttribute("initialAmount", initAmount);
            model.addAttribute("convertedAmount", initAmount);
            model.addAttribute("dateAndTime", "сегодня");
            return "result";
        }

        model.addAttribute("initCurrent", initCurrent);
        model.addAttribute("destCurrent", destCurrent);
        model.addAttribute("initialAmount", initAmount);
        model.addAttribute("convertedAmount", converterService.convert(initCurrent, initAmount, destCurrent).getConvertedAmount());
        long timestamp = converterService.convert(initCurrent, initAmount, destCurrent).getTimestamp();
        model.addAttribute("dateAndTime", converterService.getDateAndTime(timestamp));

        return "result";
    }

}
