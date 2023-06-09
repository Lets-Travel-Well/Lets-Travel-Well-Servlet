1. 제목 : 알고리즘 적용 기획서 #2

2. 내용 : Hash와 Salt를 사용한 비밀번호 암호화

3. 적용 알고리즘 : DP, 비트마스킹, DFS, 백트레킹

4. 알고리즘 개요

   1. Hash

      - Hash란, 입력값을 고정된 길이의 출력값으로 변환하는 알고리즘입니다.
      - 비밀번호를 입력 받은 후 byte[] 배열로 변환해 줍니다.
      - 암호화를 위한 Salt String을 생성해줍니다.
      - SHA-256이라는 해시 알고리즘을 사용하여 256비트 길이의 고정된 출력값으로 비밀번호를 변환하여 줍니다.

   2. Salt
      - Salt는 비밀번호를 암호화할 때, 원래 비밀번호에 랜덤한 문자열을 추가하여 비밀번호의 보안성을 높이는 기술입니다.
      - 예를 들어, 비밀번호가 "password"이고, Salt가 "ab12cd"라면, "passwordab12cd"라는 문자열을 해시 알고리즘에 입력하여 암호화할 수 있습니다.
      - 이렇게 함으로써, 같은 비밀번호라도 각각 다른 Salt를 사용하여 암호화하면 다른 결과값을 가지게 되므로, 보안성이 높아집니다.
      - 각 사용자에 대한 Salt String을 디비에 저장해둡니다.

5. 적용 서비스 : Sign up, Login

6. 적용 서비스 개발 개요

   1. Sign up

   - Sign up 시 비밀번호와 아이디를 입력합니다.
   - 비밀번호를 byte[] 로 변환 시킵니다.
   - 추가적 보안성을 위한 Salt String을 생성합니다.
   - 비밀번호와 Salt를 인자로 SHA-256 해시 알고리즘을 통해 256비트의 비밀번호로 변환시켜줍니다.
   - 로그인을 위해 Salt를 사용자 PK키와 함께 테이블에 저장해줍니다.

   2. Login

   - Login 시 아이디와 비밀번호를 입력합니다.
   - 로그인 아이디를 인자로 사용자의 PK키를 찾습니다.
   - PK키를 사용하여 사용자별 Salt값을 찾아옵니다.
   - 입력된 비밀번호와 Salt를 인자로 SHA-256 해시 알고리즘을 통해 256비트의 비밀번호로 변환시켜줍니다.
   - DB에 있는 비밀번호와 일치 여부를 판별합니다.
