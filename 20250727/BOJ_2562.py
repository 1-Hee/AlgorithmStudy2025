# BOJ 2562. 최댓값
# URL : https://www.acmicpc.net/problem/2562


num_list = [int(input()) for _ in range(9)]
min_munber = 0
max_number = 0

for i in num_list:
    if i < min_munber