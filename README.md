# HazerdousSpace

위험물 안전관리를 위한 플랫폼

## 플랫폼 개요
위험물 관련 사고를 방지하기 위한 플랫폼으로, 사용자가 시각적으로 위험물의 작동여부를 알 수 있고, 청각적으로 위험을 감지할 수 있는 플랫폼이다.

- 위험물이 존재하는 장소에 초음파센서를 설치하여 사람이 다가오는 것을 감지하고, 그 값이 특정거리보다 이내일 경우 지정 이메일로 알람이 가게된다.
- APP으로 위험공간과 근처 접근하는 사람과의 거리를 파악하고, 경고등과 경고음을 APP을 통해 제어(ON/OFF)할 수 있다.
- AWS DynamoDB에 저장되어있는 디바이스의 로그 정보를 날짜와 시간을 선택하여 조회할 수 있다.

### 사용된 부품
- MKRWIFI1010보드
- 부저
- LED
- 초음파센서

## 회로도

### JAVA프로젝트 별 역할
- __GetDeviceLambdaJavaProject__

:

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
-__MainAcitivity.java__

: APP의 첫화면, 로그조회버튼과 디바이스 목록이 있음
-__DeviceAcitivity.java__

: 디바이스를 제어할 수 있으며, 디바이스의 가장 최근 상태를 받아옴
-__ListThingsAcitivity.java__

: 디바이스 목록조회 
-__LogAcitivity.java__

: 날짜와 시간을 선택하여 DynamoDB Logging테이블에서 로그값을 가져옴

