# BOJ_2477 : 참외밭
# https://www.acmicpc.net/problem/2477

K = int(input())
dirs = [] # 방향 (1: 동, 2: 서, 3: 남, 4: 북)
lens = [] # 길이
max_w, max_h = 0, 0 # 최대 길이, 높이
w = h = 0 # 최저 길이, 높이

for _ in range(6):
    A, B = map(int, input().split())
    dirs.append(A)
    lens.append(B)

for i in range(6):  # dirs를 돌면서 1,2(동,서)방향에서의 최댓값을 구함
    if dirs[i] in (1, 2):
        if lens[i] > max_w:
            max_w = lens[i]
            w = i
    else:           # dirs를 돌면서 1,2(동,서)방향을 제외한 (남,북)방향에서의 최댓값을 구함
        if lens[i] > max_h:
            max_h = lens[i]
            h = i

lg_sq = max_w * max_h   # 큰 사각형의 넓이

sm_sq = lens[(w+3) % 6] * lens[(h+3) % 6]
# 항상 모양이 ┏, ┗, ┛, ㄱ 모양이므로 최대 값 + 3의 값이 최소값이 됨
# % 연산자를 이용해 작은 사각형의 넓이를 구함

print((lg_sq - sm_sq) * K)