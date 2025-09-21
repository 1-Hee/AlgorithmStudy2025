# BOJ_2210 : 숫자판 점프
# https://www.acmicpc.net/problem/2210

def recur(y, x, number):
    if len(number) == 6: # 6자리의 수를 구해야 하므로 number의 개수가 6개일 경우 result에 add하고 return
        result.add(number)
        return

    if len(number) > 6: # 만일 6개 이상이 되면 return (가지치기)
        return

    for i in range(4):  # 4방향에 대해서 한칸씩 확인
        ny = y + dy[i]
        nx = x + dx[i]

        if ny < 0 or ny >= 5 or nx < 0 or nx >= 5: # arr 범위를 넘지 않게 범위 조절
            continue

        recur(ny, nx, number + arr[ny][nx]) # 6자리가 될 때 까지(return까지) 재귀

dy = [-1, 1, 0, 0] # 델타 이용(상하좌우)
dx = [0, 0, -1, 1]

arr = [list(input().split()) for _ in range(5)] # 문자열로 받음(set을 사용하기 위함)
result = set() # 나중에 결과를 result에 add하여 중복을 지울것임.

for start_y in range(5):
    for start_x in range(5):
        recur(start_y, start_x, arr[start_y][start_x])

print(len(result)) # result의 len을 출력