# BOJ_7576 : 토마토
# https://www.acmicpc.net/problem/7576
from collections import deque

dy = [-1, 1, 0, 0] # 델타
dx = [0, 0, -1, 1]

M, N = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

def recur():
    q = deque()
    for y in range(N):
        for x in range(M):
            if arr[y][x] == 1:  # 처음 익은 토마토의 위치를 찾아서 좌표를 q에 넣는다
                q.append((y, x))

    while q:    # q가 빌때까지 반복
        y, x = q.popleft()  # q 왼쪽부터 하나씩 pop
        for i in range(4):  # 4방향 탐색
            ny = y + dy[i]
            nx = x + dx[i]
            # arr 안에 있고, 값이 0일때
            if 0 <= ny < N and 0 <= nx < M and arr[ny][nx] == 0:
                arr[ny][nx] = arr[y][x] + 1 # 퍼져나가는 기준점 숫자 + 1 (날짜 개념)
                q.append((ny, nx))  # 그때의 x, y값을 q에 append

    result = 0  # 초기 결과값을 0으로
    for y in range(N):
        for x in range(M):
            if arr[y][x] == 0:  # 만일 모든 토마토가 익지 않았을 때(불가능할때)
                print(-1)
                return
            result = max(result, arr[y][x]) # arr값에서 max값을 계속 찾음
    print(result - 1)   # 처음 1에서 +1 한것이므로 날짜 출력시에는 -1
    # 만일 처음에 -1과 1만으로 이루어져있거나 1만으로 이루어져있으면
    # 어차피 max값이 1이고 result - 1이 출력이므로
    # 원하는 0이 출력됨

recur()