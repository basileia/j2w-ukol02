package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class QuoteController {

    @GetMapping("/")
    public ModelAndView showQuote() throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("randomQuote", getRandomQuote("citaty.txt"));
        return modelAndView;

    }

    private static String getRandomQuote(String resource) throws IOException {
        List<String> quotes = getQuotes(resource);
        Random random = new Random();
        return quotes.get(random.nextInt(quotes.size()));
    }

    private static List<String> getQuotes(String resource)throws IOException {
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

        try(InputStream inputStream=classLoader.getResourceAsStream(resource);
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){

        return reader
                .lines()
                .collect(Collectors.toList());
        }
    }
}
