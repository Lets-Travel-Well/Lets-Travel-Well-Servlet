var root = "/ltw";


 var mapContainer = document.getElementById("map"), // 지도를 표시할 div
     mapOption = {
     center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
     level: 5, // 지도의 확대 레벨
 };

 var map = new kakao.maps.Map(mapContainer, mapOption);



document.getElementById("find-button").addEventListener("click",() => {
    console.log("find");
    var url = root + "/myattraction?action=find";
    console.log(url);
    let attractions = document.getElementsByName("attractionInfoIds");
    let attractionIdInfos = [];
    for (let i = 0; i < attractions.length; i++) {
        console.log(attractions[i].value);
        if(attractions[i].checked) {
        	attractionIdInfos.push(attractions[i].value);
        }
        
    }

    fetch(url,
    {
        method: "POST",
        body: '['+attractionIdInfos+']',
        dataType: "json",
        contentType: 'application/json; charset=utf-8',
    })
    .then(function(res){ return res.json(); })
    .then(function(data){ alert( JSON.stringify( data ) ) })

    
    
    
});