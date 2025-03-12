package com.pet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pet.model.PetService;
import com.pet.model.PetVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PetServlet extends HttpServlet {
	
	//拿資料
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");  // 允許來自所有來源的請求
	    res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT");  // 允許的HTTP方法
	    res.setHeader("Access-Control-Allow-Headers", "Content-Type");  


		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");

		String action = req.getParameter("action");
//		String result = "success";

		if ("getPet".equals(action)) {
			String memberIdStr = req.getParameter("memberId");
			System.out.println(memberIdStr);
			
			Integer memberId = null;
			if(memberIdStr == null || memberIdStr.isEmpty()) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				 JSONObject resultJson = new JSONObject();
				 resultJson.put("memberId", "-1");  
			     res.getWriter().write(resultJson.toString());
			     return; 
			}
			try {
				memberId = Integer.valueOf(memberIdStr);
			} catch (Exception e) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				 JSONObject resultJson = new JSONObject();
				 resultJson.put("memberId", "-2");  
			     res.getWriter().write(resultJson.toString());
			     return; 
			}
			// 查詢資料
			PetService petService = new PetService();
			List<PetVO> petList = new ArrayList<PetVO>();
			petList = petService.getPetFindByMemberId(memberId);
			for (PetVO a : petList) {
				System.out.println(a);
			}

			JSONArray petArray = new JSONArray();
			for (PetVO pet : petList) {
				JSONObject petJson = new JSONObject();
				petJson.put("petId", pet.getPet_id());
				petJson.put("petName", pet.getPet_name());
				petJson.put("petGender", pet.getPet_gender());
				petJson.put("petWeight", pet.getPet_weight());
				petJson.put("petType", pet.getPet_type());
				petArray.put(petJson);
			}

			res.getWriter().write(petArray.toString());
		}
	}
	//儲存
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");  // 允許來自所有來源的請求
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT"); // 允許的HTTP方法
	    res.setHeader("Access-Control-Allow-Headers", "Content-Type");  

		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");

//		String action = req.getParameter("action");
//		String result = "success";
		String result="";
		BufferedReader reader = req.getReader();
		StringBuilder jsonStr = new StringBuilder();
		String jsonLine;

		Map<String, Object> resMap = new HashMap();

		while ((jsonLine = reader.readLine()) != null) {
			jsonStr.append(jsonLine);
		}
		String petInfo = jsonStr.toString();

		try {
			JSONObject petJson = new JSONObject(petInfo);
			String memberIdStr = petJson.optString("memberId", "");
			String petName = petJson.optString("petName", "");
			String petType = petJson.optString("petType", "");
			String petGenderStr = petJson.optString("petGender", "");
			String petWeightStr = petJson.optString("petWeight", "");

			PetService petService = new PetService();

			Integer petGender = null;
			Integer petWeight = null;
			Integer memberId = null;
			
			//把拿到的會員id轉成integer
			try {
				memberId = Integer.valueOf(memberIdStr);
			} catch (Exception e) {  // -2表示傳遞過來的不是數字理論上不可能發生
				resMap.put("memberId", "-2");
				result = "error";
			}
			
			
			//檢查寵物姓名
			if (petName == null || (petName.trim()).length() == 0) {
				result = "erro";
				resMap.put("petName", "-1");
			}
			
			
			//檢查寵物類別
			if (petType == null || (petType.trim()).length() == 0) {
				result = "erro";
				resMap.put("petType", "-1");
			}
			
			//檢查寵物體重
			if (petWeightStr == null || (petWeightStr.trim()).length() == 0) {
				result = "erro";
				resMap.put("petWeight", "-1");
			} else {
				try {
					petWeight = Integer.valueOf(petWeightStr);
				} catch (Exception e) {
				resMap.put("petWeight", "-2"); // -2表示傳遞過來的不是數字
				result = "error";
				}
			}
			
			//檢查寵物性別
			if (petGenderStr == null || (petGenderStr.trim()).length() == 0) {
				result = "erro";
				resMap.put("petGender", "-1");
			} else {
				try {
					petGender = Integer.valueOf(petGenderStr);
				} catch (Exception e) {
					resMap.put("petGender", "-2"); // -2表示傳遞過來的不是數字
					result = "error";
				}
			}
			
			if(resMap.isEmpty()) {
				petService.addPet(memberId, petType, petName, petGender, petWeight, 1);
				result = "success";
			}else {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			 
			resMap.put("result", result);
			JSONObject jsonPet = new JSONObject(resMap);
			res.getWriter().write(jsonPet.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			 JSONObject resultJson = new JSONObject();
			 resultJson.put("error", "data not null");  
		     res.getWriter().write(resultJson.toString());
		}

	}
	//更新
	public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");  // 允許來自所有來源的請求
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT");  // 允許的 HTTP 方法
	    res.setHeader("Access-Control-Allow-Headers", "Content-Type");  //允許的Headers
	    
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
//		String action = req.getParameter("action");
//		String result = "success";
		String result="";
		BufferedReader reader = req.getReader();
		StringBuilder jsonStr = new StringBuilder();
		String jsonLine;

		Map<String, Object> resMap = new HashMap();

		while ((jsonLine = reader.readLine()) != null) {
			jsonStr.append(jsonLine);
		}
		String petInfo = jsonStr.toString();

		try {
			JSONObject petJson = new JSONObject(petInfo);
			String petIdStr = petJson.optString("petId", "");
			String memberIdStr = petJson.optString("memberId", "");
			String petName = petJson.optString("petName", "");
			String petType = petJson.optString("petType", "");
			String petGenderStr = petJson.optString("petGender", "");
			String petWeightStr = petJson.optString("petWeight", "");

			PetService petService = new PetService();

			Integer petGender = null;
			Integer petWeight = null;
			Integer memberId = null;
			Integer petId = null;
			//把拿到的會員id轉成integer
			try {
				memberId = Integer.valueOf(memberIdStr);
			} catch (Exception e) {  // -2表示傳遞過來的不是數字理論上不可能發生
				resMap.put("memberId", "-2");
				result = "error";
			}
			
			try {
				petId = Integer.valueOf(petIdStr);
			} catch (Exception e) {  // -2表示傳遞過來的不是數字理論上不可能發生
				resMap.put("petId", "-2");
				result = "error";
			}
			
			//檢查寵物姓名
			if (petName == null || (petName.trim()).length() == 0) {
				result = "erro";
				resMap.put("petName", "-1");
			}
			
			
			//檢查寵物類別
			if (petType == null || (petType.trim()).length() == 0) {
				result = "erro";
				resMap.put("petType", "-1");
			}
			
			//檢查寵物體重
			if (petWeightStr == null || (petWeightStr.trim()).length() == 0) {
				result = "erro";
				resMap.put("petWeight", "-1");
			} else {
				try {
					petWeight = Integer.valueOf(petWeightStr);
				} catch (Exception e) {
				resMap.put("petWeight", "-2"); // -2表示傳遞過來的不是數字
				result = "error";
				}
			}
			
			//檢查寵物性別
			if (petGenderStr == null || (petGenderStr.trim()).length() == 0) {
				result = "erro";
				resMap.put("petGender", "-1");
			} else {
				try {
					petGender = Integer.valueOf(petGenderStr);
				} catch (Exception e) {
					resMap.put("petGender", "-2"); // -2表示傳遞過來的不是數字
					result = "error";
				}
			}
			
			if(resMap.isEmpty()) {
				petService.updatePet(petId, memberId, petType, petName, petGender, petWeight, 1);
				result = "success";
			}else {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			 
			resMap.put("result", result);
			JSONObject jsonPet = new JSONObject(resMap);
			res.getWriter().write(jsonPet.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			 JSONObject resultJson = new JSONObject();
			 resultJson.put("error", "data not null");  
		     res.getWriter().write(resultJson.toString());
		}
	}
	
	//跨域預檢請求 我用VSCode測試時會用到 所以複寫的
	public void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	    res.setHeader("Access-Control-Allow-Origin", "*");
	    res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT");
	    res.setHeader("Access-Control-Allow-Headers", "Content-Type");

	    // 回應空的內容，並且狀態碼設置為 200 OK
	    res.setStatus(HttpServletResponse.SC_OK);
	}
}
