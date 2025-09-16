import sys
sys.stdin = open('2806.txt', 'r')

T = int(input())

for tc in range(1, 1+T):
    N = int(input())
    visited = [0] * N   # 일차원 배열로 visited 확인
    cnt = 0

    def check(row):
        for prev_row in range(row):

            if visited[row] == visited[prev_row]:   # 세로열 체크
                return False

            # 열과 행의 차이의 절댓값이 같다 = 현재 col의 대각선이라는 뜻이므로로
            if abs(row- prev_row) == abs(visited[row] - visited[prev_row]):
                return False

        return True

    def Queen(row):
        global cnt

        if row == N:    # row가 N일 경우(모든 가지치기를 통과한 후 행렬 NxN까지 확인 한 경우)
            cnt += 1    # cnt를 더해줌
            return

        for col in range(N):
            visited[row] = col  # 현재 row의 col에 놓았다고 가정하고
            if not check(row):  # 세로와 대각선을 check 함수로 체크해줌
                continue

            Queen(row + 1)

    Queen(0)
    print(f'#{tc} {cnt}')