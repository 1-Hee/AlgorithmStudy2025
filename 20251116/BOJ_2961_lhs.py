import sys
sys.stdin = open('2961.txt', 'r')

N = int(input())

sour = []
bitter = []

for _ in range(N):  # 각각의 리스트에 넣어줌
    x, y = map(int, input().split())
    sour.append(x)
    bitter.append(y)

answer = 10**18 # 신맛(곱)이 최대 10억인 정수이므로 10억x10억을 최대기준으로..

def dfs(idx, s, b, chose):
    global answer

    if idx == N:    # 모든 idx를 다 봤으면
        if chose > 0:   # 그때의 chose가 0보다 크면 (1개 이상을 골랐다면)
            difference = abs(s - b) # 그때의 차이를 절댓값으로(차이가 가장 작은걸 구해야하므로)
            if difference < answer: # 차이가 answer보다 작으면 넣어줌
                answer = difference
        return


    dfs(idx + 1, s * sour[idx], b + bitter[idx], chose + 1) # dfs를 돌 때마다 s를 곱하고, b를 더함

    dfs(idx + 1, s, b, chose)   # 곱하지 않고 그대로 idx만 지나감

dfs(0, 1, 0, 0)

print(answer)