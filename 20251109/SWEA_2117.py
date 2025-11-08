import sys
sys.stdin = open('2117.txt', 'r')

T = int(input())
for t in range(1, T+1):

    N, M = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(N)]
    result = 0

    for x in range(N):
        for y in range(N):
            for K in range(1, N+2):     # K가 N+2일때 결국에는 다 덮게되기에 N+2까지만
                count = 0
                for i in range(N):
                    for j in range(N):
                        # 범위설정 -> x, y에서 K만큼 거리가 떨어진 곳의 배열 값이 1이라면
                        if abs(i - x) + abs(j - y) < K and arr[i][j] == 1:
                            count += 1

                cost = K * K + (K - 1) * (K - 1)
                if count * M >= cost:   # 손해보지 않는 선에서
                    result = max(result, count) # 최대값

    print(f"#{t} {result}")