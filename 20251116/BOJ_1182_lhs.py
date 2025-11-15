import sys
sys.stdin = open('1182.txt', 'r')

N, S = map(int, input().split())
arr = list(map(int, input().split()))

count = 0

def dfs(idx, number_sum):
    global count

    if idx == N:                # 만일 모든 idx를 다 돌았다면
        if number_sum == S:     # 그리고 더한값이 S가 되면
            count += 1          # count 해줌
        return

    dfs(idx + 1, number_sum + arr[idx]) # arr의 값을 더함
    dfs(idx + 1, number_sum)            # arr의 값을 더하지 않음

dfs(0, 0)   # idx가 0일때, arr의 값을 더했을때의 값이 0일때부터 dfs

# 만일 S가 0이라면 어떠한 idx라도 안들어갔을 때, 0이 나오므로 이때의 경우를 빼줌
if S == 0:
    count -= 1

print(count)