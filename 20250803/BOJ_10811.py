# BOJ 10811. 바구니 뒤집기
# URL : https://www.acmicpc.net/problem/10811

# N과 M을 정수로 받고 빈 리스트를 만듬
N, M = map(int,input().split())
pocket_num = []

# 빈 리스트에 1부터 입력받은 N까지 append를 사용해 넣어줌
for i in range(1, N+1):
    pocket_num.append(i)

# 주어진 M번까지 반복, 2줄부터 주어지는 두 수를 i, j에 담고
# pocket_num 리스트 안에 요소를 역순으로 만드는 코드 작성
for _ in range(M):
    i,j = map(int,input().split())
    pocket_num[i-1:j] = pocket_num[i-1:j][::-1]

# 리스트를 언패킹하여 출력
print(*pocket_num)