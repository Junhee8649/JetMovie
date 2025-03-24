# JetMovie

JetMovie는 Clean Architecture와 MVVM 패턴을 적용한 영화 정보 앱입니다. Jetpack Compose를 활용하여 모던한 UI를 구현하였으며, 영화 정보를 검색하고 상세 정보를 확인할 수 있습니다.

## 주요 기능

- 영화 목록 조회 및 검색
- 영화 상세 정보 확인
- 인기 영화 및 최신 영화 탐색
- 카테고리별 영화 필터링

## 사용된 기술

- **UI**: Jetpack Compose, Material3, Coil
- **아키텍처**: Clean Architecture, MVVM
- **네트워크**: Retrofit, OkHttp
- **의존성 주입**: Hilt
- **데이터 직렬화**: Kotlinx Serialization
- **비동기 처리**: Kotlin Coroutines, Flow
- **내비게이션**: Jetpack Navigation Compose

## 클린 아키텍처 구현

이 프로젝트는 클린 아키텍처 원칙을 따라 구현되었으며, 다음과 같은 계층 구조로 이루어져 있습니다.

### 아키텍처 계층

#### 1. 데이터 계층 (Data Layer)
- API나 데이터베이스에서 모든 원시 정보를 가져옵니다 (예: MovieDetailDto)
- 외부 소스와의 통신을 담당합니다
- DTO(Data Transfer Object) 형태로 데이터를 주고받습니다
- 레포지토리 인터페이스를 구현합니다

※ 데이터 계층은 '어떻게 데이터를 가져올 것인가'에 집중합니다. API 호출, 데이터베이스 접근, 캐싱 등의 구체적인 구현을 담당합니다.

#### 2. 도메인 계층 (Domain Layer)
- 애플리케이션의 핵심 비즈니스 로직을 포함합니다
- 앱에 실제로 필요한 정보만 담은 모델(예: MovieDetail)을 정의합니다
- 비즈니스 규칙과 데이터 변환 로직을 포함합니다 (예: 배우 이름을 firstName과 lastName으로 분리)
- 레포지토리 인터페이스를 정의합니다

※ 도메인 계층은 '무엇을 할 것인가'를 정의합니다. 이 계층은 다른 계층에 의존하지 않으며, 순수한 Kotlin/Java 코드로 구성됩니다.

#### 3. UI 계층 (Presentation Layer)
- 사용자 인터페이스와 관련된 모든 요소를 포함합니다
- ViewModel, Composable 함수 등이 이 계층에 포함됩니다
- 도메인 모델을 UI에 적합한 형태로 변환합니다
- 사용자 입력을 처리하고 적절한 비즈니스 로직을 호출합니다

※ UI 계층은 데이터를 '어떻게 표시할 것인가'에 집중합니다. 이 계층은 도메인 계층에만 의존하며, 데이터 계층에 직접 접근하지 않습니다.

### 레포지토리 패턴

#### 레포지토리 인터페이스를 도메인 계층에서 정의하고 데이터 계층에서 구현하는 이유

※ **의존성 역전 원칙(Dependency Inversion Principle)**: 
도메인 계층이 데이터 계층의 구체적인 구현에 의존하지 않고, 데이터 계층이 도메인 계층에서 정의한 인터페이스에 의존하게 함으로써 의존성 방향을 역전시킵니다.

※ **관심사 분리**: 
도메인 계층은 '무엇이 필요한지'를 인터페이스로 정의하고, 데이터 계층은 '어떻게 구현할지'를 담당합니다. 이를 통해 각 계층은 자신의 책임에만 집중할 수 있습니다.

※ **테스트 용이성**: 
레포지토리 인터페이스를 사용하면 실제 데이터 소스 없이도 도메인 계층과 UI 계층을 테스트할 수 있습니다. 테스트를 위한 Mock 구현을 쉽게 만들 수 있습니다.

※ **유연성과 확장성**: 
데이터 소스가 변경되어도 (예: REST API에서 GraphQL로 변경) 인터페이스가 동일하게 유지되면 다른 계층에는 영향을 미치지 않습니다.

## 코드 흐름

```
UI 계층 (Composable, ViewModel)
  ↓
도메인 계층 (MovieDetail, MovieRepository 인터페이스)
  ↓
데이터 계층 (MovieRepositoryImpl, MovieDetailDto)
  ↓
외부 데이터 소스 (API, 데이터베이스)
```

## 앱 실행 방법

1. 프로젝트를 클론합니다.
   ```
   git clone https://github.com/Junhee8649/JetMovie.git
   ```

2. Android Studio에서 프로젝트를 엽니다.

3. `local.properties` 파일에 영화 API 키를 추가합니다.
   ```
   API_KEY=your_api_key
   ```

4. 앱을 빌드하고 실행합니다.

## 더 나은 개발을 위한 추가 작업

- [ ] Unit 테스트 및 UI 테스트 작성
- [ ] 성능 최적화
- [ ] 오프라인 지원을 위한 Room 데이터베이스 통합
- [ ] 다크 모드 지원
- [ ] 다국어 지원

## 기여하기

1. 이 저장소를 포크합니다.
2. 새 기능 브랜치를 생성합니다 (`git checkout -b feature/amazing-feature`).
3. 변경 사항을 커밋합니다 (`git commit -m 'Add some amazing feature'`).
4. 브랜치에 푸시합니다 (`git push origin feature/amazing-feature`).
5. Pull Request를 생성합니다.

## 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.
