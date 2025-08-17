# BOJ_1236 : 성 지키기
# https://www.acmicpc.net/problem/1236

N, M = map(int, input().split())
arr = [input() for _ in range(N)]

wid_count = 0 # 가로 카운트
len_count = 0 # 세로 카운트
result = 0

for i in range(N): # 가로로 행렬 검사, .이 있으면 카운트를 세고 그 값이 M과 같으면 wid_count +1
    count = 0
    for j in range(M):
        if arr[i][j] == '.':
            count += 1
            if count == M:
                wid_count += 1

for j in range(M): # 세로로 행렬 검사, .이 있으면 카운트를 세고 그 값이 N과 같으면 len_count +1
    count = 0
    for i in range(N):
        if arr[i][j] == '.':
            count += 1
            if count == N:
                len_count += 1

if wid_count > len_count: # 두개중에 큰값을 결과에 넣음
    result = wid_count
else:
    result = len_count

print(result)