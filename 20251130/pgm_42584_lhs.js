/*
function solution(prices) {
    const n = prices.length;
    const answer = new Array(n).fill(0);
    
    for (let i = 0; i < n; i++) {
        for (let j = i + 1; j < n; j++) {
            answer[i] += 1
            if (prices[j] < prices[i]) {
                break
            }
        }   
    }
    
    
    return answer;
}
*/

function solution(prices) {
    const n = prices.length
    const answer = new Array(n).fill(0)
    const stack = []
    
    for (let i = 0; i < n; i++) {
        while (stack.length > 0 && prices[i] < prices[stack[stack.length - 1]]) {
            const j = stack.pop();
            answer[j] = i - j;
        }
        stack.push(i);
    }
    
    while (stack.length > 0) {
        const j = stack.pop();
        answer[j] = n - 1 - j;
    }
    
    return answer;    
}