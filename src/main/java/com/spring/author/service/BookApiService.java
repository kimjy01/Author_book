package com.spring.author.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@Service
public class BookApiService {
	
	@Value("${api-key}")
	private String book_key;

	public List<Map<String, Object>> getBookApi(){
		
		List<Map<String, Object>> bookList = new ArrayList<>();
		
        String authKey = book_key; //인증키
        String startDt = "2023-01-01";
        int age = 20;
        int pageSize = 8; //페이지 사이즈

        try {
            URL url = new URL("http://data4library.kr/api/loanItemSrch?" +
            		"authKey=" + authKey +
                    "&startDt=" + startDt +
                    "&age=" + age +
                    "&pageSize=" +pageSize +"&format=json&");

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String result = bf.readLine();

			JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject bookListJson = (JSONObject) jsonObject.get("response");
            JSONArray docs = (JSONArray) bookListJson.get("docs");

            for(int i = 0; i<docs.size();i++){
                JSONObject temp = (JSONObject) docs.get(i);
                JSONObject doc = (JSONObject) temp.get("doc");
                
                Map<String, Object> bookInfo = new HashMap<>();
                bookInfo.put("bookname", doc.get("bookname"));
                bookInfo.put("authors", doc.get("authors"));
                bookInfo.put("bookImageURL", doc.get("bookImageURL"));

                bookList.add(bookInfo);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return bookList;
    }
}
