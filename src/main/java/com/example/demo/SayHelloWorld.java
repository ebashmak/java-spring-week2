package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @GetMapping("/stringMethods")
    public String stringMethods(@RequestParam(value="inputText", defaultValue = "Hello Java!") String text){

        String s = text;
        Integer index = s.indexOf("Java");
        String s1 = "Hi Katya!";
        String s2 = "Hello java!";
        String s3 = "";
        String s4 = "   String     ";

        return
                index.toString() +
                "<br>" + s.equalsIgnoreCase(s1) +
                "<br>" + s.equalsIgnoreCase(s2) +
                "<br>" + s.contains("Hello") +
                "<br>" + (s.startsWith("H") && s.endsWith("!")) +
                "<br>" + s3.isEmpty() +
                "<br>" + s.toLowerCase() +
                "<br>" + s.toUpperCase() +
                "<br>" + s4.trim() +
                "<br>" + String.valueOf(125 + 1) +
                "<br>" + Arrays.toString(s1.split(" ")) +
                "<br>" + String.join("+", "Hello", "Katya");
    }

    @GetMapping("/list")
    public List<String> getFruits() {
        List<String> fruits = new ArrayList<>();
        fruits.add("banana");
        fruits.add("apple");
        return fruits;
    }

    @GetMapping("/map")
    public Map<String, Integer> getStock(){
        Map<String, Integer> stock = new HashMap<>();
        stock.put("iPhone", 100);
        stock.put("MacBook", 150);
        return stock;
    }
//
//    @PostMapping("/submit")
//    public String handlePost(@RequestBody String body) {
//        return "Сервер получил через POST: " + body;
//    }
//
//    public static class MyData {
//        public String message;
//        public int priority;
//    }
//
//    @PostMapping("/json")
//    public String handleJson(@RequestBody MyData data) {
//        return "Сервер успешно десериализовал JSON!" +
//                "<br>Сообщение: " + data.message +
//                "<br>Приоритет: " + data.priority;
//    }

    public static class Order{
        public String dish;
        public int quantity;
        public String comment;
    }

    @PostMapping("/order")
    public String createOrder(@RequestBody Order order){
        return "Заказ принят: " + order.dish + " x" + order.quantity + " " + order.comment;
    }
}