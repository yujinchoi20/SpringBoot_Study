## 일정 엔티티 추가 생성

#### Character <-> Item 양방향 매핑

-> Item(다)이 연관관계의 주인이 된다. @ManyToOne 과 @OneToMany(mappedBy)로 연결, 이 부분이 헷갈렸는데 주인을 Character라고 생각해서 헷갈린 것 같음. 

-> Item을 주인이라고 생각하고 다시 매핑함!

#### Member <- Profile 

-> 일대일 관계에서 어느쪽이 주인이 되어야 할지 생각해봤는데, Member 객체가 연관관계의 주인이고 Profile에서 Member에 대한 정보를 가지고 있어야 하는게 맞는지 반대가 맞는지 헷갈리네요...

#### Task, Timer

-> 컬럼 명 (start, end) -> (start_time, end_time) 변경, end 속성명 사용 시 DDL 명령어에 END 때문에 테이블 생성이 안됨.
