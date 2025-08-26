# BOJ_2798 : 블랙잭
# https://www.acmicpc.net/problem/2798

N, M = map(int,input().split()) # N : 카드의 개수, M : 목표 숫자
arr = list(map(int, input().split())) # 카드에 쓰여있는 수 배멸
max_number = 0 # M을 넘지않은 최고값을 찾기위한 변수
number = 0

# 3중 반복 나생문을 이용, 조건문으로 고르는 세 숫자가 같지않게 조절.
# M을 넘지않은 max값을 구함
for i in range(N):
    for j in range(N):
        for k in range(N):
            if i != j and j != k and k != i:
                number = arr[i]+arr[j]+arr[k]
                if number > max_number and M >= number:
                    max_number = number

print(max_number)