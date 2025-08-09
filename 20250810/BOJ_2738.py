# BOJ 2738. 행렬 덧셈
# URL : https://www.acmicpc.net/problem/2738

N, M = map(int, input().split()) # 행렬크기 N*M 변수
arrA = [list(map(int, input().split())) for _ in range(N)] # 행렬 A
arrB = [list(map(int, input().split())) for _ in range(N)] # 행렬 B
arr_result = [[0]*M for _ in range(N)] # A와 B를 더할 빈 행렬

for i in range(N):

    for j in range(M):
        arr_result[i][j] = arrA[i][j] + arrB[i][j]
        # arr_result의 i,j에 들어갈 요소는 행렬A의 i,j 요소 + 행렬B의 i,j 요소

for row in arr_result: # arr_result의 행렬을 반복해서 언패킹(출력형식 맞추기 위함)
    print(*row)
