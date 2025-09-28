import sys
sys.stdin = open('3980.txt', 'r')

T = int(input())

for tc in range(1, 1+T):
    arr = [list(map(int, input().split())) for _ in range(11)]
    visited = [0] * 11  # 일차원 배열로 visited를 받아 검색(시간복잡도 줄이기 위해)
    max_status = 0
    status = 0

    def recur(row):
        global max_status, status
        if row == 11:   # 11명의 선수이기 때문
            max_status = max(max_status, status)    # 능력치의 최대 합을 구하기 위해
            return

        for col in range(11):   # 11개의 대한 col을 반복해서 체크

            if visited[col] == 1:   # 가지치기1. 이미 방문한 곳이면 continue
                continue

            if arr[row][col] == 0:  # 가지치기2. 능력치가 0인건 continue
                continue

            status = status + arr[row][col] # status에 능력을 계속 더함
            visited[col] = 1    # 더할때 마다 그때의 visited[col]을 체크
            recur(row + 1)      # 재귀 (다음 행을 확인)
            status = status - arr[row][col] # 빠꾸뜨렉깅
            visited[col] = 0

    recur(0)
    print(max_status)