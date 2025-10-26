

from collections import deque

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

def bfs(y, x, arr, H, W):

    if arr[y][x] == 0:
        return

    q = deque([(y, x, arr[y][x])])
    arr[y][x] = 0

    while q:
        cy, cx, power = q.popleft()
        for d in range(4):
            for dist in range(1, power):
                ny = cy + dy[d] * dist
                nx = cx + dx[d] * dist
                if ny < 0 or ny >= H or nx < 0 or nx >= W:
                    break
                if arr[ny][nx] == 0:
                    continue
                if arr[ny][nx] > 1:
                    q.append((ny, nx, arr[ny][nx]))
                arr[ny][nx]=0

def dfs(depth, N, H, W, arr):
    remain = 0
    for r in range(H):
        for c in range(W):
            if arr[r][c] > 0:
                remain += 1
    if remain == 0:
        return 0
    if depth == N:
        return remain

    result = float('inf')

    for col in range(W):
        row = -1
        for r in range(H):
            if arr[r][col] > 0:
                row = r
                break
        if row == -1:
            continue

        new_arr = [row[:] for row in arr]

        bfs(row, col, new_arr, H, W)
        gravity(new_arr, H, W)
        result = min(result, dfs(depth + 1, N, H, W, new_arr))
    return result

def gravity(arr, H, W):
    for c in range(W):
        stack = []
        for r in range(H - 1, -1, -1):
            if arr[r][c] > 0:
                stack.append(arr[r][c])
        for r in range(H - 1, -1, -1):
            if stack:
                arr[r][c] = stack.pop(0)
            else:
                arr[r][c] = 0

T = int(input())

for tc in range(1, T + 1):
    N, W, H = map(int,input().split())
    arr = [list(map(int, input().split())) for _ in range(H)]
    result = dfs(0, N, H, W, arr)
    print(f'#{tc} {result}')


