# HazerdousSpace Management Platform

## 위험물 안전관리를 위한 플랫폼

### 플랫폼 개요

__위험물 관련 사고를 방지하기 위한 플랫폼으로, 사용자가 시각적으로 위험물의 작동여부를 알 수 있고, 청각적으로 위험을 감지할 수 있는 플랫폼이다.__

- 위험물이 존재하는 장소에 초음파센서를 설치하여 사람이 다가오는 것을 감지하고, 그 값이 특정거리보다 이내일 경우 지정 이메일로 알람이 가게된다.
- APP으로 위험공간과 근처 접근하는 사람과의 거리를 파악하고, 경고등과 경고음을 APP을 통해 제어(ON/OFF)할 수 있다.
- AWS DynamoDB에 저장되어있는 디바이스의 로그 정보를 날짜와 시간을 선택하여 조회할 수 있다.

### 플랫폼 구조도

![구조도](https://user-images.githubusercontent.com/50151242/70862009-a8b29080-1f79-11ea-9b89-91ab37365d65.png)

### 플랫폼 주요 기능

- __거리감지__

  :초음파 센서를 통해 위험공간과 사람사이의 거리 측정

- __최신상태조회__

  :가장 최근의 device상태를 조회 할 수 있음

- __로그값조회__

  :distance 값에 따라 BUZZER제어를 어떻게 할 것인지 정함

- __이메일 알림__

  : distance 값이 50cm 미만일 경우, 거리값을 알려주는 메일을 보냄

- __경보등 제어__

  : 이벤트 발생 조건에 따라 BUZZER를 제어함

- __경보음 제어__

  :이벤트 발생 조건에 따라 BUZZER를 제어함

### 사용된 부품
- MKRWIFI1010보드
- 부저
- LED
- 초음파센서

### 회로도
![프리징](https://user-images.githubusercontent.com/50151242/70861889-c41c9c00-1f77-11ea-9307-894812bdd0de.png)

### JAVA프로젝트 별 역할

[https://github.com/Shinyelin/HazerdousSpace/tree/master/JavaProject]

- __GetDeviceLambdaJavaProject__

  : 디바이스 

- __ListingDeviceLambdaJavaProject__

  : AWS에 저장된 디바이스 목록 조회

- __LogDeviceLambdaJavaProject__

  : 기간별 로그값 조회

- __MonitoringLambda__

  : 알림 이메일 전송

- __RecordingDeviceDataJavaProject2__

  : DynamoDB 테이블명 Logging에 로그값 조회

- __UpdateDeviceLambdaJavaProject__

  : 디바이스의 상태 업데이트

### 아두이노 소스코드

[https://github.com/Shinyelin/HazerdousSpace/tree/master/AWS_IoT_Space]

- __AWS_IoT_Space__

  : 디바이스를 제어하고 값을 받아오는 주요 코드
- __Buzzer.h__

  : Buzzer를 제어하는 코드
- __Buzzer.cpp__

  : Buzzer를 제어하는 코드
- __Led.h__

  : LED를 제어하는 코드
- __Led.cpp__

  : LED를 제어하는 코드
- __arduino_secrets.h__

  : certification, ssid, password 설정하는 코드

### 안드로이드스튜디오 JAVA코드

[https://github.com/Shinyelin/HazerdousSpace/tree/master/Android-RestAPI-master]

-__MainAcitivity.java__

: APP의 첫화면, 로그조회버튼과 디바이스 목록이 있음

-__DeviceAcitivity.java__

: 디바이스를 제어할 수 있으며, 디바이스의 가장 최근 상태를 받아옴

-__ListThingsAcitivity.java__

: 디바이스 목록조회 

-__LogAcitivity.java__

: 날짜와 시간을 선택하여 DynamoDB Logging테이블에서 로그값을 가져옴

## APP


### APP 화면

- 메인화면

![app_main](https://user-images.githubusercontent.com/50151242/70861898-d991c600-1f77-11ea-9ab9-1b94ab2ecfd3.png)


- 로그값 조회

![app_log](https://user-images.githubusercontent.com/50151242/70861896-d8f92f80-1f77-11ea-8fd6-b4f7cd4501b3.png)


- 최근상태조회 및 제어

![app_control](https://user-images.githubusercontent.com/50151242/70861897-d991c600-1f77-11ea-9d16-589c95be1816.png)


### APP 기능

- __장소별관리__

  :메인화면에서 원하는 장소를 선택하여
   로그값 조회 및 경고등,경고음 제어 가능

- __로그조회__

  :날짜와 시간을 택해서 로그값을 조회할 수 있음

- __경고등 제어__

  :위험물이 내부에 있을 때 LED로 표시
   LED ON/OFF 제어 가능

- __경고음 제어__

  :위험물근처 사람 접근 시 부저로 알람
   부저 ON/OFF 제어 가능

