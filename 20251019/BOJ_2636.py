import sys
sys.stdin = open('2636.txt', 'r')

from collections import deque

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

Y, X = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(Y)]

def bfs():
    visited = [[0] * X for _ in range(Y)]
    q = deque([(0, 0)]) # 처음 0,0 기준으로 q에 넣고 돌림
    visited[0][0] = 1   # 0,0을 넣었기 때문에 방문 처리

    while q:
        y, x = q.popleft()
        for k in range(4):  # 상하좌우 델타 확인
            ny = y + dy[k]
            nx = x + dx[k]
            if 0 <= ny < Y and 0 <= nx < X:
                if not visited[ny][nx]:     # 방문하지 않은곳이면
                    if arr[ny][nx] == 0:    # 델타 확인한 곳이 공기이면(0이면)
                        visited[ny][nx] = 1 # 방문 기록
                        q.append((ny, nx))  # 그 좌표를 q에 append
                    elif arr[ny][nx] == 1:  # 델다 확인한 곳이 치즈이면(1이면)
                        arr[ny][nx] = 2     # 2를 넣어줌

time = 0
last = 0

while True:
    bfs()

    melt = 0
    for i in range(Y):
        for j in range(X):  # arr 탐색
            if arr[i][j] == 2:  # 2인 부분 있으면 0으로 바꿈(녹아없어짐)
                arr[i][j] = 0
                melt += 1       # 그때마다 melt에 += 1
    if melt == 0:   # melt == 0일때 -> 녹은 곳이 없다 -> 이미 다 녹았다
        break

    last = melt     # 다 녹아서 break가 되면 last에는 이전에 마지막으로 남은 치즈 수가 입력되게 됨
    time +=1

print(time)
print(last)



