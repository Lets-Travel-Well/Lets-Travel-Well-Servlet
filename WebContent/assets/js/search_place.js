var root = "/lets_travel_well_servlet"; 
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
            console.log(sigunAreaUrl);
            fetch(sigunAreaUrl, { method: 'GET' })
              .then((response) => response.json())
              .then((data) => {
                console.log(data);
                makeOptionForSigun(data);
              });
          } else {
            sel.innerHTML = '<option value="0" selected>검색 할 구군</option>';
          }
        })