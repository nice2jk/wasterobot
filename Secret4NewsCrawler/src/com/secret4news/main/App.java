package com.secret4news.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.secret4news.common.Constants;
import com.secret4news.controller.BatchController;

public class App {

	public static void main(String[] args) {
		
		BatchController ct = new BatchController();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.print("DB Access ready... : " + Constants.isServer + " : ");
			
			if(Constants.isServer) {
				ct.run(args[0]);
			} else {
				ct.run(br.readLine());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
