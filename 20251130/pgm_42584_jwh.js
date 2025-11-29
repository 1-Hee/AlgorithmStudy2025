// 문제 풀이에 사용하기 위한 자바스크립트 스택 클래스
class Stack {
  constructor() {
    this.items = [];
  }

  push(item) {
    this.items.push(item);
  }

  pop() {
    return this.items.pop();
  }

  peek() {
    if (this.items.length === 0) {
      return null;
    }
    return this.items[this.items.length - 1];
  }

  getSize() {
    return this.items.length;
  }

  isEmpty() {
    return this.getSize() === 0;
  }
}

// 정답 함수!
function solution(prices) {
    let n = prices.length;
    var answer = new Array(n).fill(0);

    // 주식 가격의 손절 타이밍을 재기 위한 스택!
    let stack = new Stack();
    
    // 시간의 흐름을 for문으로 구현하여 순차적으로 진행한다.
    for(let i = 0 ; i < n ; i++){
        // stack의 요소를 꺼낼 때, 종료 조건을 나타낸 조건식
        // 1) 스택이 비어있지 않을 때까지
        // 2) 남아있는 인덱스를 peek해서 손절 타이밍이 도래했다면, peek 해서 손절하지 않아도 되는 지점까지 다 꺼낸다.

        while(!stack.isEmpty() && prices[i] < prices[stack.peek()]){
            let pIdx = stack.pop(); // 스택에 담긴 값이 곧 인덱스이므로 그대로 사용
            answer[pIdx] = i - pIdx; // 꺼냈을 때, 유효시간은 현재 인덱스(=현재 시각) - 자기 자신의 인덱스(= 매수 시점)로 판정한다.
        }        

        stack.push(i); // 모든 요소를 1번은 push
    }

    // 위의 루프 내에서 처리되지 않은 모든 요소, 즉 매물들은 최대 시각(=n)을 기준으로 자기 자신의 인덱스(= 매수 시점)를 차감하여 계산한다.
    // 단, 이때 계산하는 인덱스는 +1이 가산되므로 이를 보정하기 위해 -1만큼 감산하여 최종 시차를 계산한다.
    while(!stack.isEmpty()){
        let pIdx = stack.pop();
        answer[pIdx] = (n-1) - pIdx;
    }
  
    return answer;
}



let tc = [1, 2, 3, 2, 3];
let ans = solution(tc);
console.dir(ans);