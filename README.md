# 코루틴이란?
코루틴은 컴퓨터 프로그램 구성 요소 중 하나로 비선점형 멀티태스킹 (non-preemptive multasking)을 수행하는 일반화한 서브루틴(subroutine)이다.
코루틴은 실행을 일시 중단(suspend)하고 재개(resume)할 수 있는 여러 진입 지점(entry point)을 허용한다.

## (1) 서브루틴

- 서브루틴: 어떤 명령어를 모아 이름을 부여해서 반복 호출 할 수 있게 정의한 프로그램 구성 요소. 다른 말로 **함수**라고 한다.
- 객체지향 언어에서는 메소드도 서브루틴이라고 부른다.
- 서브루틴에 진입하는 방법은 오직 하나이다. 해당 함수를 호출하면 서브루틴의 맨 처음부터 실행이 시작된다.
    - 그때마다 활성 레코드(activation record) 라는 것이 스택에 할당되면서 서브루틴 내부의 로컬 변수등이 초기화된다.
    - 반면, 서브루틴 안에서 여러번 return을 사용할 수 있기 때문에 서브루틴이 실행을 중단하고 제어를 호출한 쪽(caller)에게 돌려주는 지점은 여럿 있을 수 있다.
    - 다만 일단 서브루틴에서 반환되고 나면 활성 레코드가 스택에서 사라지기 때문에 실행 중이던 모든 상태를 잃어버린다.
    - 그래서 서브루틴을 여러 번 반복 실행해도(전역 변수나 다른 부수효과가 있지 않는 한) 항상 같은 결과를 반복해서 얻게 된다.

## (2) 비선점형

- 비선점형: 멀티태스킹의 각 작업을 수행하는 참여자들의 실행을 운영체제가 강제로 일시 중단 시키고 다른 참여자를 실행하게 만들 수 없다는 뜻이다.
- 따라서 각 참여자들이 서로 자발적으로 협력해야만 비선점형 멀티태스킹이 제대로 동작 할 수 있다.
- 멀티태스킹은 여러 작업을 동시에 수행하는 것처럼 보이거나 실제로 동시에 수행하는 것이다.
- 따라서 **코루틴이란 서로 협력해서 실행을 주고받으면서 작동하는 여러 서브루틴을 말한다.**

## (3) 코루틴 제어흐름과 서브루틴(일반함수)의 제어흐름

<img width="4320" alt="Coroutine" src="https://github.com/kof99athena/AthenaCoroutine/assets/128768118/754e7492-d9fc-4655-af31-663336d10f5d">

- 코루틴의 대표격인 제네레이터를 예로 들어보자.
    - 어떤 함수 A가 실행되다가 제네레이터인 코루틴 B를 호출하면, A가 실행되던 스레드안에서 코루틴 B의 실행이 시작된다.
    - 코루틴 B는 실행을 진행하다가 실행을 A에 양보한다. (yield라는 명령을 사용한다.)
    - 이때 B가 일반적인 함수라면 로컬변수를 초기화하면서 처음부터 실행을 다시 시작하겠지만, 코루틴이면 이전에 yield로 실행을 양보했던 지점부터 실행을 계속하게 된다.
- **코루틴의 장점**
    - 일반적인 프로그램 로직을 기술하듯이 코드를 작성하고, 상대편 코루틴에 데이터를 넘겨야할 때만 yield를 사용하면 된다.