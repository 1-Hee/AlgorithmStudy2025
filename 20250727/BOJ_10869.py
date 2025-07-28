# BOJ 10869. 사칙연산
# URL : https://www.acmicpc.net/problem/10869

A, B= map(int, input().split()) # " "으로 구분된 두 수 A와 B에 각각 정수값을 입력받음

print(A + B)
print(A - B)
print(A * B)
print(int(A / B)) # 여기에 int를 넣은 이유는 예제 출력값으로 소수는 제외한 정수만 출력이 되기 때문에 그것을 맞춰주기 위해
print(A % B)