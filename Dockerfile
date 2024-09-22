FROM openjdk:17-jdk
# 17-jdk 사용

CMD ["./gradlew","clean","build"]
# 빌드 실행 명령어

VOLUME /tmp
# 도커 컨테이너 내부에 임시 파일을 저장하기 위한 볼륨을 생성
# 사용될 수 있는 곳
# 1. 호스트 시스템 간 데이터 공유
# 2. 컨테이너 간 데이터 공유
# 3. 컨테이너 데이터 지속적 유지
# 임시 파일은 컨테이너가 종료되었을 때 자동으로 삭제
# 이를 통해 디스크 공간 절약가능
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

COPY .env .env

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]