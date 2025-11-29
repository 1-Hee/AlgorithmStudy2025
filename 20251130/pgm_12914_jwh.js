
// 피보나치 수열을 살짝 카피한 dp 함수
function solution(n) {
    if(n == 0) return 0;
    if(n == 1) return 1;
    if(n == 2) return 2;

    const MODE = 1234567; // 문제에서 요구한 MOD 값!

    // dp 배열을 N+1로 관리하여 'n'으로 인덱스 조회하려고 한칸 더 생성
    let arr = new Array(n+1).fill(0);  
    // 점화식의 시작
    arr[0] = 0;
    arr[1] = 1; // 최대 길이가 1일때, 점프할 수 있는 경우는 1칸 점프한 경우 하나 뿐이므로 1
    arr[2] = 2; // 최대 길이가 2일때, 점프할 수 있는 경우는  {1+1}, {2}  두 가지 경우이므로 2

    // 나머지 임의의 자연수 n에 대한 경우의 수의 계산은 아래의 수식을 만족한다
    // F(n) = F(n-1) + F(n-2)
    // 1과 2에 대한 점화식을 수립했으므로, 이를 응용해 아래와 같이 DP 식을 계산함.
    for(let i = 3; i <= n; i ++){
        arr[i] = ((arr[i-1] + arr[i-2]) % MODE)
    }    
    return arr[n];
}


// 로직 검증용 테스트 코드!
let test_case = [4, 3, 10, 20, 30, 100]
let ans_case = [5, 3, 89, 10946, 111702, 496748]

for(let i = 0 ; i < test_case.length ; i++)
{
    let input = test_case[i];
    let ans = solution(input);
    console.log("input -> " + input + ", ans -> " + ans);
    if(ans == ans_case[i]) {
        console.log("PASS");
    } else {
        console.log("FAIL");       
    }
}

