var root = "/ltw";


 var mapContainer = document.getElementById("map"), // 지도를 표시할 div
     mapOption = {
     center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
     level: 5, // 지도의 확대 레벨
 };

 var map = new kakao.maps.Map(mapContainer, mapOption);



document.getElementById("find-button").addEventListener("click",() => {
    console.log("find");
    var url = root + "/myattraction";
    console.log(url);
    let attractions = document.getElementsByName("attractionInfoIds");
    let attractionIdInfos = [];
    for (let i = 0; i < attractions.length; i++) {
        console.log(attractions[i].value);
        attractionIdInfos[i] = attractions[i].value;
    }
    
    
    
});