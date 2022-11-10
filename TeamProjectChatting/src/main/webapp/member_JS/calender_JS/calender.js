////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////// 기 본 세 팅 ////////////////////////////////////////////////
///////////////////////////////////// 기 본 세 팅 ////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
function ccc(){ 
	document.querySelector('#box').style.display ='block'
 }

const date = new Date()
//Mon Aug 01 2022 00:00:00 GMT+0900 (한국 표준시)
const currentYear = date.getFullYear()
const currentMonth = date.getMonth()+1
const currentDate = date.getDate()
let toDay = new Date( currentYear , currentMonth-1 , 1 )
const monthDays = document.querySelector('.days')

let ddd = ' '+currentYear+''+currentMonth+''+currentDate+''

const monthmove = (  ) => {
	console.log( toDay )
	let lastDay = new Date( toDay.getFullYear() , toDay.getMonth()+1 , 0 ).getDate() 
	let Day1 = toDay.getDay()
	
////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// 달 력 출 력 ///////////////////////////////////////////////
////////////////////////////////////// 달 력 출 력 ///////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////

	callist(currentMonth)
	   
 const months = [
   "1_ January",
   "2_ February",
   "3_ March",
   "4_ April",
   "5_ May",
   "6_ June",
   "7_ July",
   "8_ August",
   "9_ September",
   "10_ October",
   "11_ November",
   "12_ December",
]

document.querySelector('.head h3').innerHTML = months[ toDay.getMonth() ]
document.querySelector('.head h2').innerHTML = toDay.getFullYear()  
}

// 왼쪽 버튼 클릭시  이동
// 왼쪽 버튼 클릭시  이동

document.querySelector('.left_btn').addEventListener('click' , () => {
	let nextMonth = toDay.getMonth()-1
	let nextYear = toDay.getFullYear()
	if( nextMonth == 0 ){
		nextMonth = 12
		nextYear--
		toDay.setYear( nextYear  )
	}
	toDay.setMonth( nextMonth  )
	monthmove(  )
})
// 오른쪽 버튼 클릭시 이동
// 오른쪽 버튼 클릭시 이동
document.querySelector('.right_btn').addEventListener('click' , () => {
	let nextMonth = toDay.getMonth()+1
	let nextYear = toDay.getFullYear()
	if( nextMonth == 13 ){
		nextMonth = 1
		nextYear++
		toDay.setYear( nextYear  )
	}
	toDay.setMonth( nextMonth  )
	monthmove( )
})
monthmove()

////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
let t_date =''
// 모달 on
function modalon(aa){
	t_date = aa
	document.querySelector(".btn").click()
}

// 일정 등록
function calregist(){
	let t_content = document.querySelector(".t_content").value
	console.log(t_content)
	$.ajax({
		url:"/TeamProjectChatting/cal",
		data : {"t_content" : t_content , "t_date" : t_date},
		success : function(re) {
			if(re==='true'){alert('등록됏슈')}
			else{alert('실패유')}
		}
	})
}

function callist(currentMonth){
	$.ajax({
		url:"/TeamProjectChatting/callist",
		data:{"currentMonth": currentMonth },
		async : false,
		success : function(re) {
			let t = JSON.parse(re)
			let days =""

			lastDay = new Date( toDay.getFullYear() , toDay.getMonth()+1 , 0 ).getDate()
			Day1 = toDay.getDay()
			
			for(let i = 1 ; i <= Day1 ; i++){
			      days += `<div> </div>`
			      monthDays.innerHTML = days
			   }
	    
			for(let i = 1 ; i <= lastDay ; i++){
			let id= toDay.getFullYear()+''+(toDay.getMonth()+1)+''+i
			
			if(t[i].user_name !=''){
				 days += `<div onclick="modalon(${id})">${i}
						     <ul>
						        <li> <button id="${id}" class="${id}">${t.t_content}</button></li>
						     </ul>
						   </div>`
						   monthDays.innerHTML = days
			}else{
			
		   	  	 days += `<div onclick="modalon(${id})">${i}
						     <ul>
						        <li> <button id="${id}" class="${id}"></button></li>
						     </ul>
					   	 </div>`
				 monthDays.innerHTML = days
				}
		   }
		}
	})
}


