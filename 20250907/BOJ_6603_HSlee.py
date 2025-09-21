# BOJ_6603 : 로또
# https://www.acmicpc.net/problem/6603

while True:
    data = list(map(int, input().split()))
    K = data[0] # 입력을 리스트로 받아 index 0이 0일때 break
    if K == 0:
        break
    S = data[1:] # 만일 0이 아닌 값이면 S에 index 1부터 끝까지 담음
    pick = [0] * 6 # 빈 리스트(로또 번호 추출)

    def backtrack(x, s):
        if x == 6: # 뽑은 숫자의 갯수가 6자리이면 출력
            print(*pick)

        else: # 그렇지 않은 경우 그 인덱스 0부터 재귀 돌때마다 1씩 더함
            for i in range(s, K):
                pick[x] = S[i]
                backtrack(x + 1, i + 1)

    backtrack(0, 0) # k : 카드갯수 0부터 시작, start : 시작인덱스 0부터 시작
    print() # 출력마다 띄어쓰기 위함