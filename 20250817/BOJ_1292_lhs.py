# BOJ_1292 : 쉽게 푸는문제
# https://www.acmicpc.net/problem/1292

A, B = map(int, input().split())

N = 1000 # 정수의 길이가 1000이하이므로 1000개의 수열을 만들어놓음
arr = [] # 수열 담을 리스트
num = 1
result = 0

while len(arr) < N: # while문 이용, 1을 1개, 2를 2개, 3을 3개... 리스트에 담아서 수열을 만듦
    for _ in range(num):
        arr.append(num)
        if len(arr) == N: # 1000개가 되면 break
            break
    num += 1

for i in arr[A-1:B]: # 리스트 안의 A-1부터 B번째까지의 인덱스의 값을 더함(수열이 1부터 시작이므로 A-1)
    result += i

print(result)