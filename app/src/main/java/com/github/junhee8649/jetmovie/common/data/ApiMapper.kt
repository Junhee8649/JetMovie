package com.github.junhee8649.jetmovie.common.data

// DTO(Data Transfer Object)와 도메인 모델 간의 매핑 규칙을 정의.
// 제네릭 인터페이스 사용했기에 UserDto와 User 도메인 모델을 매핑할 때도 동일한 ApiMapper 인터페이스를 사용 가능
// 여러 도메인 모델에서 공통적으로 사용할 수 있도록 매퍼를 중앙에서 관리함 그래서 common 패키지에 있는 것

interface ApiMapper<Domain,Entity> {
    fun mapToDomain(apiDto:Entity):Domain
    // 데이터베이스 저장 로직이 추가되면 이러한 매핑 함수를 추가하여 확장 가능
    //fun mapFromDomain(domain: Domain): Entity
}