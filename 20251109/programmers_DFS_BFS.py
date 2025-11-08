from collections import deque

def solution(maps):
    N = len(maps)       # 주어진 행의 길이
    M = len(maps[0])    # 주어진 열의 길이

    dx = [-1, 1, 0, 0]  # 상하좌우순서 델타
    dy = [0, 0, -1, 1]

    q = deque()         # 덱 사용
    q.append((0, 0))    # q에 0,0을 넣음 <- 무조건 0,0에서 시작하기 때문

    while q:    # q가 빌 때까지 반복
        x, y = q.popleft()

        for i in range(4):  # 델타 탐색
            nx = x + dx[i]
            ny = y + dy[i]


            if 0 <= nx < N and 0 <= ny < M and maps[nx][ny] == 1:   # 범위지정
                maps[nx][ny] = maps[x][y] + 1 # 델타로 확인한 부분에 += 1
                q.append((nx, ny))  # 그 때의 좌표를 다시 q에 append

    if maps[N-1][M-1] != 1:     # 마지막 (N-1, M-1)부분이 -1이 아니면(막혀있지 않으면)
        return maps[N-1][M-1]   # 출력
    else:                       # 만일 -1이면(막혀있어서 도달하지 못했다면)
        return -1               # -1 출력
