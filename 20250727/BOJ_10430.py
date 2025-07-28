# BOJ 10430. 나머지
# URL : https://www.acmicpc.net/problem/10430

A, B, C = map(int, input().split()) # " "로 구분된 세 수 A, B, C를 정수값으로 입력받음

print((A + B) % C) # 각 주어진 값을 구하는 식을 작성함
print(((A % C) + (B % C)) % C)
print((A * B) % C)
print(((A % C) * (B % C)) % C)