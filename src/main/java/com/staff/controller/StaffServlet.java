package com.staff.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.staff.model.StaffService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StaffServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");

		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");

		String action = req.getParameter("action");
		
		String result = "";
		Map<String, Object> resultMap = new HashMap();

		if ("getSchedule".equals(action)) {
			String timeStr = req.getParameter("apptTime");
			System.out.println(timeStr);
			// 驗證時間是否為空
			if (timeStr == null || (timeStr.trim()).length() == 0) {
				result = "error";
				resultMap.put("apptTime", "-1"); // -1表示傳遞過來的是空值
			}
			// 驗證日期是否為空
			String dateStr = req.getParameter("date");
			System.out.print(dateStr);
			if (dateStr == null || (dateStr.trim()).length() == 0) {
				result = "error";
				resultMap.put("date", "-1"); // -1表示傳遞過來的是空值
			}

			// 如果有空值出現
			if (result.equals("error")) {
				resultMap.put("result", result);
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				res.getWriter().write(new JSONObject(resultMap).toString());
				return;
			}

			// 驗證時間是否能轉成字串(理論上這邊不可能發生問題，因為前端是用下拉選單給user選，除非前端在包裝跟傳送資料時有問題)
			Integer apptTime = null;
			try {
				apptTime = Integer.valueOf(timeStr);
			} catch (Exception e) {
				resultMap.put("apptTime", "-2"); // -2表示傳遞過來的不是數字
				result = "error";
			}
			// 驗證日期格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date date = null;
			try {
				date = sdf.parse(dateStr);
			} catch (Exception e) {
				resultMap.put("date", "-2"); // -2表示傳遞過來的日期格式不正確
				result = "error";
			}
			// 如果有格式錯誤出現
			if (result.equals("error")) {
				resultMap.put("result", result);
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				res.getWriter().write(new JSONObject(resultMap).toString());
				return;
			}
			// 驗證輸入的日起是否早於昨日日期 前端透過datePick提交的會是00:00的時間 因此日期比較跟昨日比 而非當日
			// 我懶得寫兩行 所以先new一個當前日期，然後跑getTime接著減到一天的毫秒再把它提交給下一個new Date
			Date yesterday  = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
			if(date.before(yesterday)) {
				resultMap.put("result", "error");
				resultMap.put("date", "-3"); // -3表示輸入今日以前的日期
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				res.getWriter().write(new JSONObject(resultMap).toString());
				return;
			}
			
			// 查詢資料
			List<Map<String, Object>> staffList = null;
			StaffService staffService = new StaffService();
			staffList = staffService.getWorkableStaff(apptTime, date);

			// 判斷查詢是否有結果
			if (staffList == null || staffList.isEmpty()) {
				result = "noStaff";
				resultMap.put("result", result);
				res.getWriter().write(new JSONObject(resultMap).toString());
				return;
			} else {
				 result = "success";
			}
			
			// 建立 JsonObj，把請求結果的訊息放進去，等等再放進Json陣列來一併回傳
			JSONObject resultJson = new JSONObject();
			resultJson.put("result", result);
			
			// 建立 Json 陣列來包裝最後要回傳的資料
			JSONArray staffArray = new JSONArray();
			staffArray.put(resultJson);
			
			// 用迴圈把查出來的List裡包著的各個Map資料轉成JsonObj，然後放進Json陣列裡
			for (Map<String, Object> staff : staffList) {
				JSONObject staffJson = new JSONObject(staff); 
				staffArray.put(staffJson);
			}

			// 將 Json陣列回應回去
			res.getWriter().write(staffArray.toString());
		}
	}
}
