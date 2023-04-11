var root = "/ltw";


 var mapContainer = document.getElementById("map"), // 지도를 표시할 div
     mapOption = {
     center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
     level: 7, // 지도의 확대 레벨
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

var overLays = [];
var markerList= [];
var positions;
function makeShortesPathToMap(data) {
    console.log(data);
    positions = [];
    data.forEach((area) => {
        console.log(area);
        let markerInfo = {
            contentId: area.attractionInfoId,
            title: area.title,
            addr1: area.addr1,
            zipcode: area.zipcode,
            firstImage: area.firstImage,
            latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
        }

        positions.push(markerInfo);
    });
        
    displayMarker();

    for (var i = 0; i < overLays.length; i++) {
        overLays[i].setMap(null);   
    }
    overLays = [];
    addLine(markerList);

    for (var j = 0; j < positions.length; j++) {
        var content = '<div class="customoverlay">' +
            '<div class="bg-primary">' +
            ' <span id="numbers">' + (j + 1) + '</span> <span class="title">' + positions[j].title + '</span>' +
            '</div>' +

            '</div>';
        addText(markerList[j].getPosition(), content);
    }


}

function addText(pos, con) {
    var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: pos,
        content: con,
        yAnchor: 1
    });
    overLays.push(customOverlay);
}
function displayMarker() {
    for (var i = 0; i < markerList.length; i++) {
        markerList[i].setMap(null);
    }
    markerList = [];
    for (var i = 0; i < positions.length; i++) {
        
        let marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
        });

        markerList[i] = marker; 
    }
    map.setCenter(positions[0].latlng);
}

	

var lines = [];
function addLine(markers) {
    var linePath = [];
    for (let i = 0; i < lines.length; i++) {
        lines[i].setMap(null);
    }
    for (let i = 0; i < markers.length; i++) {
        linePath.push(markers[i].getPosition());
    }

    var polyline = new kakao.maps.Polyline({
        path: linePath,
        strokeWeight: 2,
        strokeColor: 'red',
        strokeOpacity: 0.7,
        strokeStlye: 'solid'
    });

    polyline.setMap(map);
    lines.push(polyline);
}