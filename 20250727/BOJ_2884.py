# BOJ 2884. 알람시계
# URL : https://www.acmicpc.net/problem/2884

# H에 시간, M에 분을 입력받음
H, M = map(int, input().split())

if M < 45:
    H -= 1
    M = M+15 # M < 45일경우 H에서 1을빼고 M에 15를 더해줌
else:
    M -= 45 # 나머지의 경우는 M에서 45를 뺌

if H < 0:
    H += 24 # H값이 음수가 될 경우 H에 24을 더해줌

print(H, M) # 값 출력