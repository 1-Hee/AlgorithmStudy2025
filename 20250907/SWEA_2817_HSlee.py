# SWEA_2817 : 부분 수열의 합
# https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7IzvG6EksDFAXB

T = int(input())

for tc in range(1, 1+ T):
    N, K = map(int, input().split())
    arr = list(map(int, input().split()))
    count = 0


    def dfs(st, s):
        global count
        if s == K:
            count += 1

        for i in range(st, N):
            dfs(i + 1, s + arr[i])


    dfs(0, 0)
    print(f"#{tc} {count}")