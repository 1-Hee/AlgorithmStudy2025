# BOJ_7569 : 토마토마토
# https://www.acmicpc.net/problem/7569
from collections import deque

dz = [0, 0, 0, 0, -1, 1]
dy = [-1, 1, 0, 0, 0, 0]
dx = [0, 0, -1, 1, 0, 0]

M, N, H = map(int, input().split())

three_dimension_arr = []
for _ in range(H):
    arr = [list(map(int, input().split())) for _ in range(N)]
    three_dimension_arr.append(arr)

def recur():
    q = deque()
    for z in range(H):
        for y in range(N):
            for x in range(M):
                if three_dimension_arr[z][y][x] == 1:
                    q.append((z, y, x))

    while q:
        z, y, x = q.popleft()
        for i in range(6):
            nz = z + dz[i]
            ny = y + dy[i]
            nx = x + dx[i]
            if 0 <= nz < H and 0 <= ny < N and 0 <= nx < M and three_dimension_arr[nz][ny][nx] == 0:
                    three_dimension_arr[nz][ny][nx] = three_dimension_arr[z][y][x] + 1
                    q.append((nz, ny, nx))

    result = 0
    for z in range(H):
        for y in range(N):
            for x in range(M):
                if three_dimension_arr[z][y][x] == 0:
                    print(-1)
                    return

                result = max(result, three_dimension_arr[z][y][x])
    print(result - 1)

recur()