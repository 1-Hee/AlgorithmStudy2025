import sys
sys.stdin = open('2589.txt', 'r')

from collections import deque

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

Y, X = map(int, input().split())
arr = [list(input().strip()) for _ in range(Y)]
result = 0

def bfs(y, x):
    global result

    visited = [[0] * X for _ in range(Y)]
    q = deque([(y, x)])
    visited[y][x] = 1

    while q:
        a, b = q.popleft()

        for k in range(4):
            na = a + dy[k]
            nb = b + dx[k]
            if 0 <= na < Y and 0 <= nb < X:
                if not visited[na][nb]:
                    if arr[na][nb] == 'W':
                        continue
                    else:
                        visited[na][nb] = (visited[a][b] + 1)
                        q.append((na, nb))
                        if result < visited[na][nb]:
                            result = visited[na][nb]

for i in range(Y):
    for j in range(X):
        if arr[i][j] == 'W':
            continue
        elif arr[i][j] == 'L':
            bfs(i, j)

print(result-1)
