# BOJ_8979 : 올림픽
# https://www.acmicpc.net/problem/8979

N, K = map(int,input().split()) # N : 국가 수, K : 등수를 알고싶은 국가
arr = [list(map(int, input().split())) for _ in range(N)]
Gol = Sil = Bro = 0
count = 0

for i in range(N): # K의 메달 현황을 찾아 각각 Gol, Sil, Bro에 넣는다.
    if arr[i][0] == K:
        Gol = arr[i][1]
        Sil = arr[i][2]
        Bro = arr[i][3]

for j in range(N): # 반복문을 통해 K보다 높은 점수를 가진 국가를 카운트 한다
    G = arr[j][1]
    S = arr[j][2]
    B = arr[j][3]
    if Gol < G or (Gol == G and Sil < S) or (Gol == G and Sil == S and Bro < B):
        count += 1

# 출력시 + 1 하는 이유는 내 앞에 1명이 있으면 2등이기 때문.
print(count + 1)