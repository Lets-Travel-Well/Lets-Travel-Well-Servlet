var root = "/ltw";
let sel = document.getElementById("search-sigun");
function makeOptionForSigun(data) {
  let option = "";
    data.forEach((area) => {
      option += "<option value=" + area['gugunCode'] + ">" + area['gugunName'] + "</option>";
    });
    sel.innerHTML = option;
  }


// 지역 이 선택된 경우 
document.getElementById("search-area").addEventListener("change", function () {
// 지역이 변경된 경우 
  let areaCode = document.getElementById("search-area").value;

  if (areaCode != 0) {
    let sigunAreaUrl = root + "/attraction?action=guguns&sidoCode=" + areaCode;
  fetch(sigunAreaUrl, { method: 'GET' })
    .then((response) => response.json())
    .then((data) => {
      makeOptionForSigun(data);
    });
  } else {
    sel.innerHTML = '<option value="0" selected>검색 할 구군</option>';
  }
})

// search 버튼이 눌린경우 
    // 검색 버튼을 누르면..
    // 지역, 유형, 검색어 얻기.
    // 위 데이터를 가지고 공공데이터에 요청.
    // 받은 데이터를 이용하여 화면 구성.
    document.getElementById("btn-search").addEventListener("click", () => {
      let searchUrl = root + "/attraction?action=search";
      
      let sidoCode = document.getElementById("search-area").value;
      let gugunCode = document.getElementById("search-sigun").value;
      let contentTypeId = document.getElementById("search-content-id").value;
  
      if (parseInt(sidoCode)) {
          searchUrl += `&sidoCode=${sidoCode}`;
      } else {
          alert("지역을 선택해주세요.");
      }
      if (parseInt(gugunCode)) {
          searchUrl += `&gugunCode=${gugunCode}`;
      } else {
          alert("시군을 선택해주세요.");
      }
      if (parseInt(contentTypeId)) {
          searchUrl += `&contentTypeId=${contentTypeId}`;
      } else {
          alert('컨텐츠를 선택해주세요.');
      }    
  
      fetch(searchUrl)
          .then((response) => response.json())
        .then((data) => {
          makeList(data)
        }
      );
  });
  
  var positions; // marker 배열.
  function makeList(data) {
    //  응답받은 json data = > postion
      let trips = data; // trips => 여행지 정보들 출력 
      positions = [];
    trips.forEach((area) => {
          // title, addr1, zipcode, firstImage, latitude, longitude, 
          let markerInfo = {
            title: area.title,
            addr1: area.addr1,
            zipcode: area.zipcode,
            firstImage: area.firstImage,
            latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
          };
        positions.push(markerInfo);
      });
      displayMarker();
  }
  
// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
    level: 5, // 지도의 확대 레벨
    };
  


// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

var ovList= [];
function displayMarker() {
  // 마커 이미지의 이미지 주소입니다

  for (var i = 0; i < positions.length; i++) {
  
    // 마커를 생성합니다
    let marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: positions[i].latlng, // 마커를 표시할 위치
    });
    
    let content = '<div class="wrap">' + 
      '    <div class="info">' + 
      '        <div class="title">' + 
                positions[i].title +
      '          <div class="close" onclick="closeOverlay('+i+')" title="닫기"></div>' + 
      '        </div>' + 
      '        <div class="body">' + 
      '            <div class="img">' +
      '                <img src="'+positions[i].firstImage+'" width="73" height="70">' +
      '           </div>' + 
      '            <div class="desc">' + 
      '                <div class="ellipsis">'+positions[i].addr1+'</div>' + 
      '                <div class="jibun ellipsis">(우)'+positions[i].zipcode+'</div>' + 
      '            </div>' + 
      '        </div>' + 
      '    </div>' +    
      '</div>';
    
      // 마커 위에 커스텀오버레이를 표시합니다
    // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
    let overlay = new kakao.maps.CustomOverlay({
        content: content,
        position: marker.getPosition()       
    });

    kakao.maps.event.addListener(marker, 'click', function () {
    	overlay.setMap(map);
      });
    ovList[i] = overlay;

//    kakao.maps.event.addListener(marker, 'mouseout', function () {
//      setTimeout(function () {
//    	  overlay.setMap();
//      });
//    });

  
  }
  // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
  map.setCenter(positions[0].latlng);
}

function closeOverlay(i) {
    ovList[i].setMap(null);     
}



