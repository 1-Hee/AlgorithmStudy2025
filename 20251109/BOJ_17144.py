import sys
sys.stdin = open('17144.txt', 'r')

R, C, T = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(R)]
dy = [-1, 1, 0, 0]  # 상하좌우 델타
dx = [0, 0, -1, 1]

air_filter =  []    # 공기청정기의 위치를 찾기 위함
for i in range(R):
    if arr[i][0] == -1:   # 공청기가 있으면
        air_filter.append(i)    # 추가
top, bottom = air_filter[0], air_filter[1]  # 공청기의 윗부분, 아랫부분

def diffuse():  # 확산 함수
    temp_arr = [[0]*C for _ in range(R)]    # 새로운 배열 생성 -> 확산되는 값을 넣음
    for r in range(R):
        for c in range(C):
            if arr[r][c] > 0:   # 미세먼지가 존재한다면(0이 아니라면)
                amount = arr[r][c] // 5  # 확산되는 미세먼지 값 구함
                if amount == 0: # 만일 5보다 작으면 값이 안나오므로 확산이 안됨(continue)
                    continue

                cnt = 0
                for d in range(4):  # 4방향 델타 확인
                    nr = r + dy[d]
                    nc = c + dx[d]
                    if 0 <= nr < R and 0 <= nc < C and arr[nr][nc] != -1:
                        temp_arr[nr][nc] += amount  # 4방향에 amount를 더함
                        cnt += 1
                arr[r][c] -= amount * cnt   # arr의 값에 확산된 값을 뺌

    for r in range(R):
        for c in range(C):
            arr[r][c] += temp_arr[r][c]

def circulate():    # 공청기 작동(순환) 함수

    for r in range(top-1, 0, -1):       # 왼쪽 벽 (공청기 위)
        arr[r][0] = arr[r-1][0]
    for c in range(0, C-1):             # 맨 위 (r = 0)
        arr[0][c] = arr[0][c+1]
    for r in range(0, top):             # 오른쪽 벽
        arr[r][C-1] = arr[r+1][C-1]
    for c in range(C-1, 1, -1):         # 맨 아래 (r = top)
        arr[top][c] = arr[top][c-1]
    arr[top][1] = 0                     # 공청기 바람 나오는곳은 0으로

    # 아래쪽(시계)
    for r in range(bottom+1, R-1):          # 왼쪽 벽 (공청기 아래)
        arr[r][0] = arr[r+1][0]
    for c in range(0, C-1):                 # 맨 아래 (r = R-1)
        arr[R-1][c] = arr[R-1][c+1]
    for r in range(R-1, bottom, -1):        # 오른쪽 벽
        arr[r][C-1] = arr[r-1][C-1]
    for c in range(C-1, 1, -1):             # 맨 위 (r = bottom)
        arr[bottom][c] = arr[bottom][c-1]
    arr[bottom][1] = 0                      # 공청기 바람 나오는곳은 0으로

for _ in range(T):  # T번 반복하면서 두 함수를 실행
    diffuse()
    circulate()

# T번 다 돌고 난 이후 배열을 돌아 0보다 큰 값이 있을경우 그 배열의 값을 result에 더함
result = 0
for r in range(R):
    for c in range(C):
        if arr[r][c] > 0:
            result += arr[r][c]

print(result)