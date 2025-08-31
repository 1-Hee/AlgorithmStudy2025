# BOJ_2578 : 빙고
# https://www.acmicpc.net/problem/2578

my_arr = [list(map(int, input().split())) for _ in range(5)] # 내 빙고판
arr = []
for _ in range(5):
    arr.extend(list(map(int, input().split()))) # 불러주는 숫자를 한 행렬로 만들기 위해

count = 1
result = 0
bingo = 0
speak = 0

for i in range(25):
    bingo = 0
    if result != 0: # 3빙고가 되면 반복문을 더 돌지않고 끝내기 위함
        break

    for j in range(5):
        for k in range(5):
            if arr[i] == my_arr[j][k]: # arr값이 불리고 my_arr안에 그 값을 0으로 만듬
                my_arr[j][k] = 0
                speak += 1 # 몇번 불렀는지 체크하기 위한 변수

                for a in range(5):
                    count = 0
                    for b in range(5): # 가로에서 빙고 검사
                        if my_arr[a][b] == 0:
                            count += 1
                    if count == 5: # 0을 세는 카운트가 5가 되면 bingo 1 추가
                        bingo += 1
                    if bingo == 3: # 빙고가 3이 되면 result에 그 때의 speak 값을 저장
                        result = speak

                for a in range(5): # 세로에서 빙고 검사
                    count = 0
                    for b in range(5):
                        if my_arr[b][a] == 0:
                            count += 1
                    if count == 5:
                        bingo += 1
                    if bingo == 3:
                        result = speak

                count = 0
                for a in range(5): # 오른쪽 아래로 대각선에서의 빙고 검사
                    if my_arr[a][a] == 0:
                        count += 1
                if count == 5:
                    bingo += 1
                if bingo == 3:
                    result = speak

                count = 0
                for a in range(5): # 왼쪽 아래로의 대각선에서의 빙고 검사
                    if my_arr[a][4 - a] == 0:
                        count += 1
                if count == 5:
                    bingo += 1
                if bingo == 3:
                    result = speak

print(result)