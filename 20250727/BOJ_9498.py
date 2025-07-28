# BOJ 9498. 시험 성적
# URL : https://www.acmicpc.net/problem/9498

score = int(input()) # 시험 성적값을 정수로 입력받음

if score >= 90: # if 조건문을 활용해 입력받은 성적 값이 90이상일 경우 
    print('A') # A를 출력
elif 89 >= score >= 80: # 입력받은 성적 값이 80이상, 89이하일 경우 
    print('B') # B를 출력
elif 79 >= score >= 70: # 입력받은 성적 값이 70이상, 79이하일 경우 
    print('C') # C를 출력
elif 69 >= score >= 60: # 입력받은 성적 값이 60이상, 69이하일 경우 
    print('D') # D를 출력
else: 
    print('F') # 나머지의 경우에는 F가 출력되도록 함