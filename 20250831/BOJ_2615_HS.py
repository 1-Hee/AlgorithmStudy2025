# BOJ_2615 : 오목
# https://www.acmicpc.net/problem/2615

arr = [list(map(int, input().split())) for _ in range(19)]
B_count = 0
W_count = 0
Winner = 0
x, y = 0, 0

dr = [1, -1]
dc = [1, 1]

for i in range(19):
    B_count = 0
    W_count = 0
    for j in range(19):
        if arr[i][j] == 1:
            B_count += 1
            W_count = 0
            if B_count == 5:
                if (j + 1 >= 19 or arr[i][j + 1] != 1) and (j - 5 < 0 or arr[i][j - 5] != 1):
                    if Winner == 0:
                        Winner = 1
                        x, y = i + 1, (j - 4) + 1
        elif arr[i][j] == 2:
            W_count += 1
            B_count = 0
            if W_count == 5:
                if (j + 1 >= 19 or arr[i][j + 1] != 2) and (j - 5 < 0 or arr[i][j - 5] != 2):
                    if Winner == 0:
                        Winner = 2
                        x, y = i + 1, (j - 4) + 1
        else:
            W_count = 0
            B_count = 0

for i in range(19):
    B_count = 0
    W_count = 0
    for j in range(19):
        if arr[j][i] == 1:
            B_count += 1
            W_count = 0
            if B_count == 5:
                if (j + 1 >= 19 or arr[j + 1][i] != 1) and (j - 5 < 0 or arr[j - 5][i] != 1):
                    if Winner == 0:
                        Winner = 1
                        x, y = (j - 4) + 1, i + 1
        elif arr[j][i] == 2:
            W_count += 1
            B_count = 0
            if W_count == 5:
                if (j + 1 >= 19 or arr[j + 1][i] != 2) and (j - 5 < 0 or arr[j - 5][i] != 2):
                    if Winner == 0:
                        Winner = 2
                        x, y = (j - 4) + 1, i + 1
        else:
            W_count = 0
            B_count = 0

for i in range(19):
    for j in range(19):
        if arr[i][j] == 1:
            B_count += 1
            W_count = 0
            for l in range(2):
                pr = i - dr[l]
                pc = j - dc[l]
                if 0 <= pr < 19 and 0 <= pc < 19 and arr[pr][pc] == 1:
                    continue

                cnt = 1
                for k in range(1, 5):
                    nr = i + dr[l] * k
                    nc = j + dc[l] * k
                    if 0 <= nr < 19 and 0 <= nc < 19 and arr[nr][nc] == 1:
                        cnt += 1
                    else:
                        break

                if cnt == 5:
                    nr = i + dr[l] * 5
                    nc = j + dc[l] * 5
                    if 0 <= nr < 19 and 0 <= nc < 19 and arr[nr][nc] == 1:
                        continue

                    if Winner == 0:
                        Winner = 1
                        x, y = i + 1, j + 1

for i in range(19):
    for j in range(19):
        if arr[i][j] == 2:
            B_count = 0
            W_count += 1
            for l in range(2):
                pr = i - dr[l]
                pc = j - dc[l]
                if 0 <= pr < 19 and 0 <= pc < 19 and arr[pr][pc] == 2:
                    continue

                cnt = 1
                for k in range(1, 5):
                    nr = i + dr[l] * k
                    nc = j + dc[l] * k
                    if 0 <= nr < 19 and 0 <= nc < 19 and arr[nr][nc] == 2:
                        cnt += 1
                    else:
                        break

                if cnt == 5:
                    nr = i + dr[l] * 5
                    nc = j + dc[l] * 5
                    if 0 <= nr < 19 and 0 <= nc < 19 and arr[nr][nc] == 2:
                        continue

                    if Winner == 0:
                        Winner = 2
                        x, y = i + 1, j + 1

if Winner == 0:
    print(Winner)
else:
    print(Winner)
    print(x, y)