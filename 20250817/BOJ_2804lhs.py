# BOJ_2804 : 크로스워드 만들기
# https://www.acmicpc.net/problem/2804

A, B = input().split()
loc_A = 0
loc_B = 0
arr = [['.'] * len(A) for _ in range(len(B))] # 처음에 '.'로 채워진 행렬 만듬

for i in range(len(A)):
    for j in range(len(B)):
        if A[i] == B[j]: # A와 B를 하나씩 비교, 일치하는 순간(제일 먼저) 각각의 loc에 입력, break
            loc_A = i
            loc_B = j
            break
    if loc_A != 0 or loc_B != 0: # 하나라도 입력이 되면 break(for문 완전히 빠져나오기 위함)
        break

for k in range(len(B)): # arr에 각각 가로, 세로에 입력
    for l in range(len(A)):
        arr[k][loc_A] = B[k]
        arr[loc_B][l] = A[l]

for row in arr:
    print(*row, sep='') # 출력을 맞추기 위해 반복문 사용. 문자 사이 공백을 없에기 위해 separation을 사용