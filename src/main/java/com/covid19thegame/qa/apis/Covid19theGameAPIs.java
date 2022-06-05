package com.covid19thegame.qa.apis;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;



public class Covid19theGameAPIs 
{

	public static Response api_UpdateUserScore(String newUser,String strScore)
	{
        
        String aUser="\""+newUser+"\"";
        String strTheScore=strScore;
		
		RestAssured.baseURI="https://supervillain.herokuapp.com/v1/user";
		Response strResponse=given().log().all()
		.header("accept","*/*")
		.header("Content-Type","application/json")
		.header("Authorization","eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJhYmhheSIsImVtYWlsIjoiYWJoYXkubmFkZ2F1ZGFAZ21haWwuY29tIiwiaWF0IjoxNjU0NDM0NDExLCJleHAiOjE2NTQ2OTM2MTF9.8ADq1kmvT9AjC5PhgwEz3Amzax-epuuY4XW7CUY9dOu7VzXrhPlYW3zIROdZlSvxKvRNkmLWLRe30CAs7kGVOQ")
		.body("{\r\n"						
				+ " \"username\": "+aUser+",\r\n"				
				+ "  \"score\": "+strTheScore+"\r\n"
				+ "}")
		
		.when().post("")
		.then().log().all().extract().response();
		
        
		
		 int resCode=strResponse.statusCode();
		 
		 System.out.println("Resp Status Code ="+resCode);
		 //System.out.println("Resp headers ="+strResponse.headers());
		 System.out.println("Resp Body ="+strResponse.body().asString());
		 
		 return strResponse;
		 
		 
		    
		//System.out.println("Responce Body #######"+strResponse);
		
	}
	
	

	
}
