# BOJ_1941 : 소문난 칠공주
# http://acmicpc.net/problem/1941

def recur(idx, cnt, y_cnt):
    global result

    if y_cnt > 3:   # y가 3명보다 많으면 return (가지치기)
        return

    if cnt == 7:    # 7명을 다 골랐으면
        if connect():   # 연결 되어있는지를 connect 로 확인 -> 연결 되어있으면 True
            result += 1 # 연결 되어있으면 result 에 += 1
        return

    for i in range(idx, 25):    # 인덱스를 반복문으로 순회하면서 pick 되어있다면 continue
        if i in pick:
            continue

        pick.append(i)  # i 를 pick 에 append
        y = i // 5      # i를 5*5 격자의 좌표로 바꿈 채찍아 곰아워
        x = i % 5
        # 재귀중 i + 1, cnt + 1, arr[y][x] == Y라면 True = 1이므로 (y_cnt + 1)
        recur(i + 1, cnt + 1, y_cnt + (arr[y][x] == 'Y'))
        pick.pop()  # 백트래킹

def connect():
    q = [pick[0]]   # 초기화
    visited = [pick[0]] # 방문 칸 기록

    while q:
        now = q[0]  # now 기준으로 시작
        q = q[1:]

        y = now // 5    # now 때의 좌표 계산
        x = now % 5

        for i in range(4):  # 델타 이용 상하좌우 탐색
            ny = y + dy[i]
            nx = x + dx[i]
            ni = ny * 5 + nx

            if 0 <= ny < 5 and 0 <= nx < 5: # 범위 넘어가지 않게

                if ni in pick and ni not in visited:    # pick 에 포함되어 있고 visited 하지 않았으면
                    visited.append(ni)  # 방문
                    q.append(ni)        # q에 추가

    return len(visited) == 7    # visited 가 7이면 연결되있음 = True

dy = [-1, 1, 0, 0]  # 벡터
dx = [0, 0, -1, 1]

arr = [list(input().strip()) for _ in range(5)]
result = 0
pick = []   # 고른 칸들의 인덱스

recur(0, 0, 0)
print(result)