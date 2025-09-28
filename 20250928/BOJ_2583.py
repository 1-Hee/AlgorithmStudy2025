import sys
sys.stdin = open ('2583.txt', 'r')

M, N, K = map(int, input().split()) # M : 행, N : 열, K : 직사각형갯수
arr = [[0] * N for _ in range(M)]
dy = [-1, 1, 0, 0] # 델타
dx = [0, 0, -1, 1]

for _ in range(K):
    x1, y1, x2, y2 = map(int, input().split())
    for y in range(y1, y2):
        for x in range(x1, x2):
            arr[y][x] = 1   # 두 꼭짓점으로 만들어진 사각형 범위 안의 arr를 1로 채움

visited = [[0] * N for _ in range(M)]
lst = []    # 각 영역의 넓이를 받을 리스트

for i in range(M):
    for j in range(N):
        #   arr를 돌면서 0인 곳(사각형 범위가 아닌)과 visited가 0인곳(중복체크방지)를 찾음
        if arr[i][j] == 0 and visited[i][j] == 0:
            q = [(i, j)]    # 그 때의 좌표를 q에 넣고
            visited[i][j] = 1   # 방문표시
            head = 0    # list를 queue로 쓰기 때문에 만든 head
            size = 0    # 사각형 범위에 포함되지 않는 곳의 범위

            while head < len(q):    # q에서 꺼낼 좌표가 있는 경우(head가 좌표를 가리킴)
                y, x = q[head]  # q에서 head 인덱스의 값을 y, x로 함
                head += 1
                size += 1   # head와 size를 처음에 더하는 이유는 애초에 위에서 0부터 시작하기 때문에

                for k in range(4):  # 델타 이용 고른 y,x의 4방향 탐색
                    ny = y + dy[k]
                    nx = x + dx[k]
                    if 0 <= ny < M and 0 <= nx < N: # 범위를 넘어가지 않게
                        if arr[ny][nx] == 0 and not visited[ny][nx]:    # arr 안의 값이 0(사각형과 겹치치 않거나)이거나 방문하지 않았다면
                            visited[ny][nx] = 1 # 방문 처리
                            q.append((ny, nx))  # q에 append(나중에 이 좌표를 기준으로 다시 탐색)

            # while을 다 돌면(처음 고른 0 주변에 0을 다 방문 한 경우)
            lst.append(size)    # 그때의 size를 lst에 append

lst.sort()  # 출력을 오름차순으로 만들기 위한 sort 작업
print(len(lst))
print(*lst)