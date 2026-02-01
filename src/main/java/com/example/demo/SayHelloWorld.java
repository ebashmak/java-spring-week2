package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloWorld {

    @GetMapping("/hello")
    public String hello(@RequestParam(value="name", defaultValue="World") String name){
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/reverse")
    public String reverse(@RequestParam(value="inputText", defaultValue = "abc") String text){
        StringBuilder sb = new StringBuilder(text);
        return sb.reverse().toString();
    }

    @GetMapping("/swap-builder")
    public String swapSB(@RequestParam(value="inputText", defaultValue = "Java") String text){
        if(text.length() <= 1){
            return text;
        }
        StringBuilder sb = new StringBuilder(text);

        char first = sb.charAt(0);
        char last = sb.charAt(sb.length() - 1);

        sb.setCharAt(0, last);
        sb.setCharAt(sb.length()-1, first);

        return sb.toString();
    }

    @GetMapping("/swap")
    public String swap(@RequestParam(value = "inputText", defaultValue = "Java") String text){
        if(text.length() <= 1){
            return text;
        }

        char first = text.charAt(0);
        char last = text.charAt(text.length() - 1);

        String middle = text.substring(1, text.length() - 1);

        return last + middle + first;
    }

    @GetMapping("/removeChars")
    public String removeChars(@RequestParam(value="inputText", defaultValue = "Java") String text){
        if(text.length() <= 2){
            return "Все символы удалены";
        }

        return text.substring(1, text.length() - 1);
    }

    @GetMapping("/factorial")
    public String factorial(@RequestParam(value="inputText", defaultValue = "Java") String text){
        int n = text.length();
        long result = 1;

        for(int i = 1; i <= n; i++){
            result *= i;
        }
        return "the text length is: " + n + "<br>" + "the factorial is: " + result;
    }

    @GetMapping("/fibonacci")
    public String fibonacci(@RequestParam(value = "inputText", defaultValue = "Java") String text){
        int n = text.length();
        if (n == 0) return "0";

        StringBuilder fib = new StringBuilder("0");
        if (n >= 1) fib.append(", 1");

        long prevPrev = 0;
        long prev = 1;

        for(int i = 2; i <= n; i++){
            long current = prev + prevPrev;

            fib.append(", ").append(current).append(" ");
            prevPrev = prev;
            prev = current;

        }
        return fib.toString();
    }
}
