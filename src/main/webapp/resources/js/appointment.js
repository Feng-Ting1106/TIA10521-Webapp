const steps = [
    "appointment",
    "serviceSelection",
    "petInformation",
    "agreement",
    "confirmation"
];
const windowHeight = window.innerHeight;
//使用者的會員ID
var memberId;
// 當前畫面頁數
let currenStep = 0;

//======個別頁面的內容======//
let appointment =`	
			<div class="agreement-container">
				<h1 class="title">預約時間與地點</h1>

				
				<!-- 選擇日期 -->
				<label for="date" class="selection">預約日期：</label>
				<input type="text" id="date_picker">
				<!-- 選擇時間 -->
				<label for="time-slot" class="selection">預約時段：</label>
				<select id="time_menu" name="time_menu" required>
					<!-- <option value="">請選擇時段</option> -->
					
				</select>
			
				<!-- 上車地址 -->
				<label for="pickup" class="selection">上車地址：</label>
				<input type="text" id="pickup" name="pickup" placeholder="請輸入上車地址" required>
			
				<!-- 下車地址 -->
				<label for="dropoff" class="selection">下車地址：</label>
				<input type="text" id="dropoff" name="dropoff" placeholder="請輸入下車地址" required>

				<!-- 費用說明 -->
				<p class="remark">單趟費用起跳價 100 元，後續每公里 50 元</p>
			
				<!-- 下一步按鈕 -->
				<button type="submit" id="nextPage">下一步</button>
			</div>`;

let serviceSelection =`
			<h2 id='title'>服務人員選擇</h2>

			<div class="card_div">
				
				<div class="none" id="lightbox">
					<article>
						<button class="close_card_btn">&times;</button>
						<div class = "staff_card">
							<div class = "staffInfo_text">
							</div>
						</div>
						<div class = "nextPage_div">
							<button type="button" id="nextPage" class="page_break">下一步</button>
						</div>
					</article>
				</div>
			</div>
			<button class="select_service">lightbox_btn</button>
			<div class="backPage_container">
				<button  id="backPage" class="page_break">上一步</button>
			</div>`;
let petInformation =`
			<h1 class="title">毛小孩資料填寫</h1>
			<div class="q_div" id="q1">
				<label for="savedPets" class="question_title">選擇已儲存的毛小孩</label>
				<select id="savedPets" class="question_input">
					<option id="noPet"value="noPet">未選擇</option>
				</select>
			</div>
			<div class="q_div" id="q2">
				<span class="question_title">毛小孩類別</span>
				<label><input type="radio" id="typeCat" name="petType" value="貓"> 貓</label>
				<label><input type="radio" id="typeDog" name="petType" value="狗"> 狗</label>
			</div>
	
			<div class="q_div" id="q3">
				<span class="question_title">毛小孩性別</span>
				<label><input type="radio" id="genderM" name="petGender" value="1"> 公</label>
				<label><input type="radio" id="genderF" name="petGender" value="2"> 母</label>
			</div>
			<div class="q_div" id="q4">
				<label for="petName" class="question_title">毛小孩大名</label>
				<input type="text" id="petName" class="input-text" placeholder="請輸入毛小孩大名">
			</div>

			<div class="q_div" id="q5">
				<label for="petWeight" class="question_title">毛小孩體重(kg)</label>
				<input type="text" id="petWeight" class="input-text" placeholder="例如：4kg">
			</div>
			<div class="q_div" id="q6">
				<label for="petNotes" class="question_title">其他毛小孩需要我們注意的事項(選填)：</label>
				<textarea id="petNotes" class="textarea" placeholder="例如：疾病、具攻擊性、容易受驚嚇等"></textarea>
			</div>
			<div class="page_break_div">
				<button class="page_break" id="backPage">上一步</button>
				<button class="page_break" id="nextPage">下一步</button>
			</div>
			<div class="none" id="lightbox">
				<article id="petInfo_Art">
					<button class="close_card_btn">&times;</button>
					<div >
						<button type="button" id="No" class="petInfo_btn">否</button>
						<button type="button" id="Yes" class="petInfo_btn">是</button>
					</div>
				</article>
			</div>`
let agreement =`
			<div class="agreement_text">
				<h3 class="title">寵愛牠 服務同意書</h3>
				<div class="agreement-content">
					<h4>一、服務內容</h4>
					<ol>
						<li>本公司提供寵物接送服務，負責將寵物從指定地點安全送達至目的地。</li>
						<li>服務時間將依據雙方約定時間進行，如需更改，請提前 24 小時通知。</li>
					</ol>
					<h4>二、寵物健康與安全</h4>
					<ol>
						<li>寵物主人需提供寵物健康狀況及年齡，無病痛或疾病才可使用服務。</li>
						<li>如有特殊情況，請提前告知，以確保服務順利進行。</li>
						<li>若寵物於運輸途中出現異常反應或攻擊行為，可能會中止服務，費用恕不退還。</li>
					</ol>
					<h4>三、基本權益與賠償</h4>
					<ol>
						<li>本公司承諾提供安全且無損壞之服務。</li>
						<li>運輸過程中，將盡全力確保寵物安全。</li>
						<li>本公司對於突發事故或天災不承擔責任。</li>
					</ol>
					<h4>四、費用與取消政策</h4>
					<ol>
						<li>服務費用需於接送前支付，並開立發票。</li>
						<li>取消需提前通知，否則可能不予退款。</li>
					</ol>
					<h4>五、其他</h4>
					<ol>
						<li>本公司保留變更條款的權利。</li>
						<li>如遇不可抗力因素，將與客戶協商解決方案。</li>
					</ol>
				</div>
				<div class="agreement-footer">
					<label><input type="checkbox" id="checkAgree"> 我已閱讀並同意以上條款</label>
					<div class="page_break_div">
						<button class="page_break" id="backPage">上一步</button>
						<button class="page_break" id="nextPage">下一步</button>
					</div>
				</div>                 
			</div>`
let confirmation =`
			<h2 class="title">訂單確認</h2>
			<div class="order_info">
				<p><span class="label">預約項目：</span><span id="project">寵物接送</span></p>
				<p><span class="label">預約時間：</span><span id="time">2025年1月1日 下午</span></p>
				<p><span class="label">上車地址：</span><span id="pickup">台北市中山區南京東路三段219號4F</span></p>
				<p><span class="label">目的地地址：</span><span id="destination">台北市中山區南京東路三段221號</span></p>
				<p><span class="label">預約服務人員：</span><span id="staff">xxx</span></p>
				<p><span class="label">服務人員聯絡電話：</span><span id="staff-phone">0912345678</span></p>
				<p><span class="label">會員姓名：</span><span id="member-name">xxx</span></p>
				<p><span class="label">會員電話：</span><span id="member-phone">0987654321</span></p>
				<p><span class="label">毛小孩類別：</span><span id="pet-type">狗</span></p>
				<p><span class="label">毛小孩品種：</span><span id="pet-breed">柴犬</span></p>
				<p><span class="label">毛小孩性別：</span><span id="pet-gender">公</span></p>
				<p><span class="label">毛小孩大名：</span><span id="pet-name">柴柴</span></p>
				<p><span class="label">毛小孩年齡：</span><span id="pet-age">1歲</span></p>
				<p><span class="label">毛小孩體重：</span><span id="pet-weight">5kg</span></p>
				<p><span class="label">其他注意事項：</span><span id="pet-notes">無</span></p>
			</div>
			<div class="pay_info">
				<p><span class="label">訂單金額：</span><span id="order_amount">100元</span></p>
				<div class="points_info">
					<p><input type="checkbox" id="use_points"> <label for="use_points">使用點數折扣</label></p>
					<p class="points_total">剩餘點數：50點</p>
				</div>
				<p><span class="label">總金額：</span><span id="total_amount">50元</span></p>
			</div>
			<div class="page_break_div">
				<button class="page_break" id="backPage">上一步</button>
				<button class="page_break" id="backPage">付款</button>
			</div>`
//======個別頁面的內容======//

//=====換頁後暫存資料=====//
//寵物資料
// function setPetInfo (){
// 	sessionStorage.setItem('savedPets', $('#savedPets').val());
// 	sessionStorage.setItem('petWeight', $('#petWeight').val());
// 	sessionStorage.setItem('petName', $('#petName').val());
// 	sessionStorage.setItem('petType', $('#petType').val());
// 	sessionStorage.setItem('petGender', $('#petGender').val());
// }

//=====取得暫存的資料=====//
//寵物資料
// function getPetInfo(){
// 	$('#petWeight').val(sessionStorage.getItem('petWeight'));
// 	$('#petName').val(sessionStorage.getItem('petName'));
// 	let petType = sessionStorage.getItem('petType');
// 	let petGender = sessionStorage.getItem('petGender')
// 	$('input[name="petType"][value="' + petType + '"]').prop('checked', true);
// 	$('input[name="petGender"][value="' + petGender + '"]').prop('checked', true);
// 	$('#savedPets').val(sessionStorage.getItem('savedPets'));
// 	console.log(petType);
// 	console.log(petGender);
// }

//到時候要刪掉的，取得測試會員ID  刪掉
let setMember=`
			<label for="memberId" class="selection">輸入測試的會員ID</label>
			<input type="text" id="memberId"  placeholder="輸入測試的會員ID" required>
			<button class="page_break" id="nextPage">下一步</button>`
function getMemberId(){
	memberId = $('#memberId').val();
	console.log(memberId);
}

//=====取得可工作的人員=====//
let card_options = '';
let error_msg ;
async function getCan_Work_Staff(){
	let getSchedule_URL = 'http://localhost:8081/TIA105G1/staffServlet?action=getSchedule&';

	let date = $('#date_picker').val();
	let apptTime = $("#time_menu Option:selected").val();
	console.log(date);
	console.log(apptTime);
	getSchedule_URL += `apptTime=${apptTime}&date=${date}`;
	console.log(getSchedule_URL);

	try{
		let res = await fetch(getSchedule_URL);
		let data =await res.json();
		console.log(data);

		if(data.result == null && data[0].result== 'success'){
			for(let i = 1; i <data.length ; i++){
				let staff_name = data[i].name;
				let staff_introduction = data[i].introduction;
				let man_img = "http://localhost:8081/TIA105G1/resources/images/staff_man.jpg";
				let woman_img = "http://localhost:8081/TIA105G1/resources/images/staff_woman.jpg"
				card_options += `<div class="service_card">
										<img class = "staff_img" src = "${data[i].gender == 1 ? man_img : woman_img}" alt="">
									<div class ="staff_info">
										<p class = "staff_name">${staff_name}</p>
										<p class = "staff_introduction">${staff_introduction}</p>			
									</div>
								</div>`
			};
			return true;
		} 
		if(!data.ok){
			error_msg = data;
			return false;
		};
	} catch(error){
		console.log(error);
	}
}

function error_handling(error_msg){
	const back_msg = {
        noStaff: '沒人上班',
        error: {
            '-1': '你沒給日期',
            '-2': '日期格式有錯，請用月曆選單輸入',
			'-3': '請勿輸入今日以前的日期，請用月曆選單輸入'
        }
    };
	if(error_msg.result == 'noStaff'){
		alert(back_msg.noStaff);
	} else if (error_msg.result == 'error'){
		alert(back_msg.error[error_msg.date]);
	}
	
}

//=====取得user的寵物=====//
let savedPets_Op ="";
let petData;
async function getMember_Pet() {
	
	let getMember_Pet_URL = 'http://localhost:8081/TIA105G1/petServlet?action=getPet&';
	getMember_Pet_URL +=`memberId=${memberId}` 
	console.log(getMember_Pet_URL)
	try{
		let res = await fetch(getMember_Pet_URL);
		 data = await res.json();
		console.log(data);  
		if(data != ""){
			petData = data;
			
			data.forEach(function(pet){
				let id = pet.petId;
				let name = pet.petName;
				savedPets_Op +=`<option id="petId${id}" value='${JSON.stringify(pet)}'>${name}</option>`
				
			})
			console.log(savedPets_Op);
			console.log(petData);
			petInformation =`
			<h1 class="title">毛小孩資料填寫</h1>
			<div class="q_div" id="q1">
				<label for="savedPets" class="question_title">選擇已儲存的毛小孩</label>
				<select id="savedPets" class="question_input">
					<option id="noPet"value="noPet">未選擇</option>
					${savedPets_Op}
				</select>
			</div>
			<div class="q_div" id="q2">
				<span class="question_title">毛小孩類別</span>
				<label><input type="radio" id="typeCat" name="petType" value="貓"> 貓</label>
				<label><input type="radio" id="typeDog" name="petType" value="狗"> 狗</label>
			</div>
	
			<div class="q_div" id="q3">
				<span class="question_title">毛小孩性別</span>
				<label><input type="radio" id="genderM" name="petGender" value="1"> 公</label>
				<label><input type="radio" id="genderF" name="petGender" value="2"> 母</label>
			</div>
			<div class="q_div" id="q4">
				<label for="petName" class="question_title">毛小孩大名</label>
				<input type="text" id="petName" class="input-text" placeholder="請輸入毛小孩大名">
			</div>

			<div class="q_div" id="q5">
				<label for="petWeight" class="question_title">毛小孩體重(kg)</label>
				<input type="text" id="petWeight" class="input-text" placeholder="例如：4kg">
			</div>
			<div class="q_div" id="q6">
				<label for="petNotes" class="question_title">其他毛小孩需要我們注意的事項(選填)：</label>
				<textarea id="petNotes" class="textarea" placeholder="例如：疾病、具攻擊性、容易受驚嚇等"></textarea>
			</div>
			<div class="page_break_div">
				<button class="page_break" id="backPage">上一步</button>
				<button class="page_break" id="nextPage">下一步</button>
			</div>
			<div class="none" id="lightbox">
				<article id="petInfo_Art">
					<button class="close_card_btn">&times;</button>
					<div >
						<button type="button" id="no" class="petInfo_btn">否</button>
						<button type="button" id="yes" class="petInfo_btn">是</button>
					</div>
				</article>
			</div>`
		}
	} catch{
		console.log(error);
	}
}

//=====跳頁更新寵物======//
let petInfo;
let thisPetName;
let thisPetType;
let thisPetGender;
let thisPetWeight;
let petServlet_URL = 'http://localhost:8081/TIA105G1/petServlet';
  function checkPetInfoChange(){
	return new Promise((resolve, reject) => {
		thisPetGender = $('input[name="petGender"]:checked').val() || null;
		thisPetName = $('#petName').val() || null;
		thisPetType = $('input[name="petType"]:checked').val() || null;
		thisPetWeight = $('#petWeight').val().match(/\d+(\.\d+)?/g) ;
		thisPetWeight = thisPetWeight ? parseFloat(thisPetWeight[0]) : null;
		if(thisPetGender == null || thisPetName == null || thisPetType == null || thisPetWeight == null){
			alert('資料請物留空 請再檢查一次');
			resolve(false);
			return ;
		}
		if($('#savedPets').val() == 'noPet') {
			petInfoLightBox(`<p>是否將此筆資料儲存 以利下次填寫</p>`);
			$('#no').off('click').on('click',function(){
				// changePageBoolean = true;
				resolve(true);
				return;
			})
			$('#yes').off('click').on('click',async function(){
				let thisPetDate = {
					memberId : memberId,
					petType : thisPetType,
					petName : thisPetName,
					petWeight : thisPetWeight, 
					petGender : thisPetGender
				}
				try {
					let res = await fetch(petServlet_URL, {
						method: "POST",
						headers: {
							"Content-Type": "application/json"
						},
						body: JSON.stringify(thisPetDate)
					});
					let data = await res.json();
					if(data.result == "success" ){
						resolve(true);
						return;
					} else{
						$("#lightbox").addClass("none");
						$('article').children('p').remove();

						if (Object.values(data).some(val => val == "-1")){
							alert('資料請物留空 請再檢查一次');
						} else if(Object.values(data).some(val => val == "-2")){
							alert('網頁發生問體 請重新整理後再試一次');
						} else{
							alert('哭啊');
						}
						resolve(false);
						return;
					}
				} catch (error) {
					alert('哭啊');
				}
				
			})
		}
		if(petInfo != null){
			if (thisPetGender != petInfo.petGender || thisPetName != petInfo.petName || thisPetType != petInfo.petType || thisPetWeight != petInfo.petWeight){
				petInfoLightBox(`<p>是否將變更儲存至寵物資料</p>`);
				$('#no').off('click').on('click',function(){
					// changePageBoolean = true;
					resolve(true);
					return;
				})
				$('#yes').off('click').on('click',async function(){
				let thisPetDate = {
					petId : petInfo.petId,
					memberId : memberId,
					petType : thisPetType,
					petName : thisPetName,
					petWeight : thisPetWeight, 
					petGender : thisPetGender
				}
				try {
					let res = await fetch(petServlet_URL, {
						method: "PUT",
						headers: {
							"Content-Type": "application/json"
						},
						body: JSON.stringify(thisPetDate)
					});
					let data = await res.json();
					if(data.result == "success" ){
						resolve(true);
						return;
					} else{
						$("#lightbox").addClass("none");
						$('article').children('p').remove();

						if (Object.values(data).some(val => val == "-1")){
							alert('資料請物留空 請再檢查一次');
						} else if(Object.values(data).some(val => val == "-2")){
							alert('網頁發生問體 請重新整理後再試一次');
						} else{
							alert('哭啊');
						}
						resolve(false);
						return;
					}
				} catch (error) {
					alert('哭啊');
				}	
				})
			} else{
				resolve(true);
				return;
			}
		}
	})
}

//寵物頁面燈箱
function petInfoLightBox(text){
	$('article').children('p').remove();
	$('#petInfo_Art').prepend(text);
		$('#lightbox').removeClass('none');
		$("#lightbox").on("click", function(){
			$("#lightbox").addClass("none");
			$('article').children('p').remove();
		});
		$('#lightbox > .petInfo_Art').click(function(e){
			e.stopPropagation();
		})
		$('.close_card_btn').click(function(){
			$("#lightbox").addClass("none");
			$('article').children('p').remove();
		})
}

// 選擇服務人員的選項迴圈
function s_card(){
	$('.card_div').append(card_options);
}
// 預約時間的時間選項迴圈
function time_menu(){
	let menu =  $('#time_menu') 
    menu.empty();
	let time_options = '';
	for(let i = 8; i<=20; i++){
		let time = `${i}:00`;
		time_options += `<option value="${i}">${time}</option>`
	}
	menu.append(time_options);
}


// 跳頁更換css
function changeCss(cssFile){
	$('#page_css').attr('href',cssFile);
}
// 按下按鈕跳頁
function changePage (){
	$("#nextPage").on('click', async function(){

		switch (currenStep){
			case 0:
				getMemberId();
				currenStep ++;
					runStep();
				break;
			case 1:
				let staffAvailable = await getCan_Work_Staff();
					console.log()
				if(staffAvailable){
					console.log(1);
					currenStep ++;
					runStep();
				} else{
					error_handling(error_msg);
				};
				break;
			case 2: 
				currenStep ++;
				runStep();
				savedPets_Op=""
				getMember_Pet();
				break;
			case 3: 
				// console.log(await checkPetInfoChange());
				if(await checkPetInfoChange()){
					currenStep ++;
					runStep();
				}else{

				}
				break;
			case 4:
				if(currenStep == 4 & check_for_agreement()){
				currenStep ++;
				runStep();
				}
				break;
			case 5:
				currenStep ++;
				runStep();
				break;	
		}
	});

	$('#backPage').on('click', function(){
		switch (currenStep){
			case 1:
				break;
			case 2: 
				card_options="";
				break;
			case 3:
				// savedPets_Op =""; //刪掉
				break;
			case 4:
				savedPets_Op=""
				getMember_Pet();
				break;
			case 5:
				break;	
		}
		currenStep --;
		runStep();
	})
}

// 個別頁面的js
function step1_js(){
	time_menu();
	$( function() {
		$("#date_picker" ).datepicker();
		$('#date_picker').datepicker('option','showAnim','slideDown');
		$('#date_picker').datepicker('option', 'minDate', 0);
	});
		
	// $('#date_picker').change(function(){
	// 	 console.log($(this).val());
	// })
	
}
function step2_js(){
	s_card();
	var staff_info;
	var staff_img;
	//服務人員選擇卡燈箱
	$(".service_card").click(function(){
		event.stopPropagation()
		staff_info = $(this).find('.staff_info').html();
		staff_img = $(this).find('img').prop('outerHTML')

		$('.select_service').click(); //啟用燈箱按鈕
		// e.stopPropagation();
	})
	$('.select_service').click(function(){
		$('.staff_card').prepend(staff_img);
		$('.staffInfo_text').prepend(staff_info);
		$('#lightbox').removeClass('none');
	})
	$("#lightbox").on("click", function(){
		$("#lightbox").addClass("none");
		$('.staffInfo_text').children('p').remove();
		$('.staff_card').children('img').remove();
	});
	$('#lightbox > article').click(function(e){
		e.stopPropagation();
	})
	$('.close_card_btn').click(function(){
		$("#lightbox").addClass("none");
		$('.staffInfo_text').children('p').remove();
		$('.staff_card').children('img').remove();
	})
}

function step3_js(){
	//寵物下拉選單
	let pets = $(savedPets);
	pets.on('change', function(){
		if(pets.val() == 'noPet'){
			$('input[name="petType"]').prop('checked', false);  // 清除寵物類型的選擇
            $('input[name="petGender"]').prop('checked', false);  // 清除性別的選擇
            $('#petWeight').val('');  // 清空體重輸入框
            $('#petName').val('');  // 清空寵物名稱輸入框
			petInfo = null;
		}	else{
			 petInfo = JSON.parse($(this).val());
			switch (petInfo.petType){
				case "狗":
					$('#typeDog').prop('checked', true);
					break;
				case "貓":
					$('#typeCat').prop('checked', true);
					break;
			}

			switch (petInfo.petGender){
				case 1:
					$('#genderM').prop('checked', true);
					break;
				case 2:
					$('#genderF').prop('checked', true);
					break;
			}
			$('#petWeight').val(`${petInfo.petWeight}kg`);
			$('#petName').val(petInfo.petName);
			console.log(petInfo);
		}
	})
	
}
// 檢查同意書是否勾選
function check_for_agreement(){
	if($('#checkAgree').prop('checked')){
		return true;
	} else{
		$('#checkAgree').addClass('notCheck')
		alert('請勾選同意條款')
		return false;
	}
}

function runStep () {
	$('.body_text').fadeOut(100,function(){
		switch(currenStep){
			case 0:
				$(".body_text").html(setMember);
				break;
			case 1:
				$(".body_text").html(appointment);
				changeCss("http://localhost:8081/TIA105G1/resources/css/appointment.css");
				step1_js();
				break;
			case 2:
				$(".body_text").html(serviceSelection);
				changeCss("http://localhost:8081/TIA105G1/resources/css/serviceSelection.css");
				step2_js();
				break;
			case 3:
				$(".body_text").html(petInformation)
				changeCss("http://localhost:8081/TIA105G1/resources/css/pet_information.css");
				step3_js();
				break;
			case 4:
				$('.body_text').html(agreement)
				changeCss("http://localhost:8081/TIA105G1/resources/css/agreement.css")
				break;
			case 5:
				$('.body_text').html(confirmation)
				changeCss("http://localhost:8081/TIA105G1/resources/css/confirmation.css")
				break;
			}
		changePage();
		$('.body_text').fadeIn(800); 
		window.scrollTo({ top: windowHeight / 2, behavior: "smooth" });
	})
}
$(function(){
	runStep();
})





