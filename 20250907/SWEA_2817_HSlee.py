# SWEA_2817 : 부분 수열의 합
# https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7IzvG6EksDFAXB

T = int(input())

for tc in range(1, 1 + T):
    N, K = map(int, input().split()) # N : 주어지는 자연수 갯수, K : 타겟넘버
    arr = list(map(int, input().split()))
    count = 0

    def dfs(start, s):
        global count
        if s == K: # s가 K가 되면 count를 더함
            count += 1

        for i in range(start, N): # start부터 N까지 반복
            dfs(i + 1, s + arr[i]) # 재귀 돌면서 start는 1 더하고, s에는 값을 하나씩 합함(완탐)

    dfs(0, 0) # start : index는 0부터 시작, s : 더한 값도 초기값 0부터 시작
    print(f"#{tc} {count}")