# BOJ_12927 : 배수 스위치
# https://www.acmicpc.net/problem/12927

light_list = list(input()) # 전구를 list로 받음
N = 0 # 전구의 갯수를 구하기위한 변수
count = 0 # 불을 몇번 껐다켜야하는지 입력할 변수

for _ in light_list: # 전구의 총 갯수 구하는 반복문
    N += 1

for i in range(N):
    if light_list[i] == 'Y': # 반복문 돌면서 Y를 만나면 그때의 인덱스에 대해서
        count += 1 # 몇번 바꾸는지 카운트
        for j in range(i, N, i+1): # j의 범위를 인덱스부터 끝까지 인덱스+1칸씩 띄어서 바꾸는걸 반복
            if light_list[j] == 'Y':
                light_list[j] = 'N'
            else:
                light_list[j] = 'Y'

print(count)
