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
    .then( (data) => {
    	makeShortesPathToMap(data)
    });
    
});

var overLay = [];
var markers = [];
function makeShortesPathToMap(data) {
    console.log(data);
    markers = [];
    data.forEach((area) => {
        let markerInfo = {
            contentId: area.contentId,
            like: area.scrap,
            title: area.title,
            addr1: area.addr1,
            zipcode: area.zipcode,
            firstImage: area.firstImage,
            latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
        }
        let marker = new kakao.maps.Marker({
            map: map,
            positions: markerInfo.latlng,
        });
        markers.push(marker);
    });
        
    for (var i = 0; i < overLay.length; i++) {
        overLay[i].setMap(null);
    }

    overLay = [];
    addLine(markers);


	
}

var lines = [];
function addLine(markers) {
    var linePath = [];
    for (let i = 0; i < markers.length; i++) {
        linePath.push(markers[i].getPosition());
    }

    var polyline = new kakao.maps.polyline({
        path: linePath,
        strokeWeight: 2,
        strokeColor: 'red',
        strokeOpacity: 0.7,
        strokeStlye: 'solid'
    });

    lines.push(polyline);
    polyline.setMap(map);
}