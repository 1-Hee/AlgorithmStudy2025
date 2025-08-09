# BOJ 2953. 나는 요리사다
# https://www.acmicpc.net/problem/2953

arr = [list(map(int, input().split())) for _ in range(5)] # 주어진 점수를 행렬로 받음
max_point = 0 # 최고점수
winner = 0 # 우승자 번호

for i in range(5): # 5명의 참가자가 정해져 있으므로 범위 5
    point = 0 # 초기 점수를 0점

    for j in range(4): # 주어진 점수는 항상 4개이므로 범위 4
        point += arr[i][j] # 요소의 점수를 point에 더함

        if max_point < point: # 만일 다 더한 point가 최고점수이면
            max_point = point # 최고점수에 point의 값을 입력
            winner = i+1 # 그때의 인덱스+1을 우승자 번호로 지정

print(winner, max_point)