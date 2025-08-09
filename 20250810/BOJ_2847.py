# BOJ 2847. 게임을 만든 동준이
# https://www.acmicpc.net/problem/2847

N = int(input()) # 레벨 갯수 변수

arr = [int(input()) for _ in range(N)] # 레벨 클리어시 얻는 경험치를 배열로 받음

reduce = 0 # 경험치 줄이는 양 점수

for i in range(N-2, -1, -1): # 마지막 2번째 인덱스부터 0번까지 거꾸로 반복

    if arr[i] >= arr[i+1]: # 뒤의 요소보다 앞의 요소가 크면
        number = arr[i+1] - 1 # number에다 뒷 요소에서 -1한 값을 넣어주고
        reduce += arr[i] - number # 경험치 양 줄이는 수를 구하기 위해 앞 요소에서 number을 뺀걸 더하고
        arr[i] = number # 앞 요소를 number로 바꾼다.

print(reduce)