# 메추리
다양한 음식 중 기호에 맞는 음식을 추천해주는 안드로이드 앱 프로젝트입니다.  
몇 가지 음식에 대해 평점을 매긴다면, 바로 기호에 맞는 식사 메뉴를 추천받을 수 있습니다.  

### 프로젝트 요약
- 매긴 평점을 바탕으로 음식을 추천받을 수 있습니다.
- 로그인 후에는 평점을 수정할 수 있으며, 다른 제품에 대한 평점을 추가할 수도 있습니다.

### 본인 기여 내용
- 추천 기능 안드로이드 부분 개발
- UI/UX 디자인

### 사용 기술
- Languages : Kotlin
- Networking : Retrofit2
- Asynchronus Task : Coroutine
- Image Loading : Glide

### 시연 영상
- 비회원 추천받기 시연 영상 & 회원가입 시연 영상
<center><img src="https://user-images.githubusercontent.com/79076150/216329177-11ec11a9-3f96-4a90-9bd0-ddc7e271ff16.gif" width="300" height="612"></center>
비회원의 경우 랜덤으로 음식을 추천 받을 수 있습니다.  

회원가입 시에는 편의점 음식 제품에 대한 평점이 5개 필요합니다.

해당 평점은 추천 알고리즘에 사용될 초기 데이터가 됩니다.
　
　
- 평점 수정 & 추천 받기 시연 영상
<center><img src="https://user-images.githubusercontent.com/79076150/216330058-071c59c3-775b-404e-bbee-4acd20f418a3.gif" width="300" height="612"></center>
평점 데이터를 수정할 수 있습니다.  

음식 이름을 검색하고 별점을 수정해주시면, 평점 데이터가 수정됩니다.

추천 받기를 클릭하면 지금까지의 평점 데이터를 통해 추천을 받을 수 있습니다.  

  
### 프로젝트 아키텍처
<center><img src="https://user-images.githubusercontent.com/79076150/216331245-388c659e-85d2-4288-87d3-cd1a9c67d636.png" width="800" height="450"></center>
아키텍처 패턴을 적용하지 않았고, Activity에서 데이터를 불러오고 View를 관리하는 등 여러 로직을 다 작성했습니다.


  
  
