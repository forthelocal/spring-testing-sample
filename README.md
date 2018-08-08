## DDD + Spring boot + MyBatis + testing
- DDDを踏襲
- Clean Architectureも意識
- UnitテストはSpringを介さずテストできるようにする

## Libraries
- MyBatis
- Flyway
- Junit5
- beanmother

## How to use
- create databases
  ```
  CREATE DATABASE db_example
  CREATE DATABASE db_example_test
  ```
  
- ./gradlew clean test
  - build/reports/tests/test にテスト結果が出力される
- ./gradlew flywayMigrate  
- ./gradlew bootRun