# BOJ_2628 : 종이자르기
# https://www.acmicpc.net/problem/2628

w, h = map(int, input().split()) # 가로, 세로의 길이
N = int(input()) # 자르는 횟수
arr = [list(map(int, input().split())) for _ in range(N)] # 자르는 조건
w_arr = [0, w] # 자른 후 가로 좌표 값
h_arr = [0, h] # 자른 후 세로 좌표 값
w_max = 0 # 가로 최대 값
h_max = 0 # 세로 최대 값

for i in arr: # 조건에 맞춰서 잘랐을 때의 가로 좌표, 세로 좌표 구함
    if i[0] == 0:
        h_arr.append(i[1])
    else:
        w_arr.append(i[1])

w_arr.sort() # sort를 하여 오름차순으로 정렬
h_arr.sort()

for j in range(1, len(w_arr)): # 가로 값 중에 최대 값 구하기
    if w_arr[j] - w_arr[j-1] > w_max:
        w_max = w_arr[j] - w_arr[j-1]

for k in range(1, len(h_arr)): # 세로 값 중에 최대 값 구하기
    if h_arr[k] - h_arr[k-1] > h_max:
        h_max = h_arr[k] - h_arr[k-1]

print(w_max * h_max)