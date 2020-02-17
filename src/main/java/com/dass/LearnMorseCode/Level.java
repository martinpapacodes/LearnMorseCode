package com.dass.LearnMorseCode;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.dass.LearnMorseCode.ApiQuote;
import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Level {
    String toBeMorseCoded = "";
    int levelTracker = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    public Level(String toBeMorseCoded) {
        this.toBeMorseCoded = toBeMorseCoded;
        this.levelTracker = 0;

    }

    public static void main(String [] args) throws IOException {
        getQuoteFromApi();
//        Scanner input = new Scanner(System.in);
//
//        convertToMorseCode(getQuoteFromApi());
//        System.out.println("What is the morse code for  " + getQuoteFromApi());
//        String userAnswer = input.nextLine();
//        if(userAnswer == convertToMorseCode(getQuoteFromApi())){
//            System.out.println("YOUR ANSWER IS CORRECT");
//        }
//        else{
//            System.out.println("YOUR ANSWER IS WRONG");
//        }
        System.out.println(convertToMorseCode("ghwhdgf fhfh"));
    }

    public static String convertToMorseCode(String regularText){
        char [] reg = regularText.toUpperCase().toCharArray();  //converts to uppercase and array of chars.
//                .toCharArray();


        String [] values = {".-","-...","-.-.","-..",".","..-.","--.", "....","..",".---", "-.-",".-..","--","-.", "---",".--.","--.-",".-.", "...","-",
                "..-","...-",".--","-..-","-.--","--..",     "-----", ".----","..---","...--","....-",".....","-....","--...","---..","----."};

        char [] keys = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
        String answers = "";
        int reset;
        for(int x = 0; x < reg.length; x++){
            reset =0;
            for(int y = 0; y < keys.length; y++) {
                if (reg[x] == keys[y]) {
                    answers += values[y] + "/" ;
                }
                else if(reg[x] == ' ' && reset <= 1){
                    reset++;
                    answers+= " ";
                }
            }
        }
        return  answers.substring(0,answers.length()-1);
    }
    public static String getQuoteFromApi() throws IOException {
        Gson gson = new Gson();
        URL url = new URL("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en");
        HttpURLConnection join = (HttpURLConnection) url.openConnection();
        join.setRequestMethod("GET");
        BufferedReader scan = new BufferedReader(new InputStreamReader(join.getInputStream()));

        StringBuilder all = new StringBuilder();
        String line = scan.readLine();
        while (line != null) {
            all.append(line);
            line = scan.readLine();
        }
        ApiQuote quoteText = gson.fromJson(String.valueOf(all), ApiQuote.class);
        return quoteText.quoteText;
    }
}
