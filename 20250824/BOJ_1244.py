# BOJ_1244 : 스위치 켜고 끄기
# https://www.acmicpc.net/problem/1244

N = int(input()) # 스위치 개수
arr = list(map(int, input().split())) # 스위치 상태
M = int(input())    # 학생수
Hm_list = [list(map(int, input().split())) for _ in range(M)]   # 학생의 성별, 받은수

for i in Hm_list:
    A = i[1] - 1 # 그냥 인덱스가 너무 더러워서 만든 변수

    if i[0] == 1: # 성별이 남자일 때
        for j in range(1, N+1):
            if j % i[1] == 0 and arr[j-1] == 1: # i[1]의 배수 확인, 상태확인
                arr[j-1] = 0
            elif j % i[1] == 0 and arr[j-1] == 0:
                arr[j-1] = 1

    if i[0] == 2: # 성별이 여자일 때
        if arr[A] == 0: # 중심 숫자 변경
            arr[A] = 1

        elif arr[A] == 1:
            arr[A] = 0

        for k in range(1, N): # 양옆 숫자 변경
            if 0 <= A-k and A+k < N and arr[A-k] == arr[A+k]: # 범위가 인덱스 안넘어가게, 두 값이 같을 때
                if arr[A-k] == 0:
                    arr[A-k], arr[A+k] = 1, 1
                elif arr[A-k] == 1:
                    arr[A-k], arr[A+k] = 0, 0
            else: # 두 값이 다를 때
                break

for l in range(N): # 출력형식을 맞추기 위해(각각 띄어서, 20개씩 끊어서 출력)
    print(arr[l], end=" ")
    if (l+1) % 20 == 0:
        print()