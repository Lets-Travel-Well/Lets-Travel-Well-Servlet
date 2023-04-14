1. 제목 : 알고리즘 적용 기획서 #1

2. 내용 : TSP를 이용한 최단경로 찾기 서비스

3. 적용 알고리즘 : DP, 비트마스킹, DFS, 백트레킹

4. 알고리즘 개요

   1. DP

      - 각 도시를 방문했을 때의 최단 경로를 DP로 계산합니다.
      - 모든 도시를 방문했을 때, 출발 도시로 돌아오는 최단 경로를 계산합니다.
      - 이를 위해, 각 상태를 정의하고 이전 상태에서 현재 상태로 갈 때 드는 비용을 계산합니다.
      - 경로중 최대 길이의 경로를 삭제하여 줍니다.

   2. 비트마스킹

      - 각 도시를 방문했는지 여부를 비트마스킹으로 표현합니다.
      - 이미 방문한 도시를 다시 방문하지 않도록 비트마스킹을 이용합니다.

   3. DFS와 백트레킹

      - DFS를 이용하여 모든 가능한 경로를 탐색합니다.
      - 이미 방문한 도시를 다시 방문하지 않도록 방문한 도시를 체크합니다.
      - 모든 도시를 방문했을 때, 출발 도시로 돌아오는 최단 경로를 계산합니다.
      - 이 때, 백트레킹을 이용하여 불필요한 탐색을 줄일 수 있습니다.

5. 적용 서비스 : MY TRAVEL

6. 적용 서비스 개발 개요

   - SEARCH PLACE 메뉴에서 좋아요를 체크합니다.
   - 체크한 Place들은 MY TRAVEL메뉴에서 확인할 수 있습니다.
   - 경로 생성을 원하는 Place를 체크 후 find 버튼을 누릅니다.
   - 선택된 Place들의 최단경로를 볼 수 있습니다.