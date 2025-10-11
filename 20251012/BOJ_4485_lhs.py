# BOJ_4485 : 녹색 옷 입은 애가 젤다지?
# https://www.acmicpc.net/problem/4485

import sys
sys.stdin = open('4485.txt', 'r')

from heapq import heappush, heappop

def dijkstra(arr, N):
    INF = 10**9
    dist = [[INF] * N for _ in range(N)]
    visited = [[0] * N for _ in range(N)]
    dist[0][0] = arr[0][0]  # 처음 위치를 arr[0][0]으로 넣음

    pq = []
    heappush(pq, (arr[0][0], 0, 0)) # 처음 arr[0][0]을 pq에 push

    while pq:
        cost, y, x = heappop(pq)

        if visited[y][x]:   # 방문했던곳이면 continue
            continue

        visited[y][x] = 1   # 아니면 visited = 1 하고 진행

        for k in range(4):  # 4방향 델타를 탐색
            ny = y + dy[k]
            nx = x + dx[k]
            if 0 <= ny < N and 0 <= nx < N: # 행렬 범위안의 4방향에 대해서
                next_cost = cost + arr[ny][nx]  # next_cost를 설정 (이전에 있던 곳 + 옮긴 곳에서 뺏기는 코인)
                if next_cost < dist[ny][nx]:    # dist보다 작으면
                    dist[ny][nx] = next_cost    # 그 자리에 next_cost를 넣어 거기까지 가는길에서 뺏기는 총 코인을 넣음
                    heappush(pq, (next_cost, ny, nx))

    return dist[N - 1][N - 1]   # 출력은 N-1, N-1까지 가는거니까 그 부분을 return

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]
testcase = 0
while True:
    N = int(input())
    if N == 0:
        break
    arr = [list(map(int, input().split())) for _ in range(N)]
    testcase += 1

    result = dijkstra(arr, N) # return 한 값을 result로
    print(f'Problem {testcase}: {result}')